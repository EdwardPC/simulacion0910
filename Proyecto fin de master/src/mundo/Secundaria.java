package mundo;

import java.util.ArrayList;

import managerXML.Acceso;
import managerXML.Contenido;
import managerXML.Tramo;

public class Secundaria {

	private Modelo matriz;
	private ItemsMundo[][] contenido;
	
	private ArrayList<Contenido> campos;
	private ArrayList<Contenido> edificios;
	
	private ArrayList<Tramo> tramos;
	
	private ArrayList<Acceso> entradas;
	private ArrayList<Acceso> salidas;
	
	public Secundaria(Modelo mundo) {
		
		matriz = mundo;
		contenido = mundo.getContenido();
		campos = mundo.getCampos();
		edificios = mundo.getEdificios();
		tramos = mundo.getTramos();
		entradas = mundo.getEntradas();
		salidas = mundo.getSalidas();
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
					entrada.getY1(),entrada.getY2(),entrada.getDir1(),entrada.getDir2());
		}
		for (int i=0;i<salidas.size();i++) {
			Acceso salida = salidas.get(i);
			matriz.salida(salida.getX1(),salida.getX2(),salida.getX3(),salida.getX4(),
					salida.getY1(),salida.getY2(),salida.getDir1(),salida.getDir2());
		}
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
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.SECUNDARIA);
				contenido[y][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y][j].setSentido(Constantes.DERECHA);
			}
			for (int j=x1-2;j<x2;j++) {
				contenido[y+1][j].setTipo(Constantes.SECUNDARIA);
				contenido[y+1][j].setDireccion(Constantes.DERECHA);
				contenido[y+1][j].setSentido(Constantes.IZQUIERDA);
			}
		}
		else if (sentido == 2) {
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.SECUNDARIA);
				contenido[y][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y][j].setSentido(Constantes.IZQUIERDA);
			}
			for (int j=x1;j<x2-2;j++) {
				contenido[y+1][j].setTipo(Constantes.SECUNDARIA);
				contenido[y+1][j].setDireccion(Constantes.DERECHA);
				contenido[y+1][j].setSentido(Constantes.DERECHA);
			}
		}
		else if (sentido == 3) {
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.SECUNDARIA);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setSentido(Constantes.DERECHA);
			}
			for (int i=x1;i<x2+2;i++) {
				contenido[i][y+1].setTipo(Constantes.SECUNDARIA);
				contenido[i][y+1].setDireccion(Constantes.ARRIBA);
				contenido[i][y+1].setSentido(Constantes.IZQUIERDA);
			}
		}
		else if (sentido == 4) {
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.SECUNDARIA);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setSentido(Constantes.IZQUIERDA);
			}
			for (int i=x1+2;i<x2;i++) {
				contenido[i][y+1].setTipo(Constantes.SECUNDARIA);
				contenido[i][y+1].setDireccion(Constantes.ARRIBA);
				contenido[i][y+1].setSentido(Constantes.DERECHA);
			}
		}
		else if (sentido == 5) {
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.SECUNDARIA);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setSentido(Constantes.IZQUIERDA);
			}
			for (int i=x1;i<x2;i++) {
				contenido[i][y+1].setTipo(Constantes.SECUNDARIA);
				contenido[i][y+1].setDireccion(Constantes.ARRIBA);
				contenido[i][y+1].setSentido(Constantes.DERECHA);
			}
		}
		else if (sentido == 6) {
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.SECUNDARIA);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setSentido(Constantes.IZQUIERDA);
			}
			for (int i=x1+2;i<x2+2;i++) {
				contenido[i][y+1].setTipo(Constantes.SECUNDARIA);
				contenido[i][y+1].setDireccion(Constantes.ARRIBA);
				contenido[i][y+1].setSentido(Constantes.DERECHA);
			}
		}
	}
}
