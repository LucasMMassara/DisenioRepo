package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "valores_generales")

public class ValoresGenerales implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private Double ajusteKM;
    private Double ajusteHijos;
    private Double descuentoUnidadAdicional; //Este y derechoEmision me confunden, reveer el enunciado.
    private Double derechoEmision;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inicioVigencia;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date finVigencia;
	
    public Double getAjusteKM() {
            return ajusteKM;
    }
    public void setAjusteKM(Double ajusteKM) {
            this.ajusteKM = ajusteKM;
    }
    public Double getAjusteHijos() {
            return ajusteHijos;
    }
    public void setAjusteHijos(Double ajusteHijos) {
            this.ajusteHijos = ajusteHijos;
    }
    public Double getDescuentoUnidadAdicional() {
            return descuentoUnidadAdicional;
    }
    public void setDescuentoUnidadAdicional(Double descuentoUnidadAdicional) {
            this.descuentoUnidadAdicional = descuentoUnidadAdicional;
    }
    public Double getDerechoEmision() {
            return derechoEmision;
    }
    public void setDerechoEmision(Double derechoEmision) {
            this.derechoEmision = derechoEmision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public Date getFinVigencia() {
        return finVigencia;
    }

    public void setFinVigencia(Date finVigencia) {
        this.finVigencia = finVigencia;
    }

}
