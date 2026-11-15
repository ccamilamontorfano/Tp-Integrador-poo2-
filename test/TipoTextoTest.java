
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TipoTextoTest {

    private TipoTexto tipo = new TipoTexto();

    @Test
    public void unStringEsValido() {
        assertTrue(tipo.esValido("Hotel"));
    }

    @Test
    public void unNumeroNoEsValido() {
        assertFalse(tipo.esValido(20));
    }

    @Test
    public void unBooleanNoEsValido() {
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

