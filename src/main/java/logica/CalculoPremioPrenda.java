package logica;

import daos.DAOValoresActualesCalculo;

public class CalculoPremioPrenda{
    
        DAOValoresActualesCalculo daovac = new DAOValoresActualesCalculo();
        ValoresActualesCalculo valactcal = daovac.get(1).get();
        
	public PremioYDescuentos calculoPremio(double valorAsegurado, EstadisticaRoboVehiculo erv, IndicadorRiesgo ir){
                
                PremioYDescuentos premio = new PremioYDescuentos(2500, 250,100, 150);
                
		return premio;
	}
	
	public Double calculoPrima(Poliza poliza) {
		//TODO hacer el calculo
		return 0.0;
	}
	
	public Double calculoBonificacionPorPagoSemestral(Poliza poliza) {
		//TODO hacer el calculo
		return 0.0;
	}
	
	public Double calculoDerechoEmision(Poliza poliza) {
		//TODO hacer calculo
		return 0.0;
	}
	
	public Double calculoDescuentoPorUnidades(Poliza poliza) {
		//TODO hacer calculo
		return 0.0;
	}

        public ValoresActualesCalculo getValactcal() {
            return valactcal;
        }

        public void setValactcal(ValoresActualesCalculo valactcal) {
            this.valactcal = valactcal;
        }

}
