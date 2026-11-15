import java.time.LocalDate;
import java.util.Date;

public class TipoFecha implements TipoValor{

    public boolean esValido(Object valor) {
        return valor instanceof LocalDate;
    }
}
