package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "domicilio")

public class Domicilio implements Serializable {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /*
    @OneToOne
    @MapsId
    @JoinColumn(name ="id")   
    private Cliente cliente;*/
    
    private String calle;
    private String numeroCalle;
    private String numeroDepto;
    private String pisoDepto;
    
    /*@OneToOne(mappedBy = "domicilio")
    private Departamento depto;*/
    
    @OneToOne
    private Localidad localidad;

    public Domicilio() {
            super();
            // TODO Auto-generated constructor stub
    }
    public Domicilio(String calle, String numero, String depto, String piso) {
            super();
            this.calle = calle;
            this.numeroCalle = numero;
            this.numeroDepto = depto;
            this.pisoDepto = piso;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(String numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public String getNumeroDepto() {
        return numeroDepto;
    }

    public void setNumeroDepto(String numeroDepto) {
        this.numeroDepto = numeroDepto;
    }

    public String getPisoDepto() {
        return pisoDepto;
    }

    public void setPisoDepto(String pisoDepto) {
        this.pisoDepto = pisoDepto;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
