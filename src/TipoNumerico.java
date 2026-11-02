public class TipoNumerico implements TipoValor{

    public boolean esValido(Object valor) {
        return valor instanceof Number;
    }
}
