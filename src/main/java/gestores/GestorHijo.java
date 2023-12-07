package gestores;

import dto.HijoDTO;
import java.util.ArrayList;
import java.util.List;
import logica.Hijo;
import logica.Poliza;
import util.ConversorEnum;

public class GestorHijo {

    public boolean edadHijoValida(HijoDTO hijo) {

        int aniosHijo = new GestorFecha().obtenerAniosDesde(hijo.getFechaNacimiento());

        return ((aniosHijo >= 18) && (aniosHijo <= 30));

    }

    public List<Hijo> DTOaHijos(ArrayList<HijoDTO> listaHijos, Poliza poliza) {
        
        List<Hijo> listaH = new ArrayList();

        for (HijoDTO hdto : listaHijos) {
            listaH.add(DTOaClase(hdto, poliza));
        }
        return listaH;
    }
    
        public ArrayList<String> EdadValidaHijos(List<HijoDTO> hijos) {

        ArrayList<String> numHijos = new ArrayList<>();
        
        int numHijo = 0;

        for (HijoDTO h : hijos) {
            
            if (!edadHijoValida(h)) {
                numHijos.add("Hijo" + numHijo);
            }
            numHijo++;
        }
        return numHijos;
    }

    private Hijo DTOaClase(HijoDTO hdto, Poliza poliza) {

        Hijo hNuevo = new Hijo();
        hNuevo.setSexo(ConversorEnum.convertirStringSexo(hdto.getSexo()));
        hNuevo.setEstadoCivil(ConversorEnum.convertirStringEstadoCivil(hdto.getEstadoCivil()));
        hNuevo.setFechaNac(hdto.getFechaNacimiento());
        hNuevo.setPoliza(poliza);

        return hNuevo;
    }
}