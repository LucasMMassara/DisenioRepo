package gestores;

import daos.DAOValorSiniestros;
import logica.ValorSiniestro;

public class GestorValorSiniestros {

    ValorSiniestro buscarValorPorId(int idValorSiniestros) {
        return new DAOValorSiniestros().get(idValorSiniestros).get();
    }
    
}
