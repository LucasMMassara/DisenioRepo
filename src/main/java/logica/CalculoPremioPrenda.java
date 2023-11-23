package logica;

import persistenciajpa.ValoresActualesCalculoJpaController;

public class CalculoPremioPrenda{
    
        ValoresActualesCalculoJpaController vacjpa = new ValoresActualesCalculoJpaController();
        ValoresActualesCalculo valactcal = vacjpa.findValoresActualesCalculo(1);
	
	public PremioYDescuentos calculoPremio(Poliza poliza){
		//Nuevo premio
		PremioYDescuentos premio = new PremioYDescuentos();
                
                //Valores usados para el calculo;
                
                valactcal.getValorGen();
                valactcal.getValorSeg();
                valactcal.getValorSin();
                
		//Set de los atributos usando los metodos de calculo de la clase
                
		premio.setPrima(calculoPrima(poliza));
		premio.setBonificacionPorPago(calculoBonificacionPorPagoSemestral(poliza));
		premio.setDerechoDeEmision(calculoDerechoEmision(poliza));
		premio.setDescuentoPorUnidades(calculoDescuentoPorUnidades(poliza));
                
		//setear premio a poliza  el premio
		return premio;
	}
	
	//Para los calculos hay que obtener los datos de la base de datos, datos generales, valor medida seguridad y valor siniestros.
	//Eso no deberian ser atributos???? O lo modelamos invocandolos en los metodos?
	//Ademas despues hay que asociar eso que obtuvimos a los datos CalculoPoliza
	
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

}
