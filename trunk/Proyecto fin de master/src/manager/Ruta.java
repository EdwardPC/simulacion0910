package manager;

public class Ruta {

	private String tipoCalzada;
	private String accion;
	private Integer numVueltas;
	
	public Ruta (Integer vueltas) {
		
		this.numVueltas = vueltas;
		this.tipoCalzada = null;
		this.accion = null;
	}
	
	public Ruta(String tipo,String accion) {
		
		this.tipoCalzada = tipo;
		this.accion = accion;
		this.numVueltas = null;
	}

	public Integer getNumVueltas() {
		return numVueltas;
	}

	public void setNumVueltas(Integer numVueltas) {
		this.numVueltas = numVueltas;
	}

	public String getTipoCalzada() {
		return tipoCalzada;
	}

	public void setTipoCalzada(String tipoCalzada) {
		this.tipoCalzada = tipoCalzada;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}
	
}
