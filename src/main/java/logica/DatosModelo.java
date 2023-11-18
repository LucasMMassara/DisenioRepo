package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="datosModelo")

public class DatosModelo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Modelo modelo;

    private Float sumaAsegurada;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicioVigencia;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFinVigencia;

    @OneToOne
    private AnioFabricacion anioFabricacion;
	
    public DatosModelo() {
            super();
            // TODO Auto-generated constructor stub
    }

    public DatosModelo(Float sumaAsegurada) {
            super();
            this.sumaAsegurada = sumaAsegurada;
    }

    public Float getSumaAsegurada() {
            return sumaAsegurada;
    }

    public void setSumaAsegurada(Float sumaAsegurada) {
            this.sumaAsegurada = sumaAsegurada;
    }

    public AnioFabricacion getAnioFabricacion() {
            return anioFabricacion;
    }

    public void setAnioFabricacion(AnioFabricacion anioFabricacion) {
            this.anioFabricacion = anioFabricacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }
	
}
