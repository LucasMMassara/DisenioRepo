/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import daos.DAOMarca;
import daos.DAOModelo;
import java.util.Arrays;
import java.util.List;
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
    
    private void cargarMarcas(){
        List<String> list = Arrays.asList("Ford","Chevrolet");
        for(String marca:list){
            Marca m = new Marca(marca);
            daomarc.save(m);
        }
    }
    
    private void cargarModelo(Marca ford, Marca chevrolet){
        List<String> mford = Arrays.asList("Ka","Mustang");
        List<String> mchevro = Arrays.asList("F100","Corsa");
        
        for(String modelo:mford){
            Modelo m = new Modelo(modelo,ford);
            daomod.save(m);
        }
        
        for(String modelo:mchevro){
            Modelo m = new Modelo(modelo,chevrolet);
            daomod.save(m);
        }
        
    }
    
}
