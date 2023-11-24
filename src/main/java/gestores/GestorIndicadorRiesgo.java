package gestores;

import daos.DAOIndicadorRiesgo;
import java.util.Date;
import logica.IndicadorRiesgo;
import logica.Localidad;
import logica.Usuario;

public class GestorIndicadorRiesgo {

    public IndicadorRiesgo crearIndicadorRiesgo(Localidad loc, Double porcentaje, Usuario usuarioMod) {

        IndicadorRiesgo ir = new IndicadorRiesgo();
        GestorFecha gf = new GestorFecha();

        ir.setLocalidad(loc);
        ir.setPorcentajeIndicador(porcentaje);
        ir.setUsuarioEditor(usuarioMod);
        ir.setInicioVigencia(gf.obtenerFechaActual());

        return ir;
    }

    public IndicadorRiesgo crearIndicadorRiesgo(Localidad loc, Double porcentaje) {

        IndicadorRiesgo ir = new IndicadorRiesgo();
        GestorFecha gf = new GestorFecha();

        ir.setLocalidad(loc);
        ir.setPorcentajeIndicador(porcentaje);
        ir.setInicioVigencia(gf.obtenerFechaActual());

        return ir;
    }

    public void guardarIndicadorRiesgo(IndicadorRiesgo ir) {
        DAOIndicadorRiesgo daoir = new DAOIndicadorRiesgo();
        daoir.save(ir);
    }

    public void actualizarFechaFinIndicadorRiesgo(IndicadorRiesgo ir) {

        GestorFecha gf = new GestorFecha();
        ir.setFinVigencia(gf.obtenerFechaActual());

        DAOIndicadorRiesgo daoir = new DAOIndicadorRiesgo();
        daoir.update(ir);

    }

    public IndicadorRiesgo obtenerIndicadorRiesgo(String nombreLocalidad, String nombrePais, String nombreProvincia) {
        //HAY QUE IMPLEMENTARLO
        DAOIndicadorRiesgo daoir = new DAOIndicadorRiesgo();
        return daoir.get(1).get();
    }

}
