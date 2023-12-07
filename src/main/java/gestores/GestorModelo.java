package gestores;

import daos.DAOAnioFabricacion;
import daos.DAOModelo;
import dto.ModeloDTO;
import java.util.ArrayList;
import java.util.List;
import logica.AnioFabricacion;
import logica.EstadisticaRoboVehiculo;
import logica.Modelo;

public class GestorModelo{
    
    public EstadisticaRoboVehiculo obtenerEstadisticaRobo(int idModelo){
        return obtenerModelo(idModelo).getEstadisticaActual();
    }
            
    public Modelo obtenerModelo(int idModelo){
        return new DAOModelo().get(idModelo).get();
    }
    
    public List<ModeloDTO> obtenerModelos(int idMarca){
        return modelosADTO(new DAOModelo().buscarPorIdMarca(idMarca));
    }
    
    public List<ModeloDTO> modelosADTO(List<Modelo> modelos){
        List<ModeloDTO> modelosDTO = new ArrayList();
        for(Modelo m:modelos){
            modelosDTO.add(modeloADTO(m));
        }
        return modelosDTO;
    }

    private ModeloDTO modeloADTO(Modelo m) {
        return new ModeloDTO(m.getId(), m.getNombre());
    }
    
    //Se deberia obtener en un gestor anios, lo veo impractico
    
    public List<Integer> obtenerAniosIntegers(int idModelo){
        List<Integer> anios = new ArrayList();
        List<AnioFabricacion> listaAnios = obtenerAniosModelo(idModelo);
        for(AnioFabricacion a: listaAnios){
            anios.add(a.getAnio());
        }
        return anios;
    }
    
    private List<AnioFabricacion> obtenerAniosModelo(int idModelo){
        return obtenerModelo(idModelo).getAnioModelo();
    }
    
    public AnioFabricacion obtenerAnioFabricacion(String anio){
        return new DAOAnioFabricacion().get(Integer.parseInt(anio)).get();
    }
}
