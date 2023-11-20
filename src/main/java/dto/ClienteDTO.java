package dto;
import java.util.Date;

public class ClienteDTO{
        
        private String nombre;
	private String apellido;
	private String numDocumento;
        private String nroCuil;
        
        private String tipoDocumento;
        private String sexo;
        private String condicionIva;
        private String estadoCivil;
        
        private String correoElectronico;
        private String profesion;
        private String anioRegistro;
        private String calle;
        private String numero;
        private String piso;
        private String dpto;
        
        private String pais;
        private String provincia;
        private String localidad;
        private String codigoPostal;
        
        private String numCliente;
        private Date fechaNacimiento;
	
	public ClienteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

    public ClienteDTO(String nombre, String apellido, String numDocumento, String nroCuil, String tipoDocumento, String sexo, String condicionIva, String estadoCivil, String correoElectronico, String profesion, String anioRegistro, String calle, String numero, String piso, String dpto, String pais, String provincia, String localidad, String codigoPostal, String numCliente, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numDocumento = numDocumento;
        this.nroCuil = nroCuil;
        this.tipoDocumento = tipoDocumento;
        this.sexo = sexo;
        this.condicionIva = condicionIva;
        this.estadoCivil = estadoCivil;
        this.correoElectronico = correoElectronico;
        this.profesion = profesion;
        this.anioRegistro = anioRegistro;
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.dpto = dpto;
        this.pais = pais;
        this.provincia = provincia;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
        this.numCliente = numCliente;
        this.fechaNacimiento = fechaNacimiento;
    }

    public ClienteDTO(String nombre, String apellido, String numCliente, String tipoDocumento, String numDocumento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numDocumento = numDocumento;
        this.tipoDocumento = tipoDocumento;
        this.numCliente = numCliente;
    }

	public String getNumCliente() {
		return numCliente;
	}
	public void setNumCliente(String numCliente) {
		this.numCliente = numCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

    public String getNroCuil() {
        return nroCuil;
    }

    public void setNroCuil(String nroCuil) {
        this.nroCuil = nroCuil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCondicionIva() {
        return condicionIva;
    }

    public void setCondicionIva(String condicionIva) {
        this.condicionIva = condicionIva;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getAnioRegistro() {
        return anioRegistro;
    }

    public void setAnioRegistro(String anioRegistro) {
        this.anioRegistro = anioRegistro;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    

}
