package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "porcentajecobertura")

public class PorcentajeCobertura implements Serializable {
        
    @Id
    private String id;

    private Float porcentaje;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inicioVigencia;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date finVigencia;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cobertura cobertura;

    public Float getPorcentaje() {
            return porcentaje;
    }
    public void setPorcentaje(Float porcentaje) {
            this.porcentaje = porcentaje;
    }
    public Date getInicioVigencia() {
            return inicioVigencia;
    }
    public void setInicioVigencia(Date inicioVigencia) {
            this.inicioVigencia = inicioVigencia;
    }
    public Cobertura getCobertura() {
            return cobertura;
    }
    public void setCobertura(Cobertura cobertura) {
            this.cobertura = cobertura;
    }
    public Date getFinVigencia() {
            return finVigencia;
    }
    public void setFinVigencia(Date finVigencia) {
            this.finVigencia = finVigencia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
