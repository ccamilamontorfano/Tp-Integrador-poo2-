package traslado;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reserva.Reserva;

public class TrasladoEstandarTest {

    private TrasladoEstandar traslado;
    private PlataformaTransporte plataforma;
    private Reserva reserva;
    private Direccion direccion;

    @BeforeEach
    public void setUp() {
        plataforma = mock(PlataformaTransporte.class);
        reserva = mock(Reserva.class);
        direccion = new Direccion("Calle 123");

        traslado = new TrasladoEstandar(plataforma);
    }

    @Test
    public void testCalcularCosto() {
        when(reserva.getCantPasajeros()).thenReturn(4);
        when(reserva.getDireccion()).thenReturn(direccion);

        when(plataforma.estimarTraslado(4, direccion))
                .thenReturn(1500f);

        float resultado = traslado.calcularCosto(reserva);

        assertEquals(1500f, resultado);

        verify(plataforma).estimarTraslado(4, direccion);
    }
}

