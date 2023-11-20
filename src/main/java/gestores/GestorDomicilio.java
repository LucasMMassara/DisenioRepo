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
        domNuevo.setDepto(crearDepartamento(dom));
        domNuevo.setLocalidad(dom.getLocalidad());
        
        //Subir a BBDD
        
        return domNuevo;
    }
    
    public Departamento crearDepartamento(DomicilioDTO dom){
        Departamento dep = null;
        
        //subiar a BBDD
        
        return dep;
    }
    
}
