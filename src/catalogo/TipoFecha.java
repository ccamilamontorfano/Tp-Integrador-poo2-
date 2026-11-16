package catalogo;

import java.time.LocalDate;

public class TipoFecha implements TipoValor {

    public boolean esValido(Object valor) {
        return valor instanceof LocalDate;
    }
}
