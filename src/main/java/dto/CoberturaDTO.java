package dto;

public class CoberturaDTO {

    private String detalle;
    private int idCobertura;

    public CoberturaDTO(String detalle, int idCobertura) {
        this.detalle = detalle;
        this.idCobertura = idCobertura;
    }

    public CoberturaDTO() {
    }

    public int getIdCobertura() {
        return idCobertura;
    }

    public void setIdCobertura(int idCobertura) {
        this.idCobertura = idCobertura;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

}
