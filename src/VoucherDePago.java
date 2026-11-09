import java.util.Date;

public class VoucherDePago implements Comprobante{
    private float monto;
    private String codigo;
    private Date fecha;
    private boolean esDigital;

    public VoucherDePago(float monto, String codigo, Date fecha, boolean esDigital){
        this.monto = monto;
        this.codigo = codigo;
        this.fecha = fecha;
        this.esDigital = esDigital;
    }

    public float getMonto(){
        return monto;
    }

    public String getCodigo(){
        return codigo;
    }

    public Date getFecha(){
        return fecha;
    }

    public boolean getEsDigital(){
        return esDigital;
    }
}
