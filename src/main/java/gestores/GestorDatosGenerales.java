/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import daos.DAOValoresGenerales;
import logica.ValoresGenerales;

/**
 *
 * @author Lucas
 */
public class GestorDatosGenerales {

    ValoresGenerales buscarValorPorId(int idDatosGenerales) {
        return new DAOValoresGenerales().get(idDatosGenerales).get();
    }
    
}
