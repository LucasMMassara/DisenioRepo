package logica;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "indicadorRiesgo")

public class IndicadorRiesgo implements Serializable {
        
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String id;
        
        @OneToOne(optional=false)
        @JoinColumn(name="localidad", unique=false, nullable=false, updatable=true)
        Localidad localidad;
        
        @Basic
	private Float porcentajeIndicador;
        @Temporal(TemporalType.DATE)
	private Date inicioVigencia;
        @Temporal(TemporalType.DATE)
	private Date finVigencia;
        
        @OneToOne(optional=false)
        @JoinColumn(name="editor", unique=false, nullable=true, updatable=true)
	private Usuario usuarioEditor;
	
	public IndicadorRiesgo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IndicadorRiesgo(Float porcentajeIndicador, Date inicioVigencia, Date finVigencia) {
		super();
		this.porcentajeIndicador = porcentajeIndicador;
		this.inicioVigencia = inicioVigencia;
		this.finVigencia = finVigencia;
	}
        
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    
	public Float getPorcentajeIndicador() {
		return porcentajeIndicador;
	}
	public void setPorcentajeIndicador(Float porcentajeIndicador) {
		this.porcentajeIndicador = porcentajeIndicador;
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
	public Usuario getUsuarioEditor() {
		return usuarioEditor;
	}
	public void setUsuarioEditor(Usuario usuarioEditor) {
		this.usuarioEditor = usuarioEditor;
	}
	
}
