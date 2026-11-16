package traslado;

import reserva.Reserva;

public class TrasladoChauffeur implements MetodoTraslado {
    private TrasladoVIP trasladoVIP;

    public TrasladoChauffeur(TrasladoVIP trasladoVIP){
        this.trasladoVIP = trasladoVIP;
    }

    public float calcularCosto(Reserva reserva) {
        return trasladoVIP.calcularCosto(reserva.valorTotal());
    }
}
