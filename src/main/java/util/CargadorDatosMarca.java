/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import daos.DAOAnioFabricacion;
import daos.DAOMarca;
import daos.DAOModelo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import logica.AnioFabricacion;
import logica.Marca;
import logica.Modelo;

/**
 *
 * @author Lucas
 */
public class CargadorDatosMarca {
    
    //TODO TERMINAR CARGAR DATOS MARCA
    
    DAOMarca daomarc = new DAOMarca();
    DAOModelo daomod = new DAOModelo();
    DAOAnioFabricacion daoanio = new DAOAnioFabricacion();
    
    public void cargarTodo(){
        cargarAnios();
        cargarMarcas();
        cargarModelo(daomarc.get(1).get(), daomarc.get(2).get());
    }
    
    private void cargarMarcas(){
        List<String> list = Arrays.asList("Ford","Chevrolet");
        for(String marca:list){
            Marca m = new Marca(marca);
            daomarc.save(m);
        }
    }
    
    private void cargarModelo(Marca ford, Marca chevrolet){
        List<String> mford = Arrays.asList("Ka","Mustang");
        List<String> mchevro = Arrays.asList("Camaro","Corsa");
        
        for(String modelo:mford){
            Modelo m = new Modelo(modelo,ford);
            m.setAnioModelo(daoanio.getAll());
            daomod.save(m);
        }
        
        for(String modelo:mchevro){
            Modelo m = new Modelo(modelo,chevrolet);
            m.setAnioModelo(daoanio.getAll());
            daomod.save(m);
        }
        
    }
    
    private void cargarAnios(){
        
        List<AnioFabricacion> anios = new ArrayList();
        
        for(int i=2006; i<2024; i++){
            AnioFabricacion af = new AnioFabricacion(i);
            anios.add(af);
        }
        
        for(AnioFabricacion a:anios){
            try{
                daoanio.save(a);
            }
            catch(Exception e){
                System.out.println("Sos un boludo lucas");
            }
            
        }
        
    }
    
}
