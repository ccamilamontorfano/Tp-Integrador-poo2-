public class BilleteraVirtual extends MedioDePago{
    private ServicioBilleteraVirtual servicio;

    public BilleteraVirtual(float monto, RegistroDePago registroPago, ServicioBilleteraVirtual servicio){
        super(monto, registroPago);
        this.servicio = servicio;
    }

    @Override
    public void validarDatos() {
        if (!servicio.validarSaldo(getMonto())){
            throw new PagoRechazadoExcepcion("Saldo insuficiente");
        }
    }

    @Override
    public void reservarFondos() {
        servicio.bloquearSaldo(getMonto());
    }

    @Override
    public void ejecutarTransaccion() {
        this.setCodigoTransaccion(servicio.acreditar(getMonto()));
    }

    @Override
    public void notificarResultado() {
        super.notificarResultado();
        servicio.notificarTransaccion("Pagaste $ " + getMonto());
    }
}

