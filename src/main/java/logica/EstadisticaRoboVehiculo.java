package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="estadistica_robo")

public class EstadisticaRoboVehiculo implements Serializable {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double porcentajeIndicador;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inicioVigencia;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date finVigencia;

    @ManyToOne(fetch = FetchType.LAZY)
    private Modelo modelo;

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
}
