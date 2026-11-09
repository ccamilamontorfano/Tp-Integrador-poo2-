public interface ServicioBilleteraVirtual {

    public boolean validarSaldo(float monto);

    public void bloquearSaldo(float monto);

    public String acreditar(float monto);

    public void notificarTransaccion(String unaNotificacion);

}
