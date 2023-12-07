package logica;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="hijos")

public class Hijo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Poliza poliza;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNac;
    
    @Enumerated(EnumType.STRING)
    private TipoSexo sexo;
    
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;
    
    public Hijo() {
            super();
            // TODO Auto-generated constructor stub
    }
    public Hijo(Date fechaNac, TipoSexo sexo, EstadoCivil estadoCivil) {
            super();
            this.fechaNac = fechaNac;
            this.sexo = sexo;
            this.estadoCivil = estadoCivil;
    }
    public Date getFechaNac() {
            return fechaNac;
    }
    public void setFechaNac(Date fechaNac) {
            this.fechaNac = fechaNac;
    }
    public TipoSexo getSexo() {
            return sexo;
    }
    public void setSexo(TipoSexo sexo) {
            this.sexo = sexo;
    }
    public EstadoCivil getEstadoCivil() {
            return estadoCivil;
    }
    public void setEstadoCivil(EstadoCivil estadoCivil) {
            this.estadoCivil = estadoCivil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Poliza getPoliza() {
        return poliza;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }
}
