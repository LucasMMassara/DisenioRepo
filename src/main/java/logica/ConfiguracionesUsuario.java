package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "configuraciones")

public class ConfiguracionesUsuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int cantClientesBusqueda;

    public ConfiguracionesUsuario() {
    }

    public ConfiguracionesUsuario(int cantClientesBusqueda) {
        this.cantClientesBusqueda = cantClientesBusqueda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantClientesBusqueda() {
        return cantClientesBusqueda;
    }

    public void setCantClientesBusqueda(int cantClientesBusqueda) {
        this.cantClientesBusqueda = cantClientesBusqueda;
    }
    
    
    
    
    
}
