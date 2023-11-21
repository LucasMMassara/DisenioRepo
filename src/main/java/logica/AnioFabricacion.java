package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="anio")

public class AnioFabricacion implements Serializable {
    
    @Id
    private String anio;

    public AnioFabricacion() {
            super();
            // TODO Auto-generated constructor stub
    }

    public AnioFabricacion(String anio) {
            super();
            this.anio = anio;
    }

    public String getAnio() {
            return anio;
    }

    public void setAnio(String anio) {
            this.anio = anio;
    }
	
}
