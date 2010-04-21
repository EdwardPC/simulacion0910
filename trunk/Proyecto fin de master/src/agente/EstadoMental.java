package agente;

import java.util.ArrayList;
import java.util.Random;

import manager.Ruta;
import mundo.Constantes;

public class EstadoMental {

	private ArrayList<Ruta> ruta;
	private String tipoConductor;
	private Integer impaciencia;
	
	public EstadoMental(String tipo,Integer ansiedad,Integer eleccion) {
		
		tipoConductor = tipo;
		impaciencia = 1;//ansiedad;
		ruta = new ArrayList<Ruta>();
		seleccionRuta(eleccion);
	}
	
	private void seleccionRuta(Integer eleccion) {
		
		Random random = new Random();
		Integer numVueltas = 10;//random.nextInt(10)+1;
		switch(eleccion) {
		case 0:
			Ruta ruta1 = new Ruta(Constantes.CRUCE,Constantes.DERECHA);
			Ruta ruta2 = new Ruta(Constantes.CALLEJON,Constantes.DERECHA);
			Ruta ruta3 = new Ruta(Constantes.CRUCE,Constantes.IZQUIERDA);
			Ruta ruta4 = new Ruta(Constantes.CALLEJON,Constantes.IZQUIERDA);
			Ruta ruta5 = new Ruta(Constantes.CALLEJON,Constantes.ABAJO);
			ruta.add(ruta1);
			ruta.add(ruta2);
			ruta.add(ruta3);
			ruta.add(ruta4);
			ruta.add(ruta5);
			break;
		case 1:
			Ruta ruta6 = new Ruta(numVueltas);
			ruta.add(ruta6);
			break;
		case 2:
			Ruta ruta7 = new Ruta(numVueltas);
			ruta.add(ruta7);
			break;
		}
	}

	public ArrayList<Ruta> getRuta() {
		return ruta;
	}

	public void setRuta(ArrayList<Ruta> ruta) {
		this.ruta = ruta;
	}

	public String getTipoConductor() {
		return tipoConductor;
	}

	public void setTipoConductor(String tipoConductor) {
		this.tipoConductor = tipoConductor;
	}

	public Integer getImpaciencia() {
		return impaciencia;
	}

	public void setImpaciencia(Integer ansiedad) {
		this.impaciencia = ansiedad;
	}
}
