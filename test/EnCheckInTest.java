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
    private MetodoTraslado traslado;
    private Direccion direccion;
    private int cantidadPasajeros;
    private LocalDate inicio;
    private LocalDate fin;

    // SETUP
    @BeforeEach
    public void setUp() {
        enCheckIn = new EnCheckIn();
        reserva = mock(Reserva.class);
        item = mock(EntidadCatalogo.class);
        inicio = LocalDate.of(2026, 2, 8);
        fin = LocalDate.of(2026, 2, 12);
        cantidadPasajeros = 2;
        traslado = mock(MetodoTraslado.class);
        direccion = mock(Direccion.class);
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
        Reserva reservaReal = new Reserva(600f, inicio, fin, cantidadPasajeros, traslado, direccion, "cami@mail.com");
        when(item.getPrecioFinal()).thenReturn(10000f);
        reservaReal.agregarItem(item);
        // EXERCISE
        enCheckIn.cancelar(reservaReal);
        // VERIFY
        assertEquals(10600f, reservaReal.getVoucher().getMonto());
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
