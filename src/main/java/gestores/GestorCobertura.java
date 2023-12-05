package gestores;

import daos.DAOCobertura;
import dto.CoberturaDTO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import logica.Cobertura;
import logica.PorcentajeCobertura;

public class GestorCobertura {
    
    public Cobertura obtenerCoberturaPorId(int idCobertura){
        return new DAOCobertura().get(idCobertura).get();
    }
    
    public String[] obtenerCoberturas(String anio){
        
        DAOCobertura daocob = new DAOCobertura();
        
        List<Cobertura> coberturasDevueltas; 
        
        if(obtenerAniosVehiculo(anio)>10){
            
            coberturasDevueltas = daocob.buscarResponsabilidadCivil();
            
            return obtenerStringCoberturas(coberturasDevueltas);
        }
        
        coberturasDevueltas = daocob.getAll();
        
        return obtenerStringCoberturas(coberturasDevueltas);
    }
    
    public Cobertura obtenerCoberturaUnica(String coberturaNombre){
        
        DAOCobertura daocob = new DAOCobertura();
        Cobertura coberturaBuscada = null;
        List<Cobertura> coberturasDevueltas; 
        coberturasDevueltas = daocob.getAll();
        
        for(Cobertura c: coberturasDevueltas){    
            if(c.getDetalle().equals(coberturaNombre)){
                coberturaBuscada = c;
                break;
            }
        }
        
        return coberturaBuscada;
    }
    
    private CoberturaDTO CoberturaADTO(Cobertura cobertura){
        return new CoberturaDTO(cobertura.getDetalle(),cobertura.getId());
    }
    
    public CoberturaDTO obtenerCoberturaDTOUnica(String coberturaNombre){
        return CoberturaADTO(obtenerCoberturaUnica(coberturaNombre));
    }
    
    private String[] obtenerStringCoberturas(List<Cobertura> cob){
        
        String[] nombreCoberturas = new String[cob.size()];
        for (int i = 0; i < cob.size(); i++) {
            nombreCoberturas[i] = cob.get(i).getDetalle();
        }
        
        return nombreCoberturas;
    }
    
    private int obtenerAniosVehiculo(String anioVehiculo){
        
        Calendar cal = Calendar.getInstance();
        
        int anioActual = cal.get(Calendar.YEAR);
        int anioAuto = Integer.parseInt(anioVehiculo);
        
        return anioActual-anioAuto;
    }

    public PorcentajeCobertura obtenerPorcentajeCoberturaPorId(int idCobertura) {
        return obtenerCoberturaPorId(idCobertura).getPorcentajeActual();
    }
    


}
