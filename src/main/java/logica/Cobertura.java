package logica;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cobertura")

public class Cobertura implements Serializable {
	
        @Id
	private String detalle;
        
        @OneToOne
        @JoinColumn(name="PorcentajeActual", unique=false, nullable=false, updatable=true)
	private PorcentajeCobertura porcentajeActual;
        
        @OneToMany(mappedBy = "cobertura", fetch = FetchType.EAGER) //FUNCIONA
	private Set<PorcentajeCobertura> historial;
	
	public PorcentajeCobertura getPorcentajeActual() {
		return porcentajeActual;
	}
	public void setPorcentajeActual(PorcentajeCobertura porcentajeActual) {
		this.porcentajeActual = porcentajeActual;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Set<PorcentajeCobertura> getHistorial() {
		return historial;
	}
	public void setHistorial(Set<PorcentajeCobertura> historial) {
		this.historial = historial;
	}
}
