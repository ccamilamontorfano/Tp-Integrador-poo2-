package pago;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComprobanteTransferenciaTest {
    private ComprobanteTransferencia comprobante;
    private Date fecha;

    // SETUP
    @BeforeEach
    public void setUp(){
        fecha = new Date();
        comprobante = new ComprobanteTransferencia(5000f, "cc208", fecha, "0000003100072014315713");
    }

    @Test
    public void elComprobanteSeCreaConLosDatosQueLeAsignaron(){
        // EXERCISE
        float monto = comprobante.getMonto();
        String codigo = comprobante.getCodigo();
        Date fechaComprobar = comprobante.getFecha();
        String cvu = comprobante.getCvu();
        // VERIFY
        assertEquals(5000f, monto);
        assertEquals("cc208", codigo);
        assertEquals(fecha, fechaComprobar);
        assertEquals("0000003100072014315713", cvu);
    }
}
