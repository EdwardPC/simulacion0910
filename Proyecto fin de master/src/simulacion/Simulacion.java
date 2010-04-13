package simulacion;

import javax.swing.UIManager;

import controlador.Controlador;
import mundo.Entorno;
import vista.Vista;

public class Simulacion {

	public static void main(String[] args) {
	
		try {
        	UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} 
		catch (Exception e) {}
		
		Entorno matriz = new Entorno();
		Controlador controlador = new Controlador(matriz);
		Vista ventana = new Vista(controlador);
		matriz.addObserver(ventana);
		
		
	}

}
