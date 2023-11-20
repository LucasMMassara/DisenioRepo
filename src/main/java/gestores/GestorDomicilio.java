/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import dto.DomicilioDTO;
import logica.Departamento;
import logica.Domicilio;

/**
 *
 * @author Lucas
 */
public class GestorDomicilio {

    public Domicilio crearDomicilio(DomicilioDTO dom) {
        
        Domicilio domNuevo = new Domicilio();
        
        domNuevo.setCalle(dom.getCalle());
        domNuevo.setNumero(dom.getNumero());
        domNuevo.setDepto(crearDepartamento(dom)); //ver si tengo que asignar el domicilio al depto tambien y updatearlo
        domNuevo.setLocalidad(dom.getLocalidad());
        
        //Subir a BBDD
        
        return domNuevo;
    }
    
    public Departamento crearDepartamento(DomicilioDTO dom){
        
        if(!(dom.getPiso()== null) && !(dom.getDpto() == null)){
            Departamento dep = new Departamento(dom.getPiso(),dom.getDpto());
            //Subir a BBDD si es que no es necesario agregar el domicilio
            return dep;
            
        }
        else{
            return null;
        }
    }
    
}
