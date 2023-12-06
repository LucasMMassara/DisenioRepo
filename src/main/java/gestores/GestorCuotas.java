/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestores;

import daos.DAOCuota;
import dto.CuotaDTO;
import dto.PremioYDescuentosDTO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import logica.Cuota;
import logica.EstadoCuota;
import logica.TipoPago;
import util.GestorNumerico;

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
    public ArrayList<CuotaDTO> crearCuotas(PremioYDescuentosDTO pydto, Date inicioVigenciaPoliza, String formaPago) {
        //Creamos la lista de cuotasdto y se la asignamos a la polizadto
        System.err.println(pydto.toString());
        return crearCuotas(inicioVigenciaPoliza, formaPago, pydto);
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

    private ArrayList<CuotaDTO> crearCuotas(Date inicioPoliza, String formaDePago, PremioYDescuentosDTO premioydescuentos) {

        ArrayList<Cuota> cuotas = null;

        if (formaDePago.equalsIgnoreCase("Semestral")) {
            cuotas = crearCuotaSemestral(premioydescuentos, inicioPoliza);
        } else if (formaDePago.equalsIgnoreCase("Mensual")) {
            cuotas = crearCutoasMensuales(premioydescuentos, inicioPoliza);
        }

        return cuotasADTO(cuotas);
    }
    

    private ArrayList<CuotaDTO> crearCuotas(Date inicioPoliza, TipoPago formaDePago, PremioYDescuentosDTO premioydescuentos) {

        ArrayList<Cuota> cuotas = null;

        if (formaDePago == TipoPago.SEMESTRAL) {
            cuotas = crearCuotaSemestral(premioydescuentos, inicioPoliza);
        } else if (formaDePago == TipoPago.MENSUAL) {
            cuotas = crearCutoasMensuales(premioydescuentos, inicioPoliza);
        }

        return cuotasADTO(cuotas);
    }

    private ArrayList<Cuota> crearCuotaSemestral(PremioYDescuentosDTO premioydescuentos, Date inicioPoliza) {

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

    private ArrayList<Cuota> crearCutoasMensuales(PremioYDescuentosDTO premioydescuentos, Date inicioPoliza) {

        ArrayList<Cuota> cuotas = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        Date inicioCuota;
        Date finCuota;
        
        Float premio = (premioydescuentos.getPrimaCalculada()) / 6;
        premio = redondear(premio);


        float descuentoTotal = premioydescuentos.getBonificacionPorPagoSemestral() + premioydescuentos.getDescuentoPorUnidad();
        Float descuentos = descuentoTotal / 6;
        descuentos = redondear(descuentos);

        
        
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
        
        return corregirCuotas(cuotas,premioydescuentos.getPrimaCalculada(),descuentoTotal);

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

    private float redondear(float num) {
        // Convertir el float a BigDecimal y redondear
        BigDecimal bigDecimal = new BigDecimal(num);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);

        // Obtener el resultado redondeado como float
        return bigDecimal.floatValue();
    }

    
    private ArrayList<Cuota> corregirCuotas(ArrayList<Cuota> cuotas, float premio, float descuento){
        
        float monto = premio - descuento;
        
        float sumaCuotas = 0;
        float sumaDescuentos = 0;
        float sumaMontos = 0;
        
        for(Cuota c : cuotas){
            sumaCuotas += c.getPremio();
            sumaDescuentos += c.getSumaTotalDescuentos();
            sumaMontos += c.getMonto();
        }
        
        float premioOriginal = cuotas.getLast().getPremio();
        float descuentoOriginal = cuotas.getLast().getSumaTotalDescuentos();
        float montoOriginal = cuotas.getLast().getMonto();
        
        cuotas.getLast().setPremio(premioOriginal-(sumaCuotas - premio));
        cuotas.getLast().setSumaTotalDescuentos(descuentoOriginal - (sumaDescuentos - descuento));
        cuotas.getLast().setMonto(montoOriginal - (sumaMontos - monto));

        return cuotas;
    }
    
}

