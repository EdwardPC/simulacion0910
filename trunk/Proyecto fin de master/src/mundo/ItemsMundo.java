package mundo;

public class ItemsMundo {

	private String tipo;
	private String direccion;
	private boolean inicio;
	
	public ItemsMundo(String info,boolean start) {
		
		tipo = info;
		inicio = start; 
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
