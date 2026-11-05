public class PuntoDeEncuentro implements MetodoTraslado{
    private PuntoEncuentro punto;

    public float calcularCosto(Reserva reserva) {
        return 0;
    }

    public boolean hayDisponibilidad(){
        return punto.hayCupo();
    }
}
