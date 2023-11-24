package logica;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "departamento")

public class Departamento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne
    private Domicilio domicilio;
    private String numDpto;
    private String piso;

    public Departamento() {
            super();
            // TODO Auto-generated constructor stub
    }

    public Departamento(Domicilio domicilio, String numeroDpto, String piso) {
        this.domicilio = domicilio;
        this.numDpto = numeroDpto;
        this.piso = piso;
    }

    public Departamento(String piso, String nombredpto) {
        this.numDpto = nombredpto;
        this.piso = piso;
    }
    
    public String getNumeroDpto() {
            return numDpto;
    }
    public void setNumeroDpto(String numeroDpto) {
            this.numDpto = numeroDpto;
    }
    public String getPiso() {
            return piso;
    }
    public void setPiso(String piso) {
            this.piso = piso;
    }

    public Domicilio getDomicilio() {
    return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
