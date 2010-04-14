package agente;

import java.util.ArrayList;
import java.util.Random;

import mundo.Constantes;

public class EstadoMental {

	private ArrayList<String> ruta;
	private String tipoConductor;
	private Integer ansiedad;
	private Integer intento;
	
	public EstadoMental(String tipo,Integer impaciencia,Integer eleccion) {
		
		tipoConductor = tipo;
		ansiedad = impaciencia;
		seleccionRuta(eleccion);
		intento = 0;
	}
	
	private void seleccionRuta(Integer eleccion) {
		
		ruta = new ArrayList<String>();
		Random random = new Random();
		Integer numVueltas = 42;//random.nextInt(10)+1;
		switch(eleccion) {
		case 0:
			
			break;
		case 1:
			ruta.add(numVueltas.toString());
			break;
		case 2:
			ruta.add(numVueltas.toString());
			break;
		}
	}
	
	public boolean deboAdelantar() {
		
		boolean opcion = false;
		if (tipoConductor.equals(Constantes.AGRESIVO)) {
			if (ansiedad == 3) {
				opcion = true;
			}
			else if (ansiedad == 2) {
				opcion = true;
			}
			else if (ansiedad == 1) {
				intento = intento+1;
				if (intento == 2) {
					opcion = true;
					intento = 0;
				}
				else 
					opcion = false;
			}
		}
		else if (tipoConductor.equals(Constantes.NORMAL)) {
			if (ansiedad == 3) {
				opcion = true;
			}
			else if (ansiedad == 2) {
				opcion = true;
			}
			else if (ansiedad == 1) {
				intento = intento+1;
				if (intento == 3) {
					opcion = true;
					intento = 0;
				}
				else 
					opcion = false;
			}
				
		}
		else if (tipoConductor.equals(Constantes.MODERADO)) {
			if (ansiedad == 3) {
				opcion = true;
			}
			else if (ansiedad == 2) {
				intento = intento+1;
				if (intento == 4) {
					opcion = true;
					intento = 0;
				}
				else 
					opcion = false;
			}
			else if (ansiedad == 1) {
				intento = intento+1;
				if (intento == 5) {
					opcion = true;
					intento = 0;
				}
				else 
					opcion = false;
			}
		}
		return opcion;
	}
	
	public boolean deboVolverACarril() {
		
		boolean opcion = false;
		if (tipoConductor.equals(Constantes.AGRESIVO)) {
			if (ansiedad == 3) {
				opcion = false;
			}
			else if (ansiedad == 2) {
				opcion = false;
			}
			else if (ansiedad == 1) {
				opcion = true;
			}
		}
		else if (tipoConductor.equals(Constantes.NORMAL)) {
			if (ansiedad == 3) {
				opcion = false;
			}
			else if (ansiedad == 2) {
				opcion = false;
			}
			else if (ansiedad == 1) {
				opcion = true;
			}
				
		}
		else if (tipoConductor.equals(Constantes.MODERADO)) {
			if (ansiedad == 3) {
				opcion = false;
			}
			else if (ansiedad == 2) {
				opcion = true;
			}
			else if (ansiedad == 1) {
				opcion = true;
			}
		}
		return opcion;
	}
	
	public Integer velocidadAdelantamiento() {
		
		Integer velocidad = 0;
		if (tipoConductor.equals(Constantes.AGRESIVO)) {
			if (ansiedad == 3) {
				velocidad = 40;
			}
			else if (ansiedad == 2) {
				velocidad = 30;
			}
			else if (ansiedad == 1) {
				velocidad = 20;
			}
		}
		else if (tipoConductor.equals(Constantes.NORMAL)) {
			if (ansiedad == 3) {
				velocidad = 20;
			}
			else if (ansiedad == 2) {
				velocidad = 10;
			}
			else if (ansiedad == 1) {
				velocidad = 5;
			}
				
		}
		else if (tipoConductor.equals(Constantes.MODERADO)) {
			if (ansiedad == 3) {
				velocidad = 10;
			}
			else if (ansiedad == 2) {
				velocidad = 5;
			}
			else if (ansiedad == 1) {
				velocidad = 1;
			}
		}
		return velocidad;
	}
	
	public boolean salir() {
		
		Integer opcion = new Integer(ruta.get(0));
		return opcion <= 0;
	}

	public ArrayList<String> getRuta() {
		return ruta;
	}

	public void setRuta(ArrayList<String> ruta) {
		this.ruta = ruta;
	}

	public String getTipoConductor() {
		return tipoConductor;
	}

	public void setTipoConductor(String tipoConductor) {
		this.tipoConductor = tipoConductor;
	}

	public Integer getAnsiedad() {
		return ansiedad;
	}

	public void setAnsiedad(Integer ansiedad) {
		this.ansiedad = ansiedad;
	}
}
