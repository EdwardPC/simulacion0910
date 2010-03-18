package mundo;

public class ItemsMundo {

	private String tipo;
	private String direccion;
	private String colorSemaforo;
	private String sentido;
	private Integer tramo1;
	private Integer tramo2;
	private Integer numCarril;
	private boolean inicio;
	
	public ItemsMundo(String info,boolean start) {
		
		tipo = info;
		inicio = start; 
		colorSemaforo = Constantes.VERDE;
		tramo1 = 0;
		tramo2 = 0;
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

	public boolean isInicio() {
		return inicio;
	}

	public void setInicio(boolean inicio) {
		this.inicio = inicio;
	}
	
	public Integer getTramo1() {
		return tramo1;
	}

	public void setTramo1(Integer tramo1) {
		this.tramo1 = tramo1;
	}

	public Integer getTramo2() {
		return tramo2;
	}

	public void setTramo2(Integer tramo2) {
		this.tramo2 = tramo2;
	}
	
}
