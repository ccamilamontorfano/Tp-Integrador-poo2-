package catalogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaqueteTuristicoTest {
    private PaqueteTuristico unPaquete;
    private EntidadCatalogo itemA;
    private EntidadCatalogo itemB;
    private List<EntidadCatalogo> items;
    private LocalDate inicio;
    private LocalDate fin;
    private int cantidadPasajeros;


    // SETUP
    @BeforeEach
    public void setUp(){
        itemA = mock(EntidadCatalogo.class);
        itemB = mock(EntidadCatalogo.class);
        items = new ArrayList<>();
        items.add(itemA);
        items.add(itemB);
        unPaquete = new PaqueteTuristico("Paquete verano", "Ideal para relajarse", 20f, items);
        inicio = LocalDate.of(2026, 1, 5);
        fin = LocalDate.of(2026, 1, 10);
        cantidadPasajeros = 3;
    }

    @Test
    public void constructorFuncionaCorrectamente(){
        assertEquals("Paquete verano",unPaquete.getNombre());
        assertEquals("Ideal para relajarse", unPaquete.getDescripcion());
        assertEquals(20f, unPaquete.getDescuento());
        assertEquals(items,unPaquete.getItems());
    }

    @Test
    public void precioBaseEsLaSumaDeLosPreciosDeLosItems() {
        when(itemA.getPrecioBase()).thenReturn(10000f);
        when(itemB.getPrecioBase()).thenReturn(5000f);

        assertEquals(15000f, unPaquete.getPrecioBase());
    }

    @Test
    public void precioBaseEsCeroCuandoNoHayItems() {
        PaqueteTuristico paqueteVacio =
                new PaqueteTuristico(
                        "Paquete vacío",
                        "Sin servicios",
                        20f,
                        new ArrayList<>()
                );

        assertEquals(0f, paqueteVacio.getPrecioBase());
    }

    @Test
    public void precioFinalAplicaElDescuentoCorrespondiente() {
        when(itemA.getPrecioBase()).thenReturn(10000f);
        when(itemB.getPrecioBase()).thenReturn(5000f);

        assertEquals(12000f, unPaquete.getPrecioFinal());
    }

    @Test
    public void sePuedeAgregarUnItem() {
        EntidadCatalogo nuevoItem = mock(EntidadCatalogo.class);

        unPaquete.agregarItem(nuevoItem);

        assertEquals(3, unPaquete.getItems().size());
        assertTrue(unPaquete.getItems().contains(nuevoItem));
    }

    @Test
    public void sePuedeEliminarUnItem() {
        unPaquete.eliminarItem(itemA);

        assertEquals(1, unPaquete.getItems().size());
        assertFalse(unPaquete.getItems().contains(itemA));
    }


    @Test
    public void todosLosItemsDelPaqueteReservanCupo(){
        // EXERCISE
        unPaquete.reservarCupo(inicio, fin, cantidadPasajeros);
        // VERIFY
        verify(itemA).reservarCupo(inicio, fin, cantidadPasajeros);
        verify(itemB).reservarCupo(inicio, fin, cantidadPasajeros);
    }

    @Test
    public void todosLosItemsDelPaqueteDevuelvenElCupo(){
        // EXERCISE
        unPaquete.devolverCupo(inicio, fin, cantidadPasajeros);
        // VERIFY
        verify(itemA).devolverCupo(inicio, fin, cantidadPasajeros);
        verify(itemB).devolverCupo(inicio, fin, cantidadPasajeros);
    }

    @Test
    public void elPaqueteTieneCupoPorqueTodosSusItemsTienenCupo(){
        // SETUP
        when(itemA.tieneCupo(inicio, fin, cantidadPasajeros)).thenReturn(true);
        when(itemB.tieneCupo(inicio, fin, cantidadPasajeros)).thenReturn(true);
        // EXERCISE Y VERIFY
        assertTrue(unPaquete.tieneCupo(inicio, fin, cantidadPasajeros));
    }

    @Test
    public void elPaqueteNoTieneCupoPorqueNoTodosSusItemsTienenCupo(){
        // SETUP
        when(itemA.tieneCupo(inicio, fin, cantidadPasajeros)).thenReturn(true);
        when(itemB.tieneCupo(inicio, fin, cantidadPasajeros)).thenReturn(false);
        // EXERCISE Y VERIFY
        assertFalse(unPaquete.tieneCupo(inicio, fin, cantidadPasajeros));
    }
}
