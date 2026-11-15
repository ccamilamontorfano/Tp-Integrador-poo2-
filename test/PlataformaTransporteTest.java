import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlataformaTransporteTest {
    private PlataformaTrasporte plataforma;

    @BeforeEach
    public void setUp() {
        plataforma = mock(PlataformaTrasporte.class);
    }

    @Test
    public void laPlataformaEstimaElCostoDelTraslado() {
        Direccion direccion = new Direccion("Av. Rivadavia 1234");

        when(plataforma.estimarTraslado(3, direccion))
                .thenReturn(5000f);

        assertEquals(5000f, plataforma.estimarTraslado(3, direccion));
    }
}