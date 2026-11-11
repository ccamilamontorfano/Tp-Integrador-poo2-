import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaqueteTuristico implements EntidadCatalogo {
    private String nombre;
    private String descripcion;
    private float descuento;
    private List<EntidadCatalogo> items = new ArrayList<>();
    private int cupos = 10;
    private Date fechaInicio;
    private Date fechaFin;

    public PaqueteTuristico(String nombre,String descripcion,float descuento,List<EntidadCatalogo> items){
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

    public float getPrecioBase() {
        float precioBase =0;
        for(EntidadCatalogo item : items){
            precioBase+=item.getPrecioBase();
        }
        return  precioBase;
    }

    public float getDescuento(){
        return descuento;
    }

    public float getPrecioFinal(){
        float descuento = (this.getPrecioBase()*(this.getDescuento()/100));
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

    public void reservarCupo(){
        this.getItems().forEach(EntidadCatalogo::reservarCupo);
    }

    public void devolverCupo() {
        this.getItems().forEach(EntidadCatalogo::devolverCupo);
    }

}
