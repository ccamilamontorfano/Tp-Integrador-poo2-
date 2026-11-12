import java.time.LocalDate;

public interface EntidadCatalogo {

    public String getNombre();

    public String getDescripcion();

    public float getPrecioBase();

    public float getPrecioFinal();

    public void reservarCupo(LocalDate fechaInicio, LocalDate fechaFin, int cantPasajeros);

    public void devolverCupo(LocalDate fechaInicio, LocalDate fechaFin, int cantPasajeros);

    public boolean tieneCupo(LocalDate fechaInicio, LocalDate fechaFin, int cantPasajeros);
}
