package notificacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reserva.*;

import static org.mockito.Mockito.*;

public class NotificadorEmailTest {
    private NotificadorEmail notificador;
    private MailSender mailSender;
    private Reserva reserva;

    // SETUP
    @BeforeEach
    public void setUp(){
        mailSender = mock(MailSender.class);
        notificador = new NotificadorEmail(mailSender);
        reserva = mock(Reserva.class);
        when(reserva.getEmailCliente()).thenReturn("cliente@email.com");
    }

    @Test
    public void siLaReservaEstaConfirmadaSeLeMandaUnEmailAlCliente(){
        // SETUP
        when(reserva.getEstado()).thenReturn(new Confirmada());
        // EXERCISE
        notificador.cambioEstado(reserva);
        // VERIFY
        verify(mailSender).enviarMail(eq("cliente@email.com"), any(), any(), any());
    }

    @Test
    public void siLaReservaEstaEnCursoSeLeMandaUnEmailAlCliente(){
        // SETUP
        when(reserva.getEstado()).thenReturn(new EnCurso());
        // EXERCISE
        notificador.cambioEstado(reserva);
        // VERIFY
        verify(mailSender).enviarMail(eq("cliente@email.com"), any(), any(), any());
    }

    @Test
    public void siLaReservaEstaFinalizadaSeLeMandaUnEmailAlCliente(){
        // SETUP
        when(reserva.getEstado()).thenReturn(new Finalizada());
        // EXERCISE
        notificador.cambioEstado(reserva);
        // VERIFY
        verify(mailSender).enviarMail(eq("cliente@email.com"), any(), any(), any());
    }

    @Test
    public void siLaReservaEstaEnOtroEstadoNoSeLeMandaUnEmailAlCliente(){
        // SETUP
        when(reserva.getEstado()).thenReturn(new Cancelada());
        // EXERCISE
        notificador.cambioEstado(reserva);
        // VERIFY
        verifyNoInteractions(mailSender);
    }
}
