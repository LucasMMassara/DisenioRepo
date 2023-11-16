package logica;

import java.time.LocalDate;

public class Cuota {

        private String id;
	private LocalDate fechaPago;
	private Float monto;
        private Float premio;
	private Float recargoMora;
	private Float bonificacionAdelanto;
	private EstadoCuota estado;
        private Pago pago;

	public Cuota() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cuota(LocalDate fechaPago, Float monto, EstadoCuota estado, Float premio, Float recargoMora,
			Float bonificacionAdelanto) {
		super();
		this.fechaPago = fechaPago;
		this.monto = monto;
		this.estado = estado;
		this.premio = premio;
		this.recargoMora = recargoMora;
		this.bonificacionAdelanto = bonificacionAdelanto;
	}
	public LocalDate getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Float getMonto() {
		return monto;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	public EstadoCuota getEstado() {
		return estado;
	}
	public void setEstado(EstadoCuota estado) {
		this.estado = estado;
	}
	public Float getPremio() {
		return premio;
	}
	public void setPremio(Float premio) {
		this.premio = premio;
	}
	public Float getRecargoMora() {
		return recargoMora;
	}
	public void setRecargoMora(Float recargoMora) {
		this.recargoMora = recargoMora;
	}
	public Float getBonificacionAdelanto() {
		return bonificacionAdelanto;
	}
	public void setBonificacionAdelanto(Float bonificacionAdelanto) {
		this.bonificacionAdelanto = bonificacionAdelanto;
	}
        
        public Pago getPago() {
            return pago;
        }

        public void setPago(Pago pago) {
            this.pago = pago;
        }
	
}
