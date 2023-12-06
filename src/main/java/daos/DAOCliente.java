package daos;

import logica.Cliente;
import java.util.List;
import javax.persistence.Query;
import static util.EntityManagerUtil.getEntityManager;
import util.GeneradorQueryCliente;


public class DAOCliente extends DAOAbstract<Cliente> {
    
    public DAOCliente(){
            setClazz(Cliente.class);
    }
    
    
    public Cliente obtenerClientePorNumCliente(String numCliente){
        
        String stringquery = "SELECT c FROM " + Cliente.class.getName() + " c WHERE c.numCliente = ?1";
        Query q = getEntityManager().createQuery(stringquery);
        q.setParameter(1, numCliente);
        return (Cliente) q.getSingleResult();
        
    }
    
    public List<Cliente> filtroClientes(String numCliente, String nombre, String apellido, String tipoDoc, String numDoc){
        
        GeneradorQueryCliente gqc = new GeneradorQueryCliente();
        
        String squery = gqc.generarQueryFiltroClientes(numCliente, nombre, apellido, tipoDoc, numDoc);
        
        Query q = getEntityManager().createQuery(squery);
        return q.getResultList();
    }
        
}

