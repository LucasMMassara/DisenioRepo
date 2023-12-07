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

    int obtenerIdPais(int idLocalidad) {
        int idprovincia = new GestorLocalidad().obtenerLocalidad(idLocalidad).getProvincia().getId();
        int idpais = new GestorProvincias().obtenerProvincia(idprovincia).getPais().getId();
        return idpais;
    }
}
