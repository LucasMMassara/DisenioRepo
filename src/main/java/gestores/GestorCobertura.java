package gestores;

import daos.DAOCobertura;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import logica.Cobertura;

public class GestorCobertura {
    
    private List<Cobertura> coberturasDevueltas;
    
    public List<String> obtenerCoberturas(String anio){
        
        DAOCobertura daocob = new DAOCobertura();
        
        if(obtenerAniosVehiculo(anio)>10){
            
            coberturasDevueltas = daocob.buscarResponsabilidadCivil();
            
            return obtenerStringCoberturas(coberturasDevueltas);
        }
        
        coberturasDevueltas = daocob.obtenerCoberturas();
        
        return obtenerStringCoberturas(coberturasDevueltas);
    }
    
    public Cobertura obtenerCoberturaUnica(String coberturaNombre){
        
        Cobertura coberturaBuscada = null;
        
        for(Cobertura c: coberturasDevueltas){    
            if(c.getDetalle().equals(coberturaNombre)){
                coberturaBuscada = c;
                break;
            }
        }
        
        return coberturaBuscada;
    }
    
    private List<String> obtenerStringCoberturas(List<Cobertura> cob){
        
        List<String> nombreCoberturas = new ArrayList();
        
        for(Cobertura c: cob){
            nombreCoberturas.add(c.getDetalle());
        }
        
        return nombreCoberturas;
    }
    
    private int obtenerAniosVehiculo(String anioVehiculo){
        
        Calendar cal = Calendar.getInstance();
        
        int anioActual = cal.get(Calendar.YEAR);
        int anioAuto = Integer.parseInt(anioVehiculo);
        
        return anioActual-anioAuto;
    }
    

}
