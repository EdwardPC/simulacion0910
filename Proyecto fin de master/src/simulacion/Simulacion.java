package simulacion;

import javax.swing.UIManager;

import controlador.Controlador;
import modelo.Matriz;
import vista.Ventana;

public class Simulacion {

	public static void main(String[] args) {
	
		try {
        	UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} 
		catch (Exception e) {}
		
		Matriz matriz = new Matriz();
		Controlador controlador = new Controlador(matriz);
		Ventana ventana = new Ventana(controlador);
		matriz.addObserver(ventana);
		
		
	}

}
