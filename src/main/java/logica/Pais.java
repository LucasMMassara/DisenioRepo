package logica;

import java.io.Serializable;
/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;*/

//@Entity
//@Table(name = "pais")

public class Pais implements Serializable {
        
        //@Id
        //@Column(name = "nombre")
	private String nombre;

	public Pais() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pais(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
