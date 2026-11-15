import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VoucherDePagoTest {

    private VoucherDePago voucher;
    private Date fecha;

    // SETUP
    @BeforeEach
    public void setUp(){
        fecha = new Date();
        voucher = new VoucherDePago(15000f, "10g82", fecha, true);
    }

    @Test
    public void elVoucherSeCreaConLosDatosQueLeAsignaron(){
        // EXERCISE
        float monto = voucher.getMonto();
        String codigo = voucher.getCodigo();
        Date fechaComprobar = voucher.getFecha();
        Boolean esDigital = voucher.getEsDigital();
        // VERIFY
        assertEquals(15000f, monto);
        assertEquals("10g82", codigo);
        assertEquals(fecha, fechaComprobar);
        assertTrue(esDigital);
    }
}
