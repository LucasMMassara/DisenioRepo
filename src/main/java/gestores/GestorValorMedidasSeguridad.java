package gestores;

import daos.DAOValoresSeguridad;
import logica.ValorMedidaSeguridad;

public class GestorValorMedidasSeguridad {

    ValorMedidaSeguridad buscarValorPorId(int idValorMedidaSeguridad) {
        return new DAOValoresSeguridad().get(idValorMedidaSeguridad).get();
    }
    
}
