package dto;
import logica.TipoDocumento;
import static logica.TipoDocumento.DNI;

public class ClienteDTO{
	private String apellido;
	private String nombre;
	private String numCliente;
	private TipoDocumento tipoDocumento;
	private String numDocumento;
	
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
        

}
