package logica;

import daos.DAOValoresActualesCalculo;
import dto.LocalidadDTO;
import dto.ModeloDTO;
import dto.PremioYDescuentosDTO;

public class CalculoPremioPrenda{   
        
	public PremioYDescuentosDTO calculoPremio(double sumaAsegurada, ModeloDTO modelo, LocalidadDTO localidad){
            
            DAOValoresActualesCalculo daovac = new DAOValoresActualesCalculo();
            ValoresActualesCalculo valactcal = daovac.get(1).get();
            
            PremioYDescuentosDTO premio = new PremioYDescuentosDTO();
            premio.setPrimaCalculada(2500);
            premio.setDerechosEmision(250);
            premio.setBonificacionPorPagoSemestral(100);
            premio.setDescuentoPorUnidad(150);
            premio.setIdDatosGenerales(valactcal.getValorGen().getId());
            premio.setIdValorMedidaSeguridad(valactcal.getValorSeg().getId());
            premio.setIdValorSiniestros(valactcal.getValorSin().getId());
            
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

}
