import java.util.ArrayList;
import java.util.List;

public class ClubDeViajeros implements ObserverReserva{
    private MailSender mailSender;
    private List<String> emailsClientes;

    public ClubDeViajeros(MailSender mailSender){
        this.mailSender = mailSender;
        this.emailsClientes = new ArrayList<>();
    }

    public void cambioEstado(Reserva reserva) {
        if(reserva.getEstado() instanceof Cancelada){
            for(String email:emailsClientes){
                mailSender.enviarMail(email,"ReservaCancelada","Te regalamos un descuento del 5% en tu proxima reserva",null);
            }
        }

    }

    public void agregarEmail(String emailNuevo){
        emailsClientes.add(emailNuevo);
    }

    public void eliminarEmail(String email){
        emailsClientes.remove(email);
    }
}
