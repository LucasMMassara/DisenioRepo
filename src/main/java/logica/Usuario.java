package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")

public class Usuario extends Persona implements Serializable{
    
    private int id;

    private String usuario;

    private String contrasenia;

    @Enumerated(EnumType.STRING)
    private RolUsuario rol;

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
    
}
