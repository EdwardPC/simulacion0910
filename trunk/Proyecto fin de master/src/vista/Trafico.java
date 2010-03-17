package vista;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JPanel;

import controlador.Controlador;

public class Trafico extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Controlador controlador;
	
	private Casilla[][] tablero;
	
	public Trafico(Controlador control) {
		
		setLayout(new GridLayout(100,100));
		controlador = control;
		tablero = new Casilla[100][100];
		inicializar();
		setPreferredSize(new Dimension(100,100));
	}
	
	private void inicializar() {
		
		for (int i=0;i<99;i++)
			for (int j=0;j<99;j++) {
				Casilla casilla = new Casilla();
				tablero[i][j] =  casilla;
				add(casilla);
			}
	}
	
	/*public void cargarSimulacion(Integer opcion,File fichero) {
		
		controlador.rellenarMatriz(opcion,fichero);
	}*/
	
	public void dibujarMapa() {
		
		String contenido;
		for (int i=0;i<99;i++)
			for (int j=0;j<99;j++) {
				contenido = controlador.getMatriz().getTipo(i, j);
				if(!tablero[i][j].getContenido().equals(contenido))
					tablero[i][j].setContenido(contenido);
					tablero[i][j].pintarCasilla();
			}
		repaint();
	}
	
	public void update(Graphics g) {
		
		paint(g);
	}
	
	
}
