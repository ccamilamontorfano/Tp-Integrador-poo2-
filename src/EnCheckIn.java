public class EnCheckIn extends EstadoReserva{

    public EnCheckIn(){}

    @Override
    public void iniciar(Reserva reserva){
        reserva.setEstado(new EnCurso());
    }

    @Override
    public void cancelar(Reserva reserva){
        float montoReembolso = reserva.getCostoServicios() + reserva.getCostoGestion();
        reserva.devolverCuposAItems();
        reserva.setVoucher(new VoucherDeCredito(montoReembolso));
        reserva.setEstado(new Cancelada());
    }
}
