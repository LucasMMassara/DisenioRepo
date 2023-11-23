package gestores;

import daos.DAOPoliza;
import dto.HijoDTO;
import java.util.List;
import logica.EstadoPoliza;
import logica.Poliza;

public class GestorPoliza {

    public boolean EdadValidaHijos(List<HijoDTO> hijos) {

        boolean edadValida = true;
        GestorHijo gh = new GestorHijo();

        for (HijoDTO h : hijos) {
            if (!gh.edadHijoValida(h)) {
                return false; //Como manejamos el retorno para saber a que hijo corresponde?
            }
        }

        return edadValida;
        //Podriamos retornar una lista de string que si es vacia entonces todos son validos, sino mostramos cuales no son validos.
    }

    public boolean existePolizaVigente(String patente, String numMotor, String chasis) {
        
        //Si es vacio devuelve false, si contiene poliza devuelve true.
        
        List<Poliza> polizasDatos = (new DAOPoliza()).buscarPolizaNumMotorNumChasisPatente(patente, numMotor, chasis);
        
        
        if(polizasDatos.isEmpty()){
            return false;
        }
        
        for(Poliza p: polizasDatos){
            if(p.getEstado() == EstadoPoliza.ACTIVA){
                return true;
            }
        }
        
        return false;
    }

}
