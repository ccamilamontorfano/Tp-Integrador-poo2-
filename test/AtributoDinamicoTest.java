import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AtributoDinamicoTest {

    private AtributoDinamico atributo;
    private TipoValor tipo;
    private AtributoDinamico opcional;

    @BeforeEach
    public void setUp() {
        tipo = mock(TipoValor.class);
        atributo = new AtributoDinamico("Capacidad", true, tipo);
        opcional = new AtributoDinamico("Capacidad",false,tipo);
    }

    @Test
    public void constructoFuncionaCorrectamente() {
        assertEquals("Capacidad", atributo.getNombre());
        assertTrue(atributo.getObligatorio());
        assertEquals(tipo, atributo.getTipo());
    }

    @Test
    public void sePuedeAsignarUnValor() {
        atributo.setValor(20);

        when(tipo.esValido(20)).thenReturn(true);
        assertTrue(atributo.validar());
    }

    @Test
    public void unAtributoObligatorioSinValorNoEsValido() {
        assertFalse(atributo.validar());
        verify(tipo, never()).esValido(any());
    }

    @Test
    public void unAtributoOpcionalSinValorEsValido() {
        assertTrue(opcional.validar());
        verify(tipo, never()).esValido(any());
    }

    @Test
    public void unAtributoConValorValidoEsValido() {
        atributo.setValor(20);

        when(tipo.esValido(20)).thenReturn(true);
        assertTrue(atributo.validar());
        verify(tipo).esValido(20);
    }

    @Test
    public void unAtributoConValorInvalidoNoEsValido() {
        atributo.setValor("texto");

        when(tipo.esValido("texto")).thenReturn(false);
        assertFalse(atributo.validar());
        verify(tipo).esValido("texto");
    }

    @Test
    public void unAtributoOpcionalConValorInvalidoNoEsValido() {
            opcional.setValor("texto");

            when(tipo.esValido("texto")).thenReturn(false);
            assertFalse(opcional.validar());
    }
}

