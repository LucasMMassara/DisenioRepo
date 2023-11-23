/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.util.List;
import javax.persistence.Query;
import logica.Poliza;
import static util.EntityManagerUtil.getEntityManager;

/**
 *
 * @author Lucas
 */
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
        
        //Deberiamos ver si podemos hacer que retorne el primer valor encontrado unicamente asi no busca demas.
        
        return q.getResultList();
    
    }

}
