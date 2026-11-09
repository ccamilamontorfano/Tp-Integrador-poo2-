import java.util.Date;

public class TarjetaDeCredito extends MedioDePago {
    private String numero;
    private String cvv;
    private Date vencimiento;
    private ServicioTarjeta servicio;
    private boolean esDigital = true;

    public TarjetaDeCredito(String numero, String cvv, Date vencimiento, ServicioTarjeta servicio, float monto, RegistroDePago registroPago){
        super(monto, registroPago);
        this.numero = numero;
        this.cvv = cvv;
        this.vencimiento = vencimiento;
        this.servicio = servicio;
    }

    @Override
    public void validarDatos() {
        if (!servicio.validarTarjeta(this.numero, this.cvv, this.vencimiento)){
            throw new PagoRechazadoExcepcion("Esta tarjeta no es válida");
        }
    }

    @Override
    public void reservarFondos(){
        servicio.preAutorizar(getMonto());
    }

    @Override
    public void ejecutarTransaccion(){
        this.setCodigoTransaccion(servicio.transferirInstantaneamente(getMonto()));
    }

    @Override
    public void notificarResultado(){
        super.notificarResultado();
        getRegistroPago().agregarComprobante(new VoucherDePago(getMonto(), getCodigoTransaccion(), new Date(), esDigital));
    }
}
