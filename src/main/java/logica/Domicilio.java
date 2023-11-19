package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "domicilio")

public class Domicilio implements Serializable {
        
    @Id
    private int id;
    
    @OneToOne
    @MapsId
    @JoinColumn(name ="id")   
    private Cliente cliente;
    
    private String calle;
    private String numero;
    
    @OneToOne(mappedBy = "domicilio")
    private Departamento depto;
    
    @OneToOne
    private Localidad localidad;

    public Domicilio() {
            super();
            // TODO Auto-generated constructor stub
    }
    public Domicilio(String calle, String numero) {
            super();
            this.calle = calle;
            this.numero = numero;
    }

    public String getCalle() {
            return calle;
    }
    public void setCalle(String calle) {
            this.calle = calle;
    }
    public String getNumero() {
            return numero;
    }
    public void setNumero(String numero) {
            this.numero = numero;
    }
    public Departamento getDepto() {
            return depto;
    }
    public void setDepto(Departamento depto) {
            this.depto = depto;
    }
    public Localidad getLocalidad() {
            return localidad;
    }
    public void setLocalidad(Localidad localidad) {
            this.localidad = localidad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
