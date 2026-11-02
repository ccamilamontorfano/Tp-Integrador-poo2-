import javax.print.attribute.standard.NumberOfInterveningJobs;
import javax.swing.plaf.PanelUI;

public class AtributoDinamico {
    private String nombre;
    private boolean obligatorio;
    private TipoValor tipo;
    private Object valor;

    public AtributoDinamico(String nombre, boolean obligatorio, TipoValor tipo){
        this.nombre=nombre;
        this.obligatorio=obligatorio;
        this.tipo=tipo;
    }

    public String getNombre(){
        return nombre;
    }

    public boolean getObligatorio(){
        return obligatorio;
    }

    public TipoValor getTipo(){
        return tipo;
    }

    public void setValor(Object valor){
        this.valor=valor;
    }

    public boolean validar(){
        if(obligatorio && valor==null){
            return false;
        }
        if (valor != null && !tipo.esValido(valor)){
            return false;
        }
        return true;
    }
}
