import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ConfirmadaTest {
    private Confirmada confirmada;
    private Reserva reserva;
    private EntidadCatalogo item;

    // SETUP
    @BeforeEach
    public void setUp() {
        confirmada = new Confirmada();
        reserva = mock(Reserva.class);
        item = mock(EntidadCatalogo.class);
    }

    @Test
    public void alHacerCheckInLaReservaPasaAEstarEnCheckIn() {
        // EXERCISE
        confirmada.hacerCheckIn(reserva);
        // VERIFY
        verify(reserva).setEstado(any(EnCheckIn.class));
    }

    @Test
    public void alCancelarLaReservaSeLeDevuelvenLosCuposALosItems() {
        // EXERCISE
        confirmada.cancelar(reserva);
        // VERIFY
        verify(reserva).devolverCuposAItems();
    }


    @Test
    public void alCancelarLaReservaSePasaAEstadoCancelada() {
        // EXERCISE
        confirmada.cancelar(reserva);
        // VERIFY
        verify(reserva).setEstado(any(Cancelada.class));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeAgregarItemConLaReservaConfirmada() {
        assertThrows(OperacionInvalidaExcepcion.class, () -> confirmada.agregarItem(item, reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeQuitarItemConLaReservaConfirmada() {
        assertThrows(OperacionInvalidaExcepcion.class, () -> confirmada.quitarItem(item, reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeConfirmarUnaReservaQueYaEstaConfirmada() {
        assertThrows(OperacionInvalidaExcepcion.class, () -> confirmada.confirmar(reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeIniciarUnaReservaYaConfirmada(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> confirmada.iniciar(reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeFinalizarUnaReservaYaConfirmada(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> confirmada.finalizar(reserva));
    }
}

