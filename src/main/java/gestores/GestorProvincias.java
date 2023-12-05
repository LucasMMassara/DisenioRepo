package gestores;

import daos.DAOProvincia;
import dto.ProvinciaDTO;
import java.util.ArrayList;
import java.util.List;
import logica.Provincia;

public class GestorProvincias {
    
    public List<ProvinciaDTO> getProvinciasDTO(int idPais){
        return listaProvinciasADTO(ObtenerProvincias(idPais));
    }
    
    private List<Provincia> ObtenerProvincias(int idPais){
        return new DAOProvincia().buscarPorIdPais(idPais);
    }
    
    private List<ProvinciaDTO> listaProvinciasADTO(List<Provincia> provincias){
        
        List<ProvinciaDTO> provinciasDTO = new ArrayList();
        
        for(Provincia p:provincias){
            provinciasDTO.add(provinciaADTO(p));
        }
        
        return provinciasDTO;
    }
    
    private ProvinciaDTO provinciaADTO(Provincia p){
        return new ProvinciaDTO(p.getId(), p.getNombreProvincia());
    }
    
}
