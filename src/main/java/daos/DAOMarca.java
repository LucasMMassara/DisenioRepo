/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.util.List;
import logica.Marca;
import persistenciajpa.MarcaJpaController;

/**
 *
 * @author Lucas
 */
public class DAOMarca extends DAOAbstract<Marca> {
    
    public DAOMarca(){
        setClazz(Marca.class);
    }
    
    public List<Marca> obtenerAll(){
        MarcaJpaController mjpa = new MarcaJpaController();
        return mjpa.findMarcaEntities();
    }
    
}
