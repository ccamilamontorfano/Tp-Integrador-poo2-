import java.util.ArrayList;
import java.util.List;

public class RegistroDePago {
    private List<String> pagos = new ArrayList<>();
    private List<Comprobante> comprobantes = new ArrayList<>();

    public RegistroDePago(){}

    public void agregarPago(String unPago){
        pagos.add(unPago);
    }

    public List<String> getPagos() {
        return pagos;
    }

    public List<Comprobante> getComprobantes() {
        return comprobantes;
    }

    public void agregarComprobante(Comprobante unComprobante){
        comprobantes.add(unComprobante);
    }
}
