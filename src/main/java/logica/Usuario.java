package logica;

import java.io.Serializable;
/*import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;*/

//@Entity
//@Table(name = "Usuario")

public class Usuario extends Persona implements Serializable{
        
        //@Id
	private String usuario;
	private String contrasenia;
	
        
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(String usuario, String contrasenia) {
		super();
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
}
