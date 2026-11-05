public class TrasladoChauffeur implements MetodoTraslado{
    private TrasladoVIP trasladoVIP;

    public float calcularCosto(Reserva reserva) {
        return trasladoVIP.calcularCosto(reserva.precioFinal());
    }
}
