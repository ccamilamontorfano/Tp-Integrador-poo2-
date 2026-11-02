import java.util.ArrayList;
import java.util.List;

public class ServicioIndividual implements EntidadCatalogo{

    private static int ultimoCodigo = 0;
    private int codigoReserva;
    private String nombre;
    private String proveedor;
    private Categoria categoria;
    private double precioBase;
    private String descripcion;
    private List<AtributoDinamico> atributos;

    public ServicioIndividual(String nombre,String proveedor,Categoria categoria,double precioBase,String descripcion){
        ultimoCodigo++;
        this.codigoReserva = ultimoCodigo;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.categoria = categoria;
        this.precioBase = precioBase;
        this.descripcion = descripcion;
        this.atributos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public int getCodigoReserva(){
        return codigoReserva;
    }

    public String getProveedor(){
        return proveedor;
    }

    public Categoria getCategoria(){
        return categoria;
    }

    public double precioFinal(){
        return this.getPrecioBase();
    }

    public void agregarAtributo(AtributoDinamico atributo){
        atributos.add(atributo);
    }

    public boolean esServicioValido(){
        if(codigoReserva <= 0){
            return false;
        }
        if(nombre == null || nombre.isBlank()){
            return false;
        }
        for (AtributoDinamico atributo : atributos){
            if(!atributo.validar()){
                return false;
            }
        }
        return true;
    }

    public void eliminarAtributo(AtributoDinamico atributo){
        atributos.remove(atributo);
    }

    public List<AtributoDinamico> getAtributos(){
        return atributos;
    }

}
