package daos;

import java.util.List;
import javax.persistence.Query;
import logica.Localidad;
import static util.EntityManagerUtil.getEntityManager;

public class DAOLocalidad extends DAOAbstract<Localidad> {

    public DAOLocalidad() {
        setClazz(Localidad.class);
    }

    public List<Localidad> buscarPorIdProvincia(int idProvincia) {
        String qlString = "SELECT p FROM " + Localidad.class.getName() + " p WHERE p.provincia.id = " + idProvincia;
        Query query = getEntityManager().createQuery(qlString);
        return query.getResultList();
    }
}
