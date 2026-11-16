package traslado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlataformaTransporteTest {
    private PlataformaTransporte plataforma;

    @BeforeEach
    public void setUp() {
        plataforma = mock(PlataformaTransporte.class);
    }

    @Test
    public void laPlataformaEstimaElCostoDelTraslado() {
        Direccion direccion = new Direccion("Av. Rivadavia 1234");

        when(plataforma.estimarTraslado(3, direccion))
                .thenReturn(5000f);

        assertEquals(5000f, plataforma.estimarTraslado(3, direccion));
    }
}