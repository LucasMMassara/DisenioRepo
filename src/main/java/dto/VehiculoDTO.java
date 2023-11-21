package dto;

import logica.AnioFabricacion;
import logica.Marca;
import logica.Modelo;

public class VehiculoDTO {
	private String id;
	private String marcaVehiculo;
	private String modeloVehiculo;
	private String anioVehiculo;
	private String numMotor;
	private String numChasis;
	private String numPatente;
	private String kmPorAnio;
	private Boolean guardaEnGarage;
	private Boolean tieneAlarma;
	private Boolean dispositivoRastreo;
	private Boolean tuercasAntirrobo;

    public VehiculoDTO() {
    }

    public VehiculoDTO(String id, String marcaVehiculo, String modeloVehiculo, String anioVehiculo, String numMotor, String numChasis, String numPatente, String kmPorAnio, Boolean guardaEnGarage, Boolean tieneAlarma, Boolean dispositivoRastreo, Boolean tuercasAntirrobo) {
        this.id = id;
        this.marcaVehiculo = marcaVehiculo;
        this.modeloVehiculo = modeloVehiculo;
        this.anioVehiculo = anioVehiculo;
        this.numMotor = numMotor;
        this.numChasis = numChasis;
        this.numPatente = numPatente;
        this.kmPorAnio = kmPorAnio;
        this.guardaEnGarage = guardaEnGarage;
        this.tieneAlarma = tieneAlarma;
        this.dispositivoRastreo = dispositivoRastreo;
        this.tuercasAntirrobo = tuercasAntirrobo;
    }

    public String getId() {
        return id;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public String getAnioVehiculo() {
        return anioVehiculo;
    }

    public String getNumMotor() {
        return numMotor;
    }

    public String getNumChasis() {
        return numChasis;
    }

    public String getNumPatente() {
        return numPatente;
    }

    public String getKmPorAnio() {
        return kmPorAnio;
    }

    public Boolean getGuardaEnGarage() {
        return guardaEnGarage;
    }

    public Boolean getTieneAlarma() {
        return tieneAlarma;
    }

    public Boolean getDispositivoRastreo() {
        return dispositivoRastreo;
    }

    public Boolean getTuercasAntirrobo() {
        return tuercasAntirrobo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public void setAnioVehiculo(String anioVehiculo) {
        this.anioVehiculo = anioVehiculo;
    }

    public void setNumMotor(String numMotor) {
        this.numMotor = numMotor;
    }

    public void setNumChasis(String numChasis) {
        this.numChasis = numChasis;
    }

    public void setNumPatente(String numPatente) {
        this.numPatente = numPatente;
    }

    public void setKmPorAnio(String kmPorAnio) {
        this.kmPorAnio = kmPorAnio;
    }

    public void setGuardaEnGarage(Boolean guardaEnGarage) {
        this.guardaEnGarage = guardaEnGarage;
    }

    public void setTieneAlarma(Boolean tieneAlarma) {
        this.tieneAlarma = tieneAlarma;
    }

    public void setDispositivoRastreo(Boolean dispositivoRastreo) {
        this.dispositivoRastreo = dispositivoRastreo;
    }

    public void setTuercasAntirrobo(Boolean tuercasAntirrobo) {
        this.tuercasAntirrobo = tuercasAntirrobo;
    }
        

    
}
