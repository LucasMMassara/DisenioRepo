package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="personas")

public abstract class Persona implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
        
    private String nombre;
    private String apellido;
    private String numeroDni;
    
    @Enumerated
    private TipoDocumento tipodni;
    
    @Enumerated
    private TipoSexo sexo;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;
    
    private String email;
    
    @Enumerated
    private EstadoCivil estadoCivil;
    
    private String anioRegistro;
    
    public String getNombre() {
            return nombre;
    }
    public void setNombre(String nombre) {
            this.nombre = nombre;
    }
    public String getApellido() {
            return apellido;
    }
    public void setApellido(String apellido) {
            this.apellido = apellido;
    }
    public String getNumeroDni() {
            return numeroDni;
    }
    public void setNumeroDni(String numeroDni) {
            this.numeroDni = numeroDni;
    }
    public TipoDocumento getTipodni() {
            return tipodni;
    }
    public void setTipodni(TipoDocumento tipodni) {
            this.tipodni = tipodni;
    }
    public TipoSexo getSexo() {
            return sexo;
    }
    public void setSexo(TipoSexo sexo) {
            this.sexo = sexo;
    }
    public Date getFechaNacimiento() {
            return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
    }
    public String getEmail() {
            return email;
    }
    public void setEmail(String email) {
            this.email = email;
    }
    public EstadoCivil getEstadoCivil() {
            return estadoCivil;
    }
    public void setEstadoCivil(EstadoCivil estadoCivil) {
            this.estadoCivil = estadoCivil;
    }
    public String getAnioRegistro() {
            return anioRegistro;
    }
    public void setAnioRegistro(String anioRegistro) {
            this.anioRegistro = anioRegistro;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
