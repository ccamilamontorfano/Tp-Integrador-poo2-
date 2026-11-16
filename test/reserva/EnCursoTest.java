package reserva;

import catalogo.EntidadCatalogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import traslado.Direccion;
import traslado.MetodoTraslado;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EnCursoTest {
    private EnCurso enCurso;
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
        enCurso = new EnCurso();
        reserva = mock(Reserva.class);
        item = mock(EntidadCatalogo.class);
        inicio = LocalDate.of(2026, 2, 8);
        fin = LocalDate.of(2026, 2, 12);
        cantidadPasajeros = 2;
        traslado = mock(MetodoTraslado.class);
        direccion = mock(Direccion.class);
    }

    @Test
    public void alFinalizarLaReservaPasaAEstadoFinalizada() {
        // EXERCISE
        enCurso.finalizar(reserva);
        // VERIFY
        verify(reserva).setEstado(any(Finalizada.class));
    }

    @Test
    public void alCancelarLaReservaSeGeneraElVoucher() {
        // SETUP
        Reserva reservaReal = new Reserva(600f, inicio, fin, cantidadPasajeros, traslado, direccion, "cami@mail.com");
        when(item.getPrecioFinal()).thenReturn(10000f);
        reservaReal.agregarItem(item);
        // EXERCISE
        enCurso.cancelar(reservaReal);
        // VERIFY
        assertEquals(10000f, reservaReal.getVoucher().getMonto());
    }


    @Test
    public void alCancelarLaReservaSePasaAEstadoCancelada() {
        // EXERCISE
        enCurso.cancelar(reserva);
        // VERIFY
        verify(reserva).setEstado(any(Cancelada.class));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeAgregarItemConLaReservaEnCurso() {
        assertThrows(OperacionInvalidaExcepcion.class, () -> enCurso.agregarItem(item, reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeQuitarItemConLaReservaEnCurso() {
        assertThrows(OperacionInvalidaExcepcion.class, () -> enCurso.quitarItem(item, reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeConfirmarUnaReservaQueEstaEnCurso() {
        assertThrows(OperacionInvalidaExcepcion.class, () -> enCurso.confirmar(reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSeLePuedeHacerCheckInAUnaReservaEnCurso(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> enCurso.hacerCheckIn(reserva));
    }

    // EXERCISE Y VERIFY
    @Test
    public void noSePuedeIniciarUnaReservaEnCurso(){
        assertThrows(OperacionInvalidaExcepcion.class, () -> enCurso.iniciar(reserva));
    }
}
