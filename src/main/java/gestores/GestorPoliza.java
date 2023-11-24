package gestores;

import daos.DAOPoliza;
import dto.HijoDTO;
import java.util.List;
import logica.Cuota;
import logica.EstadoPoliza;
import logica.Poliza;

public class GestorPoliza {

    public String[] EdadValidaHijos(List<HijoDTO> hijos) {

        String[] numHijos = new String[hijos.size()];

        GestorHijo gh = new GestorHijo();
        
        int numHijo = 0;

        for (HijoDTO h : hijos) {
            
            if (!gh.edadHijoValida(h)) {
                numHijos[numHijo] = "Hijo " + numHijo;
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

    private Poliza generarCuotas(Poliza poliza) {
        
        /*
        //Retorna la poliza con las cuotas ya generadas
        List<Cuota> cuotasPoliza = (new GestorCuotas()).crearCuotas(poliza.getInicioVigencia(), poliza.getFormaDePago(), poliza.getPremioydescuentos());

        poliza.setCuotas(cuotasPoliza);
        
        */
        
        return poliza;
    }

}
