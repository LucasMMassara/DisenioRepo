package daos;

import java.util.List;
import javax.persistence.Query;
import logica.Modelo;
import static util.EntityManagerUtil.getEntityManager;

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
