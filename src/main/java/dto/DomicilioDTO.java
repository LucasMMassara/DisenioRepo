package dto;

public class DomicilioDTO {
    
    private String calle;
    private String numero;
    private String piso;
    private String dpto;

    private LocalidadDTO localidad;
        
    private String codigoPostal;

    public DomicilioDTO() {
        super();
    }

    public DomicilioDTO(String calle, String numero, String piso, String dpto, LocalidadDTO localidad, String codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.dpto = dpto;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
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

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public LocalidadDTO getLocalidad() {
        return localidad;
    }

    public void setLocalidad(LocalidadDTO localidad) {
        this.localidad = localidad;
    }
}
