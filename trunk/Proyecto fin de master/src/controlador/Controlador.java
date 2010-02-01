package controlador;

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
	
	public void rellenarMatriz(Integer opcion) {
		
		matriz.inicializar();
		matriz.rellenarMatriz(opcion);
	}

	public void simular() {
		
		matriz.simular();
	}
	
	public void finalizar() {
		
		matriz.finalizar();
	}
}
