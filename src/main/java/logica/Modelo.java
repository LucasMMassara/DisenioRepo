package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="modelo")

public class Modelo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    private Marca marca;

    @ManyToMany
    private List<AnioFabricacion> anioModelo;

    @OneToMany(mappedBy = "modelo")
    private List<EstadisticaRoboVehiculo> historial;

    @OneToOne
    private EstadisticaRoboVehiculo estadisticaActual;

    public Modelo() {
            super();
            // TODO Auto-generated constructor stub
    }

    public Modelo(String nombre) {
            super();
            this.nombre = nombre;
    }

    public Modelo(String nombre, Marca marca) {
        this.nombre = nombre;
        this.marca = marca;
    }

    public String getNombre() {
            return nombre;
    }

    public void setNombre(String nombre) {
            this.nombre = nombre;
    }

    public List<EstadisticaRoboVehiculo> getHistorial() {
            return historial;
    }

    public void setHistorial(List<EstadisticaRoboVehiculo> historial) {
            this.historial = historial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<AnioFabricacion> getAnioModelo() {
        return anioModelo;
    }

    public void setAnioModelo(List<AnioFabricacion> anioModelo) {
        this.anioModelo = anioModelo;
    }
    
    public EstadisticaRoboVehiculo getEstadisticaActual() {
        return estadisticaActual;
    }

    public void setEstadisticaActual(EstadisticaRoboVehiculo estadisticaActual) {
        this.estadisticaActual = estadisticaActual;
    }
   	
}
