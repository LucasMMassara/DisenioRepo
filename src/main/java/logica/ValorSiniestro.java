package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "valores_siniestros")

public class ValorSiniestro implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    private Double sinSiniestro;
    private Double unSiniestro;
    private Double dosSiniestros;
    private Double masDeDosSiniestros;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inicioVigencia;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date finVigencia;
    
    @OneToOne(optional=false)
    private Usuario usuarioEditor;

    public Usuario getUsuarioEditor() {
        return usuarioEditor;
    }

    public void setUsuarioEditor(Usuario usuarioEditor) {
        this.usuarioEditor = usuarioEditor;
    }
    public Double getUnSiniestro() {
            return unSiniestro;
    }
    public void setUnSiniestro(Double unSiniestro) {
            this.unSiniestro = unSiniestro;
    }
    public Double getSinSiniestro() {
            return sinSiniestro;
    }
    public void setSinSiniestro(Double sinSiniestro) {
            this.sinSiniestro = sinSiniestro;
    }
    public Double getDosSiniestros() {
            return dosSiniestros;
    }
    public void setDosSiniestros(Double dosSiniestros) {
            this.dosSiniestros = dosSiniestros;
    }
    public Double getMasDeDosSiniestros() {
            return masDeDosSiniestros;
    }
    public void setMasDeDosSiniestros(Double masDeDosSiniestros) {
            this.masDeDosSiniestros = masDeDosSiniestros;
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
	   