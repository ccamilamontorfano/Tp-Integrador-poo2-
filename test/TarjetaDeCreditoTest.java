import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TarjetaDeCreditoTest {
    private TarjetaDeCredito tarjetaCredito;
    private Date fechaVen;
    private ServicioTarjeta servicio;
    private RegistroDePago registroDePago;

    // SETUP
    @BeforeEach
    public void setUp(){
        fechaVen = new Date();
        servicio = mock(ServicioTarjeta.class);
        registroDePago = new RegistroDePago();
        tarjetaCredito = new TarjetaDeCredito("4006", "444", fechaVen, servicio, 3000f, registroDePago);

        when(servicio.validarTarjeta("4006", "444", fechaVen)).thenReturn(true);
        when(servicio.transferirInstantaneamente(3000f)).thenReturn("cc80lc");
    }

    @Test
    public void siLaTarjetaEsValidaSeContinuaConLaTransaccion(){
        // EXERCISE
        tarjetaCredito.procesarPago();
        // VERIFY
        verify(servicio).validarTarjeta("4006", "444", fechaVen);
        verify(servicio).preAutorizar(3000f);
        verify(servicio).transferirInstantaneamente(3000f);
    }

    @Test
    public void siLaTarjetaEsValidaQuedaRegistradoElCodigo(){
        // EXERCISE
        tarjetaCredito.procesarPago();
        // VERIFY
        assertTrue(registroDePago.getPagos().contains("cc80lc"));
    }

    @Test
    public void siLaTarjetaEsValidaSeGeneraElVoucherCorrespondiente(){
        // EXERCISE
        tarjetaCredito.procesarPago();
        // VERIFY
        Comprobante voucher = registroDePago.getComprobantes().get(0);
        assertInstanceOf(VoucherDePago.class, voucher);
        assertEquals(3000f, voucher.getMonto());
        assertEquals("cc80lc", voucher.getCodigo());
    }

    @Test
    public void siLaTarjetaEsInvalidaLanzaUnaExcepcion(){
        // SETUP
        when(servicio.validarTarjeta("4006", "444", fechaVen)).thenReturn(false);
        // EXERCISE Y VERIFY
        assertThrows(PagoRechazadoExcepcion.class, () -> tarjetaCredito.procesarPago());
    }

    @Test
    public void siLaTarjetaEsInvalidaNoSeContinuaConLaTransaccion(){
        // SETUP
        when(servicio.validarTarjeta("4006", "444", fechaVen)).thenReturn(false);
        // EXERCISE
        assertThrows(PagoRechazadoExcepcion.class, () -> tarjetaCredito.procesarPago());
        // VERIFY
        verify(servicio, never()).preAutorizar(3000f);
        verify(servicio, never()).transferirInstantaneamente(3000f);
    }
}
