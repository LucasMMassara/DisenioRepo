package gestores;

import dto.HijoDTO;
import java.util.List;

public class GestorPoliza {
    
    public boolean EdadValidaHijos(List<HijoDTO> hijos){
        
        boolean edadValida = true;
        GestorHijo gh =  new GestorHijo();
        
        for(HijoDTO h: hijos){
            if(!gh.edadHijoValida(h)){
                return false; //Como manejamos el retorno para saber a que hijo corresponde?
            }
        }
        
        return edadValida;
        //Podriamos retornar una lista de string que si es vacia entonces todos son validos, sino mostramos cuales no son validos.
    }
    
    

    
	

}
