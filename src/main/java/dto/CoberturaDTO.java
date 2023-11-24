package dto;

import java.util.ArrayList;
import logica.PorcentajeCobertura;

public class CoberturaDTO {

    private String detalle;
    private PorcentajeCobertura porcentajeActual;
    private ArrayList<PorcentajeCobertura> historialPorcentajes = new ArrayList<>();

    public CoberturaDTO(String detalle, PorcentajeCobertura porcentajeActual) {
        this.detalle = detalle;
        this.porcentajeActual = porcentajeActual;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public PorcentajeCobertura getPorcentajeActual() {
        return porcentajeActual;
    }

    public void setPorcentajeActual(PorcentajeCobertura porcentajeActual) {
        this.porcentajeActual = porcentajeActual;
    }

    public ArrayList<PorcentajeCobertura> getHistorialPorcentajes() {
        return historialPorcentajes;
    }

    public void setHistorialPorcentajes(ArrayList<PorcentajeCobertura> historialPorcentajes) {
        this.historialPorcentajes = historialPorcentajes;
    }

}
