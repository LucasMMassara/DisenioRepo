package gestores;

import java.util.List;
import daos.DAOMarca;
import dto.MarcaDTO;
import java.util.ArrayList;
import logica.Marca;

public class GestorMarca {
            
    public Marca obtenerModelo(int idMarca){
        return new DAOMarca().get(idMarca).get();
    }
    
    public List<MarcaDTO> obtenerMarcas(){
        return marcasADTO(new DAOMarca().getAll());
    }
    
    public List<MarcaDTO> marcasADTO(List<Marca> marcas){
        List<MarcaDTO> marcasDTO = new ArrayList();
        for(Marca m:marcas){
            marcasDTO.add(marcaADTO(m));
        }
        return marcasDTO;
    }

    private MarcaDTO marcaADTO(Marca m) {
        return new MarcaDTO(m.getId(), m.getNombre());
    }
}
