public class OperacionInvalidaExcepcion extends RuntimeException {

    public OperacionInvalidaExcepcion(String mensajeError) {
        super(mensajeError);
    }
}
