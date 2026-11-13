import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoucherDeCreditoTest {
    private VoucherDeCredito voucher;

    // SETUP
    @BeforeEach
    public void setUp(){
        voucher = new VoucherDeCredito(20000f);
    }

    @Test
    public void elVoucherSeCreaConElMontoQueLeAsignaron(){
        // EXERCISE
        float monto = voucher.getMonto();
        // VERIFY
        assertEquals(20000f, monto);
    }
}
