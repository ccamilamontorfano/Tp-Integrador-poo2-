import java.util.Date;

public class ComprobanteTransferencia implements Comprobante{
    private float monto;
    private String codigo;
    private Date fecha;
    private String cvu;

    public ComprobanteTransferencia(float monto, String codigo, Date fecha, String cvu){
        this.monto = monto;
        this.codigo = codigo;
        this.fecha = fecha;
        this.cvu = cvu;
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

    public String getCvu(){
        return cvu;
    }
}
