package util;

import daos.DAOValorSiniestros;
import daos.DAOValoresActualesCalculo;
import daos.DAOValoresGenerales;
import daos.DAOValoresSeguridad;
import java.util.Date;
import java.util.Random;
import logica.ValorMedidaSeguridad;
import logica.ValorSiniestro;
import logica.ValoresActualesCalculo;
import logica.ValoresGenerales;

public class CargadorDatosValoresCalculo {
    
    DAOValoresActualesCalculo daovac = new DAOValoresActualesCalculo();
    DAOValorSiniestros daovalsin = new DAOValorSiniestros();
    DAOValoresSeguridad daovalseg = new DAOValoresSeguridad();
    DAOValoresGenerales daovalgen = new DAOValoresGenerales();
    
    public void cargarDatosValoresCalculo(){
        
        ValoresActualesCalculo valactcalc = new ValoresActualesCalculo();
        
        ValoresGenerales valgen = crearValoresGenerales();
        daovalgen.save(valgen);
        
        ValorSiniestro valsin = crearValorSiniestro();
        daovalsin.save(valsin);
        
        ValorMedidaSeguridad valseg = crearValoresSeguridad();
        daovalseg.save(valseg);
        
        valactcalc.setValorGen(valgen);
        valactcalc.setValorSeg(valseg);
        valactcalc.setValorSin(valsin);
        
        daovac.save(valactcalc);
    }
    
    private ValorSiniestro crearValorSiniestro(){
        ValorSiniestro valsis = new ValorSiniestro();
        Date d = new Date();
        valsis.setInicioVigencia(d);
        Random rand = new Random();
        valsis.setSinSiniestro(rand.nextDouble());
        valsis.setUnSiniestro(rand.nextDouble());
        valsis.setDosSiniestros(rand.nextDouble());
        valsis.setMasDeDosSiniestros(rand.nextDouble());
        return valsis;
    }
    
    private ValoresGenerales crearValoresGenerales(){
        ValoresGenerales valgen = new ValoresGenerales();
        Date d = new Date();
        valgen.setInicioVigencia(d);
        Random rand = new Random();
        valgen.setAjusteHijos(rand.nextDouble());
        valgen.setAjusteKM(rand.nextDouble());
        valgen.setDerechoEmision(rand.nextDouble());
        valgen.setDescuentoUnidadAdicional(rand.nextDouble());
        return valgen;
    }
    
        private ValorMedidaSeguridad crearValoresSeguridad(){
        ValorMedidaSeguridad valseg = new ValorMedidaSeguridad();
        Date d = new Date();
        valseg.setInicioVigencia(d);
        Random rand = new Random();
        valseg.setAlarma(rand.nextDouble());
        valseg.setDispRastreo(rand.nextDouble());
        valseg.setTuercasAntirrobo(rand.nextDouble());
        valseg.setValorGarage(rand.nextDouble());
        return valseg;
    }
    
    
    
}
