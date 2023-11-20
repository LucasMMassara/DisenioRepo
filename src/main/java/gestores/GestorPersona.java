/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import daos.DAOPersona;
import java.util.List;
import logica.Persona;
import logica.TipoDocumento;

/**
 *
 * @author Lucas
 */
class GestorPersona {
    
    public List<Persona> buscarPersonas(String tipodoc, String numDni){
        DAOPersona daop = new DAOPersona();
        return daop.buscarPersonas(tipodoc,numDni);
    }
}
