package logica;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")

public class Usuario extends Persona implements Serializable{
    
    private int id;

    private String usuario;

    private String contrasenia;

    @Enumerated(EnumType.STRING)
    private RolUsuario rol;
    
    @ManyToOne
    private Sucursal sucursal;
    
    @OneToOne(cascade = CascadeType.ALL) //CUIDADO
    private ConfiguracionesUsuario cu;
    
    public Usuario() {
            super();
            // TODO Auto-generated constructor stub
    }
    public Usuario(String usuario, String contrasenia) {
            super();
            this.usuario = usuario;
            this.contrasenia = contrasenia;
    }
    public String getUsuario() {
            return usuario;
    }
    public void setUsuario(String usuario) {
            this.usuario = usuario;
    }
    public String getContrasenia() {
            return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
            this.contrasenia = contrasenia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public ConfiguracionesUsuario getCu() {
        return cu;
    }

    public void setCu(ConfiguracionesUsuario cu) {
        this.cu = cu;
    }
    
}
