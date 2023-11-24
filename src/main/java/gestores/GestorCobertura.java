package gestores;

import daos.DAOCobertura;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import logica.Cobertura;

public class GestorCobertura {
    
    private List<Cobertura> coberturasDevueltas;
    
    public String[] obtenerCoberturas(String anio){
        
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
    


}
