package notificacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reserva.Cancelada;
import reserva.Reserva;
import reserva.Finalizada;
import static org.mockito.Mockito.*;

class GeneradorDeFacturaTest {

    private GeneradorDeFactura generador;
    private Reserva reserva;

    // SETUP
    @BeforeEach
    public void setUp(){
        generador = new GeneradorDeFactura();
        reserva = mock(Reserva.class);
    }

    @Test
    public void siLaReservaQuedaFinalizadaSeGeneranLosComprobantes(){
        // SETUP
        when(reserva.getEstado()).thenReturn(new Finalizada());
        // EXERCISE
        generador.cambioEstado(reserva);
        // VERIFY
        verify(reserva).generarComprobantes();
    }

    @Test
    public void siLaReservaQuedaEnOtroEstadoNoSeGeneranLosComprobantes(){
        // SETUP
        when(reserva.getEstado()).thenReturn(new Cancelada());
        // EXERCISE
        generador.cambioEstado(reserva);
        // VERIFY
        verify(reserva, never()).generarComprobantes();
    }

}