package mundo;

public class ItemsMundo {

	private String informacion;
	private boolean inicio;
	
	public ItemsMundo(String info,boolean start) {
		
		informacion = info;
		inicio = start; 
	}

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	public boolean isInicio() {
		return inicio;
	}

	public void setInicio(boolean inicio) {
		this.inicio = inicio;
	}
	
}
