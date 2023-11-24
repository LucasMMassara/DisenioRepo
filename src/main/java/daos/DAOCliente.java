package daos;

import java.util.ArrayList;
import logica.Cliente;
import java.util.List;
import javax.persistence.Query;
import persistenciajpa.ClienteJpaController;
import static util.EntityManagerUtil.getEntityManager;
import util.GeneradorQueryCliente;


public class DAOCliente extends DAOAbstract<Cliente> {
    
    public DAOCliente(){
            setClazz(Cliente.class);
    }
    
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
    
    public List<Cliente> obtenerAllClientes(){
        ClienteJpaController cjpa = new ClienteJpaController();
        return cjpa.findClienteEntities();
    }
    
    public List<Cliente> filtroClientes(String numCliente, String nombre, String apellido, String tipoDoc, String numDoc){
        
        GeneradorQueryCliente gqc = new GeneradorQueryCliente();
        
        String squery = gqc.generarQueryFiltroClientes(numCliente, nombre, apellido, tipoDoc, numDoc);
        
        System.out.println(squery);
        
        Query q = getEntityManager().createQuery(squery);
        return q.getResultList();
    }
        
}

