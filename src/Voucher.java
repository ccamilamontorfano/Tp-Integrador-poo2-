import java.time.LocalDate;

public class Voucher {
    private Reserva reserva;
    private LocalDate fechaEmision;

    public Voucher(Reserva reserva){
        this.reserva = reserva;
        this.fechaEmision = LocalDate.now();
    }
}
