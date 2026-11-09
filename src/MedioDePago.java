public abstract class MedioDePago {
    private float monto;
    private String codigoTransaccion;
    private RegistroDePago registroPago;

    public MedioDePago(float monto, RegistroDePago registroPago){
        this.monto = monto;
        this.registroPago = registroPago;
    }

    public final void procesarPago(){
        this.validarDatos();
        this.reservarFondos();
        this.ejecutarTransaccion();
        this.notificarResultado();
    }

    public abstract void validarDatos();

    public abstract void reservarFondos();

    public abstract void ejecutarTransaccion();

    public void notificarResultado(){
        this.registroPago.agregarPago(codigoTransaccion);
    }

    public void setCodigoTransaccion(String codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }

    public float getMonto() {
        return monto;
    }

    public RegistroDePago getRegistroPago() {
        return registroPago;
    }

    public String getCodigoTransaccion() {
        return codigoTransaccion;
    }
}
