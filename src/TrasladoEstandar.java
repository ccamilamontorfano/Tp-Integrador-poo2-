public class TrasladoEstandar implements MetodoTraslado{
    private PlataformaTrasporte plataforma;

    public float calcularCosto(Reserva reserva){
        return plataforma.estimarTraslado(reserva.getCantPasajeros(),reserva.getDireccion());
    }
}
