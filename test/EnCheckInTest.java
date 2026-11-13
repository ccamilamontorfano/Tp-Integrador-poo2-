import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EnCheckInTest {
    private EnCheckIn enCheckIn;
    private Reserva reserva;
    private EntidadCatalogo item;

    // SETUP
    @BeforeEach
    public void setUp() {
        enCheckIn = new EnCheckIn();
        reserva = mock(Reserva.class);
        item = mock(EntidadCatalogo.class);
    }

    @Test
    public void alIniciarLaReservaPasaAEstarEnCurso() {
        // EXERCISE
        enCheckIn.iniciar(reserva);
        // VERIFY
        verify(reserva).setEstado(any(EnCurso.class));
    }

    @Test
    public void alCancelarLaReservaSeLeDevuelvenLosCuposALosItems() {
        // EXERCISE
        enCheckIn.cancelar(reserva);
        // VERIFY
        verify(reserva).devolverCuposAItems();
    }

    @Test
    public void alCancelarLaReservaSeGeneraElVoucher() {
        // SETUP
        Reserva reservaReal = new Reserva(500f, LocalDate.of(2026, 1, 5), LocalDate.of(2026, 1, 10), 3);
        when(item.getPrecioFinal()).thenReturn(10000f);
        reservaReal.agregarItem(item);
        // EXERCISE
        enCheckIn.cancelar(reservaReal);
        // VERIFY
        assertEquals(10500f, reservaReal.getVoucher().getMonto());
    }

    @Test
    public void alCancelarLaReservaSePasaAEstadoCancelada() {
        // EXERCISE
        enCheckIn.cancelar(reserva);
        // VERIFY
        verify(reserva).setEstado(any(Cancelada.class));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeAgregarItemConLaReservaEnCheckIn() {
        assertThrows(OperacionInvalidaExcepcion.class, () -> enCheckIn.agregarItem(item, reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeQuitarItemConLaReservaEnCheckIn() {
        assertThrows(OperacionInvalidaExcepcion.class, () -> enCheckIn.quitarItem(item, reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeConfirmarUnaReservaQueEstaEnCheckIn() {
        assertThrows(OperacionInvalidaExcepcion.class, () -> enCheckIn.confirmar(reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeIniciarUnaReservaYaEnCheckIn(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> enCheckIn.hacerCheckIn(reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeFinalizarUnaReservaEnCheckIn(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> enCheckIn.finalizar(reserva));
    }
}
