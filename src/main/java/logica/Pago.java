package logica;
import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public class Pago implements Serializable {
	
        //@Id
        //@GeneratedValue(strategy = GenerationType.SEQUENCE)
        private String nroRecibo;
        
        //@Basic
	private Float montoAbonado;
	private Float vueltoTotal;
	private String nroPoliza;
	private Float premio;
        
        //@Temporal(TemporalType.DATE)
        private Date fechaPago;
        
        //@Temporal(TemporalType.TIME)
	private LocalTime hora;
	
        
	public Pago() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pago(Float montoAbonado, Float vueltoTotal, Date fechaPago, LocalTime hora, String nroRecibo, String nroPoliza,
			Float premio) {
		super();
		this.montoAbonado = montoAbonado;
		this.vueltoTotal = vueltoTotal;
		this.fechaPago = fechaPago;
		this.hora = hora;
		this.nroRecibo = nroRecibo;
		this.nroPoliza = nroPoliza;
		this.premio = premio;
	}
	public Float getMontoAbonado() {
		return montoAbonado;
	}
	public void setMontoAbonado(Float montoAbonado) {
		this.montoAbonado = montoAbonado;
	}
	public Float getVueltoTotal() {
		return vueltoTotal;
	}
	public void setVueltoTotal(Float vueltoTotal) {
		this.vueltoTotal = vueltoTotal;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	public String getNroRecibo() {
		return nroRecibo;
	}
	public void setNroRecibo(String nroRecibo) {
		this.nroRecibo = nroRecibo;
	}
	public String getNroPoliza() {
		return nroPoliza;
	}
	public void setNroPoliza(String nroPoliza) {
		this.nroPoliza = nroPoliza;
	}
	public Float getPremio() {
		return premio;
	}
	public void setPremio(Float premio) {
		this.premio = premio;
	}
	
}
