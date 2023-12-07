package logica;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pago")

public class Pago implements Serializable {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nroRecibo;

    private Float montoAbonado;
    private Float montoActualizadoAbonar;
    private Float vueltoTotal;
    private String nroPoliza;

    @Temporal(TemporalType.DATE)
    private Date fechaPago;

    @Temporal(TemporalType.TIME)
    private Date hora;

    @OneToOne(fetch = FetchType.LAZY)
    private Cuota cuota;

    public Pago() {
            super();
            // TODO Auto-generated constructor stub
    }
    public Pago(Float montoAbonado, Float vueltoTotal, Date fechaPago, Date hora, int nroRecibo, String nroPoliza) {
            super();
            this.montoAbonado = montoAbonado;
            this.vueltoTotal = vueltoTotal;
            this.fechaPago = fechaPago;
            this.hora = hora;
            this.nroRecibo = nroRecibo;
            this.nroPoliza = nroPoliza;
    }
    public Float getMontoAbonado() {
            return montoAbonado;
    }
    public void setMontoAbonado(Float montoAbonado) {
            this.montoAbonado = montoAbonado;
    }
    public Float getVueltoTotal() {
            return vueltoTotal;
    }
    public void setVueltoTotal(Float vueltoTotal) {
            this.vueltoTotal = vueltoTotal;
    }
    public Date getFechaPago() {
            return fechaPago;
    }
    public void setFechaPago(Date fechaPago) {
            this.fechaPago = fechaPago;
    }
    public Date getHora() {
            return hora;
    }
    public void setHora(Date hora) {
            this.hora = hora;
    }

    public Cuota getCuota() {
        return cuota;
    }

    public void setCuota(Cuota cuota) {
        this.cuota = cuota;
    }

    public int getNroRecibo() {
        return nroRecibo;
    }

    public void setNroRecibo(int nroRecibo) {
        this.nroRecibo = nroRecibo;
    }

    public String getNroPoliza() {
        return nroPoliza;
    }

    public void setNroPoliza(String nroPoliza) {
        this.nroPoliza = nroPoliza;
    }

    public Float getMontoActualizadoAbonar() {
        return montoActualizadoAbonar;
    }

    public void setMontoActualizadoAbonar(Float montoActualizadoAbonar) {
        this.montoActualizadoAbonar = montoActualizadoAbonar;
    }
    
}
