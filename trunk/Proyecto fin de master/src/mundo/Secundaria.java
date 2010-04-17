package mundo;

import java.util.ArrayList;

import manager.Acceso;
import manager.Contenido;
import manager.Punto;
import manager.Tramo;

public class Secundaria {

	private final static Integer VMAX = 100;
	
	private Entorno matriz;
	private ItemsMundo[][] contenido;
	
	private ArrayList<Contenido> campos;
	private ArrayList<Contenido> edificios;
	
	private ArrayList<Tramo> tramos;
	
	private ArrayList<Acceso> entradas;
	private ArrayList<Acceso> salidas;
	private ArrayList<Punto> comienzoVueltas;
	
	public Secundaria(Entorno mundo) {
		
		matriz = mundo;
		contenido = mundo.getContenido();
		campos = mundo.getCampos();
		edificios = mundo.getEdificios();
		tramos = mundo.getTramos();
		entradas = mundo.getEntradas();
		salidas = mundo.getSalidas();
		comienzoVueltas = mundo.getComienzoVueltas();
	}
	
	public void generarSecundaria() {
		
		for (int i=0;i<campos.size();i++) {
			Contenido contenido = campos.get(i);
			construirCampo(contenido.getX1(),contenido.getX2(),contenido.getY1(),contenido.getY2());
		}
		for (int i=0;i<edificios.size();i++) {
			Contenido contenido = edificios.get(i);
			construirEdificio(contenido.getX1(),contenido.getX2(),contenido.getY1(),contenido.getY2());
		}
		for (int i=0;i<tramos.size();i++) {
			Tramo tramo = tramos.get(i);
			secundaria(tramo.getX1(),tramo.getX2(),tramo.getY(),tramo.getSentido());
		}
		for (int i=0;i<entradas.size();i++) {
			Acceso entrada = entradas.get(i);
			matriz.entrada(entrada.getX1(),entrada.getX2(),entrada.getX3(),entrada.getX4(),
					entrada.getY1(),entrada.getY2(),entrada.getIniX(),entrada.getIniY(),
					entrada.getTram1(),entrada.getTram2(),entrada.getDir1(),entrada.getDir2());
		}
		for (int i=0;i<salidas.size();i++) {
			Acceso salida = salidas.get(i);
			matriz.salida(salida.getX1(),salida.getX2(),salida.getX3(),salida.getX4(),
					salida.getY1(),salida.getY2(),salida.getTram1(),salida.getTram2(),salida.getDir1(),salida.getDir2());
		}
		for (int i=0;i<comienzoVueltas.size();i++) {
			Punto comienzo = comienzoVueltas.get(i);
			comienzoVuelta(comienzo.getX(),comienzo.getY());
		}
	}
	
	private void comienzoVuelta(int pos1,int pos2) {
		
		for (int i=pos1;i<pos1+2;i++)
			contenido[i][pos2].setComienzoVuelta(true);
	}
	
	private void construirCampo(int x1,int x2,int y1,int y2) {
		
		for (int i=x1;i<x2;i++)
			for(int j=y1;j<y2;j++)
				contenido[i][j].setTipo(Constantes.CAMPO);
	}
	
	private void construirEdificio(int x1,int x2,int y1,int y2) {
		
		for (int i=x1;i<x2;i++)
			for(int j=y1;j<y2;j++)
				contenido[i][j].setTipo(Constantes.EDIFICIO);
	}
	
