public class Confirmada extends EstadoReserva{

    public Confirmada(){}

    @Override
    public void hacerCheckIn(Reserva reserva){
        reserva.setEstado(new EnCheckIn());
    }

    @Override
    public void cancelar(Reserva reserva){
        reserva.getItems().forEach(item -> item.devolverCupo());
        reserva.setEstado(new Cancelada());
    }
}
