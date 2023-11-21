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
    private Date fechaPago;

    private Float monto;
    private Float premio;
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
    public Cuota(Date fechaPago, Float monto, EstadoCuota estado, Float premio, Float recargoMora,
                    Float bonificacionAdelanto) {
            super();
            this.fechaPago = fechaPago;
            this.monto = monto;
            this.estado = estado;
            this.premio = premio;
            this.recargoMora = recargoMora;
            this.bonificacionAdelanto = bonificacionAdelanto;
    }
    public Date getFechaPago() {
            return fechaPago;
    }
    public void setFechaPago(Date fechaPago) {
            this.fechaPago = fechaPago;
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
    public Float getRecargoMora() {
            return recargoMora;
    }
    public void setRecargoMora(Float recargoMora) {
            this.recargoMora = recargoMora;
    }
    public Float getBonificacionAdelanto() {
            return bonificacionAdelanto;
    }
    public void setBonificacionAdelanto(Float bonificacionAdelanto) {
            this.bonificacionAdelanto = bonificacionAdelanto;
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
    
}
