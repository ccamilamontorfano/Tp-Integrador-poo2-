import java.time.LocalDate;

public class ComprobanteFiscal {
    private Reserva reserva;
    private LocalDate fechaEmision;

    public ComprobanteFiscal(Reserva reserva){
        this.reserva=reserva;
        this.fechaEmision= LocalDate.now();
    }
}
