package dto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logica.*;

public class PolizaDTO {
	
        private IndicadorRiesgo indicadorRiesgo;
	private PorcentajeCobertura cobertura;
	private Date inicioVigenciaPoliza;
	private String formaPago;
        private ClienteDTO cliente;
        private ArrayList<HijoDTO> listaHijos;
        private ArrayList<CuotaDTO> listaCuotas;
        private Date finVigencia;
        private Date fechaEmision;
        private ValoresActualesCalculo valoresCalculo;
        
        
}
