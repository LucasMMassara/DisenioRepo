/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import java.util.List;
import logica.Pais;
import persistenciajpa.PaisJpaController;

/**
 *
 * @author Lucas
 */
public class GestorPais {
    
    public List<Pais> ObtenerPaises(){
        PaisJpaController pjpa = new PaisJpaController();
        return pjpa.findPaisEntities();
    }
    
}
