/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import daos.DAOCuota;
import dto.CuotaDTO;
import dto.PolizaDTO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import logica.CalculoPremioPrenda;
import logica.Cuota;
import logica.EstadisticaRoboVehiculo;
import logica.EstadoCuota;
import logica.IndicadorRiesgo;
import logica.PremioYDescuentos;
import logica.TipoPago;

/**
 *
 * @author Lucas
 */
public class GestorCuotas {

    public ArrayList<CuotaDTO> cuotasADTO(List<Cuota> cuotas) {

        ArrayList<CuotaDTO> cuotasDTO = new ArrayList();
        GestorFecha gf = new GestorFecha();

        String importesDescuento;
        String premio;
        String importe;
        Date inicioCuota;
        Date finCuota;

        for (Cuota c : cuotas) {

            importesDescuento = c.getSumaTotalDescuentos() + "";
            premio = c.getPremio() + "";
            importe = c.getMonto() + "";
            inicioCuota = c.getInicioCuota();
            finCuota = c.getUltimoDiaPago();

            //String importeDescuentos, String premio, String importeCuota, String inicioCuota, String ultimoDiaPagoCuota
            CuotaDTO cuota = new CuotaDTO(importesDescuento, premio, importe, inicioCuota, finCuota);
            cuotasDTO.add(cuota);

        }

        return cuotasDTO;

    }
    //Primero hacer el calculo premio prenda y luego calculamos las cuotas
    //Debe recibir polizaDTO y vehiculoDTO
    public PolizaDTO crearCuotas(PolizaDTO pdto) {

        //Obtenemos datos para la creacion de las cuotas y el premio y descuento
        Double suma = Double.parseDouble(pdto.getSumaAsegurada());
        EstadisticaRoboVehiculo erv = pdto.getVehiculo().getEstadisticaRobo();
        
        IndicadorRiesgo ir = new GestorLocalidad().obtenerIndicadorRiesgo(pdto.getLocalidadRiesgo().getId());;

        //Iniciamos el calculo premio prenda y creamos el premio y descuento con los datos de la poliza.
        CalculoPremioPrenda cpp = new CalculoPremioPrenda();
        PremioYDescuentos pyd = cpp.calculoPremio(suma, erv, ir);

        //Creamos la lista de cuotasdto y se la asignamos a la polizadto
        pdto.setListaCuotas(crearCuotas(pdto.getInicioVigenciaPoliza(), pdto.getFormaPago(), pyd));

        //obtenemos los valores de calculo actuales y los seteamos a la poliza dto
        pdto.setValoresCalculo(cpp.getValactcal());

        //Seteamos el premio y descuento
        pdto.setPyd(pyd);

        return pdto;
    }

    /*
    
    public ArrayList<CuotaDTO> crearCuotasInferfaz(Date inicioPoliza, TipoPago formaDePago, String sumaAsegurada, EstadisticaRoboVehiculo erv, IndicadorRiesgo ir){
        
        Double suma = Double.parseDouble(sumaAsegurada);
        
        CalculoPremioPrenda cpp =  new CalculoPremioPrenda();
        PremioYDescuentos pyd = cpp.calculoPremio(suma, erv, ir);
        
        valactcal = cpp.getValactcal();
        
        return crearCuotas(inicioPoliza,formaDePago,pyd);  
    }
    
     */
    public Double montoTotal(List<CuotaDTO> cuotas) {

        double montoTotal = 0;

        for (CuotaDTO c : cuotas) {
            montoTotal = montoTotal + Double.parseDouble(c.getImporteCuota());
        }

        return montoTotal;
    }

    private ArrayList<CuotaDTO> crearCuotas(Date inicioPoliza, String formaDePago, PremioYDescuentos premioydescuentos) {

        ArrayList<Cuota> cuotas = null;

        if (formaDePago.equalsIgnoreCase("Semestral")) {
            cuotas = crearCuotaSemestral(premioydescuentos, inicioPoliza);
        } else if (formaDePago.equalsIgnoreCase("Mensual")) {
            cuotas = crearCutoasMensuales(premioydescuentos, inicioPoliza);
        }

        return cuotasADTO(cuotas);
    }

