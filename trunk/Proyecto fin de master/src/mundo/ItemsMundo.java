package mundo;

public class ItemsMundo {

	private String tipo;
	private String direccion;
	private String colorSemaforo;
	private String sentido;
	private String conductor;

	private Integer tramo;
	private Integer numCarril;
	
	private Boolean inicio;
	
	public ItemsMundo(String info,boolean start) {
		
		tipo = info;
		inicio = start; 
		colorSemaforo = Constantes.VERDE;
		tramo = 0;
		numCarril = 0;
		conductor = "";
		sentido = "";
	}
	
	public String getConductor() {
		return conductor;
	}

	public void setConductor(String conductor) {
		this.conductor = conductor;
	}

	public Integer getNumCarril() {
		return numCarril;
	}

	public void setNumCarril(Integer numCarril) {
		this.numCarril = numCarril;
	}

	public String getSentido() {
		return sentido;
	}

	public void setSentido(String sentido) {
		this.sentido = sentido;
	}
	
	public String getColorSemaforo() {
		return colorSemaforo;
	}

	public void setColorSemaforo(String colorSemaforo) {
		this.colorSemaforo = colorSemaforo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String informacion) {
		this.tipo = informacion;
	}

	public Boolean isInicio() {
		return inicio;
	}

	public void setInicio(Boolean inicio) {
		this.inicio = inicio;
	}
	
	public Integer getTramo() {
		return tramo;
	}

	public void setTramo(Integer tramo) {
		this.tramo = tramo;
	}

	
}
