import java.util.ArrayList;
import java.util.List;

public class PaqueteTuristico implements EntidadCatalogo {
    private String nombre;
    private String descripcion;
    private double descuento;
    private List<EntidadCatalogo> items = new ArrayList<>();

    public PaqueteTuristico(String nombre,String descripcion,double descuento,List<EntidadCatalogo> items){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.descuento =descuento;
        this.items=items;
    }

    public String getNombre(){
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioBase() {
        double precioBase =0;
        for(EntidadCatalogo item : items){
            precioBase+=item.getPrecioBase();
        }
        return  precioBase;
    }

    public double getDescuento(){
        return descuento;
    }

    public double precioFinal(){
        double descuento = this.getPrecioBase()*(this.getDescuento()/100.0);
        return this.getPrecioBase()- descuento;
    }

    public List<EntidadCatalogo> getItems(){
        return items;
    }

    public void agregarItem(EntidadCatalogo item){
        items.add(item);
    }

    public void eliminarItem(EntidadCatalogo item){
        items.remove(item);
    }

}
