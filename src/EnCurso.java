public class EnCurso extends EstadoReserva{

    public EnCurso(){}

    @Override
    public void finalizar(Reserva reserva){
        reserva.setEstado(new Finalizada());
    }

    @Override
    public void cancelar(Reserva reserva){
        reserva.setVoucher(new VoucherDeCredito(reserva.getCostoServicios()));
        reserva.setEstado(new Cancelada());
    }
}
