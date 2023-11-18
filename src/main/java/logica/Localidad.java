package logica;

import historiales.IndicadorRiesgoHistorial;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "localidad")

public class Localidad implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
        
    private String nombreLocalidad;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Provincia provincia;
    
    @OneToOne(mappedBy = "localidad")
    private IndicadorRiesgoHistorial indicadorHistorial;

    public Localidad() {
            super();
            // TODO Auto-generated constructor stub
    }

    public Localidad(String nombreLocalidad) {
            super();
            this.nombreLocalidad = nombreLocalidad;
    }

    public String getNombreLocalidad() {
            return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
            this.nombreLocalidad = nombreLocalidad;
    }

    public Provincia getProvincia() {
            return provincia;
    }

    public void setProvincia(Provincia provincia) {
            this.provincia = provincia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IndicadorRiesgoHistorial getIndicadorHistorial() {
        return indicadorHistorial;
    }

    public void setIndicadorHistorial(IndicadorRiesgoHistorial indicadorHistorial) {
        this.indicadorHistorial = indicadorHistorial;
    }
    	
}
