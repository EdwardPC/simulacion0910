package agente;

import mundo.Constantes;

public class Comportamiento {
	
	private EstadoMental estadoMental;
	private Integer intentoAdelantamiento;
	private Integer tiempoEnCarrilIzquierdo;
	private boolean apurarAdelantamiento;

	public Comportamiento(EstadoMental estado) {
		
		estadoMental = estado;
		intentoAdelantamiento = 0;
		tiempoEnCarrilIzquierdo = 0;
		apurarAdelantamiento = false;
	}
	
	public Integer getIntentoAdelantamiento() {
		return intentoAdelantamiento;
	}

	public void setIntentoAdelantamiento(Integer intentoAdelantamiento) {
		this.intentoAdelantamiento = intentoAdelantamiento;
	}

	public Integer getTiempoEnCarrilIzquierdo() {
		return tiempoEnCarrilIzquierdo;
	}

	public void setTiempoEnCarrilIzquierdo(Integer tiempoEnCarrilIzquierdo) {
		this.tiempoEnCarrilIzquierdo = tiempoEnCarrilIzquierdo;
	}

	public boolean isApurarAdelantamiento() {
		return apurarAdelantamiento;
	}

	public void setApurarAdelantamiento(boolean apurarAdelantamiento) {
		this.apurarAdelantamiento = apurarAdelantamiento;
	}
	
	public boolean deboAdelantar() {
		boolean opcion = false;
		if (estadoMental.getTipoConductor().equals(Constantes.AGRESIVO)) {
			if (estadoMental.getImpaciencia() == 3) {
				opcion = true;
			}
			else if (estadoMental.getImpaciencia() == 2) {
				opcion = true;
			}
			else if (estadoMental.getImpaciencia() == 1) {
				intentoAdelantamiento = intentoAdelantamiento+1;
				if (intentoAdelantamiento == 2) {
					opcion = true;
					intentoAdelantamiento = 0;
				}
				else 
					opcion = false;
			}
		}
		else if (estadoMental.getTipoConductor().equals(Constantes.NORMAL)) {
			if (estadoMental.getImpaciencia() == 3) {
				opcion = true;
			}
			else if (estadoMental.getImpaciencia() == 2) {
				opcion = true;
			}
			else if (estadoMental.getImpaciencia() == 1) {
				intentoAdelantamiento = intentoAdelantamiento+1;
				if (intentoAdelantamiento == 3) {
					opcion = true;
					intentoAdelantamiento = 0;
				}
				else 
					opcion = false;
			}
				
		}
		else if (estadoMental.getTipoConductor().equals(Constantes.MODERADO)) {
			if (estadoMental.getImpaciencia() == 3) {
				opcion = true;
			}
			else if (estadoMental.getImpaciencia() == 2) {
				intentoAdelantamiento = intentoAdelantamiento+1;
				if (intentoAdelantamiento == 4) {
					opcion = true;
					intentoAdelantamiento = 0;
				}
				else 
					opcion = false;
			}
			else if (estadoMental.getImpaciencia() == 1) {
				intentoAdelantamiento = intentoAdelantamiento+1;
				if (intentoAdelantamiento == 5) {
					opcion = true;
					intentoAdelantamiento = 0;
				}
				else 
					opcion = false;
			}
		}
		return opcion;
	}
	
	public boolean deboVolverACarril() {
		
		boolean opcion = false;
		if (estadoMental.getTipoConductor().equals(Constantes.AGRESIVO)) {
			if (estadoMental.getImpaciencia() == 3) {
				opcion = true;
			}
			else if (estadoMental.getImpaciencia() == 2) {
				opcion = true;
			}
			else if (estadoMental.getImpaciencia() == 1) {
				opcion = true;
			}
		}
		if (estadoMental.getTipoConductor().equals(Constantes.NORMAL)) {
			if (estadoMental.getImpaciencia() == 3) {
				opcion = true;
			}
			else if (estadoMental.getImpaciencia() == 2) {
				opcion = true;
			}
			else if (estadoMental.getImpaciencia() == 1) {
				opcion = true;
			}
		}
		if (estadoMental.getTipoConductor().equals(Constantes.MODERADO)) 
				opcion = true;
		return true;
	}
	
	public Integer velocidadAdelantamiento() {
		
		Integer velocidad = 0;
		if (estadoMental.getTipoConductor().equals(Constantes.AGRESIVO)) {
			if (estadoMental.getImpaciencia() == 3) {
				velocidad = 40;
			}
			else if (estadoMental.getImpaciencia() == 2) {
				velocidad = 30;
			}
			else if (estadoMental.getImpaciencia() == 1) {
				velocidad = 20;
			}
		}
		else if (estadoMental.getTipoConductor().equals(Constantes.NORMAL)) {
			if (estadoMental.getImpaciencia() == 3) {
				velocidad = 20;
			}
			else if (estadoMental.getImpaciencia() == 2) {
				velocidad = 10;
			}
			else if (estadoMental.getImpaciencia() == 1) {
				velocidad = 5;
			}
				
		}
		else if (estadoMental.getTipoConductor().equals(Constantes.MODERADO)) {
			if (estadoMental.getImpaciencia() == 3) {
				velocidad = 10;
			}
			else if (estadoMental.getImpaciencia() == 2) {
				velocidad = 5;
			}
			else if (estadoMental.getImpaciencia() == 1) {
				velocidad = 1;
			}
		}
		return velocidad;
	}

	public boolean salir() {
	
		Integer opcion = new Integer(estadoMental.getRuta().get(0));
		return opcion <= 0;
	}

}
