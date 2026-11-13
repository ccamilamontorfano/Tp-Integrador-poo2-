import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ReservaTest {
    private Reserva reserva;
    private int cantidadPasajeros;
    private LocalDate inicio;
    private LocalDate fin;
    private EstadoReserva estado;
    private EntidadCatalogo item;
    private List<EntidadCatalogo> items;
    private EntidadCatalogo itemA;
    private EntidadCatalogo itemB;

    // SETUP
    @BeforeEach
    public void setUp(){
        inicio = LocalDate.of(2026, 2, 8);
        fin = LocalDate.of(2026, 2, 12);
        cantidadPasajeros = 2;
        reserva = new Reserva(600f, inicio, fin, cantidadPasajeros);
        estado = mock(EstadoReserva.class);
        reserva.setEstado(estado);
        item = mock(EntidadCatalogo.class);
        itemA = mock(EntidadCatalogo.class);
        itemB = mock(EntidadCatalogo.class);
        items = new ArrayList<>();
    }



    @Test
    public void unaReservaNuevaEstaEnEstadoSolicitudPendiente(){
        // SETUP
        Reserva reservaReal = new Reserva(600f, inicio, fin, cantidadPasajeros);
        // EXERCISE
        EstadoReserva estadoReal = reservaReal.getEstado();
        // VERIFY
        assertInstanceOf(SolicitudPendiente.class, estadoReal);
    }

    @Test
    public void laReservaGuardaLaFechaDeInicio(){
        // EXERCISE
        LocalDate fecha = reserva.getFechaInicio();
        // VERIFY
        assertEquals(inicio, fecha);
    }

    @Test
    public void laReservaGuardaLaFechaDeFin(){
        // EXERCISE
        LocalDate fecha = reserva.getFechaFin();
        // VERIFY
        assertEquals(fin, fecha);
    }

    @Test
    public void laReservaGuardaLaCantidadDePasajeros(){
        // EXERCISE
        int cantidadObtenida = reserva.getCantPasajeros();
        // VERIFY
        assertEquals(2, cantidadPasajeros);
    }

    @Test
    public void laReservaCalculaElCostoDeLosServicios(){
        // SETUP
        Reserva reservaReal = new Reserva(600f, inicio, fin, cantidadPasajeros);
        when(itemA.getPrecioFinal()).thenReturn(10000f);
        when(itemB.getPrecioFinal()).thenReturn(20000f);
        reservaReal.agregarItem(itemA);
        reservaReal.agregarItem(itemB);
        // EXERCISE Y VERIFY
        assertEquals(30000f, reservaReal.getCostoServicios());
    }

    @Test
    public void laReservaDevuelveLosCuposALosItems(){
        // SETUP
        Reserva reservaReal = new Reserva(600f, inicio, fin, cantidadPasajeros);
        reservaReal.agregarItem(itemA);
        reservaReal.agregarItem(itemB);
        // EXERCISE
        reservaReal.devolverCuposAItems();
        // VERIFY
        verify(itemA).devolverCupo(inicio, fin, cantidadPasajeros);
        verify(itemB).devolverCupo(inicio, fin, cantidadPasajeros);
    }


    @Test
    public void laReservaGuardaElCostoGestion(){
        // EXERCISE
        float costoTasas = reserva.getCostoGestion();
        // VERIFY
        assertEquals(600f, costoTasas);
    }


    @Test
    public void laReservaGuardaElVoucher(){
        // SETUP
        VoucherDeCredito voucher = new VoucherDeCredito(400f);
        reserva.setVoucher(voucher);
        // EXERCISE
        VoucherDeCredito unVoucher = reserva.getVoucher();
        // VERIFY
        assertEquals(voucher, unVoucher);
    }

    @Test
    public void alConfirmarLaReservaLeDelegaAlEstado(){
        // EXERCISE
        reserva.confirmar();
        // VERIFY
        verify(estado).confirmar(reserva);
    }

    @Test
    public void alIniciarLaReservaLeDelegaAlEstado(){
        // EXERCISE
        reserva.iniciar();
        // VERIFY
        verify(estado).iniciar(reserva);
    }

    @Test
    public void alHacerCheckInLaReservaLeDelegaAlEstado(){
        // EXERCISE
        reserva.hacerCheckIn();
        // VERIFY
        verify(estado).hacerCheckIn(reserva);
    }

    @Test
    public void alFinalizarLaReservaLeDelegaAlEstado(){
        // EXERCISE
        reserva.finalizar();
        // VERIFY
        verify(estado).finalizar(reserva);
    }

    @Test
    public void alCancelarLaReservaLeDelegaAlEstado(){
        // EXERCISE
        reserva.cancelar();
        // VERIFY
        verify(estado).cancelar(reserva);
    }

    @Test
    public void alQuitarUnItemLaReservaLeDelegaAlEstado(){
        // EXERCISE
        reserva.quitarItem(item);
        // VERIFY
        verify(estado).quitarItem(item, reserva);
    }

    @Test
    public void alAgregarUnItemLaReservaLeDelegaAlEstado(){
        // EXERCISE
        reserva.agregarItem(item);
        // VERIFY
        verify(estado).agregarItem(item, reserva);
    }
}
