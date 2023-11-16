package logica;

/*import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;*/

//@Entity
//@Table(name="marca")

public class Marca {
    
	//@Id
	private String nombre;

	public Marca() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Marca(String nombre) {
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
