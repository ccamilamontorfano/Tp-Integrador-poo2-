import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class CanceladaTest {
    private Cancelada cancelada;
    private Reserva reserva;
    private EntidadCatalogo item;

    // SETUP
    @BeforeEach
    public void setUp() {
        cancelada = new Cancelada();
        reserva = mock(Reserva.class);
        item = mock(EntidadCatalogo.class);
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeAgregarItemEnReservaCancelada() {
        assertThrows(OperacionInvalidaExcepcion.class, () -> cancelada.agregarItem(item, reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeQuitarItemEnReservaCancelada() {
        assertThrows(OperacionInvalidaExcepcion.class, () -> cancelada.quitarItem(item, reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeConfirmarUnaReservaCancelada() {
        assertThrows(OperacionInvalidaExcepcion.class, () -> cancelada.confirmar(reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSeLePuedeHacerCheckInAUnaReservaCancelada() {
        assertThrows(OperacionInvalidaExcepcion.class, () -> cancelada.hacerCheckIn(reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeIniciarUnaReservaCancelada() {
        assertThrows(OperacionInvalidaExcepcion.class, () -> cancelada.iniciar(reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeFinalizarUnaReservaCancelada(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> cancelada.finalizar(reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeCancelarUnaReservaYaCancelada(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> cancelada.cancelar(reserva));
    }
}
