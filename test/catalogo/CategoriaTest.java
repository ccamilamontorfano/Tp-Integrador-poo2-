package catalogo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriaTest {

    @Test
    public void constructoFuncionaCorrectamente() {
        Categoria categoria = new Categoria("Alojamiento");

        assertEquals("Alojamiento", categoria.getNombre());
    }

    @Test
    public void unaCategoriaPuedeTenerNombreNull() {
        Categoria categoria = new Categoria(null);

        assertNull(categoria.getNombre());
    }

    @Test
    public void unaCategoriaPuedeTenerNombreVacio() {
        Categoria categoria = new Categoria("");

        assertEquals("", categoria.getNombre());
    }
}

