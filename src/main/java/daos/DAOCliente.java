package daos;

import java.util.ArrayList;
import logica.Cliente;
import java.util.List;
import dto.ClienteDTO;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.TipoDocumento;
import static util.EntityManagerUtil.getEntityManager;


public class DAOCliente extends DAOAbstract<Cliente> {

    public int obtenerCantClientesPorPais(int idPais) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
