package controlador;

import java.io.File;

import modelo.Matriz;

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
	
	public void finalizar() {
		
		matriz.finalizar();
	}
}
