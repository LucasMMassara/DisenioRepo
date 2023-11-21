package gestores;

import daos.DAOCobertura;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import logica.Cobertura;

public class GestorCobertura {
    
    public List<String> obtenerCoberturas(String anio){
        
        List<Cobertura> cob;
        
        DAOCobertura daocob = new DAOCobertura();
        
        if(obtenerAniosVehiculo(anio)>10){
            cob = daocob.buscarResponsabilidadCivil();
            return obtenerStringCoberturas(cob);
        }
        
        return obtenerStringCoberturas(daocob.obtenerCoberturas());
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
