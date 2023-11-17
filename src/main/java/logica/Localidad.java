package logica;


public class Localidad{
        
	private String nombreLocalidad;
        
	private Provincia provincia;
        
	private IndicadorRiesgo indicadorActual;
        
        /*@OneToMany(cascade=CascadeType.ALL, mappedBy="localidad")
        private Set<IndicadorRiesgo> historialIndicadorRiesgo; //No se si va aca.*/

	public Localidad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Localidad(String nombreLocalidad) {
		super();
		this.nombreLocalidad = nombreLocalidad;
	}

	public String getNombreLocalidad() {
		return nombreLocalidad;
	}

	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public IndicadorRiesgo getIndicadorActual() {
		return indicadorActual;
	}

	public void setIndicadorActual(IndicadorRiesgo indicadorActual) {
		this.indicadorActual = indicadorActual;
	}

	/*public Set<IndicadorRiesgo> getHistorialIndicadorRiesgo() {
		return historialIndicadorRiesgo;
	}

	public void setHistorialIndicadorRiesgo(Set<IndicadorRiesgo> historialIndicadorRiesgo) {
		this.historialIndicadorRiesgo = historialIndicadorRiesgo;
	}*/
	
}