	private void secundaria(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) {
			for (int j=x1;j<x1+3;j++)
				contenido[y][j].setAdelantar(false);
			for (int j=x2-5;j<x2;j++)
				contenido[y][j].setAdelantar(false);
			
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.SECUNDARIA);
				contenido[y][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y][j].setSentido(Constantes.DERECHA);
				contenido[y][j].setVelocidadVia(VMAX);
			}
			
			for (int j=x1-2;j<x1+3;j++)
				contenido[y+1][j].setAdelantar(false);
			for (int j=x2-5;j<x2;j++)
				contenido[y+1][j].setAdelantar(false);
			
			for (int j=x1-2;j<x2;j++) {
				contenido[y+1][j].setTipo(Constantes.SECUNDARIA);
				contenido[y+1][j].setDireccion(Constantes.DERECHA);
				contenido[y+1][j].setSentido(Constantes.IZQUIERDA);
				contenido[y+1][j].setVelocidadVia(VMAX);
			}
		}
		else if (sentido == 2) {
			for (int j=x1;j<x1+4;j++)
				contenido[y][j].setAdelantar(false);
			for (int j=x2-5;j<x2;j++)
				contenido[y][j].setAdelantar(false);
			
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.SECUNDARIA);
				contenido[y][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y][j].setSentido(Constantes.IZQUIERDA);
				contenido[y][j].setVelocidadVia(VMAX);
			}
			
			for (int j=x1;j<x1+4;j++)
				contenido[y+1][j].setAdelantar(false);
			for (int j=x2-5;j<x2-2;j++)
				contenido[y+1][j].setAdelantar(false);
			
			for (int j=x1;j<x2-2;j++) {
				contenido[y+1][j].setTipo(Constantes.SECUNDARIA);
				contenido[y+1][j].setDireccion(Constantes.DERECHA);
				contenido[y+1][j].setSentido(Constantes.DERECHA);
				contenido[y+1][j].setVelocidadVia(VMAX);
			}
		}
		else if (sentido == 3) {
			for (int i=x1;i<x1+5;i++)
				contenido[i][y].setAdelantar(false);
			for (int i=x2-3;i<x2;i++)
				contenido[i][y].setAdelantar(false);
			
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.SECUNDARIA);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setSentido(Constantes.DERECHA);
				contenido[i][y].setVelocidadVia(VMAX);
			}
			
			for (int i=x1;i<x1+5;i++)
				contenido[i][y+1].setAdelantar(false);
			for (int i=x2-3;i<x2+2;i++)
				contenido[i][y+1].setAdelantar(false);
			
			for (int i=x1;i<x2+2;i++) {
				contenido[i][y+1].setTipo(Constantes.SECUNDARIA);
				contenido[i][y+1].setDireccion(Constantes.ARRIBA);
				contenido[i][y+1].setSentido(Constantes.IZQUIERDA);
				contenido[i][y+1].setVelocidadVia(VMAX);
			}
		}
		else if (sentido == 4) {
			for (int i=x1;i<x1+5;i++)
				contenido[i][y].setAdelantar(false);
			for (int i=x2-4;i<x2;i++)
				contenido[i][y].setAdelantar(false);
			
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.SECUNDARIA);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setSentido(Constantes.IZQUIERDA);
				contenido[i][y].setVelocidadVia(VMAX);
			}
			
			for (int i=x1+2;i<x1+5;i++)
				contenido[i][y+1].setAdelantar(false);
			for (int i=x2-4;i<x2;i++)
				contenido[i][y+1].setAdelantar(false);
			
			for (int i=x1+2;i<x2;i++) {
				contenido[i][y+1].setTipo(Constantes.SECUNDARIA);
				contenido[i][y+1].setDireccion(Constantes.ARRIBA);
				contenido[i][y+1].setSentido(Constantes.DERECHA);
				contenido[i][y+1].setVelocidadVia(VMAX);
			}
		}
		else if (sentido == 5) {
			for (int i=x1;i<x1+5;i++)
				contenido[i][y].setAdelantar(false);
			for (int i=x2-4;i<x2;i++)
				contenido[i][y].setAdelantar(false);
			
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.SECUNDARIA);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setSentido(Constantes.IZQUIERDA);
				contenido[i][y].setVelocidadVia(VMAX);
			}
			
			for (int i=x1;i<x1+5;i++)
				contenido[i][y+1].setAdelantar(false);
			for (int i=x2-4;i<x2;i++)
				contenido[i][y+1].setAdelantar(false);
			
			for (int i=x1;i<x2;i++) {
				contenido[i][y+1].setTipo(Constantes.SECUNDARIA);
				contenido[i][y+1].setDireccion(Constantes.ARRIBA);
				contenido[i][y+1].setSentido(Constantes.DERECHA);
				contenido[i][y+1].setVelocidadVia(VMAX);
			}
		}
		else if (sentido == 6) {
			for (int i=x1;i<x1+5;i++)
				contenido[i][y].setAdelantar(false);
			for (int i=x2-4;i<x2;i++)
				contenido[i][y].setAdelantar(false);
			
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.SECUNDARIA);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setSentido(Constantes.IZQUIERDA);
				contenido[i][y].setVelocidadVia(VMAX);
			}
			
			for (int i=x1+2;i<x1+5;i++)
				contenido[i][y+1].setAdelantar(false);
			for (int i=x2-4;i<x2+2;i++)
				contenido[i][y+1].setAdelantar(false);
			
			for (int i=x1+2;i<x2+2;i++) {
				contenido[i][y+1].setTipo(Constantes.SECUNDARIA);
				contenido[i][y+1].setDireccion(Constantes.ARRIBA);
				contenido[i][y+1].setSentido(Constantes.DERECHA);
				contenido[i][y+1].setVelocidadVia(VMAX);
			}
		}
	}
}
