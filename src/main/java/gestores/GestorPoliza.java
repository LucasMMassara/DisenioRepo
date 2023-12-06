package gestores;

import daos.DAOPoliza;
import dto.HijoDTO;
import dto.PolizaDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import logica.EstadoPoliza;
import logica.Poliza;
import util.ConversorEnum;

public class GestorPoliza {
    
    public void cargarPoliza(PolizaDTO pdto){      
        DAOPoliza daop = new DAOPoliza();
        daop.save(DTOaObjeto(pdto));
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
        
        //Generamos lista de hijos
        poliza.setHijosDeclarados(new GestorHijo().DTOaHijos(pdto.getListaHijos()));
        
        //Generamos las cuotas
        poliza.setCuotas(new GestorCuotas().DTOaCuotas(pdto.getListaCuotas()));
        
        //Generamos el cliente
        poliza.setClientePoliza(new GestorClientes().buscarCliente(pdto.getCliente()));
        
        //Asignamos los valores con los que se realizo el calculo
        
        poliza.setValorSiniestroUtilizados(new GestorValorSiniestros().buscarValorPorId(pdto.getPyd().getIdValorSiniestros()));
        poliza.setValoresGeneralesUtilizados(new GestorDatosGenerales().buscarValorPorId(pdto.getPyd().getIdDatosGenerales()));
        poliza.setValoresMedidasSeguridadUtilizados(new GestorValorMedidasSeguridad().buscarValorPorId(pdto.getPyd().getIdValorMedidaSeguridad()));
        
        return poliza;
    }

    public ArrayList<String> EdadValidaHijos(List<HijoDTO> hijos) {

        ArrayList<String> numHijos = new ArrayList<>();

        GestorHijo gh = new GestorHijo();
        
        int numHijo = 0;

        for (HijoDTO h : hijos) {
            
            if (!gh.edadHijoValida(h)) {
                numHijos.add("Hijo" + numHijo);
            }
            numHijo++;
        }
        return numHijos;
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
    
    public String generarNumPoliza(){
        
        Random rand = new Random();
        
        String numero = rand.nextLong(100000, 999999) +"";
        
        numero = numero + rand.nextLong(1000000, 9999999);
        
        return numero;
    }

    public List<Poliza> obtenerPolizasCliente(int id) {

            
    }
    
}
