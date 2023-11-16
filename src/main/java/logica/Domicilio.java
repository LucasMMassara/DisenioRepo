package logica;

public class Domicilio{
        

    private String id;
    private String calle;
    private String numero;
    private Departamento depto;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}
