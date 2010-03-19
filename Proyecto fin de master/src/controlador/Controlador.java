package controlador;

import java.io.File;

import mundo.Modelo;

public class Controlador {

	private Modelo matriz;
	
	
	public Controlador(Modelo mapa) {
		
		matriz = mapa;
	}
	
	public Modelo getMatriz() {
		
		return matriz;
	}
	
	public void setMatriz(Modelo mapa) {
		
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

	public void setVelocidad(int value) {
		
		matriz.setVelocidadSimulacion(value);
		
	}
}
