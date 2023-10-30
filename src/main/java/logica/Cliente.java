package logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")

public class Cliente extends Persona implements Serializable {
        
        @Id
	private String cuil;
        
        @Enumerated
	private Iva condicionIva;
        @Enumerated
	private EstadoCliente estadoCliente;
        
        @Basic
	private String profesion;
	private String numCliente;
        //Domicilio
        @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Domicilio domicilio;
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cliente(String cuil, Iva condicionIva, String profesion, EstadoCliente estadoCliente, String numCliente) {
		super();
		this.cuil = cuil;
		this.condicionIva = condicionIva;
		this.profesion = profesion;
		this.estadoCliente = estadoCliente;
		this.numCliente = numCliente;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public Iva getCondicionIva() {
		return condicionIva;
	}
	public void setCondicionIva(Iva condicionIva) {
		this.condicionIva = condicionIva;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public EstadoCliente getEstadoCliente() {
		return estadoCliente;
	}
	public void setEstadoCliente(EstadoCliente estadoCliente) {
		this.estadoCliente = estadoCliente;
	}
	public String getNumCliente() {
		return numCliente;
	}
	public void setNumCliente(String numCliente) {
		this.numCliente = numCliente;
	}
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	
}
