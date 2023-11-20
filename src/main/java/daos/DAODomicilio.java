/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import logica.Domicilio;
import persistenciajpa.DomicilioJpaController;

/**
 *
 * @author Lucas
 */
public class DAODomicilio extends DAOAbstract<Domicilio> {
    
    public void guardarDomicilio(Domicilio d){
        try{
            DomicilioJpaController domjpa = new DomicilioJpaController();
            domjpa.create(d);
        }
        catch(Exception e){
            System.out.println("Error al cargar el domicilio en la BBDD");
        }
    }
    
    public void actualizarDomicilio(Domicilio d){
        try{
            DomicilioJpaController domjpa = new DomicilioJpaController();
            domjpa.edit(d);
        }
        catch(Exception e){
            System.out.println("Error al cargar el domicilio en la BBDD" + e.getMessage());
        }
    }
    
    
    
}
