public class SolicitudPendiente extends EstadoReserva{

    public SolicitudPendiente(){}

    @Override
    public void agregarItem(EntidadCatalogo item, Reserva reserva){
        reserva.getItems().add(item);
    }

    @Override
    public void quitarItem(EntidadCatalogo item, Reserva reserva){
        reserva.getItems().remove(item);
    }

    @Override
    public void confirmar(Reserva reserva){
        reserva.getItems().forEach(item -> item.reservarCupo());
        reserva.setEstado(new Confirmada());
    }

    @Override
    public void cancelar(Reserva reserva){
        reserva.setEstado(new Cancelada());
    }
}

