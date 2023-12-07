package logica;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="polizas")

public class Poliza implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private double sumaAseguradaVehiculo;
    
    @Column(unique = true)
    private String numPoliza;
    
    @Enumerated(EnumType.STRING)
    private TipoPago formaDePago;
    
    @Enumerated(EnumType.STRING)
    private CantidadSiniestros cantidadSiniestros;
    
    @Enumerated(EnumType.STRING)  
    private EstadoPoliza estado;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inicioVigencia;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date finVigencia;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaEmision;

    //atributos de relacion

    
    @OneToOne(cascade = CascadeType.PERSIST)
    private Vehiculo vehiculoAsegurado;
    
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy="poliza")
    private List<Cuota> cuotas;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "poliza")
    private List<Hijo> hijosDeclarados;
    
    @OneToMany(mappedBy="poliza")
    private List<Modificacion> modificaciones;
    
    @ManyToOne
    private Cliente clientePoliza;
    
    @ManyToOne
    private PorcentajeCobertura cobertura;
    
    @ManyToOne
    private IndicadorRiesgo riesgoLocalidad;
    
    @ManyToOne
    private ValorSiniestro valorSiniestroUtilizados;
    
    @ManyToOne
    private ValoresGenerales valoresGeneralesUtilizados;
    
    @ManyToOne
    private ValorMedidaSeguridad valoresMedidasSeguridadUtilizados;
    
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy="poliza")
    private PremioYDescuentos premioydescuentos;
    
    //metodos de atributos base

    public PremioYDescuentos getPremioydescuentos() {
        return premioydescuentos;
    }

    public void setPremioydescuentos(PremioYDescuentos premioydescuentos) {
        this.premioydescuentos = premioydescuentos;
    }
    
    public TipoPago getFormaDePago() {
            return formaDePago;
    }
    public void setFormaDePago(TipoPago formaDePago) {
            this.formaDePago = formaDePago;
    }
    public CantidadSiniestros getCantidadSiniestros() {
            return cantidadSiniestros;
    }
    public void setCantidadSiniestros(CantidadSiniestros cantidadSiniestros) {
            this.cantidadSiniestros = cantidadSiniestros;
    }
    public Date getInicioVigencia() {
            return inicioVigencia;
    }
    public void setInicioVigencia(Date inicioVigencia) {
            this.inicioVigencia = inicioVigencia;
    }
    public Date getFinVigencia() {
            return finVigencia;
    }
    public void setFinVigencia(Date finVigencia) {
            this.finVigencia = finVigencia;
    }
    public String getNumPoliza() {
            return numPoliza;
    }
    public void setNumPoliza(String numPoliza) {
            this.numPoliza = numPoliza;
    }
    public Date getFechaEmision() {
            return fechaEmision;
    }
    public void setFechaEmision(Date fechaEmision) {
            this.fechaEmision = fechaEmision;
    }

    //metodos de atributos de relaciones

    public Vehiculo getVehiculoAsegurado() {
            return vehiculoAsegurado;
    }
    public void setVehiculoAsegurado(Vehiculo vehiculoAsegurado) {
            this.vehiculoAsegurado = vehiculoAsegurado;
    }
    public List<Cuota> getCuotas() {
            return cuotas;
    }
    public void setCuotas(List<Cuota> cuotas) {
            this.cuotas = cuotas;
    }
    public List<Modificacion> getModificaciones() {
            return modificaciones;
    }
    public void setModificaciones(List<Modificacion> modificaciones) {
            this.modificaciones = modificaciones;
    }
    public List<Hijo> getHijosDeclarados() {
            return hijosDeclarados;
    }
    public void setHijosDeclarados(List<Hijo> hijosDeclarados) {
            this.hijosDeclarados = hijosDeclarados;
    }
    public PorcentajeCobertura getCobertura() {
            return cobertura;
    }
    public void setCobertura(PorcentajeCobertura cobertura) {
            this.cobertura = cobertura;
    }
    public IndicadorRiesgo getRiesgoLocalidad() {
            return riesgoLocalidad;
    }
    public void setRiesgoLocalidad(IndicadorRiesgo riesgoLocalidad) {
            this.riesgoLocalidad = riesgoLocalidad;
    }
    public Cliente getClientePoliza() {
            return clientePoliza;
    }
    public void setClientePoliza(Cliente clientePoliza) {
            this.clientePoliza = clientePoliza;
    }

    public ValorSiniestro getValorSiniestroUtilizados() {
        return valorSiniestroUtilizados;
    }

    public void setValorSiniestroUtilizados(ValorSiniestro valorSiniestroUtilizados) {
        this.valorSiniestroUtilizados = valorSiniestroUtilizados;
    }

    public ValoresGenerales getValoresGeneralesUtilizados() {
        return valoresGeneralesUtilizados;
    }

    public void setValoresGeneralesUtilizados(ValoresGenerales valoresGeneralesUtilizados) {
        this.valoresGeneralesUtilizados = valoresGeneralesUtilizados;
    }

    public ValorMedidaSeguridad getValoresMedidasSeguridadUtilizados() {
        return valoresMedidasSeguridadUtilizados;
    }

    public void setValoresMedidasSeguridadUtilizados(ValorMedidaSeguridad valoresMedidasSeguridadUtilizados) {
        this.valoresMedidasSeguridadUtilizados = valoresMedidasSeguridadUtilizados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSumaAseguradaVehiculo() {
        return sumaAseguradaVehiculo;
    }

    public void setSumaAseguradaVehiculo(double sumaAseguradaVehiculo) {
        this.sumaAseguradaVehiculo = sumaAseguradaVehiculo;
    }

    public EstadoPoliza getEstado() {
        return estado;
    }

    public void setEstado(EstadoPoliza estado) {
        this.estado = estado;
    }
}
