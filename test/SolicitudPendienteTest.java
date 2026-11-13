import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SolicitudPendienteTest {
    private SolicitudPendiente solicitudPendiente;
    private Reserva reserva;
    private EntidadCatalogo item;
    private List<EntidadCatalogo> items;
    private LocalDate inicio;
    private LocalDate fin;
    private int cantidadPasajeros;
    private EntidadCatalogo itemA;
    private EntidadCatalogo itemB;

    // SETUP
    @BeforeEach
    public void setUp() {
        solicitudPendiente = new SolicitudPendiente();
        reserva = mock(Reserva.class);
        item = mock(EntidadCatalogo.class);
        itemA = mock(EntidadCatalogo.class);
        itemB = mock(EntidadCatalogo.class);
        items = new ArrayList<>();
        inicio = LocalDate.of(2026, 1, 5);
        fin = LocalDate.of(2026, 1, 10);
        cantidadPasajeros = 3;
        when(reserva.getItems()).thenReturn(items);
        when(reserva.getFechaInicio()).thenReturn(inicio);
        when(reserva.getFechaFin()).thenReturn(fin);
        when(reserva.getCantPasajeros()).thenReturn(cantidadPasajeros);
    }

    @Test
    public void laReservaTieneLosItemsAgregadosSiSeAgregan() {
        // EXERCISE
        solicitudPendiente.agregarItem(item, reserva);
        // VERIFY
        assertTrue(items.contains(item));
    }

    @Test
    public void alQuitarUnItemLaReservaYaNoLoTiene() {
        // SETUP
        items.add(item);
        // EXERCISE
        solicitudPendiente.quitarItem(item, reserva);
        // VERIFY
        assertFalse(items.contains(item));
    }

    @Test
    public void alConfirmarLaReservaSiHayCuposSeReserva(){
        // SETUP
        items.add(item);
        when(item.tieneCupo(inicio, fin, cantidadPasajeros)).thenReturn(true);
        // EXERCISE
        solicitudPendiente.confirmar(reserva);
        // VERIFY
        verify(item).reservarCupo(inicio, fin, cantidadPasajeros);
    }

    @Test
    public void alConfirmarLaReservaSiHayCuposPasaAConfirmada(){
        // SETUP
        items.add(item);
        when(item.tieneCupo(inicio, fin, cantidadPasajeros)).thenReturn(true);
        // EXERCISE
        solicitudPendiente.confirmar(reserva);
        // VERIFY
        verify(reserva).setEstado(any(Confirmada.class));
    }

    @Test
    public void alConfirmarLaReservaSiNoHayCuposNoReserva(){
        // SETUP
        items.add(itemA);
        items.add(itemB);
        when(itemA.tieneCupo(inicio, fin, cantidadPasajeros)).thenReturn(true);
        when(itemB.tieneCupo(inicio, fin, cantidadPasajeros)).thenReturn(false);
        // EXERCISE y VERIFY
        assertThrows(SinCupoExcepcion.class, () -> solicitudPendiente.confirmar(reserva));
        verify(itemA, never()).reservarCupo(inicio, fin, cantidadPasajeros);
    }


    @Test
    public void alCancelarLaReservaSePasaAEstadoCancelada() {
        // EXERCISE
        solicitudPendiente.cancelar(reserva);
        // VERIFY
        verify(reserva).setEstado(any(Cancelada.class));
    }


    // EXERCISE Y VERIFY
    @Test
    public void noSeLePuedeHacerCheckInAUnaReservaQueEstaEnSolicitudPendiente(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> solicitudPendiente.hacerCheckIn(reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeIniciarUnaReservaQueEstaEnSolicitudPendiente(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> solicitudPendiente.iniciar(reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeFinalizarUnaReservaQueEstaEnSolicitudPendiente(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> solicitudPendiente.finalizar(reserva));
    }
}
