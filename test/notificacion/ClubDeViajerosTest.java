package notificacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reserva.Cancelada;
import reserva.Confirmada;
import reserva.Reserva;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ClubDeViajerosTest {
    private ClubDeViajeros club;
    private MailSender mailSender;
    private Reserva reserva;

    // SETUP
    @BeforeEach
    public void setUp(){
        mailSender = mock(MailSender.class);
        reserva = mock(Reserva.class);
        club = new ClubDeViajeros(mailSender);
        club.agregarEmail("cami@email.com");
        club.agregarEmail("ori@email.com");
    }

    @Test
    public void siLaReservaEstaCanceladaElCuponLLegaACadaEmail(){
        // SETUP
        when(reserva.getEstado()).thenReturn(new Cancelada());
        // EXERCISE
        club.cambioEstado(reserva);
        // VERIFY
        verify(mailSender).enviarMail(eq("cami@email.com"), any(), any(), any());
        verify(mailSender).enviarMail(eq("ori@email.com"), any(), any(), any());
    }

    @Test
    public void siLaReservaEstaConOtroEstadoElCuponNoLLegaACadaEmail(){
        // SETUP
        when(reserva.getEstado()).thenReturn(new Confirmada());
        // EXERCISE
        club.cambioEstado(reserva);
        // VERIFY
        verifyNoInteractions(mailSender);
    }

    @Test
    public void unEmailEliminadoNoRecibeUnCupon() {
        // SETUP
        club.eliminarEmail("ori@email.com");
        when(reserva.getEstado()).thenReturn(new Cancelada());
        // EXERCISE
        club.cambioEstado(reserva);
        // VERIFY
        verify(mailSender).enviarMail(eq("cami@email.com"), any(), any(), any());
        verify(mailSender, never()).enviarMail(eq("ori@email.com"), any(), any(), any());
    }
}
