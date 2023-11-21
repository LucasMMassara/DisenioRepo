package daos;

import java.util.ArrayList;
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
    
    public List<Cliente> obtenerAllClientes(){
        ClienteJpaController cjpa = new ClienteJpaController();
        return cjpa.findClienteEntities();
    }
    
    public List<Cliente> filtroClientes(String numCliente, String nombre, String apellido, String tipoDoc, String numDoc){
        
        //TODO INCOMPLETO
        
        if(numCliente == null && nombre == null && apellido == null && tipoDoc == null && numDoc == null){
            return obtenerAllClientes();
        }
        
        
        if(numCliente != null){
            return obtenerClientePorNumCliente(numCliente);
        }
        
        String squery = "SELECT c FROM " + Cliente.class.getName() + " c WHERE ";
        
        String cuerpoQuery = filtroAtributoGeneral(numCliente, "numCliente");
        

        cuerpoQuery = cuerpoQuery.concat(cadenaComienzaCon(nombre, "nombre"));
        cuerpoQuery = cuerpoQuery.concat(cadenaComienzaCon(apellido, "apellido"));
        cuerpoQuery = cuerpoQuery.concat(filtroAtributoGeneral(tipoDoc, "tipodni"));
        cuerpoQuery = cuerpoQuery.concat(filtroAtributoGeneral(numDoc, "numeroDni"));
        
        cuerpoQuery = verificarQuery(cuerpoQuery);
        
        squery = squery + cuerpoQuery;
        
        //SOLO PARA TESTEAR
        
        System.out.println(squery);
        
        Query q = getEntityManager().createQuery(squery);
        return q.getResultList();
    }
    
    
    private String filtroNumCliente(String numCliente){
        
        if(numCliente == null){
            return "";
        }
        
        return "c.numCliente = '" + numCliente + "' AND ";
    }
    
    private String filtroAtributoGeneral(String valorAtributo, String nombreAtributo){
        
        if(valorAtributo == null){
            return "";
        }
        
        return "c." + nombreAtributo + " = ' " + valorAtributo + " ' AND ";
    }
    
    private String cadenaComienzaCon(String valorAtributo, String nombreAtributo){
    
        if(valorAtributo == null){
            return "";
        }
        
        return "c." + nombreAtributo + " like '" + valorAtributo + "%' AND ";
    }
    
    private String verificarQuery(String cuerpoQuery){
        
        if (cuerpoQuery.endsWith(" AND ")) {
        int lastIndex = cuerpoQuery.lastIndexOf(" AND ");

        // Remove the last " AND" from the string
        cuerpoQuery = cuerpoQuery.substring(0, lastIndex);
        
        return cuerpoQuery;
        
        }
        
        return cuerpoQuery;
    }
        
}

