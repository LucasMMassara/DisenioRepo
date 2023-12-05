/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.util.List;
import javax.persistence.Query;
import logica.Provincia;
import static util.EntityManagerUtil.getEntityManager;

public class DAOProvincia extends DAOAbstract<Provincia> {
    
    public DAOProvincia() {
        setClazz(Provincia.class);
    }

    public List<Provincia> buscarPorIdPais(int idPais) {
        String qlString = "SELECT p FROM " + Provincia.class.getName() +" p WHERE p.pais.id = " + idPais;
        Query query = getEntityManager().createQuery(qlString);
        return query.getResultList();
    }
}
