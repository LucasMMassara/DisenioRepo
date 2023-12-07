package gestores;

import dto.PremioYDescuentosDTO;
import logica.PremioYDescuentos;

public class GestorPremioYDescuentos {
    
    public PremioYDescuentos convertirDTOAClase(PremioYDescuentosDTO pydto){
        PremioYDescuentos pyd = new PremioYDescuentos();
        pyd.setPrimaCalculada(pydto.getPrimaCalculada());
        pyd.setDescuentoPorUnidad(pydto.getDescuentoPorUnidad());
        pyd.setDerechosEmision(pydto.getDerechosEmision());
        pyd.setBonificacionPorPagoSemestral(pydto.getBonificacionPorPagoSemestral());
        return pyd;
    }
    
}
