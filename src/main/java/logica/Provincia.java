package logica;

import java.io.Serializable;
/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;*/

//@Entity
//@Table(name = "provincia")

public class Provincia{
        
        //@Id
        //@Column(name = "nombre")
	private String nombreProvincia;
        
        //@OneToOne(optional=false)
        //@JoinColumn(name="pais", unique=false, nullable=false, updatable=true)
	private Pais pais;

	public Provincia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Provincia(String nombreProvincia) {
		super();
		this.nombreProvincia = nombreProvincia;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
}
