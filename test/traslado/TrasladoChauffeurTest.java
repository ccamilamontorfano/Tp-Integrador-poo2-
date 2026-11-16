package traslado;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reserva.Reserva;

public class TrasladoChauffeurTest {

    private TrasladoChauffeur traslado;
    private TrasladoVIP trasladoVIP;
    private Reserva reserva;

    @BeforeEach
    public void setUp() {
        trasladoVIP = mock(TrasladoVIP.class);
        reserva = mock(Reserva.class);

        traslado = new TrasladoChauffeur(trasladoVIP);
    }

    @Test
    public void testCalcularCosto() {
        when(reserva.valorTotal()).thenReturn(5000f);
        when(trasladoVIP.calcularCosto(5000f)).thenReturn(2000f);

        float resultado = traslado.calcularCosto(reserva);

        assertEquals(2000f, resultado);

        verify(trasladoVIP).calcularCosto(5000f);
    }
}


