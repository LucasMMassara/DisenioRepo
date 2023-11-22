package gestores;

import java.util.List;

import daos.DAOMarca;
import logica.Marca;

public class GestorMarca {
	
	//Capaz hay que traer todo y ir filtrando a medida que elije el usuario y no hacerlo de esta manera.
	
	public List<Marca> obtenerMarcas(){
		
		return (new DAOMarca()).obtenerAll();
	}

}
