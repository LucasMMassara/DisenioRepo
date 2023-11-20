/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import daos.DAODomicilio;
import dto.DomicilioDTO;
import logica.Departamento;
import logica.Domicilio;
import logica.Localidad;
import persistenciajpa.DomicilioJpaController;
import persistenciajpa.LocalidadJpaController;

/**
 *
 * @author Lucas
 */
public class GestorDomicilio {

    public Domicilio crearDomicilio(DomicilioDTO dom) {
        
        Domicilio domNuevo = new Domicilio(dom.getCalle(),dom.getNumero(),dom.getDpto(),dom.getPiso());
        domNuevo.setLocalidad(dom.getLocalidad());
        
        DAODomicilio daoDom = new DAODomicilio();
        daoDom.guardarDomicilio(domNuevo);
        
        return domNuevo;
    }
    
    /*public Departamento crearDepartamento(DomicilioDTO dom, Domicilio domicilio){
        
        if(!(dom.getPiso()== null) && !(dom.getDpto() == null)){
            Departamento dep = new Departamento(dom.getPiso(),dom.getDpto());
            dep.setDomicilio(domicilio);
            
            //Subir a BBDD si es que no es necesario agregar el domicilio
            try{
                DepartamentoJpaController deptojpa = new DepartamentoJpaController();
                deptojpa.create(dep);
            }
            catch(Exception e){
                System.out.println("Error en la carga de BBDD de departamento");
            }
            
            return dep;
            
        }
        else{
            return null;
        }
    }*/
    
}
