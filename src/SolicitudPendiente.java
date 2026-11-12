import java.time.LocalDate;

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
        if(!todosTienenCupo(reserva)){
            throw new SinCupoExcepcion("No se puede confirmar, no hay suficientes cupos");
        }
        reservarTodosLosItems(reserva);
        reserva.setEstado(new Confirmada());
    }

    private void reservarTodosLosItems(Reserva reserva){
        reserva.getItems().forEach(item -> item.reservarCupo(reserva.getFechaInicio(), reserva.getFechaFin(), reserva.getCantPasajeros()));
    }

    private boolean todosTienenCupo(Reserva reserva){
        return reserva.getItems().stream().allMatch(item -> item.tieneCupo(reserva.getFechaInicio(), reserva.getFechaFin(), reserva.getCantPasajeros()));
    }

    @Override
    public void cancelar(Reserva reserva){
        reserva.setEstado(new Cancelada());
    }
}

