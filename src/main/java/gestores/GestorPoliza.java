package gestores;

import daos.DAOPoliza;
import dto.HijoDTO;
import java.util.ArrayList;
import java.util.List;
import logica.Cuota;
import logica.EstadoPoliza;
import logica.Poliza;

public class GestorPoliza {

    public ArrayList<String> EdadValidaHijos(List<HijoDTO> hijos) {

        ArrayList<String> numHijos = new ArrayList<>();

        GestorHijo gh = new GestorHijo();
        
        int numHijo = 0;

        for (HijoDTO h : hijos) {
            
            if (!gh.edadHijoValida(h)) {
                numHijos.add("Hijo" + numHijo);
            }
            numHijo++;
        }
        return numHijos;
    }

    public boolean existePolizaVigente(String patente, String numMotor, String chasis) {

        //Si es vacio devuelve false, si contiene poliza devuelve true.
        List<Poliza> polizasDatos = (new DAOPoliza()).buscarPolizaNumMotorNumChasisPatente(patente, numMotor, chasis);

        if (polizasDatos.isEmpty()) {
            return false;
        }

        for (Poliza p : polizasDatos) {
            if (p.getEstado() == EstadoPoliza.ACTIVA) {
                return true;
            }
        }

        return false;
    }
    
}
