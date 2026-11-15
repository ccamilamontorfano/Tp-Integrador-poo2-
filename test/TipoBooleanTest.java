
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TipoBooleanTest {

    private TipoBoolean tipo = new TipoBoolean();

    @Test
    public void unBooleanTrueEsValido() {
        assertTrue(tipo.esValido(true));
    }

    @Test
    public void unBooleanFalseEsValido() {
        assertTrue(tipo.esValido(false));
    }

    @Test
    public void unNumeroNoEsValido() {
        assertFalse(tipo.esValido(20));
    }

    @Test
    public void unTextoNoEsValido() {
        assertFalse(tipo.esValido("true"));
    }

    @Test
    public void unaFechaNoEsValida() {
        assertFalse(tipo.esValido(java.time.LocalDate.of(2026, 11, 15)));
    }

    @Test
    public void unValorNullNoEsValido() {
        assertFalse(tipo.esValido(null));
    }
}




