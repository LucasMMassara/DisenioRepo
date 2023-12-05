package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "provincia")

public class Provincia implements Serializable {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreProvincia;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pais pais;

    public Provincia(Pais pais, String nombre) {
        super();
        this.pais = pais;
        this.nombreProvincia = nombre;
    }
    
    public Provincia(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Provincia{" + "id=" + id + ", nombreProvincia=" + nombreProvincia + ", pais=" + pais.getNombre() + '}';
    }
}
