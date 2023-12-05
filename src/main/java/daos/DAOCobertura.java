/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.util.List;
import javax.persistence.Query;
import logica.Cobertura;
import static util.EntityManagerUtil.getEntityManager;

/**
 *
 * @author Lucas
 */
public class DAOCobertura extends DAOAbstract<Cobertura>{
    
    public DAOCobertura(){
            setClazz(Cobertura.class);
    }

    public List<Cobertura> buscarResponsabilidadCivil() {
        
        String stringquery = "SELECT c FROM " + Cobertura.class.getName() + " c WHERE c.detalle = ?1";
        Query q = getEntityManager().createQuery(stringquery);
        q.setParameter(1, "Responsabilidad Civil");
        return q.getResultList();
        
    }
}
