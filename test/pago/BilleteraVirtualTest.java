package pago;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class BilleteraVirtualTest {
    private BilleteraVirtual billetera;
    private ServicioBilleteraVirtual servicio;
    private RegistroDePago registroDePago;

    // SETUP
    @BeforeEach
    public void setUp(){
        servicio = mock(ServicioBilleteraVirtual.class);
        registroDePago = new RegistroDePago();
        billetera = new BilleteraVirtual(10000f, registroDePago, servicio);

        when(servicio.validarSaldo(10000f)).thenReturn(true);
        when(servicio.acreditar(10000f)).thenReturn("clm7890");
    }

    @Test
    public void siElSaldoEsValidoSeRealizaLaTransaccion(){
        // EXERCISE
        billetera.procesarPago();
        // VERIFY
        verify(servicio).validarSaldo(10000f);
        verify(servicio).acreditar(10000f);
        verify(servicio).bloquearSaldo(10000f);
    }


    @Test
    public void siElSaldoNoEsValidoSeLanzaExcepcion(){
        // SETUP
        when(servicio.validarSaldo(10000f)).thenReturn(false);
        // EXERCISE Y VERIFY
        assertThrows(PagoRechazadoExcepcion.class, () -> billetera.procesarPago());
    }

    @Test
    public void siElSaldoEsInvalidoNoSeContinuaConLaTransaccion(){
        // SETUP
        when(servicio.validarSaldo(10000f)).thenReturn(false);
        // EXERCISE
        assertThrows(PagoRechazadoExcepcion.class, () -> billetera.procesarPago());
        // VERIFY
        verify(servicio, never()).acreditar(10000f);
        verify(servicio, never()).bloquearSaldo(10000f);
    }

    @Test
    public void siElSaldoEsValidoQuedaRegistradoElCodigo(){
        // EXERCISE
        billetera.procesarPago();
        // VERIFY
        assertTrue(registroDePago.getPagos().contains("clm7890"));
    }

    @Test
    public void siElSaldoEsValidoSeRecibeUnaNotificacion(){
        // EXERCISE
        billetera.procesarPago();
        // VERIFY
        verify(servicio).notificarTransaccion("Pagaste $ 10000.0");
    }
}

