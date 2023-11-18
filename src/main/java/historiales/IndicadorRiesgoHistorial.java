/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package historiales;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import logica.IndicadorRiesgo;
import logica.Localidad;

@Entity
@Table(name="indicadorRiesgoHistorial")

public class IndicadorRiesgoHistorial implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne(fetch = FetchType.LAZY)
    private Localidad localidad;
    
    @OneToOne
    private IndicadorRiesgo indicadorActual;
    
    @OneToMany(mappedBy="irHistorial")
    private List<IndicadorRiesgo> historialIndicador;

    public IndicadorRiesgo getIndicadorActual() {
        return indicadorActual;
    }

    public void setIndicadorActual(IndicadorRiesgo indicadorActual) {
        this.indicadorActual = indicadorActual;
    }

    public List<IndicadorRiesgo> getHistorialIndicador() {
        return historialIndicador;
    }

    public void setHistorialIndicador(List<IndicadorRiesgo> historialIndicador) {
        this.historialIndicador = historialIndicador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }
    
    
    
}
