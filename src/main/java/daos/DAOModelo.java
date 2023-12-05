/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.util.List;
import javax.persistence.Query;
import logica.Modelo;
import static util.EntityManagerUtil.getEntityManager;

/**
 *
 * @author Lucas
 */
public class DAOModelo extends DAOAbstract<Modelo> {
    
    public DAOModelo(){
        setClazz(Modelo.class);
    }

    public List<Modelo> buscarPorIdMarca(int idMarca) {
        String qlString = "SELECT p FROM " + Modelo.class.getName() + " p WHERE p.marca.id = " + idMarca;
        Query query = getEntityManager().createQuery(qlString);
        return query.getResultList();
    }
}