    private ArrayList<CuotaDTO> crearCuotas(Date inicioPoliza, TipoPago formaDePago, PremioYDescuentos premioydescuentos) {

        ArrayList<Cuota> cuotas = null;

        if (formaDePago == TipoPago.SEMESTRAL) {
            cuotas = crearCuotaSemestral(premioydescuentos, inicioPoliza);
        } else if (formaDePago == TipoPago.MENSUAL) {
            cuotas = crearCutoasMensuales(premioydescuentos, inicioPoliza);
        }

        return cuotasADTO(cuotas);
    }

    private ArrayList<Cuota> crearCuotaSemestral(PremioYDescuentos premioydescuentos, Date inicioPoliza) {

        ArrayList<Cuota> cuota = new ArrayList();

        Calendar calendar = Calendar.getInstance();

        Date inicioCuota = calendar.getTime();

        calendar.setTime(inicioPoliza);
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        Date finCuota = calendar.getTime();

        Float precioCuota = premioydescuentos.getPrimaCalculada();
        Float descuentos = premioydescuentos.getBonificacionPorPagoSemestral() + premioydescuentos.getDescuentoPorUnidad();

        //TODO NO ENTIENDO que es monto y que es premio
        Cuota cuotaUnica = new Cuota(inicioCuota, finCuota, precioCuota, premioydescuentos.getPrimaCalculada() + premioydescuentos.getDerechosEmision(), descuentos);

        cuota.add(cuotaUnica);

        return cuota;
    }

    private ArrayList<Cuota> crearCutoasMensuales(PremioYDescuentos premioydescuentos, Date inicioPoliza) {

        ArrayList<Cuota> cuotas = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        Date inicioCuota;
        Date finCuota;
        Float premio = (premioydescuentos.getPrimaCalculada()) / 6;
        Float descuentos = (premioydescuentos.getBonificacionPorPagoSemestral() + premioydescuentos.getDescuentoPorUnidad()) / 6;

        for (int i = 1; i <= 6; i++) {
            if (i == 1) {
                inicioCuota = calendar.getTime();
            } else {
                calendar.setTime(inicioPoliza);
                calendar.add(Calendar.MONTH, +i - 2);
                inicioCuota = calendar.getTime();
            }

            calendar.setTime(inicioPoliza);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            calendar.add(Calendar.MONTH, i - 1);
            finCuota = calendar.getTime();

            Cuota cuota = new Cuota(inicioCuota, finCuota, premio - descuentos, premio, descuentos);
            cuotas.add(cuota);
        }

        return cuotas;

    }

    List<Cuota> DTOaCuotas(ArrayList<CuotaDTO> listaCuotas) {
        
        //TODO crear cuotas y subir a bbdd
        
        List<Cuota> listaC = new ArrayList();
        DAOCuota daoc = new DAOCuota();

        for (CuotaDTO cdto : listaCuotas) {
            listaC.add(DTOaClase(cdto));
        }
        return listaC;

    }

    private Cuota DTOaClase(CuotaDTO cdto) {

        Cuota cNueva = new Cuota();
        
        cNueva.setEstado(EstadoCuota.NO_PAGADA);
        cNueva.setInicioCuota(cdto.getInicioCuota());
        cNueva.setUltimoDiaPago(cdto.getUltimoDiaPagoCuota());
        cNueva.setSumaTotalDescuentos(Float.parseFloat(cdto.getImporteDescuentos()));
        cNueva.setMonto(Float.parseFloat(cdto.getImporteCuota()));
        cNueva.setPremio(Float.parseFloat(cdto.getPremio()));

        return cNueva;

    }
}
