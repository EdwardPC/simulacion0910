package agente;

import mundo.Constantes;

public class Comportamiento {
	
	private EstadoMental estadoMental;
	private Integer intentoAdelantamiento;
	private Integer tiempoEnCarrilIzquierdo;
	private Integer tiempoEspera;

	public Comportamiento(EstadoMental estado) {
		
		estadoMental = estado;
		intentoAdelantamiento = 0;
		tiempoEnCarrilIzquierdo = 0;
		tiempoEspera = 0;
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
	
	public boolean deboCeder(Integer peligrosidad) {
		
		boolean opcion = false;
		if (estadoMental.getTipoConductor().equals(Constantes.AGRESIVO)) {
			if (estadoMental.getImpaciencia() == 3) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = false;
				else if (peligrosidad == 1)
					opcion = false;
			}
			else if (estadoMental.getImpaciencia() == 2) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = false;
				else if (peligrosidad == 1)
					opcion = false;
			}
			else if (estadoMental.getImpaciencia() == 1) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = false;
				else if (peligrosidad == 1)
					opcion = false;
			}
		}
		else if (estadoMental.getTipoConductor().equals(Constantes.NORMAL)) {
			if (estadoMental.getImpaciencia() == 3) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = false;
				else if (peligrosidad == 1)
					opcion = false;
			}
			else if (estadoMental.getImpaciencia() == 2) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = true;
				else if (peligrosidad == 1)
					opcion = false;
			}
			else if (estadoMental.getImpaciencia() == 1) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = true;
				else if (peligrosidad == 1)
					opcion = true;
			}
		}
		else if (estadoMental.getTipoConductor().equals(Constantes.MODERADO)) {
			if (estadoMental.getImpaciencia() == 3) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = true;
				else if (peligrosidad == 1)
					opcion = false;
			}
			else if (estadoMental.getImpaciencia() == 2) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = true;
				else if (peligrosidad == 1)
					opcion = false;
			}
			else if (estadoMental.getImpaciencia() == 1) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = true;
				else if (peligrosidad == 1)
					opcion = true;
			}
		}
		return opcion;
	}
	
	public boolean deboParar(Integer peligrosidad) {
		
		boolean opcion = false;
		if (estadoMental.getTipoConductor().equals(Constantes.AGRESIVO)) {
			if (estadoMental.getImpaciencia() == 3) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = false;
				else if (peligrosidad == 1)
					opcion = false;
				else if (peligrosidad == 0)
					opcion = false;
			}
			else if (estadoMental.getImpaciencia() == 2) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = true;
				else if (peligrosidad == 1)
					opcion = false;
				else if (peligrosidad == 0)
					opcion = false;
			}
			else if (estadoMental.getImpaciencia() == 1) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = true;
				else if (peligrosidad == 1)
					opcion = false;
				else if (peligrosidad == 0)
					opcion = false;
			}
		}
		else if (estadoMental.getTipoConductor().equals(Constantes.NORMAL)) {
			if (estadoMental.getImpaciencia() == 3) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = true;
				else if (peligrosidad == 1)
					opcion = true;
				else if (peligrosidad == 0)
					opcion = false;
			}
			else if (estadoMental.getImpaciencia() == 2) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = true;
				else if (peligrosidad == 1)
					opcion = true;
				else if (peligrosidad == 0) {
					if (tiempoEspera < 2) {
						opcion = true;
						tiempoEspera = tiempoEspera+1;
					}
					else {
						opcion = false;
						tiempoEspera = 0;
					}
				}
			}
			else if (estadoMental.getImpaciencia() == 1) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = true;
				else if (peligrosidad == 1)
					opcion = true;
				else if (peligrosidad == 0) {
					if (tiempoEspera < 4) {
						opcion = true;
						tiempoEspera = tiempoEspera+1;
					}
					else {
						opcion = false;
						tiempoEspera = 0;
					}
				}
			}
		}
		else if (estadoMental.getTipoConductor().equals(Constantes.MODERADO)) {
			if (estadoMental.getImpaciencia() == 3) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = true;
				else if (peligrosidad == 1)
					opcion = true;
				else if (peligrosidad == 0)
					opcion = false;
			}
			else if (estadoMental.getImpaciencia() == 2) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = true;
				else if (peligrosidad == 1)
					opcion = true;
				else if (peligrosidad == 0) {
					if (tiempoEspera < 4) {
						opcion = true;
						tiempoEspera = tiempoEspera+1;
					}
					else {
						opcion = false;
						tiempoEspera = 0;
					}
				}
			}
			else if (estadoMental.getImpaciencia() == 1) {
				if (peligrosidad == 3)
					opcion = true;
				else if (peligrosidad == 2)
					opcion = true;
				else if (peligrosidad == 1)
					opcion = true;
				else if (peligrosidad == 0) {
					if (tiempoEspera < 5) {
						opcion = true;
						tiempoEspera = tiempoEspera+1;
					}
					else {
						opcion = false;
						tiempoEspera = 0;
					}
				}
			}
		}
		if (!opcion)
			tiempoEspera = 0;
		return opcion;
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
	
	public boolean deboVolverACarril(Integer peligrosidad) {
		
		boolean opcion = false;
		if (estadoMental.getTipoConductor().equals(Constantes.AGRESIVO)) {
			if (estadoMental.getImpaciencia() == 3) {
				opcion = resolverPeligrosidad(peligrosidad,Constantes.AGRESIVO,3);
			}
			else if (estadoMental.getImpaciencia() == 2) {
				opcion = resolverPeligrosidad(peligrosidad,Constantes.AGRESIVO,2);
			}
			else if (estadoMental.getImpaciencia() == 1) {
				opcion = true;
			}
		}
		if (estadoMental.getTipoConductor().equals(Constantes.NORMAL)) {
			if (estadoMental.getImpaciencia() == 3) {
				opcion = resolverPeligrosidad(peligrosidad,Constantes.NORMAL,3);
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
		return opcion;
	}
	
	private boolean resolverPeligrosidad(Integer peligrosidad,String tipo,Integer grado) {
		
		boolean respuesta = false;
		if (tipo.equals(Constantes.AGRESIVO)) {
			if (grado == 3) {
				if (peligrosidad == 1) {
					if (tiempoEnCarrilIzquierdo < 3) {
						respuesta = false;
						tiempoEnCarrilIzquierdo = tiempoEnCarrilIzquierdo+1;
					}
					else {
						respuesta = true;
						tiempoEnCarrilIzquierdo = 0;
					}
				}
				else if (peligrosidad == 2) {
					if (tiempoEnCarrilIzquierdo < 1) {
						respuesta = false;
						tiempoEnCarrilIzquierdo = tiempoEnCarrilIzquierdo+1;
					}
					else {
						respuesta = true;
						tiempoEnCarrilIzquierdo = 0;
					}
				}
				else if (peligrosidad == 3)
					respuesta = true;
			}
			else if (grado == 2) {
				if (peligrosidad == 1) {
					if (tiempoEnCarrilIzquierdo < 2) {
						respuesta = false;
						tiempoEnCarrilIzquierdo = tiempoEnCarrilIzquierdo+1;
					}
					else {
						respuesta = true;
						tiempoEnCarrilIzquierdo = 0;
					}
				}
				else if (peligrosidad == 2) {
					if (tiempoEnCarrilIzquierdo < 1) {
						respuesta = false;
						tiempoEnCarrilIzquierdo = tiempoEnCarrilIzquierdo+1;
					}
					else {
						respuesta = true;
						tiempoEnCarrilIzquierdo = 0;
					}
				}
				else if (peligrosidad == 3)
					respuesta = true;
			}
		}
		else if (tipo.equals(Constantes.NORMAL)) {
			if (peligrosidad == 1) {
				if (tiempoEnCarrilIzquierdo < 1) {
					respuesta = false;
					tiempoEnCarrilIzquierdo = tiempoEnCarrilIzquierdo+1;
				}
				else {
					respuesta = true;
					tiempoEnCarrilIzquierdo = 0;
				}
			}
			else if (peligrosidad == 2) 
				respuesta = true;
			else if (peligrosidad == 3)
				respuesta = true;
		}
		return respuesta;
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
	
		return estadoMental.getRuta().get(0).getNumVueltas() <= 0;
	}

}
