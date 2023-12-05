/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import dto.PremioYDescuentosDTO;
import logica.PremioYDescuentos;

/**
 *
 * @author Lucas
 */
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
