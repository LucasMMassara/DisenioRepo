package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "porcentajecobertura")

public class PorcentajeCobertura implements Serializable {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double porcentaje;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inicioVigencia;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date finVigencia;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cobertura cobertura;
    
    @OneToOne(optional=false)
    private Usuario usuarioEditor;

    public PorcentajeCobertura() {
        super();
    }
    
    public PorcentajeCobertura(Double porcentajeGenerado, Date fechaInicio, Cobertura cob) {
        super();
        this.porcentaje = porcentajeGenerado;
        this.inicioVigencia = fechaInicio;
        this.cobertura = cob;
    }

    public Double getPorcentaje() {
            return porcentaje;
    }
    public void setPorcentaje(Double porcentaje) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuarioEditor() {
        return usuarioEditor;
    }

    public void setUsuarioEditor(Usuario usuarioEditor) {
        this.usuarioEditor = usuarioEditor;
    }
}
