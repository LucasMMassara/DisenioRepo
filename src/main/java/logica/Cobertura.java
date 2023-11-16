package logica;


import java.util.Set;

public class Cobertura{
	
	private String detalle;
	private PorcentajeCobertura porcentajeActual;
	private Set<PorcentajeCobertura> historial;
	
	public PorcentajeCobertura getPorcentajeActual() {
		return porcentajeActual;
	}
	public void setPorcentajeActual(PorcentajeCobertura porcentajeActual) {
		this.porcentajeActual = porcentajeActual;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Set<PorcentajeCobertura> getHistorial() {
		return historial;
	}
	public void setHistorial(Set<PorcentajeCobertura> historial) {
		this.historial = historial;
	}
}
