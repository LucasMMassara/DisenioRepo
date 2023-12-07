package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "valores_medida_seguridad")

public class ValorMedidaSeguridad implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
        
    private Double valorGarage;
    private Double alarma;
    private Double dispRastreo;
    private Double tuercasAntirrobo;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inicioVigencia;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date finVigencia;
    
    @ManyToOne(optional=false)
    private Usuario usuarioEditor;

    public Usuario getUsuarioEditor() {
        return usuarioEditor;
    }

    public void setUsuarioEditor(Usuario usuarioEditor) {
        this.usuarioEditor = usuarioEditor;
    }

    public Double getValorGarage() {
            return valorGarage;
    }
    public void setValorGarage(Double valorGarage) {
            this.valorGarage = valorGarage;
    }
    public Double getAlarma() {
            return alarma;
    }
    public void setAlarma(Double alarma) {
            this.alarma = alarma;
    }
    public Double getDispRastreo() {
            return dispRastreo;
    }
    public void setDispRastreo(Double dispRastreo) {
            this.dispRastreo = dispRastreo;
    }
    public Double getTuercasAntirrobo() {
            return tuercasAntirrobo;
    }
    public void setTuercasAntirrobo(Double tuercasAntirrobo) {
            this.tuercasAntirrobo = tuercasAntirrobo;
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
