package mundo;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

import managerXML.Acceso;
import managerXML.Contenido;
import managerXML.Punto;
import managerXML.Tramo;
import managerXML.XMLManager;
import managerXML.Atributo;

//enum movimientos {DERECHA,IZQUIERDA,ARRIBA,ABAJO};

public class Matriz extends Observable {
	
	private ItemsMundo[][] contenido;
	private ArrayList<Coche> coches;
	private GeneradorVehiculos generador;
	private SemaforosManager semaforos;
	private Boolean parar;
	private Boolean finalizar;

	private Integer eleccion;
	private XMLManager manager;
	private Integer agresivos;
	private Integer normales;
	private Integer moderados;
	private Integer longitud;
	private Integer limite1;
	private Integer limite2;
	private Integer velocidadSimulacion;
	
	public void inicializar() {
		
		parar = false;
		finalizar = false;
		velocidadSimulacion = 0;
		manager = new XMLManager();
		File ficheroMapa = new File("./xml/Mapas/Inicializa1.xml");
		manager.lanzarManager(ficheroMapa);
		ArrayList<Atributo> atributos = manager.getAtributos();
		for (int i=0;i<atributos.size();i++) {
			Atributo atributo = atributos.get(i);
			longitud = atributo.getLongitud();
			limite1 = atributo.getLimite1();
			limite2 = atributo.getLimite2();
		}
		contenido = new ItemsMundo[longitud][longitud];
		coches = new ArrayList<Coche>();
		for (int i=0;i<longitud;i++)
			for (int j=0;j<longitud;j++) {
				if ((j<limite1) || (i<limite1) || (i>limite2) || (j>limite2)) 
					contenido[i][j] = new ItemsMundo(Constantes.BORDE,false);
				else
					contenido[i][j] = new ItemsMundo(Constantes.TIERRA,false);
			}
	}
	
	
	public void rellenarMatriz(Integer tipo,File fichero) {
		
		eleccion = tipo;
		manager.lanzarManager(fichero);
		ArrayList<Tramo> tramos = manager.getTramos();
		ArrayList<Acceso> entradas = manager.getEntradas();
		ArrayList<Acceso> salidas = manager.getSalidas();
		ArrayList<Tramo> principales = manager.getPrincipales();
		ArrayList<Tramo> horizontales = manager.getHorizontales();
		ArrayList<Tramo> verticales = manager.getVerticales();
		ArrayList<Tramo> callejones = manager.getCallejones();
		ArrayList<Tramo> semaforos = manager.getSemaforos();
		ArrayList<Punto> stops = manager.getStops();
		ArrayList<Punto> cedas = manager.getCedas();
		ArrayList<Punto> crucesS = manager.getCrucesSimples();
		ArrayList<Contenido> cruces = manager.getCruces(); 
		File ficheroCampo = new File("./xml/mapas/Paisaje1.xml");
		manager.lanzarManager(ficheroCampo);
		ArrayList<Contenido> campos = manager.getCampos();
		ArrayList<Contenido> edificios = manager.getEdificios();
		switch(tipo) {
		case 0:
			for (int i=0;i<entradas.size();i++) {
				Acceso entrada = entradas.get(i);
				entrada(entrada.getX1(),entrada.getX2(),entrada.getX3(),entrada.getX4(),
						entrada.getY1(),entrada.getY2(),entrada.getDir1(),entrada.getDir2());
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
			break;
		case 1:
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
				autovia(tramo.getX1(),tramo.getX2(),tramo.getY(),tramo.getSentido());
			}
			for (int i=0;i<entradas.size();i++) {
				Acceso entrada = entradas.get(i);
				entrada(entrada.getX1(),entrada.getX2(),entrada.getX3(),entrada.getX4(),
						entrada.getY1(),entrada.getY2(),entrada.getDir1(),entrada.getDir2());
			}
			for (int i=0;i<salidas.size();i++) {
				Acceso salida = salidas.get(i);
				salida(salida.getX1(),salida.getX2(),salida.getX3(),salida.getX4(),
						salida.getY1(),salida.getY2(),salida.getDir1(),salida.getDir2());
			}
			break;
		case 2:
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
				entrada(entrada.getX1(),entrada.getX2(),entrada.getX3(),entrada.getX4(),
						entrada.getY1(),entrada.getY2(),entrada.getDir1(),entrada.getDir2());
			}
			for (int i=0;i<salidas.size();i++) {
				Acceso salida = salidas.get(i);
				salida(salida.getX1(),salida.getX2(),salida.getX3(),salida.getX4(),
						salida.getY1(),salida.getY2(),salida.getDir1(),salida.getDir2());
			}
			break;
		}
		actualizar();
	}
	
	public Boolean getFinalizar() {
		return finalizar;
	}

	public void setFinalizar(Boolean finalizar) {
		this.finalizar = finalizar;
	}
	
	public Integer getVelocidadSimulacion() {
		
		return velocidadSimulacion;
	}

	public void setVelocidadSimulacion(Integer velocidadSimulacion) {
		
		this.velocidadSimulacion = velocidadSimulacion;
	}

	public void setParar(boolean stop) {
		
		parar = stop;
	}
	
	public boolean getParar() {
		
		return parar;
	}
	
	public GeneradorVehiculos getGenerador() {
		return generador;
	}

	public void setGenerador(GeneradorVehiculos generador) {
		this.generador = generador;
	}

	public Integer getEleccion() {
		return eleccion;
	}

	public void setEleccion(Integer eleccion) {
		this.eleccion = eleccion;
	}

	public XMLManager getManager() {
		return manager;
	}

	public void setManager(XMLManager manager) {
		this.manager = manager;
	}

	public Integer getAgresivos() {
		return agresivos;
	}

	public void setAgresivos(Integer agresivos) {
		this.agresivos = agresivos;
	}

	public Integer getNormales() {
		return normales;
	}

	public void setNormales(Integer normales) {
		this.normales = normales;
	}

	public Integer getModerados() {
		return moderados;
	}

	public void setModerados(Integer moderados) {
		this.moderados = moderados;
	}

	public Integer getLongitud() {
		return longitud;
	}

	public void setLongitud(Integer longitud) {
		this.longitud = longitud;
	}

	public Integer getLimite1() {
		return limite1;
	}

	public void setLimite1(Integer limite1) {
		this.limite1 = limite1;
	}

	public Integer getLimite2() {
		return limite2;
	}

	public void setLimite2(Integer limite2) {
		this.limite2 = limite2;
	}

	public void setContenido(ItemsMundo[][] contenido) {
		this.contenido = contenido;
	}

	public void setCoches(ArrayList<Coche> coches) {
		this.coches = coches;
	}

	public void setParar(Boolean parar) {
		this.parar = parar;
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
	
	private void salida(int x1,int x2,int x3,int x4,int y1,int y2,int dir1,
			int dir2) {
		
		for (int i=x1;i<x2;i++) {
			contenido[i][y1].setTipo(Constantes.CARRIL_SALIDA);
			contenido[i][y1].setTramo(1);
			contenido[i][y1].setDireccion(obtenerDireccion(dir1));
		}
	
		for (int j=x3;j<x4;j++) {
			contenido[y2][j].setTipo(Constantes.CARRIL_SALIDA);
			contenido[y2][j].setTramo(2);
			contenido[y2][j].setDireccion(obtenerDireccion(dir2));
		}
	}
	
	private void entrada(int x1,int x2,int x3,int x4,int y1,int y2,int dir1,
			int dir2) {
		
		for (int i=x1;i<x2;i++) {
			contenido[i][y1].setTipo(Constantes.CARRIL_ENTRADA); 
			contenido[i][y1].setInicio(true);
			contenido[i][y1].setTramo(1);
			contenido[i][y1].setDireccion(obtenerDireccion(dir1));
		}
	
		for (int j=x3;j<x4;j++) {
			contenido[y2][j].setTipo(Constantes.CARRIL_ENTRADA);
			contenido[y2][j].setInicio(true);
			contenido[y2][j].setTramo(2);
			contenido[y2][j].setDireccion(obtenerDireccion(dir2));
		}
	}
		
	private String obtenerDireccion(int dir) {
		
		String valor = null;
		switch(dir) {
		case 0:
			valor = Constantes.ARRIBA;
			break;
		case 1:
			valor = Constantes.ABAJO;
			break;
		case 2:
			valor = Constantes.IZQUIERDA;
			break;
		case 3:
			valor = Constantes.DERECHA;
			break;
		}
		return valor;
	}
	
	private void autovia(int x1,int x2,int y,int sentido) {

		if (sentido == 1) { //Izquierda
			for (int j=x1;j<x2-1;j++) {
				contenido[y][j].setTipo(Constantes.AUTOVIA);
				contenido[y][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y][j].setSentido(Constantes.DERECHA);
				contenido[y][j].setNumCarril(1);
			}
			for (int j=x1-1;j<x2;j++) {
				contenido[y+1][j].setTipo(Constantes.AUTOVIA);
				contenido[y+1][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y+1][j].setSentido(Constantes.DERECHA);
				contenido[y+1][j].setNumCarril(2);
			}
			
			for(int j=x1-2;j<x2+1;j++)
				contenido[y+2][j].setTipo(Constantes.SEPARACION);
			
			for (int j=x1-4;j<x2+1;j++) {
				contenido[y+3][j].setTipo(Constantes.AUTOVIA);
				contenido[y+3][j].setDireccion(Constantes.DERECHA);
				contenido[y+3][j].setSentido(Constantes.IZQUIERDA);
				contenido[y+3][j].setNumCarril(2);
			}
			for (int j=x1-5;j<x2+2;j++) {
				contenido[y+4][j].setTipo(Constantes.AUTOVIA);
				contenido[y+4][j].setDireccion(Constantes.DERECHA);
				contenido[y+4][j].setSentido(Constantes.IZQUIERDA);
				contenido[y+4][j].setNumCarril(1);
			}
		}
		else if (sentido == 2) { //Derecha
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.AUTOVIA);
				contenido[y][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y][j].setSentido(Constantes.IZQUIERDA);
				contenido[y][j].setNumCarril(1);
			}
			for (int j=x1+1;j<x2;j++) {
				contenido[y+1][j].setTipo(Constantes.AUTOVIA);
				contenido[y+1][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y+1][j].setSentido(Constantes.IZQUIERDA);
				contenido[y+1][j].setNumCarril(2);
			}
			for(int j=x1+1;j<x2-2;j++)
				contenido[y+2][j].setTipo(Constantes.SEPARACION);
			
			for (int j=x1+2;j<x2-4;j++) {
				contenido[y+3][j].setTipo(Constantes.AUTOVIA);
				contenido[y+3][j].setDireccion(Constantes.DERECHA);
				contenido[y+3][j].setSentido(Constantes.DERECHA);
				contenido[y+3][j].setNumCarril(2);
			}
			for (int j=x1+3;j<x2-5;j++) {
				contenido[y+4][j].setTipo(Constantes.AUTOVIA);
				contenido[y+4][j].setDireccion(Constantes.DERECHA);
				contenido[y+4][j].setSentido(Constantes.DERECHA);
				contenido[y+4][j].setNumCarril(1);
			}
		}
		else if (sentido == 3) {
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.AUTOVIA);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setSentido(Constantes.IZQUIERDA);
				contenido[i][y].setNumCarril(1);
			}
			for (int i=x1+1;i<x2-1;i++) {
				contenido[i][y+1].setTipo(Constantes.AUTOVIA);
				contenido[i][y+1].setDireccion(Constantes.ABAJO);
				contenido[i][y+1].setSentido(Constantes.IZQUIERDA);
				contenido[i][y+1].setNumCarril(2);
			}
			
			for (int i=x1+2;i<x2-1;i++) 
				contenido[i][y+2].setTipo(Constantes.SEPARACION);
			
			for (int i=x1+4;i<x2-2;i++) {
				contenido[i][y+3].setTipo(Constantes.AUTOVIA);
				contenido[i][y+3].setDireccion(Constantes.ARRIBA);
				contenido[i][y+3].setSentido(Constantes.DERECHA);
				contenido[i][y+3].setNumCarril(2);
			}
			for (int i=x1+5;i<x2-3;i++) {
				contenido[i][y+4].setTipo(Constantes.AUTOVIA);
				contenido[i][y+4].setDireccion(Constantes.ARRIBA);
				contenido[i][y+4].setSentido(Constantes.DERECHA);
				contenido[i][y+4].setNumCarril(1);
			}
		}
		else if (sentido == 4) {
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.AUTOVIA);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setSentido(Constantes.DERECHA);
				contenido[i][y].setNumCarril(1);
			}
			for (int i=x1-1;i<x2+1;i++) {
				contenido[i][y+1].setTipo(Constantes.AUTOVIA);
				contenido[i][y+1].setDireccion(Constantes.ABAJO);
				contenido[i][y+1].setSentido(Constantes.DERECHA);
				contenido[i][y+1].setNumCarril(2);
			}
			
			for (int i=x1-1;i<x2+2;i++) 
				contenido[i][y+2].setTipo(Constantes.SEPARACION);
			
			for (int i=x1-2;i<x2+4;i++) {
				contenido[i][y+3].setTipo(Constantes.AUTOVIA);
				contenido[i][y+3].setDireccion(Constantes.ARRIBA);
				contenido[i][y+3].setSentido(Constantes.IZQUIERDA);
				contenido[i][y+3].setNumCarril(2);
			}
			for (int i=x1-3;i<x2+5;i++) {
				contenido[i][y+4].setTipo(Constantes.AUTOVIA);
				contenido[i][y+4].setDireccion(Constantes.ARRIBA);
				contenido[i][y+4].setSentido(Constantes.IZQUIERDA);
				contenido[i][y+4].setNumCarril(1);
			}
		}
		else if (sentido == 5) {
			for (int i=x1;i<x2+5;i++) {
				contenido[i][y].setTipo(Constantes.AUTOVIA);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setSentido(Constantes.DERECHA);
				contenido[i][y].setNumCarril(1);
			}
			for (int i=x1-1;i<x2+4;i++) {
				contenido[i][y+1].setTipo(Constantes.AUTOVIA);
				contenido[i][y+1].setDireccion(Constantes.ABAJO);
				contenido[i][y+1].setSentido(Constantes.DERECHA);
				contenido[i][y+1].setNumCarril(2);
			}
			
			for (int i=x1-1;i<x2+4;i++) 
				contenido[i][y+2].setTipo(Constantes.SEPARACION);
			
			for (int i=x1-2;i<x2+3;i++) {
				contenido[i][y+3].setTipo(Constantes.AUTOVIA);
				contenido[i][y+3].setDireccion(Constantes.ARRIBA);
				contenido[i][y+3].setSentido(Constantes.IZQUIERDA);
				contenido[i][y+3].setNumCarril(2);
			}
			for (int i=x1-3;i<x2+2;i++) {
				contenido[i][y+4].setTipo(Constantes.AUTOVIA);
				contenido[i][y+4].setDireccion(Constantes.ARRIBA);
				contenido[i][y+4].setSentido(Constantes.IZQUIERDA);
				contenido[i][y+4].setNumCarril(1);
			}
		}
		else if (sentido == 6) {
			for (int i=x1-4;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.AUTOVIA);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setSentido(Constantes.DERECHA);
				contenido[i][y].setNumCarril(1);
			}
			for (int i=x1-3;i<x2+1;i++) {
				contenido[i][y+1].setTipo(Constantes.AUTOVIA);
				contenido[i][y+1].setDireccion(Constantes.ABAJO);
				contenido[i][y+1].setSentido(Constantes.DERECHA);
				contenido[i][y+1].setNumCarril(2);
			}
			
			for (int i=x1-1;i<x2+2;i++) 
				contenido[i][y+2].setTipo(Constantes.SEPARACION);
			
			for (int i=x1-2;i<x2+4;i++) {
				contenido[i][y+3].setTipo(Constantes.AUTOVIA);
				contenido[i][y+3].setDireccion(Constantes.ARRIBA);
				contenido[i][y+3].setSentido(Constantes.IZQUIERDA);
				contenido[i][y+3].setNumCarril(2);
			}
			for (int i=x1-3;i<x2+5;i++) {
				contenido[i][y+4].setTipo(Constantes.AUTOVIA);
				contenido[i][y+4].setDireccion(Constantes.ARRIBA);
				contenido[i][y+4].setSentido(Constantes.IZQUIERDA);
				contenido[i][y+4].setNumCarril(1);
			}
		}
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
	
	private void callePrincipal(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) {
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.CALLE_PRINCIPAL);
				contenido[y][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y][j].setNumCarril(1);
			}
			for (int j=x1-1;j<x2+1;j++) {
				contenido[y+1][j].setTipo(Constantes.CALLE_PRINCIPAL);
				contenido[y+1][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y+1][j].setNumCarril(2);
			}
			
			for (int j=x1-2;j<x2+1;j++)
				contenido[y+2][j].setTipo(Constantes.SEPARACION);	
			
			for (int j=x1-2;j<x2;j++) {
				contenido[y+3][j].setTipo(Constantes.CALLE_PRINCIPAL);
				contenido[y+3][j].setDireccion(Constantes.DERECHA);
				contenido[y+3][j].setNumCarril(2);
			}
			for (int j=x1-1;j<x2-1;j++) {
				contenido[y+4][j].setTipo(Constantes.CALLE_PRINCIPAL);
				contenido[y+4][j].setDireccion(Constantes.DERECHA);
				contenido[y+4][j].setNumCarril(1);
			}
		}
		else if (sentido == 2) {
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.CALLE_PRINCIPAL);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setNumCarril(1);
			}
			for (int i=x1-1;i<x2+1;i++) {
				contenido[i][y+1].setTipo(Constantes.CALLE_PRINCIPAL);
				contenido[i][y+1].setDireccion(Constantes.ABAJO);
				contenido[i][y+1].setNumCarril(2);
			}
			
			for (int i=x1-1;i<x2+2;i++)
				contenido[i][y+2].setTipo(Constantes.SEPARACION);	
			
			for (int i=x1;i<x2+2;i++) {
				contenido[i][y+3].setTipo(Constantes.CALLE_PRINCIPAL);
				contenido[i][y+3].setDireccion(Constantes.ARRIBA);
				contenido[i][y+3].setNumCarril(2);
			}
			for (int i=x1+1;i<x2+1;i++) {
				contenido[i][y+4].setTipo(Constantes.CALLE_PRINCIPAL);
				contenido[i][y+4].setDireccion(Constantes.ARRIBA);
				contenido[i][y+4].setNumCarril(1);
			}
		}
	}
	
	private void calleHorizontal(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) {
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.CALLE);
				contenido[y][j].setDireccion(Constantes.DERECHA);
				contenido[y][j].setNumCarril(2);
			}
			for (int j=x1+1;j<x2-1;j++) {
				contenido[y+1][j].setTipo(Constantes.CALLE);
				contenido[y+1][j].setDireccion(Constantes.DERECHA);
				contenido[y+1][j].setNumCarril(1);
			}
		}
		else if (sentido == 2) {
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.CALLE);
				contenido[y][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y][j].setNumCarril(1);
			}
			for (int j=x1-1;j<x2+1;j++) {
				contenido[y+1][j].setTipo(Constantes.CALLE);
				contenido[y+1][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y+1][j].setNumCarril(2);
			}
		}
		else if (sentido == 3) {
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.CALLE);
				contenido[y][j].setDireccion(Constantes.DERECHA);
				contenido[y][j].setNumCarril(1);
			}
			for (int j=x1-1;j<x2+1;j++) {
				contenido[y-1][j].setTipo(Constantes.CALLE);
				contenido[y-1][j].setDireccion(Constantes.DERECHA);
				contenido[y-1][j].setNumCarril(2);
			}
		}
		else if (sentido == 4) {
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.CALLE);
				contenido[y][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y][j].setNumCarril(1);
			}
			for (int j=x1-1;j<x2+1;j++) {
				contenido[y+1][j].setTipo(Constantes.CALLE);
				contenido[y+1][j].setDireccion(Constantes.IZQUIERDA);
				contenido[y+1][j].setNumCarril(2);
			}
		}
	}
	
	private void calleVertical(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) {
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.CALLE);
				contenido[i][y].setDireccion(Constantes.ARRIBA);
				contenido[i][y].setNumCarril(1);
			}
			for (int i=x1-1;i<x2+1;i++) {
				contenido[i][y-1].setTipo(Constantes.CALLE);
				contenido[i][y-1].setDireccion(Constantes.ARRIBA);
				contenido[i][y-1].setNumCarril(2);
			}
		}
		else if (sentido == 2) {
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.CALLE);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setNumCarril(2);
			}
			for (int i=x1+1;i<x2-1;i++) {
				contenido[i][y-1].setTipo(Constantes.CALLE);
				contenido[i][y-1].setDireccion(Constantes.ABAJO);
				contenido[i][y-1].setNumCarril(1);
			}
		}
		else if (sentido == 3) {
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.CALLE);
				contenido[i][y].setDireccion(Constantes.ARRIBA);
				contenido[i][y].setNumCarril(1);
			}
			for (int i=x1-1;i<x2+1;i++) {
				contenido[i][y-1].setTipo(Constantes.CALLE);
				contenido[i][y-1].setDireccion(Constantes.ARRIBA);
				contenido[i][y-1].setNumCarril(2);
			}
		}
		else if (sentido == 4) {
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.CALLE);
				contenido[i][y].setDireccion(Constantes.ABAJO);
				contenido[i][y].setNumCarril(1);
			}
			for (int i=x1-1;i<x2+1;i++) {
				contenido[i][y+1].setTipo(Constantes.CALLE);
				contenido[i][y+1].setDireccion(Constantes.ABAJO);
				contenido[i][y+1].setNumCarril(2);
			}
		}
	}
	
	private void callejon(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) 
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.CALLEJON);
				contenido[y][j].setDireccion(Constantes.IZQUIERDA);
			}
		else if (sentido == 2) 
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(Constantes.CALLEJON);
				contenido[y][j].setDireccion(Constantes.DERECHA);
			}
		else if (sentido == 3)
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.CALLEJON);
				contenido[i][y].setDireccion(Constantes.ARRIBA);
			}
		else if (sentido == 4)
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(Constantes.CALLEJON);
				contenido[i][y].setDireccion(Constantes.ABAJO);
			}
	}
	
	private void semaforos(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1)
			for (int i=x1;i<x2;i++)
				contenido[i][y].setTipo(Constantes.SEMAFORO);
		else if (sentido == 2)
			for (int j=x1;j<x2;j++)
				contenido[y][j].setTipo(Constantes.SEMAFORO);
	}
	
	private void stops(int x,int y) {
		
		contenido[x][y].setTipo(Constantes.STOP);
	}
	
	private void cedas(int x,int y) {
		
		contenido[x][y].setTipo(Constantes.CEDA_EL_PASO);
	}
	
	private void crucesS(int x,int y) {
		
		contenido[x][y].setTipo(Constantes.CRUCE);
	}
	
	private void cruces(int x1,int x2,int y1,int y2) {
		
		for (int i=x1;i<x2;i++) 
			for(int j=y1;j<y2;j++)
				contenido[i][j].setTipo(Constantes.CRUCE);
		
	}
	
	public ItemsMundo[][] getContenido() {
	
		return contenido;
	}
	
	public ItemsMundo getItem(int i,int j) {
		
		return contenido[i][j];
	}
	
	public ArrayList<Coche> getCoches() {
		
		return coches;
	}

	public void simular() {
		
		generador = new GeneradorVehiculos(this);
		generador.start();
		if (eleccion == 0) {
			semaforos = new SemaforosManager(this);
			semaforos.start();
		}
	}

	public void finalizar() {
		
		finalizar = true;
		generador.finalizar();
		if (eleccion == 0) 
			semaforos.finalizar();
		for (int i=0;i<coches.size();i++)
			coches.get(i).finalizar();
	}
	
	public void actualizar() {
		
		setChanged();
		notifyObservers();
	}

	public void obtenerConductores(Integer agre, Integer norm,
			Integer mod) {
		
		agresivos = agre;
		normales = norm;
		moderados = mod;
	}
}
