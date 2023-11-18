/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name="premioydescuentos")

public class PremioYDescuentos implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private float primaCalculada;
    private float derechosEmision;
    private float descuentoPorUnidad;
    private float bonificacionPorPagoSemestral;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
