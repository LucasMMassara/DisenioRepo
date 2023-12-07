package daos;

import java.util.List;
import javax.persistence.Query;
import logica.Poliza;
import static util.EntityManagerUtil.getEntityManager;

public class DAOPoliza extends DAOAbstract<Poliza> {

    public DAOPoliza() {
        setClazz(Poliza.class);
    }
    
    public List<Poliza> buscarPolizaNumMotorNumChasisPatente(String patente, String numMotor, String chasis){
        
        String squery = "SELECT p FROM " + Poliza.class.getName() + " p WHERE p.vehiculoAsegurado.numMotor = '"+numMotor+"' OR p.vehiculoAsegurado.numChasis = '"+chasis+"'";
        
        if(!patente.isEmpty()){
            squery = squery + " OR p.vehiculoAsegurado.patente = '"+patente+"'";
        }
        
        Query q = getEntityManager().createQuery(squery);
        
        return q.getResultList();
    
    }

    public List<Poliza> obtenerPolizasCliente(int id) {     
        try{
        String squery = "SELECT p FROM " + Poliza.class.getName() + " p WHERE p.clientePoliza.id = " + id;
        Query q = getEntityManager().createQuery(squery);
        return q.getResultList();
        }
        catch(Exception e){
          return null;   //Ver que retornar en caso de excepcion
        }
    }

    public List<Poliza> obtenerVersionesPoliza(String comienzoNumPoliza) {
        String squery = "SELECT p FROM " + Poliza.class.getName() + " p WHERE p.numPoliza like '" + comienzoNumPoliza + "%' ";
        Query q = getEntityManager().createQuery(squery);
        return q.getResultList();
    }

}
