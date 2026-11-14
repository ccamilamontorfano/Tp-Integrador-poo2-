import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
