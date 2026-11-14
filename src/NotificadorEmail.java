public class NotificadorEmail implements ObserverReserva{
    private MailSender mailSender;

    public NotificadorEmail(MailSender mailSender){
        this.mailSender=mailSender;
    }

    public void cambioEstado(Reserva reserva) {
        if(reserva.getEstado() instanceof Confirmada || reserva.getEstado() instanceof EnCurso || reserva.getEstado() instanceof Finalizada){
            mailSender.enviarMail(reserva.getEmailCliente(),"Cambio el estado de tu reserva","Tu reserva ahora es"+ reserva.getEstado(),null);
        }

    }
}
