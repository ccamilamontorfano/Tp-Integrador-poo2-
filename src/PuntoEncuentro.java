public class PuntoEncuentro {
    private int cuposDeGuias;

    public PuntoEncuentro(int cuposDeGuias){
        this.cuposDeGuias = cuposDeGuias;
    }

    public boolean hayCupo(){
        return cuposDeGuias > 0;
    }
}
