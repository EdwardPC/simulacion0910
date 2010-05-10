package mundo;

import java.util.ArrayList;

import manager.Acceso;
import manager.Contenido;
import manager.Punto;
import manager.Tramo;

public class Ciudad {

	private Entorno matriz;
	
	private ItemsMundo[][] contenido;
	
	private ArrayList<Acceso> entradas;
	private ArrayList<Tramo> principales;
	private ArrayList<Tramo> horizontales;
	private ArrayList<Tramo> verticales;
	private ArrayList<Tramo> callejones;
	private ArrayList<Tramo> semaforos;
	
	private ArrayList<Punto> stops;
	private ArrayList<Punto> cedas;
	private ArrayList<Punto> crucesS;
	
	private ArrayList<Contenido> cruces;
	
	public Ciudad(Entorno mundo) {
		
		matriz = mundo;
		contenido = mundo.getContenido();
		principales = mundo.getPrincipales();
		horizontales = mundo.getHorizontales();
		verticales = mundo.getVerticales();
		callejones = mundo.getCallejones();
		semaforos = mundo.getSemaforos();
		stops = mundo.getStops();
		cedas = mundo.getCedas();
		crucesS = mundo.getCrucesS();
		cruces = mundo.getCruces();
		entradas = mundo.getEntradas();
	}
	
	public void generarCiudad() {
		
		for (int i=0;i<entradas.size();i++) {
			Acceso entrada = entradas.get(i);
			matriz.entrada(entrada.getX1(),entrada.getX2(),entrada.getX3(),entrada.getX4(),
					entrada.getY1(),entrada.getY2(),entrada.getIniX(),entrada.getIniY(),
					entrada.getTram1(),entrada.getTram2(),entrada.getDir1(),entrada.getDir2());
		}
		for (int i=0;i<principales.size();i++) {
			Tramo tramo = principales.get(i);
			callePrincipal(tramo.getX1(),tramo.getX2(),tramo.getY(),tramo.getSentido());
		}
		for (int i=0;i<horizontales.size();i++) {
			Tramo tramo = horizontales.get(i);
			calleHorizontal(tramo.getX1(),tramo.getX2(),tramo.getY(),tramo.getSentido());
		}
		for (int i=0;i<verticales.size();i++) {
			Tramo tramo = verticales.get(i);
			calleVertical(tramo.getX1(),tramo.getX2(),tramo.getY(),tramo.getSentido());
		}
		for (int i=0;i<callejones.size();i++) {
			Tramo tramo = callejones.get(i);
			callejon(tramo.getX1(),tramo.getX2(),tramo.getY(),tramo.getSentido());
		}
		for (int i=0;i<semaforos.size();i++) {
			Tramo tramo = semaforos.get(i);
			semaforos(tramo.getX1(),tramo.getX2(),tramo.getY(),tramo.getSentido());
		}
		for (int i=0;i<stops.size();i++) {
			Punto punto = stops.get(i);
			stops(punto.getX(),punto.getY());
		}
		for (int i=0;i<cedas.size();i++) {
			Punto punto = cedas.get(i);
			cedas(punto.getX(),punto.getY());
		}
		for (int i=0;i<crucesS.size();i++) {
			Punto punto = crucesS.get(i);
			crucesS(punto.getX(),punto.getY());
		}
		for (int i=0;i<cruces.size();i++) {
			Contenido contenido = cruces.get(i);
			cruces(contenido.getX1(),contenido.getX2(),contenido.getY1(),contenido.getY2());
		}
		
		
	}
	
