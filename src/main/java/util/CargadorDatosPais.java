package util;

import daos.DAOIndicadorRiesgo;
import daos.DAOLocalidad;
import daos.DAOPais;
import daos.DAOProvincia;
import gestores.GestorIndicadorRiesgo;
import java.util.List;
import java.util.Random;
import logica.IndicadorRiesgo;
import logica.Localidad;
import logica.Pais;
import logica.Provincia;


public class CargadorDatosPais {
    
    DAOPais daop = new DAOPais();
    DAOProvincia daoprov = new DAOProvincia();
    DAOLocalidad daoloc = new DAOLocalidad();
    
    public void cargarPackPaisesLocIndic(){
        //Carga paises con provincias localidades y sus indicadores de riesgo
        cargarPaises();
        cargarProvincias();
        cargarLocalidades();
        cargarIndicadoresRiesgo();
    }
    
    private void cargarPaises(){
        
        Pais arg = new Pais();
        arg.setNombre("Argentina");
        
        daop.save(arg);
        
        Pais uru = new Pais();
        uru.setNombre("Uruguay");
        
        daop.save(uru);
    }
    
    private void cargarProvincias(){
        Pais arg = daop.get(1).get();
        Pais uru = daop.get(2).get();
        
        Provincia sfe = new Provincia(arg, "Santa Fe"); 
        daoprov.save(sfe);
        Provincia bsas = new Provincia(arg, "Buenos Aires");
        daoprov.save(bsas);
        
        Provincia mvid = new Provincia(uru, "Monte Video");
        daoprov.save(mvid);
        Provincia mald = new Provincia(uru, "Maldonado");
        daoprov.save(mald);        
    }
    
    private void cargarLocalidades(){
        Provincia sfe = daoprov.get(1).get();
        Provincia bsas = daoprov.get(2).get();
        Provincia mvid = daoprov.get(3).get();
        Provincia mald = daoprov.get(4).get();
        
        Localidad cap = new Localidad(sfe,"La capital");
        daoloc.save(cap);
        Localidad ros = new Localidad(sfe,"Rosario");
        daoloc.save(ros);
        Localidad per = new Localidad(bsas,"Pergamino");
        daoloc.save(per);
        Localidad mdp = new Localidad(bsas,"Mar del Plata");
        daoloc.save(mdp);
        Localidad cv = new Localidad(mvid,"Ciudad Vieja");
        daoloc.save(cv);
        Localidad pc = new Localidad(mvid,"Punta Carretas");
        daoloc.save(pc);
        Localidad pde = new Localidad(mald,"Punta del Este");
        daoloc.save(pde);
        Localidad sca = new Localidad(mald, "San Carlos");
        daoloc.save(sca);
    }
    
    private void cargarIndicadoresRiesgo(){
        
        GestorIndicadorRiesgo gir = new GestorIndicadorRiesgo();
        
        DAOIndicadorRiesgo daoir = new DAOIndicadorRiesgo();
        
        List<Localidad> localidades = daoloc.getAll();
        
        Random rand = new Random();
        
        for(Localidad l:localidades){
            Double double_random = rand.nextDouble();
            
            IndicadorRiesgo ir = gir.crearIndicadorRiesgo(l, double_random);
            
            daoir.save(ir);
            
            l.setIndicadorActual(ir);
            
            daoloc.update(l);
        }
    }
    
}
