package pago;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class TransferenciaDirectaTest {
    private TransferenciaDirecta transferencia;
    private Date fecha;
    private ServicioBancario servicio;
    private RegistroDePago registroDePago;
    private TransferenciaDirecta transferenciaP;

    // SETUP
    @BeforeEach
    public void setUp(){
        fecha = new Date();
        servicio = mock(ServicioBancario.class);
        registroDePago = new RegistroDePago();
        transferencia = new TransferenciaDirecta("0000003100072014315713", "camiMon", fecha, servicio, 6000f, registroDePago);
        transferenciaP = new TransferenciaDirecta("0000003100072014315713", "camiMon", null, servicio, 6000f, registroDePago);

        when(servicio.validarCuenta("0000003100072014315713", "camiMon")).thenReturn(true);
        when(servicio.transferirConProgramacion(6000f, fecha)).thenReturn("ll305");
    }

    @Test
    public void siLaCuentaEsValidaYNoTieneFechaSeHaceUnaTransferenciaInmediata(){
        // EXERCISE
        transferenciaP.procesarPago();
        // VERIFY
        verify(servicio).validarCuenta("0000003100072014315713", "camiMon");
        verify(servicio).transferirInstantaneamente(6000f);
    }

    @Test
    public void siLaCuentaEsValidaYTieneFechaSeHaceUnaTransferenciaProgramada(){
        // EXERCISE
        transferencia.procesarPago();
        // VERIFY
        verify(servicio).validarCuenta("0000003100072014315713", "camiMon");
        verify(servicio).transferirConProgramacion(6000f, fecha);
    }


    @Test
    public void siLaCuentaEsValidaSeGeneraElComprobanteCorrespondiente(){
        // EXERCISE
        transferencia.procesarPago();
        // VERIFY
        ComprobanteTransferencia comprobante = (ComprobanteTransferencia) registroDePago.getComprobantes().get(0);
        assertInstanceOf(ComprobanteTransferencia.class, comprobante);
        assertEquals(6000f, comprobante.getMonto());
        assertEquals("ll305", comprobante.getCodigo());
        assertEquals(fecha, comprobante.getFecha());
        assertEquals("0000003100072014315713", comprobante.getCvu());
    }

    @Test
    public void siLaCuentaEsInvalidaLanzaUnaExcepcion(){
        // SETUP
        when(servicio.validarCuenta("0000003100072014315713", "camiMon")).thenReturn(false);
        // EXERCISE Y VERIFY
        assertThrows(PagoRechazadoExcepcion.class, () -> transferencia.procesarPago());
    }

    @Test
    public void siLaCuentaEsInvalidaNoSeContinuaConLaTransaccion(){
        // SETUP
        when(servicio.validarCuenta("0000003100072014315713", "camiMon")).thenReturn(false);
        // EXERCISE
        assertThrows(PagoRechazadoExcepcion.class, () -> transferencia.procesarPago());
        // VERIFY
        verify(servicio, never()).transferirConProgramacion(6000f, fecha);
        verify(servicio, never()).transferirInstantaneamente(6000f);
    }

    @Test
    public void siLaCuentaEsValidaQuedaRegistradoElCodigo(){
        // EXERCISE
        transferencia.procesarPago();
        // VERIFY
        assertTrue(registroDePago.getPagos().contains("ll305"));
    }

    @Test
    public void laTransferenciaGuardaLaFecha(){
        // EXERCISE Y VERIFY
        assertEquals(fecha, transferencia.getFecha());
    }
}
