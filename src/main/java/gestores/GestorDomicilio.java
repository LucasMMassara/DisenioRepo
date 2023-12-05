package gestores;

import daos.DAODomicilio;
import dto.DomicilioDTO;
import logica.Departamento;
import logica.Domicilio;

public class GestorDomicilio {

    public Domicilio crearDomicilio(DomicilioDTO dom) {
        
        GestorLocalidad gl = new GestorLocalidad();
        
        Domicilio domNuevo = new Domicilio(dom.getCalle(),dom.getNumero());
        domNuevo.setLocalidad(gl.obtenerLocalidad(dom.getLocalidad().getId()));
        domNuevo.setDepto(crearDepto(dom.getDpto(),dom.getNumero()));
        
        DAODomicilio daoDom = new DAODomicilio();
        
        daoDom.save(domNuevo);
        
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
