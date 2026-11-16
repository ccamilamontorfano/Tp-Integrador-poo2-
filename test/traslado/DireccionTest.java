package traslado;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DireccionTest {

    @Test
    public void sePuedeCrearUnaDireccion() {
        Direccion direccion = new Direccion("Av. Rivadavia 1234");

        assertEquals("Av. Rivadavia 1234", direccion.getDireccion());
    }

}
