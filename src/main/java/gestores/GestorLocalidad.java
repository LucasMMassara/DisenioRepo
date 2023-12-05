package gestores;

import daos.DAOLocalidad;
import dto.LocalidadDTO;
import java.util.ArrayList;
import java.util.List;
import logica.Localidad;

public class GestorLocalidad{
    
    public List<LocalidadDTO> getLocalidadesDTO(int idProvincia){
        return listaLocalidadesADTO(ObtenerLocalidades(idProvincia));
    }
    
    private List<Localidad> ObtenerLocalidades(int idProvincia){
        return new DAOLocalidad().buscarPorIdProvincia(idProvincia);
    }
    
    private List<LocalidadDTO> listaLocalidadesADTO(List<Localidad> localidades){
        
        List<LocalidadDTO> localidadesDTO = new ArrayList();
        
        for(Localidad l:localidades){
            localidadesDTO.add(provinciaADTO(l));
        }
        
        return localidadesDTO;
    }
    
    private LocalidadDTO provinciaADTO(Localidad l){
        return new LocalidadDTO(l.getId(),l.getNombreLocalidad());
    }
	
}
