package traslado;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PuntoDeEncuentroTest {

    private PuntoDeEncuentro punto;

    @BeforeEach
    public void setUp() {
        punto = new PuntoDeEncuentro(LocalDate.now());
    }

    @Test
    public void testCalcularCosto() {
        assertEquals(0f, punto.calcularCosto(null));
    }

    @Test
    public void testHayCupoCuandoEsHoy() {
        assertTrue(punto.hayCupo());
    }

    @Test
    public void testNoHayCupoCuandoNoEsHoy() {
        PuntoDeEncuentro otroPunto =
                new PuntoDeEncuentro(LocalDate.now().plusDays(1));

        assertFalse(otroPunto.hayCupo());
    }

    @Test
    public void testNoHayCupoCuandoSeAgotanLosGuias() {
        for (int i = 0; i < 10; i++) {
            punto.descontarGuia();
        }

        assertFalse(punto.hayCupo());
    }

    @Test
    public void testDescontarGuia() {
        punto.descontarGuia();

        assertTrue(punto.hayCupo());
    }

    @Test
    public void testCambioDeDia() {
        punto.cambioDeDia();

        assertFalse(punto.hayCupo());
    }

    @Test
    public void testHayDisponibilidad() {
        assertTrue(punto.hayDisponibilidad());
    }

    @Test
    public void testNoHayDisponibilidad() {
        for (int i = 0; i < 10; i++) {
            punto.descontarGuia();
        }

        assertFalse(punto.hayDisponibilidad());
    }
}




