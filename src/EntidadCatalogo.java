public interface EntidadCatalogo {

    public String getNombre();

    public String getDescripcion();

    public float getPrecioBase();

    public float getPrecioFinal();

    public void reservarCupo();

    public void devolverCupo();
}
