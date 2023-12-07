package gestores;

import daos.DAOPoliza;
import dto.PolizaDTO;
import java.util.List;
import logica.EstadoPoliza;
import logica.Poliza;
import util.ConversorEnum;

public class GestorPoliza {
    
    public void altaPoliza(PolizaDTO pdto){      
        DAOPoliza daop = new DAOPoliza();
        Poliza poliza = DTOaObjeto(pdto);
        new GestorClientes().ActualizarConsideracionCliente(poliza.getClientePoliza().getId());
        daop.save(poliza);
    }
    
    public String generarNumPoliza(String nroSucursal, String documento, String chasis){
        String digitosAuto = digitosAuto(documento,chasis);
        return nroSucursal + digitosAuto + digitosRenovacion(nroSucursal + digitosAuto);
    }

    public List<Poliza> obtenerPolizasCliente(int id) {
        return new DAOPoliza().obtenerPolizasCliente(id);
    }
    
    private Poliza DTOaObjeto(PolizaDTO pdto){
        
        Poliza poliza = new Poliza();
        
        //Datos ingresados
        poliza.setCantidadSiniestros(ConversorEnum.convertirCantSiniestros(pdto.getCantidadSiniestros()));
        poliza.setEstado(EstadoPoliza.GENERADA);
        poliza.setFechaEmision(pdto.getFechaEmision());
        poliza.setInicioVigencia(pdto.getInicioVigenciaPoliza());
        poliza.setFinVigencia(pdto.getFinVigencia());
        poliza.setFormaDePago(ConversorEnum.convertirStringTipoPago(pdto.getFormaPago()));
        poliza.setSumaAseguradaVehiculo(Double.parseDouble(pdto.getSumaAsegurada()));
        poliza.setNumPoliza(pdto.getNumPoliza());
        
        //Derecho de  emision premio y descuento
        poliza.setPremioydescuentos(new GestorPremioYDescuentos().convertirDTOAClase(pdto.getPyd()));
        
        //Valores de fk 
        poliza.setRiesgoLocalidad(new GestorLocalidad().obtenerIndicadorRiesgo(pdto.getLocalidadRiesgo().getId()));
        poliza.setCobertura(new GestorCobertura().obtenerPorcentajeCoberturaPorId(pdto.getCobertura().getIdCobertura()));
        
        //Generamos el vehiculo        
        poliza.setVehiculoAsegurado(new GestorVehiculo().DTOaVehiculo(pdto.getVehiculo()));
        
        //Generamos las cuotas
        poliza.setCuotas(new GestorCuotas().DTOaCuotas(pdto.getListaCuotas()));
        
        //Generamos el cliente
        poliza.setClientePoliza(new GestorClientes().buscarCliente(pdto.getCliente()));
        
        //Asignamos los valores con los que se realizo el calculo
        
        poliza.setValorSiniestroUtilizados(new GestorValorSiniestros().buscarValorPorId(pdto.getPyd().getIdValorSiniestros()));
        poliza.setValoresGeneralesUtilizados(new GestorDatosGenerales().buscarValorPorId(pdto.getPyd().getIdDatosGenerales()));
        poliza.setValoresMedidasSeguridadUtilizados(new GestorValorMedidasSeguridad().buscarValorPorId(pdto.getPyd().getIdValorMedidaSeguridad()));
        
        //Generamos lista de hijos
        poliza.setHijosDeclarados(new GestorHijo().DTOaHijos(pdto.getListaHijos(),poliza));
        
        return poliza;
    }

    public boolean existePolizaVigente(String patente, String numMotor, String chasis) {

        //Si es vacio devuelve false, si contiene poliza devuelve true.
        List<Poliza> polizasDatos = (new DAOPoliza()).buscarPolizaNumMotorNumChasisPatente(patente, numMotor, chasis);

        if (polizasDatos.isEmpty()) {
            return false;
        }

        for (Poliza p : polizasDatos) {
            if (p.getEstado() == EstadoPoliza.ACTIVA) {
                return true;
            }
        }

        return false;
    }
    
    private String digitosAuto(String documento, String chasis) {
        return documento.substring(documento.length() - 2) + chasis.substring(chasis.length() - 5);
    }

    private String digitosRenovacion(String comienzoNroPoliza) {
        
        int nroPolizas = new DAOPoliza().obtenerVersionesPoliza(comienzoNroPoliza).size();
        
        if(nroPolizas < 10){
            return ""+ 0 + nroPolizas;
        } else{
            return "" + nroPolizas;
        }
    }
    
}
