package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String numPisoDepto;

    public Departamento() {
            super();
            // TODO Auto-generated constructor stub
    }

    public Departamento(Domicilio domicilio, String numDpto, String numPisoDepto) {
        this.domicilio = domicilio;
        this.numDpto = numDpto;
        this.numPisoDepto = numPisoDepto;
    }
    
    public String getNumeroDpto() {
            return numDpto;
    }
    public void setNumeroDpto(String numeroDpto) {
            this.numDpto = numeroDpto;
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

    public String getNumPisoDepto() {
        return numPisoDepto;
    }

    public void setNumPisoDepto(String numPisoDepto) {
        this.numPisoDepto = numPisoDepto;
    }
    
}
