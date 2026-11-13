import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ServicioIndividualTest {
    private ServicioIndividual servicio;
    private Categoria categoria;
    private LocalDate inicio;
    private LocalDate fin;

    @BeforeEach
    public void setUp(){
        categoria = new Categoria("alojamiento");
        servicio = new ServicioIndividual("hotel", "camila", categoria, 10000f, "muyComodo", 20);
        inicio = LocalDate.of(2026, 2, 8);
        fin = LocalDate.of(2026, 2, 12);
    }

    @Test
    public void unServicioNuevoTieneTodaSuCapacidadLibre(){
        // EXERCISE Y VERIFY
        assertTrue(servicio.tieneCupo(inicio, fin, 20));
    }

    @Test
    public void alReservarSeDescuentaLaCapacidadEnTodoElRango(){
        // EXERCISE
        servicio.reservarCupo(inicio, fin, 3);
        // VERIFY
        assertTrue(servicio.tieneCupo(inicio, fin, 17));
        assertFalse(servicio.tieneCupo(inicio, fin, 18));
    }

    @Test
    public void noHayCupoSiElLugarLibreNoAlcanza(){
        // SETUP
        servicio.reservarCupo(inicio, fin, 15);
        // EXERCISE Y VERIFY
        assertFalse(servicio.tieneCupo(inicio, fin, 10));
    }


    @Test
    public void noSeModificaLaCapacidadFueraDelRangoReservado(){
        // EXERCISE
        servicio.reservarCupo(inicio, fin, 3);
        // VERIFY
        assertEquals(20, servicio.capacidadSobrante(fin.plusDays(1)));
    }

    @Test
    public void noHayMasCapacidadSiSeReservoTodo(){
        // SETUP
        servicio.reservarCupo(inicio, fin, 15);
        servicio.reservarCupo(inicio, fin, 5);
        // EXERCISE Y VERIFY
        assertFalse(servicio.tieneCupo(inicio, fin, 1));
    }

    @Test
    public void noSePuedeReservarSiNoHayCupo(){
        // SETUP
        servicio.reservarCupo(inicio, fin, 15);
        servicio.reservarCupo(inicio, fin, 5);
        // EXERCISE Y VERIFY
        assertThrows(SinCupoExcepcion.class, () -> servicio.reservarCupo(inicio, fin, 2));
    }

    @Test
    public void hayMasCapacidadAlDevolverCupos() {
        // SETUP
        servicio.reservarCupo(inicio, fin, 15);
        // EXERCISE
        servicio.devolverCupo(inicio, fin, 15);
        // VERIFY
        assertTrue(servicio.tieneCupo(inicio, fin, 20));
    }
}
