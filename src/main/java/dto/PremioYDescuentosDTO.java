/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Lucas
 */
public class PremioYDescuentosDTO {
    
    private float primaCalculada;
    private float derechosEmision;
    private float descuentoPorUnidad; //Si el clietne tiene mas de un vehiculo tiene descuento por unidad
    private float bonificacionPorPagoSemestral;
    private int idValorMedidaSeguridad;
    private int idValorSiniestros;
    private int idDatosGenerales;

    public PremioYDescuentosDTO(float primaCalculada, float derechosEmision, float descuentoPorUnidad, float bonificacionPorPagoSemestral, int idValorMedidaSeguridad, int idValorSiniestros, int idDatosGenerales) {
        this.primaCalculada = primaCalculada;
        this.derechosEmision = derechosEmision;
        this.descuentoPorUnidad = descuentoPorUnidad;
        this.bonificacionPorPagoSemestral = bonificacionPorPagoSemestral;
        this.idValorMedidaSeguridad = idValorMedidaSeguridad;
        this.idValorSiniestros = idValorSiniestros;
        this.idDatosGenerales = idDatosGenerales;
    }

    public PremioYDescuentosDTO() {
    }

    public float getPrimaCalculada() {
        return primaCalculada;
    }

    public void setPrimaCalculada(float primaCalculada) {
        this.primaCalculada = primaCalculada;
    }

    public float getDerechosEmision() {
        return derechosEmision;
    }

    public void setDerechosEmision(float derechosEmision) {
        this.derechosEmision = derechosEmision;
    }

    public float getDescuentoPorUnidad() {
        return descuentoPorUnidad;
    }

    public void setDescuentoPorUnidad(float descuentoPorUnidad) {
        this.descuentoPorUnidad = descuentoPorUnidad;
    }

    public float getBonificacionPorPagoSemestral() {
        return bonificacionPorPagoSemestral;
    }

    public void setBonificacionPorPagoSemestral(float bonificacionPorPagoSemestral) {
        this.bonificacionPorPagoSemestral = bonificacionPorPagoSemestral;
    }

    public int getIdValorMedidaSeguridad() {
        return idValorMedidaSeguridad;
    }

    public void setIdValorMedidaSeguridad(int idValorMedidaSeguridad) {
        this.idValorMedidaSeguridad = idValorMedidaSeguridad;
    }

    public int getIdValorSiniestros() {
        return idValorSiniestros;
    }

    public void setIdValorSiniestros(int idValorSiniestros) {
        this.idValorSiniestros = idValorSiniestros;
    }

    public int getIdDatosGenerales() {
        return idDatosGenerales;
    }

    public void setIdDatosGenerales(int idDatosGenerales) {
        this.idDatosGenerales = idDatosGenerales;
    }
    
    public float getPremio(){
        return this.primaCalculada + this.derechosEmision;
    }

    @Override
    public String toString() {
        return "PremioYDescuentosDTO{" + "primaCalculada=" + primaCalculada + ", derechosEmision=" + derechosEmision + ", descuentoPorUnidad=" + descuentoPorUnidad + ", bonificacionPorPagoSemestral=" + bonificacionPorPagoSemestral + ", idValorMedidaSeguridad=" + idValorMedidaSeguridad + ", idValorSiniestros=" + idValorSiniestros + ", idDatosGenerales=" + idDatosGenerales + '}';
    }
    
}
