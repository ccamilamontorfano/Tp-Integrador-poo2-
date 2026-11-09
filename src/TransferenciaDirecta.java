import java.util.Date;

public class TransferenciaDirecta extends MedioDePago{
    private String cvu;
    private String alias;
    private Date fecha;
    private ServicioBancario servicio;

    public TransferenciaDirecta(String cvu, String alias, Date fecha, ServicioBancario servicio, float monto, RegistroDePago registroPago){
        super(monto, registroPago);
        this.cvu = cvu;
        this.alias = alias;
        this.fecha = fecha;
        this.servicio = servicio;
    }

    @Override
    public void validarDatos() {
        if(!servicio.validarCuenta(this.cvu, this.alias)){
            throw new PagoRechazadoExcepcion("Esta cuenta no es válida");
        }
    }

    @Override
    public void reservarFondos() {
        // no aplica para transferencias directas
    }

    @Override
    public void ejecutarTransaccion() {
        if(fecha == null){
            this.setCodigoTransaccion(servicio.transferirInstantaneamente(getMonto()));
        } else{
            this.setCodigoTransaccion(servicio.transferirConProgramacion(getMonto(), this.fecha));
        }
    }

    @Override
    public void notificarResultado() {
        super.notificarResultado();
        getRegistroPago().agregarComprobante(new ComprobanteTransferencia(getMonto(), getCodigoTransaccion(), this.fechaElegida(), this.cvu));
    }

    public Date getFecha() {
        return fecha;
    }

    private Date fechaElegida() {
        if (fecha == null) {
            return new Date();
        } else {
            return fecha;
        }
    }
}
