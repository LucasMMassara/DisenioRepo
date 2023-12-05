/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import daos.DAOValoresSeguridad;
import logica.ValorMedidaSeguridad;

/**
 *
 * @author Lucas
 */
public class GestorValorMedidasSeguridad {

    ValorMedidaSeguridad buscarValorPorId(int idValorMedidaSeguridad) {
        return new DAOValoresSeguridad().get(idValorMedidaSeguridad).get();
    }
    
}