	private void callePrincipal(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) {
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.CALLE_PRINCIPAL);
				contenido[y][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y][j].setNumCarril(1);
				contenido[y][j].setVelocidadVia(50);
			}
			for (int j=x1-1;j<x2+1;j++) {
				contenido[y+1][j].setTipo(Constantes.CALLE_PRINCIPAL);
				contenido[y+1][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y+1][j].setNumCarril(2);
				contenido[y+1][j].setVelocidadVia(50);
			}
			
			for (int j=x1-2;j<x2+1;j++)
				contenido[y+2][j].setTipo(Constantes.SEPARACION);	
			
			for (int j=x1-2;j<x2;j++) {
				contenido[y+3][j].setTipo(Constantes.CALLE_PRINCIPAL);
				contenido[y+3][j].setDireccion(Constantes.DERECHA);
				contenido[y+3][j].setNumCarril(2);
				contenido[y+3][j].setVelocidadVia(50);
			}
			for (int j=x1-1;j<x2-1;j++) {
				contenido[y+4][j].setTipo(Constantes.CALLE_PRINCIPAL);
				contenido[y+4][j].setDireccion(Constantes.DERECHA);
				contenido[y+4][j].setNumCarril(1);
				contenido[y+4][j].setVelocidadVia(50);
			}
		}
		else if (sentido == 2) {
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.CALLE_PRINCIPAL);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setNumCarril(1);
				contenido[i][y].setVelocidadVia(50);
			}
			for (int i=x1-1;i<x2+1;i++) {
				contenido[i][y+1].setTipo(Constantes.CALLE_PRINCIPAL);
				contenido[i][y+1].setDireccion(Constantes.ABAJO);
				contenido[i][y+1].setNumCarril(2);
				contenido[i][y+1].setVelocidadVia(50);
			}
			
			for (int i=x1-1;i<x2+2;i++)
				contenido[i][y+2].setTipo(Constantes.SEPARACION);	
			
			for (int i=x1;i<x2+2;i++) {
				contenido[i][y+3].setTipo(Constantes.CALLE_PRINCIPAL);
				contenido[i][y+3].setDireccion(Constantes.ARRIBA);
				contenido[i][y+3].setNumCarril(2);
				contenido[i][y+3].setVelocidadVia(50);
			}
			for (int i=x1+1;i<x2+1;i++) {
				contenido[i][y+4].setTipo(Constantes.CALLE_PRINCIPAL);
				contenido[i][y+4].setDireccion(Constantes.ARRIBA);
				contenido[i][y+4].setNumCarril(1);
				contenido[i][y+4].setVelocidadVia(50);
			}
		}
	}
	
	private void calleHorizontal(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) {
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.CALLE);
				contenido[y][j].setDireccion(Constantes.DERECHA);
				contenido[y][j].setNumCarril(2);
				contenido[y][j].setVelocidadVia(40);
			}
			for (int j=x1+1;j<x2-1;j++) {
				contenido[y+1][j].setTipo(Constantes.CALLE);
				contenido[y+1][j].setDireccion(Constantes.DERECHA);
				contenido[y+1][j].setNumCarril(1);
				contenido[y+1][j].setVelocidadVia(40);
			}
		}
		else if (sentido == 2) {
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.CALLE);
				contenido[y][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y][j].setNumCarril(1);
				contenido[y][j].setVelocidadVia(40);
			}
			for (int j=x1-1;j<x2+1;j++) {
				contenido[y+1][j].setTipo(Constantes.CALLE);
				contenido[y+1][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y+1][j].setNumCarril(2);
				contenido[y+1][j].setVelocidadVia(40);
			}
		}
	}
	
	private void calleVertical(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) {
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.CALLE);
				contenido[i][y].setDireccion(Constantes.ARRIBA);
				contenido[i][y].setNumCarril(1);
				contenido[i][y].setVelocidadVia(40);
			}
			for (int i=x1-1;i<x2+1;i++) {
				contenido[i][y-1].setTipo(Constantes.CALLE);
				contenido[i][y-1].setDireccion(Constantes.ARRIBA);
				contenido[i][y-1].setNumCarril(2);
				contenido[i][y-1].setVelocidadVia(40);
			}
		}
		else if (sentido == 2) {
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.CALLE);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setNumCarril(2);
				contenido[i][y].setVelocidadVia(40);
			}
			for (int i=x1+1;i<x2-1;i++) {
				contenido[i][y-1].setTipo(Constantes.CALLE);
				contenido[i][y-1].setDireccion(Constantes.ABAJO);
				contenido[i][y-1].setNumCarril(1);
				contenido[i][y-1].setVelocidadVia(40);
			}
		}
	}
	
	private void callejon(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) 
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.CALLEJON);
				contenido[y][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y][j].setVelocidadVia(40);
			}
		else if (sentido == 2) 
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.CALLEJON);
				contenido[y][j].setDireccion(Constantes.DERECHA);
				contenido[y][j].setVelocidadVia(40);
			}
		else if (sentido == 3)
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.CALLEJON);
				contenido[i][y].setDireccion(Constantes.ARRIBA);
				contenido[i][y].setVelocidadVia(40);
			}
		else if (sentido == 4)
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.CALLEJON);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setVelocidadVia(40);
			}
	}
	
	private void semaforos(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1)
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.SEMAFORO);
				//contenido[i][y].setDireccion("");
			}
		else if (sentido == 2)
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.SEMAFORO);
				//contenido[y][j].setDireccion("");
			}
	}
	
	private void stops(int x,int y) {
		
		contenido[x][y].setTipo(Constantes.STOP);
	}
	
	private void cedas(int x,int y) {
		
		contenido[x][y].setTipo(Constantes.CEDA_EL_PASO);
	}
	
	private void crucesS(int x,int y) {
		
		contenido[x][y].setTipo(Constantes.CRUCE_SIMPLE);
		contenido[x][y].setDireccion("");
	}
	
	private void cruces(int x1,int x2,int y1,int y2) {
		
		for (int i=x1;i<x2;i++) 
			for(int j=y1;j<y2;j++) {
				contenido[i][j].setTipo(Constantes.CRUCE);
				contenido[i][j].setDireccion("");
				contenido[i][j].setNumCarril(0);
			}
		
	}
 }
