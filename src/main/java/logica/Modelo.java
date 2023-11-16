package logica;

import java.util.List;
/*import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;*/

//@Entity
//@Table(name="modelo")

public class Modelo {
    
        //@Id
        //@GeneratedValue
	private String id;
        
	private String nombre;
	private Marca nombreMarca;
	private List<EstadisticaRoboVehiculo> historial;
	private EstadisticaRoboVehiculo estRoboActual; //marchear con estadistica
	private List<DatosVehiculo> precioYAño;

	public Modelo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Modelo(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<EstadisticaRoboVehiculo> getHistorial() {
		return historial;
	}

	public void setHistorial(List<EstadisticaRoboVehiculo> historial) {
		this.historial = historial;
	}

	public EstadisticaRoboVehiculo getEstRoboActual() {
		return estRoboActual;
	}

	public void setEstRoboActual(EstadisticaRoboVehiculo estRoboActual) {
		this.estRoboActual = estRoboActual;
	}

	public Marca getNombreMarca() {
		return nombreMarca;
	}

	public void setNombreMarca(Marca nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	public List<DatosVehiculo> getPrecioYAño() {
		return precioYAño;
	}

	public void setPrecioYAño(List<DatosVehiculo> precioYAño) {
		this.precioYAño = precioYAño;
	}
	
}
