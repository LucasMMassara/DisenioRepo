package logica;

import java.io.Serializable;
/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;*/

//@Entity
//@Table(name = "localidad")

public class Localidad /*implements Serializable*/ {
        
        //@Id
        //@Column(name = "nombre")
	private String nombreLocalidad;
        
        //@OneToOne(optional=false)
        //@JoinColumn(name="provincia", unique=false, nullable=false, updatable=true, referencedColumnName = "nombre")
	private Provincia provincia;
        
        //@JoinColumn(name="IndicadorActual", unique=false, nullable=false, updatable=true, referencedColumnName = "id")
        //@OneToOne
	private IndicadorRiesgo indicadorActual;
        
        /*@OneToMany(cascade=CascadeType.ALL, mappedBy="localidad")
        private Set<IndicadorRiesgo> historialIndicadorRiesgo; //No se si va aca.*/

	public Localidad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Localidad(String nombreLocalidad) {
		super();
		this.nombreLocalidad = nombreLocalidad;
	}

	public String getNombreLocalidad() {
		return nombreLocalidad;
	}

	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public IndicadorRiesgo getIndicadorActual() {
		return indicadorActual;
	}

	public void setIndicadorActual(IndicadorRiesgo indicadorActual) {
		this.indicadorActual = indicadorActual;
	}

	/*public Set<IndicadorRiesgo> getHistorialIndicadorRiesgo() {
		return historialIndicadorRiesgo;
	}

	public void setHistorialIndicadorRiesgo(Set<IndicadorRiesgo> historialIndicadorRiesgo) {
		this.historialIndicadorRiesgo = historialIndicadorRiesgo;
	}*/
	
}
