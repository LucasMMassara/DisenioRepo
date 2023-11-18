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
    private Float vueltoTotal;
    private int nroPoliza;
    private Float premio;

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
    public Pago(Float montoAbonado, Float vueltoTotal, Date fechaPago, Date hora, int nroRecibo, int nroPoliza,
                    Float premio) {
            super();
            this.montoAbonado = montoAbonado;
            this.vueltoTotal = vueltoTotal;
            this.fechaPago = fechaPago;
            this.hora = hora;
            this.nroRecibo = nroRecibo;
            this.nroPoliza = nroPoliza;
            this.premio = premio;
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
    
    public Float getPremio() {
            return premio;
    }
    public void setPremio(Float premio) {
            this.premio = premio;
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

    public int getNroPoliza() {
        return nroPoliza;
    }

    public void setNroPoliza(int nroPoliza) {
        this.nroPoliza = nroPoliza;
    }
}
