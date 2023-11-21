package daos;

import logica.Cliente;
import java.util.List;
import javax.persistence.Query;
import persistenciajpa.ClienteJpaController;
import static util.EntityManagerUtil.getEntityManager;


public class DAOCliente extends DAOAbstract<Cliente> {
    
    public void guardarCliente(Cliente c){
        
        try{
            ClienteJpaController cjpa = new ClienteJpaController();
            cjpa.create(c);
        }
        catch(Exception e){
            System.out.println("Error al cargar el cliente en la BBDD" +  e.getMessage());
        }

    }
    
    public List<Cliente> obtenerClientePorNumCliente(String numCliente){
        
        String stringquery = "SELECT c FROM " + Cliente.class.getName() + " c WHERE c.numCliente = ?1";
        Query q = getEntityManager().createQuery(stringquery);
        q.setParameter(1, numCliente);
        return q.getResultList();
        
    }

}
