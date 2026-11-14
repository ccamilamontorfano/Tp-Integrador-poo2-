import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private float costoGestion;
    private EstadoReserva estado;
    private EstadoReserva antiguoEstado;
    private List<EntidadCatalogo> items = new ArrayList<>();
    private VoucherDeCredito voucherCredito;
    private int cantPasajeros;
    private MetodoTraslado metodoTraslado;
    private Direccion direccion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<ObserverReserva> observers = new ArrayList<>();
    private String emailCliente;
    private ComprobanteFiscal comprobanteFiscal;
    private Voucher voucher;

    public Reserva(float costoGestion, LocalDate fechaInicio, LocalDate fechaFin, int cantPasajeros, MetodoTraslado metodoTraslado,Direccion direccion,String emailCliente){
        this.costoGestion = costoGestion;
        this.estado = new SolicitudPendiente(); // el estado arranca en Solicitud Pendiente
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantPasajeros = cantPasajeros;
        this.metodoTraslado = metodoTraslado;
        this.direccion=direccion;
        this.emailCliente = emailCliente;
    }

    public void setEstado(EstadoReserva nuevoEstado){
        antiguoEstado = estado;
        estado = nuevoEstado;
        this.notificarCambio();
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public List<EntidadCatalogo> getItems(){
        return items;
    }

    public void agregarItem(EntidadCatalogo item){
        estado.agregarItem(item, this);
    }

    public void quitarItem(EntidadCatalogo item){
        estado.quitarItem(item, this);
    }

    public void confirmar(){
        estado.confirmar(this);
    }

    public void hacerCheckIn(){
        estado.hacerCheckIn(this);
    }

    public void iniciar(){
        estado.iniciar(this);
    }

    public void finalizar(){
        estado.finalizar(this);
    }

    public void cancelar(){
        estado.cancelar(this);
    }

    public VoucherDeCredito getVoucher(){
        return voucherCredito;
    }

    public void setVoucher(VoucherDeCredito nuevoVoucher){
        voucherCredito = nuevoVoucher;
    }

    public float getCostoGestion(){
        return costoGestion;
    }

    public float getCostoServicios(){
        return (float) this.getItems().stream().mapToDouble(item -> item.getPrecioFinal()).sum();
    }

    public float valorTotal(){
        return this.getCostoServicios() + this.getCostoGestion();
    }

    public int getCantPasajeros(){
        return cantPasajeros;
    }

    public Direccion getDireccion(){
        return direccion;
    }

    public void setMetodoTraslado(MetodoTraslado nuevoTraslado){
        metodoTraslado = nuevoTraslado;
    }

    public LocalDate getFechaInicio(){
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void devolverCuposAItems(){
        this.getItems().forEach(item -> item.devolverCupo(fechaInicio, fechaFin, cantPasajeros));
    }

    public void agregarObserver(ObserverReserva nuevoObserver){
        observers.add(nuevoObserver);
    }

    public void eliminarObserver(ObserverReserva observer){
        observers.remove(observer);
    }

    public void notificarCambio(){
        for(ObserverReserva observer : observers){
            observer.cambioEstado(this);
        }
    }

    public String getEmailCliente(){
        return emailCliente;
    }

    public void generarComprobantes(){
        this.comprobanteFiscal = new ComprobanteFiscal(this);
        this.voucher= new Voucher(this);
    }
}
