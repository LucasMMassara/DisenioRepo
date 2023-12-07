package util;

import daos.DAOCobertura;
import daos.DAOPorcentajeCobertura;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import logica.Cobertura;
import logica.PorcentajeCobertura;

public class CargadorDatosCobertura {
    
    DAOCobertura daocob = new DAOCobertura();
    DAOPorcentajeCobertura daopor = new DAOPorcentajeCobertura();
    
    public void agregarCoberturas(){
        
        List<String> list = Arrays.asList("Responsabilidad Civil", "Resp. Civil, Robo o incendio total", "Todo total","Terceros completo","Todo riesgo con franquicia");
        Random rand = new Random();
        
        for(String nombre:list){
            Double porcentajeGenerado = rand.nextDouble();
            Date d = new Date();
            
            Cobertura cob = new Cobertura(nombre);
            daocob.save(cob);
            
            PorcentajeCobertura pc = new PorcentajeCobertura(porcentajeGenerado,d,cob);
            daopor.save(pc);
            
            cob.setPorcentajeActual(pc);
            daocob.update(cob);
        }
    }
    
}
