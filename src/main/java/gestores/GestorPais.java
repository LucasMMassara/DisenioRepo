/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import daos.DAOPais;
import dto.PaisDTO;
import java.util.ArrayList;
import java.util.List;
import logica.Pais;

public class GestorPais {
    
    
    public List<PaisDTO> getPaisesDTO(){
        return listaPaisADTO(ObtenerPaises());
    }
    
    private List<Pais> ObtenerPaises(){
        return new DAOPais().getAll();
    }
    
    private List<PaisDTO> listaPaisADTO(List<Pais> paises){
        
        List<PaisDTO> paisesDTO = new ArrayList();
        
        for(Pais p:paises){
            paisesDTO.add(paisADTO(p));
        }
        
        return paisesDTO;
    }
    
    private PaisDTO paisADTO(Pais p){
        
        return new PaisDTO(p.getId(), p.getNombre());
        
    }
}
