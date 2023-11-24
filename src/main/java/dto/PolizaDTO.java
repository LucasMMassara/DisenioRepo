package dto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logica.*;

public class PolizaDTO {
	
        private IndicadorRiesgo indicadorRiesgo;
	private PorcentajeCobertura cobertura;
	private Date inicioVigenciaPoliza;
	private String formaPago;
        private ClienteDTO cliente;
        private ArrayList<HijoDTO> listaHijos;
        private ArrayList<CuotaDTO> listaCuotas;
        private String sumaAsegurada;
        private Date finVigencia;
        private Date fechaEmision;
        private ValoresActualesCalculo valoresCalculo;
        private VehiculoDTO vehiculo;
        private String cantidadSiniestros;
        private String numPoliza;
        private PremioYDescuentos pyd;

    public PremioYDescuentos getPyd() {
        return pyd;
    }

    public void setPyd(PremioYDescuentos pyd) {
        this.pyd = pyd;
    }
        
        

    public String getNumPoliza() {
        return numPoliza;
    }

    public void setNumPoliza(String numPoliza) {
        this.numPoliza = numPoliza;
    }

    public IndicadorRiesgo getIndicadorRiesgo() {
        return indicadorRiesgo;
    }

    public void setIndicadorRiesgo(IndicadorRiesgo indicadorRiesgo) {
        this.indicadorRiesgo = indicadorRiesgo;
    }

    public String getCantidadSiniestros() {
        return cantidadSiniestros;
    }

    public void setCantidadSiniestros(String cantidadSiniestros) {
        this.cantidadSiniestros = cantidadSiniestros;
    }
    
    

    public String getSumaAsegurada() {
        return sumaAsegurada;
    }

    public void setSumaAsegurada(String sumaAsegurada) {
        this.sumaAsegurada = sumaAsegurada;
    }
    
    public PorcentajeCobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(PorcentajeCobertura cobertura) {
        this.cobertura = cobertura;
    }

    public Date getInicioVigenciaPoliza() {
        return inicioVigenciaPoliza;
    }

    public void setInicioVigenciaPoliza(Date inicioVigenciaPoliza) {
        this.inicioVigenciaPoliza = inicioVigenciaPoliza;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public ArrayList<HijoDTO> getListaHijos() {
        return listaHijos;
    }

    public void setListaHijos(ArrayList<HijoDTO> listaHijos) {
        this.listaHijos = listaHijos;
    }

    public ArrayList<CuotaDTO> getListaCuotas() {
        return listaCuotas;
    }

    public void setListaCuotas(ArrayList<CuotaDTO> listaCuotas) {
        this.listaCuotas = listaCuotas;
    }

    public Date getFinVigencia() {
        return finVigencia;
    }

    public void setFinVigencia(Date finVigencia) {
        this.finVigencia = finVigencia;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public ValoresActualesCalculo getValoresCalculo() {
        return valoresCalculo;
    }

    public void setValoresCalculo(ValoresActualesCalculo valoresCalculo) {
        this.valoresCalculo = valoresCalculo;
    }

    public VehiculoDTO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = vehiculo;
    }   

    @Override
    public String toString() {
        return "PolizaDTO{" + "indicadorRiesgo=" + indicadorRiesgo + ", cobertura=" + cobertura + ", inicioVigenciaPoliza=" + inicioVigenciaPoliza + ", formaPago=" + formaPago + ", cliente=" + cliente + ", listaHijos=" + listaHijos + ", listaCuotas=" + listaCuotas + ", sumaAsegurada=" + sumaAsegurada + ", finVigencia=" + finVigencia + ", fechaEmision=" + fechaEmision + ", valoresCalculo=" + valoresCalculo + ", vehiculo=" + vehiculo + '}';
    }

    public PremioYDescuentos getPyd() {
        
    }
        
}
