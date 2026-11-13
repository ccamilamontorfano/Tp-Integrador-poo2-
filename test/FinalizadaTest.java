import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class FinalizadaTest {
    private Finalizada finalizada;
    private Reserva reserva;
    private EntidadCatalogo item;

    // SETUP
    @BeforeEach
    public void setUp(){
        finalizada = new Finalizada();
        reserva = mock(Reserva.class);
        item = mock(EntidadCatalogo.class);
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeAgregarItemEnReservaFinalizada(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> finalizada.agregarItem(item, reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeQuitarItemEnReservaFinalizada(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> finalizada.quitarItem(item, reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeConfirmarUnaReservaFinalizada(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> finalizada.confirmar(reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSeLePuedeHacerCheckInAUnaReservaFinalizada(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> finalizada.hacerCheckIn(reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeIniciarUnaReservaFinalizada(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> finalizada.iniciar(reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeFinalizarUnaReservaYaFinalizada(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> finalizada.finalizar(reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeCancelarUnaReservaYaFinalizada(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> finalizada.cancelar(reserva));
    }
}
