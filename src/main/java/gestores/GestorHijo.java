/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import daos.DAOHijo;
import dto.HijoDTO;
import java.util.ArrayList;
import java.util.List;
import logica.Hijo;
import logica.Poliza;
import util.ConversorEnum;

/**
 *
 * @author Lucas
 */
public class GestorHijo {

    //HijoDTO? O solo hijo?
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

    private Hijo DTOaClase(HijoDTO hdto, Poliza poliza) {

        Hijo hNuevo = new Hijo();
        hNuevo.setSexo(ConversorEnum.convertirStringSexo(hdto.getSexo()));
        hNuevo.setEstadoCivil(ConversorEnum.convertirStringEstadoCivil(hdto.getEstadoCivil()));
        hNuevo.setFechaNac(hdto.getFechaNacimiento());
        hNuevo.setPoliza(poliza);

        return hNuevo;

    }

}
