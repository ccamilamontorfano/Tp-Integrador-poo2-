package catalogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reserva.SinCupoExcepcion;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioIndividualTest {
    private ServicioIndividual servicio;
    private Categoria categoria;
    private LocalDate inicio;
    private LocalDate fin;
    private AtributoDinamico atributo;

    // SETUP
    @BeforeEach
    public void setUp(){
        categoria = new Categoria("alojamiento");
        servicio = new ServicioIndividual("hotel", "camila", categoria, 10000f, "muyComodo", 20);
        inicio = LocalDate.of(2026, 2, 8);
        fin = LocalDate.of(2026, 2, 12);
        atributo = mock(AtributoDinamico.class);
    }


    @Test
    public void constructorFuncionaCorectamente() {
        assertEquals("hotel", servicio.getNombre());
        assertEquals("camila", servicio.getProveedor());
        assertEquals(categoria, servicio.getCategoria());
        assertEquals(10000f, servicio.getPrecioBase());
        assertEquals("muyComodo", servicio.getDescripcion());
        assertTrue(servicio.getCodigoReserva() > 0);
        assertEquals(20, servicio.getCapacidad());
    }

    @Test
    public void precioFinalEsIgualAPrecioBase() {
        assertEquals(10000f, servicio.getPrecioFinal());
    }

    @Test
    public void unServicioNuevoNoTieneAtributosDinamicos() {
        assertTrue(servicio.getAtributos().isEmpty());
    }

    @Test
    public void sePuedeAgregarUnAtributoDinamico() {
        servicio.agregarAtributo(atributo);

        assertEquals(1, servicio.getAtributos().size());
        assertTrue(servicio.getAtributos().contains(atributo));
    }

    @Test
    public void sePuedeEliminarUnAtributoDinamico() {
        servicio.agregarAtributo(atributo);
        servicio.eliminarAtributo(atributo);

        assertTrue(servicio.getAtributos().isEmpty());
    }

    @Test
    public void unServicioSinAtributosEsValido() {
        assertTrue(servicio.esServicioValido());
    }

    @Test
    public void unServicioConAtributoValidoEsValido() {
        when(atributo.validar()).thenReturn(true);

        servicio.agregarAtributo(atributo);

        assertTrue(servicio.esServicioValido());

        verify(atributo).validar();
    }

    @Test
    public void unServicioConAtributoInvalidoNoEsValido() {
        when(atributo.validar()).thenReturn(false);

        servicio.agregarAtributo(atributo);

        assertFalse(servicio.esServicioValido());

        verify(atributo).validar();
    }

    @Test
    public void unServicioSinNombreNoEsValido() {
        ServicioIndividual servicioSinNombre =
                new ServicioIndividual(
                        null, "", categoria, 10000f,
                        "muyComodo", 20
                );

        assertFalse(servicioSinNombre.esServicioValido());
    }

    @Test
    public void unServicioConNombreVacioNoEsValido() {
        ServicioIndividual servicioSinNombre =
                new ServicioIndividual(
                        "", "camila", categoria, 10000f,
                        "muyComodo", 20
                );

        assertFalse(servicioSinNombre.esServicioValido());
    }

    @Test
    public void unServicioConNombreEnBlancoNoEsValido() {
        ServicioIndividual servicioSinNombre =
                new ServicioIndividual(
                        "   ", "camila", categoria, 10000f,
                        "muyComodo", 20
                );

        assertFalse(servicioSinNombre.esServicioValido());
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
