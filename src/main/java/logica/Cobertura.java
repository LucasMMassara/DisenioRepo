package logica;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cobertura")

public class Cobertura implements Serializable {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
        
    private String detalle;

    @OneToOne
    private PorcentajeCobertura porcentajeActual;

    @OneToMany(mappedBy = "cobertura")
    private Set<PorcentajeCobertura> historialPorcentaje;
    
    public Cobertura(){
        super();
    }

    public Cobertura(String nombre) {
        super();
        this.detalle = nombre;
    }

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
            return historialPorcentaje;
    }
    public void setHistorial(Set<PorcentajeCobertura> historial) {
            this.historialPorcentaje = historial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<PorcentajeCobertura> getHistorialPorcentaje() {
        return historialPorcentaje;
    }

    public void setHistorialPorcentaje(Set<PorcentajeCobertura> historialPorcentaje) {
        this.historialPorcentaje = historialPorcentaje;
    }
        
        
}
