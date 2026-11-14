import java.time.LocalDate;

public class PuntoDeEncuentro implements MetodoTraslado{
    private int cuposDeGuias = 10;
    private LocalDate dia;


    public float calcularCosto(Reserva reserva) {
        return 0;
    }

    public boolean hayCupo(){
        return this.dia.isEqual(LocalDate.now())&& this.cuposDeGuias>0;
    }

    public void cambioDeDia() {
        this.dia = this.dia.plusDays(1);
        this.cuposDeGuias = 10;
    }

    public boolean hayDisponibilidad(){
        return this.hayCupo();
    }
}
