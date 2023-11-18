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

    private String nombre;
    private String numeroDpto;
    private String piso;

    public Departamento() {
            super();
            // TODO Auto-generated constructor stub
    }
    public Departamento(String nombre, String numeroDpto, String piso) {
            super();
            this.nombre = nombre;
            this.numeroDpto = numeroDpto;
            this.piso = piso;
    }

    public String getNombre() {
            return nombre;
    }
    public void setNombre(String nombre) {
            this.nombre = nombre;
    }
    public String getNumeroDpto() {
            return numeroDpto;
    }
    public void setNumeroDpto(String numeroDpto) {
            this.numeroDpto = numeroDpto;
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
