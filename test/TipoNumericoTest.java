
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TipoNumericoTest {

    private TipoNumerico tipo = new TipoNumerico();

    @Test
    public void unNumeroEnteroEsValido() {
        assertTrue(tipo.esValido(20));
    }

    @Test
    public void unNumeroDecimalEsValido() {
        assertTrue(tipo.esValido(20.5));
    }

    @Test
    public void unNumeroFloatEsValido() {
        assertTrue(tipo.esValido(20f));
    }

    @Test
    public void unTextoNoEsValido() {
        assertFalse(tipo.esValido("hola"));
    }

    @Test
    public void unBooleanoNoEsValido() {
        assertFalse(tipo.esValido(true));
    }

    @Test
    public void unValorNullNoEsValido() {
        assertFalse(tipo.esValido(null));
    }

    @Test
    public void unaFechaNoEsValido(){
        assertFalse(tipo.esValido(LocalDate.of(26,11,15)));
    }
}

