package manager;

public class Estadisticas {

	private Integer conductores;
	private Integer agresivos;
	private Integer normales;
	private Integer moderados;
	private Integer adelantamientos;
	private Integer accidentes;
	
	public Estadisticas() {
		
		resetear();
	}
	
	public void resetear() {
		
		conductores = 0;
		agresivos = 0;
		normales = 0;
		moderados = 0;
		adelantamientos = 0;
		accidentes = 0;
	}
	
	public Integer getConductores() {
		return conductores;
	}

	public void setConductores(Integer conductores) {
		this.conductores = conductores;
	}

	public Integer getAgresivos() {
		return agresivos;
	}

	public void setAgresivos(Integer agresivos) {
		this.agresivos = agresivos;
	}

	public Integer getNormales() {
		return normales;
	}

	public void setNormales(Integer normales) {
		this.normales = normales;
	}

	public Integer getModerados() {
		return moderados;
	}

	public void setModerados(Integer moderados) {
		this.moderados = moderados;
	}

	public Integer getAdelantamientos() {
		return adelantamientos;
	}

	public void setAdelantamientos(Integer adelantamientos) {
		this.adelantamientos = adelantamientos;
	}

	public Integer getAccidentes() {
		return accidentes;
	}

	public void setAccidentes(Integer accidentes) {
		this.accidentes = accidentes;
	}
}
