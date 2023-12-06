package logica;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="cuotas")

public class Cuota implements Serializable {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date inicioCuota;
    
    @Temporal(TemporalType.DATE)
    private Date ultimoDiaPago;

    private Float monto;
    private Float premio;
    private Float sumaTotalDescuentos;
    
    private Float recargoMora;
    private Float bonificacionAdelanto;

    @Enumerated(EnumType.STRING)
    private EstadoCuota estado;

    @OneToOne(mappedBy = "cuota")
    private Pago pago;

    public Cuota() {
            super();
            // TODO Auto-generated constructor stub
    }

    public Cuota(Date inicioCuota, Date ultimoDiaPago, Float monto, Float premio, Float descuentos) {
        this.inicioCuota = inicioCuota;
        this.ultimoDiaPago = ultimoDiaPago;
        this.monto = monto;
        this.premio = premio;
        this.estado = EstadoCuota.NO_PAGADA;
        this.sumaTotalDescuentos = descuentos;
    }
    
    public Float getMonto() {
            return monto;
    }
    public void setMonto(Float monto) {
            this.monto = monto;
    }
    public EstadoCuota getEstado() {
            return estado;
    }
    public void setEstado(EstadoCuota estado) {
            this.estado = estado;
    }
    public Float getPremio() {
            return premio;
    }
    public void setPremio(Float premio) {
            this.premio = premio;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getInicioCuota() {
        return inicioCuota;
    }

    public void setInicioCuota(Date inicioCuota) {
        this.inicioCuota = inicioCuota;
    }

    public Date getUltimoDiaPago() {
        return ultimoDiaPago;
    }

    public void setUltimoDiaPago(Date ultimoDiaPago) {
        this.ultimoDiaPago = ultimoDiaPago;
    }

    @Override
    public String toString() {
        return "Cuota{" + "inicioCuota=" + inicioCuota.toString() + ", ultimoDiaPago=" + ultimoDiaPago.toString() + ", monto=" + monto + ", premio=" + premio + ", estado=" + estado.toString() + '}';
    }

    public Float getSumaTotalDescuentos() {
        return sumaTotalDescuentos;
    }

    public void setSumaTotalDescuentos(Float sumaTotalDescuentos) {
        this.sumaTotalDescuentos = sumaTotalDescuentos;
    }
    
}
