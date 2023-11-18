package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="anio")

public class AnioFabricacion implements Serializable {
    
    @Id
    private Integer anio;

    public AnioFabricacion() {
            super();
            // TODO Auto-generated constructor stub
    }

    public AnioFabricacion(Integer anio) {
            super();
            this.anio = anio;
    }

    public Integer getAnio() {
            return anio;
    }

    public void setAnio(Integer anio) {
            this.anio = anio;
    }
	
}
