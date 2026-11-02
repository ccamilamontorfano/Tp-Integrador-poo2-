public class TipoTexto implements TipoValor{

    public boolean esValido(Object valor) {
        return valor instanceof String;
    }
}
