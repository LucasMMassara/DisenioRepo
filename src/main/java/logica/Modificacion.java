package logica;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="modificaciones_polizas")

public class Modificacion implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String patente;
    private String numMotor;
    private String numChasis;
    private Integer kmPorAnio;
    
    @Enumerated
    private CantidadSiniestros cantidadSiniestros;
    
    private Boolean guardaEnGarage;
    private Boolean tuercasAntirrobo;
    private Boolean alarma;
    private Boolean dispositivoRastreo;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaMod;
    
    @ManyToMany
    private Set<Hijo> hijos;
    
    //TODO Falta el usuario
    
    public Modificacion() {
            super();
            // TODO Auto-generated constructor stub
    }

    public Modificacion(String patente, String numMotor, String numChasis, Integer kmPorAnio,
                    CantidadSiniestros cantidadSiniestros, Boolean guardaEnGarage, Boolean tuercasAntirrobo, Boolean alarma,
                    Boolean dispositivoRastreo, Date fechaMod) {
            super();
            this.patente = patente;
            this.numMotor = numMotor;
            this.numChasis = numChasis;
            this.kmPorAnio = kmPorAnio;
            this.cantidadSiniestros = cantidadSiniestros;
            this.guardaEnGarage = guardaEnGarage;
            this.tuercasAntirrobo = tuercasAntirrobo;
            this.alarma = alarma;
            this.dispositivoRastreo = dispositivoRastreo;
            this.fechaMod = fechaMod;
    }

    public String getPatente() {
            return patente;
    }

    public void setPatente(String patente) {
            this.patente = patente;
    }

    public String getNumMotor() {
            return numMotor;
    }

    public void setNumMotor(String numMotor) {
            this.numMotor = numMotor;
    }

    public String getNumChasis() {
            return numChasis;
    }

    public void setNumChasis(String numChasis) {
            this.numChasis = numChasis;
    }

    public Integer getKmPorAnio() {
            return kmPorAnio;
    }

    public void setKmPorAnio(Integer kmPorAnio) {
            this.kmPorAnio = kmPorAnio;
    }

    public CantidadSiniestros getCantidadSiniestros() {
            return cantidadSiniestros;
    }

    public void setCantidadSiniestros(CantidadSiniestros cantidadSiniestros) {
            this.cantidadSiniestros = cantidadSiniestros;
    }

    public Boolean getGuardaEnGarage() {
            return guardaEnGarage;
    }

    public void setGuardaEnGarage(Boolean guardaEnGarage) {
            this.guardaEnGarage = guardaEnGarage;
    }

    public Boolean getTuercasAntirrobo() {
            return tuercasAntirrobo;
    }

    public void setTuercasAntirrobo(Boolean tuercasAntirrobo) {
            this.tuercasAntirrobo = tuercasAntirrobo;
    }

    public Boolean getAlarma() {
            return alarma;
    }

    public void setAlarma(Boolean alarma) {
            this.alarma = alarma;
    }

    public Boolean getDispositivoRastreo() {
            return dispositivoRastreo;
    }

    public void setDispositivoRastreo(Boolean dispositivoRastreo) {
            this.dispositivoRastreo = dispositivoRastreo;
    }

    public Date getFechaMod() {
            return fechaMod;
    }

    public void setFechaMod(Date fechaMod) {
            this.fechaMod = fechaMod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Hijo> getHijos() {
        return hijos;
    }

    public void setHijos(Set<Hijo> hijos) {
        this.hijos = hijos;
    }
}
