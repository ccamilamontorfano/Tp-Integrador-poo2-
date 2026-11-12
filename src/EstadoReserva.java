public abstract class EstadoReserva {

    public void agregarItem(EntidadCatalogo item, Reserva reserva){
        throw new OperacionInvalidaExcepcion("No se puede agregar este servicio");
    }

    public void quitarItem(EntidadCatalogo item, Reserva reserva){
        throw new OperacionInvalidaExcepcion("No se puede quitar este servicio");
    }

    public void confirmar(Reserva reserva){
        throw new OperacionInvalidaExcepcion("No se puede confirmar");
    }

    public void hacerCheckIn(Reserva reserva){
        throw new OperacionInvalidaExcepcion("No se puede hacer CheckIn");
    }

    public void iniciar(Reserva reserva){
        throw new OperacionInvalidaExcepcion("No se puede iniciar la reserva");
    }

    public void finalizar(Reserva reserva){
        throw new OperacionInvalidaExcepcion("No se puede finalizar la reserva");
    }

    public void cancelar(Reserva reserva){
        throw new OperacionInvalidaExcepcion("No se puede cancelar la reserva");
    }
}

