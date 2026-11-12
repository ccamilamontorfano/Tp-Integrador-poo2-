import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ServicioIndividual implements EntidadCatalogo{

    private static int ultimoCodigo = 0;
    private int codigoReserva;
    private String nombre;
    private String proveedor;
    private Categoria categoria;
    private float precioBase;
    private String descripcion;
    private List<AtributoDinamico> atributos;
    private int capacidad;
    private Map<LocalDate, Integer> ocupado;

    public ServicioIndividual(String nombre,String proveedor,Categoria categoria,float precioBase,String descripcion, int capacidad){
        ultimoCodigo++;
        this.codigoReserva = ultimoCodigo;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.categoria = categoria;
        this.precioBase = precioBase;
        this.descripcion = descripcion;
        this.atributos = new ArrayList<>();
        this.capacidad = capacidad;
        this.ocupado = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecioBase() {
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

    public float getPrecioFinal(){
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

    public void reservarCupo(LocalDate fechaInicio, LocalDate fechaFin, int cantPasajeros){
        if(!tieneCupo(fechaInicio, fechaFin, cantPasajeros)){
            throw new SinCupoExcepcion("No hay mas capacidad");
        }
        diasEntre(fechaInicio, fechaFin).forEach(dia -> ocupado.put(dia, this.ocupadosActualizados(dia, cantPasajeros)));
    }

    private int ocupadosActualizados(LocalDate dia, int cambio){
        return ocupado.getOrDefault(dia, 0) + cambio;
    }

    public void devolverCupo(LocalDate fechaInicio, LocalDate fechaFin, int cantPasajeros){
        diasEntre(fechaInicio, fechaFin).forEach(dia -> ocupado.put(dia, this.ocupadosActualizados(dia, - cantPasajeros)));
    }


    public int getCapacidad(){
        return capacidad;
    }

    public boolean tieneCupo(LocalDate fechaInicio, LocalDate fechaFin, int cantPasajeros){
        return diasEntre(fechaInicio, fechaFin).allMatch(dia -> hayCapacidad(dia, cantPasajeros));
    }

    private Stream<LocalDate> diasEntre(LocalDate fechaInicio, LocalDate fechaFin){
        return fechaInicio.datesUntil(fechaFin.plusDays(1));
    }

    private boolean hayCapacidad(LocalDate dia, int cantPasajeros){
        return (this.capacidadSobrante(dia)) >= cantPasajeros;
    }

    public int capacidadSobrante(LocalDate dia){
        return capacidad - ocupado.getOrDefault(dia, 0);
    }
}
