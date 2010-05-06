package controlador;

import java.io.File;

import mundo.Entorno;

public class Controlador {

	private Entorno matriz;
	
	
	public Controlador(Entorno mapa) {
		
		matriz = mapa;
	}
	
	public Entorno getMatriz() {
		
		return matriz;
	}
	
	public void setMatriz(Entorno mapa) {
		
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
	
	public void pasarImpaciencia(Integer seleccion) {
		
		matriz.obtenerImpaciencia(seleccion);
		
	}

	public void pasarTipoVehiculos(boolean seleccion) {
		
		matriz.obtenerTipoVehiculos(seleccion);
		
	}
	
	public void setVelocidad(int value) {
		
		matriz.setVelocidadSimulacion(value);
		
	}
}
