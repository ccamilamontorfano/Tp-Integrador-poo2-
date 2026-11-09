import java.util.Date;

public interface ServicioTarjeta {
    public boolean validarTarjeta(String numero, String cvv, Date vencimiento);

    public void preAutorizar(float monto);

    public String transferirInstantaneamente(float monto);
}
