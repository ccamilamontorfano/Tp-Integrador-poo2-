package notificacion;

import reserva.Finalizada;
import reserva.Reserva;

public class GeneradorDeFactura implements ObserverReserva {

    public GeneradorDeFactura(){}

    public void cambioEstado(Reserva reserva) {
        if(reserva.getEstado() instanceof Finalizada){
            reserva.generarComprobantes();
        }
    }

}
