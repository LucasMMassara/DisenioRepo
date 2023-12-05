/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import daos.DAOValorSiniestros;
import logica.ValorSiniestro;

/**
 *
 * @author Lucas
 */
public class GestorValorSiniestros {

    ValorSiniestro buscarValorPorId(int idValorSiniestros) {
        return new DAOValorSiniestros().get(idValorSiniestros).get();
    }
    
}
