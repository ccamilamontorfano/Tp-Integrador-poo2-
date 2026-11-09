import java.util.Date;

public interface ServicioBancario {
    public boolean validarCuenta(String cvu, String alias);

    public String transferirInstantaneamente(float monto);

    public String transferirConProgramacion(float monto, Date fecha);
}
