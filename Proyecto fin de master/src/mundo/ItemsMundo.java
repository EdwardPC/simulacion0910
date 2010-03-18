package mundo;

public class ItemsMundo {

	private String tipo;
	private String direccion;
	private String colorSemaforo;
	private boolean inicio;
	
	public ItemsMundo(String info,boolean start) {
		
		tipo = info;
		inicio = start; 
		colorSemaforo = Constantes.VERDE;
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
	
}
