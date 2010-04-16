package vista;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

import mundo.Constantes;
import mundo.ItemsMundo;

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
	
	public void dibujarMapa() {
		
		ItemsMundo contenido;
		for (int i=0;i<99;i++)
			for (int j=0;j<99;j++) {
				contenido = controlador.getMatriz().getItem(i, j);
					
				if(!tablero[i][j].getTipo().equals(contenido.getTipo()))
					tablero[i][j].setTipo(contenido.getTipo());
				if (contenido.getTipo().equals(Constantes.SEMAFORO))
					tablero[i][j].setColorSemaforo(contenido.getColorSemaforo());
				if(contenido.getTipo().equals(Constantes.AUTOMOVIL))
					tablero[i][j].setTipoConductor(contenido.getConductor());
				tablero[i][j].setDireccion(contenido.getDireccion());
				tablero[i][j].pintarCasilla();
			}
		repaint();
	}
	
	public void update(Graphics g) {
		
		paint(g);
	}
	
	
}
