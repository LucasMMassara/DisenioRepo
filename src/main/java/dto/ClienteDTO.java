package dto;
import java.util.Date;
import logica.TipoDocumento;
import static logica.TipoDocumento.DNI;

public class ClienteDTO{
        
        private String nombre;
	private String apellido;
	private String numDocumento;
        private String nroCuil;
        
        private TipoDocumento tipoDocumento;
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
	
	public ClienteDTO(String nombre, String apellido, String numCliente, TipoDocumento tipoDoc, String numDoc) {
		super();
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setNumCliente(numCliente);
		this.setTipoDocumento(tipoDoc);
		this.setNumDocumento(numDoc);
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

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
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
        
        public String getTipoDocumentoAsString(){
            
            switch(tipoDocumento){
                default: return "";
                case DNI: return "DNI";
                case CC: return "CC";
                case CI: return "CI";
                case CIC: return "CIC";
            }
            
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
