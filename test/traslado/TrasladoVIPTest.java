package traslado;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrasladoVIPTest {

    private TrasladoVIP traslado;

    @BeforeEach
    public void setUp() {
        traslado = new TrasladoVIP();
    }

    @Test
    public void testCalcularCosto() {
        assertEquals(1500f, traslado.calcularCosto(1500f));
    }
}

