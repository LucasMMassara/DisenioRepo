package logica;

import java.util.Date;

public class EstadisticaRoboVehiculo {
        
	private String id;
        
	private Double porcentajeIndicador;
	private Date inicioVigencia;
	private Date finVigencia;
	private Modelo modeloVehiculo; //Matchear con modelo

	public Double getPorcentajeIndicador() {
		return porcentajeIndicador;
	}
	public void setPorcentajeIndicador(Double porcentajeIndicador) {
		this.porcentajeIndicador = porcentajeIndicador;
	}
	public Date getInicioVigencia() {
		return inicioVigencia;
	}
	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	public Date getFinVigencia() {
		return finVigencia;
	}
	public void setFinVigencia(Date finVigencia) {
		this.finVigencia = finVigencia;
	}
	public Modelo getModeloVehiculo() {
		return modeloVehiculo;
	}
	public void setModeloVehiculo(Modelo modeloVehiculo) {
		this.modeloVehiculo = modeloVehiculo;
	}

}
