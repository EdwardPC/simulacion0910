package agente;

import java.util.ArrayList;
import java.util.Random;

import manager.Ruta;
import mundo.Constantes;

public class EstadoMental {

	private ArrayList<Ruta> ruta;
	private String tipoConductor;
	private Integer impaciencia;
	
	public EstadoMental(String tipo,Integer ansiedad,Integer eleccion,Integer tipoSimulacion) {
		
		tipoConductor = tipo;
		if (ansiedad == 4) {
			Random imp = new Random();
			impaciencia = imp.nextInt(3)+1;
		}
		else
			impaciencia = ansiedad;
		ruta = new ArrayList<Ruta>();
		seleccionRuta(eleccion,tipoSimulacion);
	}
	
	private void seleccionRuta(Integer eleccion,Integer tipoSimulacion) {
		
		Integer numVueltas = 0;
		if (tipoSimulacion == 1)
		numVueltas = 10;
		else if (tipoSimulacion == 2)
			numVueltas = 25;
		else if (tipoSimulacion == 3)
			numVueltas = 50;
		Random random;
		switch(eleccion) {
		case 0:
			Random r = new Random();
			Integer entorno = 0;
			Integer direccion = 0;
			String constante1 = null;
			String constante2 = null;
			for (int i=1;i<numVueltas/2;i++) {
				entorno = r.nextInt(2);
				if (entorno == 0)
					constante1 = Constantes.CRUCE;
				else
					constante1 = Constantes.CALLEJON;
				direccion = r.nextInt(4);
				if (direccion == 0)
					constante2 = Constantes.ARRIBA;
				else if (direccion == 1)
					constante2 = Constantes.ABAJO;
				else if (direccion == 2)
					constante2 = Constantes.DERECHA;
				else if (direccion == 3)
					constante2 = Constantes.IZQUIERDA;
				Ruta trozoRuta = new Ruta(constante1,constante2);
				ruta.add(trozoRuta);
			}
				
			/*Ruta ruta1 = new Ruta(Constantes.CRUCE,Constantes.ARRIBA);
			Ruta ruta2 = new Ruta(Constantes.CALLEJON,Constantes.DERECHA);
			Ruta ruta3 = new Ruta(Constantes.CRUCE,Constantes.IZQUIERDA);
			Ruta ruta4 = new Ruta(Constantes.CALLEJON,Constantes.IZQUIERDA);
			Ruta ruta5 = new Ruta(Constantes.CALLEJON,Constantes.ABAJO);
			Ruta ruta6 = new Ruta(Constantes.CRUCE,Constantes.ARRIBA);
			Ruta ruta7 = new Ruta(Constantes.CALLEJON,Constantes.DERECHA);
			Ruta ruta8 = new Ruta(Constantes.CRUCE,Constantes.IZQUIERDA);
			Ruta ruta9 = new Ruta(Constantes.CALLEJON,Constantes.IZQUIERDA);
			Ruta ruta10 = new Ruta(Constantes.CALLEJON,Constantes.ABAJO);
			ruta.add(ruta1);
			ruta.add(ruta2);
			ruta.add(ruta3);
			ruta.add(ruta4);
			ruta.add(ruta5);
			ruta.add(ruta6);
			ruta.add(ruta7);
			ruta.add(ruta8);
			ruta.add(ruta9);
			ruta.add(ruta10);*/
			break;
		case 1:
			Ruta rutaA = new Ruta(numVueltas);
			ruta.add(rutaA);
			break;
		case 2:
			Ruta rutaB = new Ruta(numVueltas);
			ruta.add(rutaB);
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
