public class TipoBoolean implements TipoValor{

    public boolean esValido(Object valor) {
        return valor instanceof Boolean;
    }
}
