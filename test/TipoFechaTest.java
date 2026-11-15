
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TipoFechaTest {

    private TipoFecha tipo = new TipoFecha();

    @Test
    public void unaFechaEsValida() {
        assertTrue(tipo.esValido(LocalDate.of(2026, 11, 15)));
    }

    @Test
    public void unNumeroNoEsValido() {
        assertFalse(tipo.esValido(20));
    }

    @Test
    public void unTextoNoEsValido() {
        assertFalse(tipo.esValido("15/11/2026"));
    }

    @Test
    public void unBooleanoNoEsValido() {
        assertFalse(tipo.esValido(true));
    }

    @Test
    public void unValorNullNoEsValido() {
        assertFalse(tipo.esValido(null));
    }
}

