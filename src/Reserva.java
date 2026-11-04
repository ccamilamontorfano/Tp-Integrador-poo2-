import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private float costoGestion;
    private EstadoReserva estado;
    private List<EntidadCatalogo> items = new ArrayList<>();
    private VoucherDeCredito voucher;

    public Reserva(float costoGestion){
        this.costoGestion = costoGestion;
        this.estado = new SolicitudPendiente(); // el estado arranca en Solicitud Pendiente
    }

    public void setEstado(EstadoReserva nuevoEstado){
        estado = nuevoEstado;
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
        return voucher;
    }

    public void setVoucher(VoucherDeCredito nuevoVoucher){
        voucher = nuevoVoucher;
    }

    public float getCostoGestion(){
        return costoGestion;
    }

    public float getCostoServicios(){
        return (float) this.getItems().stream().mapToDouble(item -> item.getPrecioBase()).sum();
    }
}
