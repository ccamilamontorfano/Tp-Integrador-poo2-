package traslado;

import reserva.Reserva;

public class TrasladoEstandar implements MetodoTraslado {
    private PlataformaTransporte plataforma;

    public  TrasladoEstandar(PlataformaTransporte plataforma){
        this.plataforma = plataforma;
    }

    public float calcularCosto(Reserva reserva){
        return plataforma.estimarTraslado(reserva.getCantPasajeros(),reserva.getDireccion());
    }
}
