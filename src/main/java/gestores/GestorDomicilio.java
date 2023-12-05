/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import daos.DAODomicilio;
import dto.DomicilioDTO;
import logica.Departamento;
import logica.Domicilio;

/**
 *
 * @author Lucas
 */
public class GestorDomicilio {

    public Domicilio crearDomicilio(DomicilioDTO dom) {
        
        Domicilio domNuevo = new Domicilio(dom.getCalle(),dom.getNumero());
        domNuevo.setLocalidad(dom.getLocalidad());
        domNuevo.setDepto(crearDepto(dom.getDpto(),dom.getNumero()));
        
        DAODomicilio daoDom = new DAODomicilio();
        
        daoDom.guardarDomicilio(domNuevo);
        
        return domNuevo;
    }

    private Departamento crearDepto(String dpto, String numero) {
        if(!(dpto== null) && !(numero == null)){
        Departamento d = new Departamento();
        d.setNumeroDpto(numero);
        d.setNumPisoDepto(dpto);
        return d;
        }
        return null;
    }
    
}
