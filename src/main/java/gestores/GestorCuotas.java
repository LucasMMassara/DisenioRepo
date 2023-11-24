/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import dto.CuotaDTO;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import logica.CalculoPremioPrenda;
import logica.Cuota;
import logica.EstadisticaRoboVehiculo;
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
        String inicioCuota;
        String finCuota;
        
        DecimalFormat df = new DecimalFormat("#.0");
        
        for (Cuota c : cuotas) {
            
            importesDescuento = c.getSumaTotalDescuentos()+"";
            premio = c.getPremio()+"";
            importe = c.getMonto()+"";
            inicioCuota = gf.formatoFecha(c.getInicioCuota());
            finCuota = gf.formatoFecha(c.getUltimoDiaPago());
            
            //String importeDescuentos, String premio, String importeCuota, String inicioCuota, String ultimoDiaPagoCuota
            
            CuotaDTO cuota = new CuotaDTO(importesDescuento,premio,importe,inicioCuota,finCuota);
            cuotasDTO.add(cuota);

        }
        
        return cuotasDTO;

    }
    
    public ArrayList<CuotaDTO> crearCuotasInferfaz(Date inicioPoliza, TipoPago formaDePago, String sumaAsegurada, EstadisticaRoboVehiculo erv, IndicadorRiesgo ir){
        
        Double suma = Double.parseDouble(sumaAsegurada);
        
        CalculoPremioPrenda cpp =  new CalculoPremioPrenda();
        PremioYDescuentos pyd = cpp.calculoPremio(suma, erv, ir);
        
        return crearCuotas(inicioPoliza,formaDePago,pyd);  
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
        Cuota cuotaUnica = new Cuota(inicioCuota, finCuota, precioCuota, premioydescuentos.getDerechosEmision(),descuentos);

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

            Cuota cuota = new Cuota(inicioCuota, finCuota, premio-descuentos,premio, descuentos);
            cuotas.add(cuota);
        }

        return cuotas;

    }
}
