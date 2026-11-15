import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class RegistroDePagoTest {

    private RegistroDePago registro;
    private Comprobante comprobante;

    // SETUP
    @BeforeEach
    public void setUp(){
        registro = new RegistroDePago();
        comprobante = mock(Comprobante.class);
    }

    @Test
    public void unRegistroNuevoArrancaVacio(){
        // EXERCISE Y VERIFY
        assertTrue(registro.getPagos().isEmpty());
        assertTrue(registro.getComprobantes().isEmpty());
    }

    @Test
    public void alAgregarUnPagoElRegistroLoContiene(){
        // EXERCISE
        registro.agregarPago("CM200507");
        // VERIFY
        assertTrue(registro.getPagos().contains("CM200507"));
    }

    @Test
    public void alAgregarUnComprobanteElRegistroLoContiene(){
        // EXERCISE
        registro.agregarComprobante(comprobante);
        // VERIFY
        assertTrue(registro.getComprobantes().contains(comprobante));
    }
}
