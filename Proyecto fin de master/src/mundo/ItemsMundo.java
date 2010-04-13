package mundo;

import java.util.ArrayList;

public class ItemsMundo {

	private String tipo;
	private String direccion;
	private String colorSemaforo;
	private String sentido;
	private String conductor;

	private Integer tramo;
	private Integer numCarril;
	private Integer velocidadVia;
	
	private Boolean inicio;
	private Boolean salida;
	private Boolean adelantar;
	private Boolean comienzoVuelta;

	public ItemsMundo(String info,boolean start) {
		
		tipo = info;
		inicio = start; 
		salida = false;
		adelantar = true;
		comienzoVuelta = false;
		colorSemaforo = Constantes.VERDE;
		tramo = 0;
		numCarril = 0;
		velocidadVia = 0;
		conductor = "";
		sentido = "";
		direccion = "";
	}
	
	public Integer getVelocidadVia() {
		return velocidadVia;
	}

	public void setVelocidadVia(Integer velocidadVia) {
		this.velocidadVia = velocidadVia;
	}

	public Boolean getComienzoVuelta() {
		return comienzoVuelta;
	}

	public void setComienzoVuelta(Boolean comienzoVuelta) {
		
		this.comienzoVuelta = comienzoVuelta;
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
	
	public Integer getTramo() {
		return tramo;
	}

	public void setTramo(Integer tramo) {
		this.tramo = tramo;
	}

	public Boolean isInicio() {
		return inicio;
	}

	public void setInicio(Boolean inicio) {
		this.inicio = inicio;
	}
	
	public Boolean isSalida() {
		return salida;
	}

	public void setSalida(Boolean salida) {
		this.salida = salida;
	}
	
	public Boolean getAdelantar() {
		return adelantar;
	}

	public void setAdelantar(Boolean adelantar) {
		this.adelantar = adelantar;
	}
}
