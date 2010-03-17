package controlador;

import java.io.File;

import mundo.Matriz;

public class Controlador {

	private Matriz matriz;
	
	
	public Controlador(Matriz mapa) {
		
		matriz = mapa;
	}
	
	public Matriz getMatriz() {
		
		return matriz;
	}
	
	public void setMatriz(Matriz mapa) {
		
		matriz = mapa;
	}
	
	public void rellenarMatriz(Integer opcion,File fichero) {
		
		matriz.inicializar();
		matriz.rellenarMatriz(opcion,fichero);
	}

	public void simular() {
		
		matriz.simular();
	}

	public void pasarConductores(Double numAgresivos, Double numNormales,
			Double numModerados) {
		
		Integer agresivos = numAgresivos.intValue();
		Integer normales = numNormales.intValue();
		Integer moderados = numModerados.intValue();
		matriz.obtenerConductores(agresivos,normales,moderados);
	}
}
