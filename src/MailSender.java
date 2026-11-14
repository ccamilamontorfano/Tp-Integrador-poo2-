public interface MailSender {
    public void enviarMail(String direccionDestino,String titulo,String mensaje,Object adjunto);
}
