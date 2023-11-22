package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="anio")

public class AnioFabricacion implements Serializable {
    
    @Id
    private int anio;

    public AnioFabricacion() {
            super();
            // TODO Auto-generated constructor stub
    }

    public AnioFabricacion(int anio) {
            super();
            this.anio = anio;
    }

    public int getAnio() {
            return anio;
    }

    public void setAnio(int anio) {
            this.anio = anio;
    }
	
}
