package gestores;

import java.util.List;

import daos.DAOMarca;
import logica.Marca;

public class GestorMarca {
	
	//Capaz hay que traer todo y ir filtrando a medida que elije el usuario y no hacerlo de esta manera.
	
	public List<Marca> obtenerMarcas(){
		
		return (new DAOMarca()).obtenerAll();
	}

        /*
        
        GestorPais gp = new GestorPais();
        listaPaises = gp.ObtenerPaises();    
        listaProvincias = listaPaises.get(0).getProvincias();
        listaLocalidades = listaProvincias.get(0).getLocalidades();
        
        String[] paises = new String[listaPaises.size()];
        for (int i = 0; i < listaPaises.size(); i++) {
            paises[i] = listaPaises.get(i).getNombre();
        }
        pais = new PanelDropDown(paises);
        
        String[] provincias = new String[listaProvincias.size()];
        for (int i = 0; i < listaProvincias.size(); i++) {
            provincias[i] = listaProvincias.get(i).getNombreProvincia();
        }
        provincia = new PanelDropDown(provincias);
        
        String[] localidades = new String[listaLocalidades.size()];
        for (int i = 0; i < listaLocalidades.size(); i++) {
            localidades[i] = listaLocalidades.get(i).getNombreLocalidad();
        }
        localidad = new PanelDropDown(localidades);
        
        */
        /*
        
        for(Provincia p: listaProvincias){    
                    if(p.getNombreProvincia().equals(selectedItem)){
                        listaLocalidades = p.getLocalidades();
                        actualizarListaLocalidades();
                        break;
                    }
                }
        
        */
        
}
