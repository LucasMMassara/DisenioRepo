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
import util.ConversorEnum;

/**
 *
 * @author Lucas
 */
public class GestorHijo {

    //HijoDTO? O solo hijo?
    public boolean edadHijoValida(HijoDTO hijo) {

        int aniosHijo = new GestorFecha().obtenerAnios(hijo.getFechaNacimiento());

        return ((aniosHijo >= 18) && (aniosHijo <= 30));

    }

    public List<Hijo> DTOaHijos(ArrayList<HijoDTO> listaHijos) {
        
        List<Hijo> listaH = new ArrayList();
        DAOHijo daoh = new DAOHijo();

        for (HijoDTO hdto : listaHijos) {
            Hijo hnuevo = DTOaClase(hdto);
            daoh.save(hnuevo);
            listaH.add(hnuevo);
        }
        return listaH;
    }

    private Hijo DTOaClase(HijoDTO hdto) {

        Hijo hNuevo = new Hijo();
        hNuevo.setSexo(ConversorEnum.convertirStringSexo(hdto.getSexo()));
        hNuevo.setEstadoCivil(ConversorEnum.convertirStringEstadoCivil(hdto.getEstadoCivil()));
        hNuevo.setFechaNac(hdto.getFechaNacimiento());

        return hNuevo;

    }

}
