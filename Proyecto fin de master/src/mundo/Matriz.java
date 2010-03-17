package mundo;

import java.io.File;
import java.net.URI;
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
	
	private static final String ARRIBA = "Arriba";
	private static final String ABAJO = "Abajo";
	private static final String DERECHA = "Derecha";
	private static final String IZQUIERDA = "Izquierda";
	private static final String AUTOVIA_D_HD_1 = "Autovia_D_HD_1";
	private static final String AUTOVIA_D_HD_2 = "Autovia_D_HD_2";
	private static final String AUTOVIA_D_HI_1 = "Autovia_D_HI_1";
	private static final String AUTOVIA_D_HI_2 = "Autovia_D_HI_2";
	private static final String AUTOVIA_D_VA_1 = "Autovia_D_VA_1";
	private static final String AUTOVIA_D_VA_2 = "Autovia_D_VA_2";
	private static final String AUTOVIA_D_VB_1 = "Autovia_D_VB_1";
	private static final String AUTOVIA_D_VB_2 = "Autovia_D_VB_2";
	private static final String AUTOVIA_I_HD_1 = "Autovia_I_HD_1";
	private static final String AUTOVIA_I_HD_2 = "Autovia_I_HD_2";
	private static final String AUTOVIA_I_HI_1 = "Autovia_I_HI_1";
	private static final String AUTOVIA_I_HI_2 = "Autovia_I_HI_2";
	private static final String AUTOVIA_I_VA_1 = "Autovia_I_VA_1";
	private static final String AUTOVIA_I_VA_2 = "Autovia_I_VA_2";
	private static final String AUTOVIA_I_VB_1 = "Autovia_I_VB_1";
	private static final String AUTOVIA_I_VB_2 = "Autovia_I_VB_2";

	private static final String SECUNDARIA_D_HD = "Secundaria_D_HD";
	private static final String SECUNDARIA_D_HI = "Secundaria_D_HI";
	private static final String SECUNDARIA_D_VA = "Secundaria_D_VA";
	private static final String SECUNDARIA_D_VB = "Secundaria_D_VB";
	private static final String SECUNDARIA_I_HD = "Secundaria_I_HD";
	private static final String SECUNDARIA_I_HI = "Secundaria_I_HI";
	private static final String SECUNDARIA_I_VA = "Secundaria_I_VA";
	private static final String SECUNDARIA_I_VB = "Secundaria_I_VB";
	
	private static final String CARRIL_ENTRADA_H = "Carril_entrada_H";
	private static final String CARRIL_ENTRADA_V = "Carril_entrada_V";
	
	private static final String CARRIL_SALIDA_H = "Carril_salida_H";
	private static final String CARRIL_SALIDA_V = "Carril_salida_V";
	
	private static final String CALLE_PRINCIPAL_VA_1 = "Calle_principal_VA_1";
	private static final String CALLE_PRINCIPAL_VA_2 = "Calle_principal_VA_2";
	private static final String CALLE_PRINCIPAL_VB_1 = "Calle_principal_VB_1";
	private static final String CALLE_PRINCIPAL_VB_2 = "Calle_principal_VB_2";
	private static final String CALLE_PRINCIPAL_HD_1 = "Calle_principal_HD_1";
	private static final String CALLE_PRINCIPAL_HD_2 = "Calle_principal_HD_2";
	private static final String CALLE_PRINCIPAL_HI_1 = "Calle_principal_HI_1";
	private static final String CALLE_PRINCIPAL_HI_2 = "Calle_principal_HI_2";
	
	private static final String CALLE_HD_1 = "Calle_HD_1";
	private static final String CALLE_HD_2 = "Calle_HD_2";
	private static final  String CALLE_HI_1 = "Calle_HI_1";
	private static final String CALLE_HI_2 = "Calle_HI_2";
	private static final String CALLE_VA_1 = "Calle_VA_1";
	private static final String CALLE_VA_2 = "Calle_VA_2";
	private static final String CALLE_VB_1 = "Calle_VB_1";
	private static final String CALLE_VB_2 = "Calle_VB_2";
	
	private static final String CALLEJON_HD = "Callejon_HD";
	private static final String CALLEJON_HI = "Callejon_HI";
	private static final String CALLEJON_VA = "Callejon_VA";
	private static final String CALLEJON_VB = "Callejon_VB";
	
	private static final String CRUCE = "Cruce";
	private static final String EDIFICIO = "Edificio";
	private static final String CAMPO = "Campo";
	private static final String TIERRA = "Tierra";
	private static final String STOP = "Stop";
	private static final String CEDA_EL_PASO = "Ceda";
	private static final String SEMAFORO_ROJO = "Semaforo_Rojo";
	private static final String SEMAFORO_AMARILLO = "Semaforo_Amarillo";
	private static final String SEMAFORO_VERDE = "Semaforo_Verde";
	private static final String AUTOMOVIL = "Automovil";
	private static final String CAMION = "Camion";
	private static final String MOTOCICLETA = "Motocicleta";
	private static final String EMERGENCIA = "Emergencia";
	private static final String BORDE = "Borde";
	private static final  String SEPARACION = "Separacion";
	
	private ItemsMundo[][] contenido;
	private ArrayList<Coche> coches;
	private GeneradorVehiculos generador;
	private Boolean parar;
	private Integer eleccion;
	private XMLManager manager;
	private Integer agresivos;
	private Integer normales;
	private Integer moderados;
	private Integer longitud;
	private Integer limite1;
	private Integer limite2;
	
	public void inicializar() {
		
		parar = false;
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
					contenido[i][j] = new ItemsMundo(BORDE,false);
				else
					contenido[i][j] = new ItemsMundo(TIERRA,false);
			}
	}
	
	public void setParar(boolean stop) {
		
		parar = stop;
	}
	
	public boolean getParar() {
		
		return parar;
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
						entrada.getY1(),entrada.getY2());
			}
			for (int i=0;i<salidas.size();i++) {
				Acceso salida = salidas.get(i);
				salida(salida.getX1(),salida.getX2(),salida.getX3(),salida.getX4(),
						salida.getY1(),salida.getY2());
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
						entrada.getY1(),entrada.getY2());
			}
			for (int i=0;i<salidas.size();i++) {
				Acceso salida = salidas.get(i);
				salida(salida.getX1(),salida.getX2(),salida.getX3(),salida.getX4(),
						salida.getY1(),salida.getY2());
			}
			break;
		}
		actualizar();
	}
	
	private void construirCampo(int x1,int x2,int y1,int y2) {
		
		for (int i=x1;i<x2;i++)
			for(int j=y1;j<y2;j++)
				contenido[i][j].setTipo(CAMPO);
	}
	
	private void construirEdificio(int x1,int x2,int y1,int y2) {
		
		for (int i=x1;i<x2;i++)
			for(int j=y1;j<y2;j++)
				contenido[i][j].setTipo(EDIFICIO);
	}
	
	private void salida(int x1,int x2,int x3,int x4,int y1,int y2) {
		
		for (int i=x1;i<x2;i++) {
			contenido[i][y1].setTipo(CARRIL_SALIDA_V);
			contenido[i][y1].setDireccion(ARRIBA);
		}
	
		for (int j=x3;j<x4;j++) {
			contenido[y2][j].setTipo(CARRIL_SALIDA_H);
			contenido[y2][j].setDireccion(IZQUIERDA);
		}
	}
	
	private void entrada(int x1,int x2,int x3,int x4,int y1,int y2) {
		
		for (int i=x1;i<x2;i++) {
			contenido[i][y1].setTipo(CARRIL_ENTRADA_V);
			contenido[i][y1].setInicio(true);
			contenido[i][y1].setDireccion(ARRIBA);
		}
	
		for (int j=x3;j<x4;j++) {
			contenido[y2][j].setTipo(CARRIL_ENTRADA_H);
			contenido[y2][j].setInicio(true);
			contenido[y2][j].setDireccion(IZQUIERDA);
		}
	}
	
	private void autovia(int x1,int x2,int y,int sentido) {

		if (sentido == 1) { //Izquierda
			for (int j=x1;j<x2-1;j++) {
				contenido[y][j].setTipo(AUTOVIA_D_HI_1);
				contenido[y][j].setDireccion(IZQUIERDA);
			}
			for (int j=x1-1;j<x2;j++) {
				contenido[y+1][j].setTipo(AUTOVIA_D_HI_2);
				contenido[y+1][j].setDireccion(IZQUIERDA);
			}
			
			for(int j=x1-2;j<x2+1;j++)
				contenido[y+2][j].setTipo(SEPARACION);
			
			for (int j=x1-4;j<x2+1;j++) {
				contenido[y+3][j].setTipo(AUTOVIA_I_HD_2);
				contenido[y+3][j].setDireccion(DERECHA);
			}
			for (int j=x1-5;j<x2+2;j++) {
				contenido[y+4][j].setTipo(AUTOVIA_I_HD_1);
				contenido[y+4][j].setDireccion(DERECHA);
			}
		}
		else if (sentido == 2) { //Derecha
			for (int j=x1;j<x2;j++) {
				contenido[y][j].setTipo(AUTOVIA_I_HI_1);
				contenido[y][j].setDireccion(IZQUIERDA);
			}
			for (int j=x1+1;j<x2;j++) {
				contenido[y+1][j].setTipo(AUTOVIA_I_HI_2);
				contenido[y+1][j].setDireccion(IZQUIERDA);
			}
			for(int j=x1+1;j<x2-2;j++)
				contenido[y+2][j].setTipo(SEPARACION);
			
			for (int j=x1+2;j<x2-4;j++) {
				contenido[y+3][j].setTipo(AUTOVIA_D_HD_2);
				contenido[y+3][j].setDireccion(DERECHA);
			}
			for (int j=x1+3;j<x2-5;j++) {
				contenido[y+4][j].setTipo(AUTOVIA_D_HD_1);
				contenido[y+4][j].setDireccion(DERECHA);
			}
		}
		else if (sentido == 3) {
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(AUTOVIA_I_VB_1);
				contenido[i][y].setDireccion(ABAJO);
			}
			for (int i=x1+1;i<x2-1;i++) {
				contenido[i][y+1].setTipo(AUTOVIA_I_VB_2);
				contenido[i][y+1].setDireccion(ABAJO);
			}
			
			for (int i=x1+2;i<x2-1;i++) 
				contenido[i][y+2].setTipo(SEPARACION);
			
			for (int i=x1+4;i<x2-2;i++) {
				contenido[i][y+3].setTipo(AUTOVIA_D_VA_2);
				contenido[i][y+3].setDireccion(ARRIBA);
			}
			for (int i=x1+5;i<x2-3;i++) {
				contenido[i][y+4].setTipo(AUTOVIA_D_VA_1);
				contenido[i][y+4].setDireccion(ARRIBA);
			}
		}
		else if (sentido == 4) {
			for (int i=x1;i<x2;i++) {
				contenido[i][y].setTipo(AUTOVIA_D_VB_1);
				contenido[i][y].setDireccion(ABAJO);
			}
			for (int i=x1-1;i<x2+1;i++) {
				contenido[i][y+1].setTipo(AUTOVIA_D_VB_2);
				contenido[i][y+1].setDireccion(ABAJO);
			}
			
			for (int i=x1-1;i<x2+2;i++) 
				contenido[i][y+2].setTipo(SEPARACION);
			
			for (int i=x1-2;i<x2+4;i++) {
				contenido[i][y+3].setTipo(AUTOVIA_I_VA_2);
				contenido[i][y+3].setDireccion(ARRIBA);
			}
			for (int i=x1-3;i<x2+5;i++) {
				contenido[i][y+4].setTipo(AUTOVIA_I_VA_1);
				contenido[i][y+4].setDireccion(ARRIBA);
			}
		}
		else if (sentido == 5) {
			for (int i=x1;i<x2+5;i++) {
				contenido[i][y].setTipo(AUTOVIA_D_VB_1);
				contenido[i][y].setDireccion(ABAJO);
			}
			for (int i=x1-1;i<x2+4;i++) {
				contenido[i][y+1].setTipo(AUTOVIA_D_VB_2);
				contenido[i][y+1].setDireccion(ABAJO);
			}
			
			for (int i=x1-1;i<x2+4;i++) 
				contenido[i][y+2].setTipo(SEPARACION);
			
			for (int i=x1-2;i<x2+3;i++) {
				contenido[i][y+3].setTipo(AUTOVIA_I_VA_2);
				contenido[i][y+3].setDireccion(ARRIBA);
			}
			for (int i=x1-3;i<x2+2;i++) {
				contenido[i][y+4].setTipo(AUTOVIA_I_VA_1);
				contenido[i][y+4].setDireccion(ARRIBA);
			}
		}
		else if (sentido == 6) {
			for (int i=x1-4;i<x2;i++) {
				contenido[i][y].setTipo(AUTOVIA_D_VB_1);
				contenido[i][y].setDireccion(ABAJO);
			}
			for (int i=x1-3;i<x2+1;i++) {
				contenido[i][y+1].setTipo(AUTOVIA_D_VB_2);
				contenido[i][y+1].setDireccion(ABAJO);
			}
			
			for (int i=x1-1;i<x2+2;i++) 
				contenido[i][y+2].setTipo(SEPARACION);
			
			for (int i=x1-2;i<x2+4;i++) {
				contenido[i][y+3].setTipo(AUTOVIA_I_VA_2);
				contenido[i][y+3].setDireccion(ARRIBA);
			}
			for (int i=x1-3;i<x2+5;i++) {
				contenido[i][y+4].setTipo(AUTOVIA_I_VA_1);
				contenido[i][y+4].setDireccion(ARRIBA);
			}
		}
	}
	
	private void secundaria(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) {
			for (int j=x1;j<x2;j++) 
				contenido[y][j].setTipo(SECUNDARIA_D_HI);
			for (int j=x1-2;j<x2;j++) 
				contenido[y+1][j].setTipo(SECUNDARIA_I_HD);
		}
		else if (sentido == 2) {
			for (int j=x1;j<x2;j++) 
				contenido[y][j].setTipo(SECUNDARIA_I_HI);
			for (int j=x1;j<x2-2;j++) 
				contenido[y+1][j].setTipo(SECUNDARIA_D_HD);
		}
		else if (sentido == 3) {
			for (int i=x1;i<x2;i++) 
				contenido[i][y].setTipo(SECUNDARIA_D_VB);
			for (int i=x1;i<x2+2;i++) 
				contenido[i][y+1].setTipo(SECUNDARIA_I_VA);
		}
		else if (sentido == 4) {
			for (int i=x1;i<x2;i++) 
				contenido[i][y].setTipo(SECUNDARIA_I_VB);
			for (int i=x1+2;i<x2;i++) 
				contenido[i][y+1].setTipo(SECUNDARIA_D_VA);
		}
		else if (sentido == 5) {
			for (int i=x1;i<x2;i++) 
				contenido[i][y].setTipo(SECUNDARIA_I_VB);
			for (int i=x1;i<x2;i++) 
				contenido[i][y+1].setTipo(SECUNDARIA_D_VA);
		}
		else if (sentido == 6) {
			for (int i=x1;i<x2;i++) 
				contenido[i][y].setTipo(SECUNDARIA_I_VB);
			for (int i=x1+2;i<x2+2;i++) 
				contenido[i][y+1].setTipo(SECUNDARIA_D_VA);
		}
	}
	
	private void callePrincipal(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) {
			for (int j=x1;j<x2;j++) 
				contenido[y][j].setTipo(CALLE_PRINCIPAL_HI_1);
			for (int j=x1-1;j<x2+1;j++) 
				contenido[y+1][j].setTipo(CALLE_PRINCIPAL_HI_2);
			
			for (int j=x1-2;j<x2+1;j++)
				contenido[y+2][j].setTipo(SEPARACION);	
			
			for (int j=x1-2;j<x2;j++)
				contenido[y+3][j].setTipo(CALLE_PRINCIPAL_HD_2);
			for (int j=x1-1;j<x2-1;j++)
				contenido[y+4][j].setTipo(CALLE_PRINCIPAL_HD_1);
		}
		else if (sentido == 2) {
			for (int i=x1;i<x2;i++) 
				contenido[i][y].setTipo(CALLE_PRINCIPAL_VB_1);
			for (int i=x1-1;i<x2+1;i++) 
				contenido[i][y+1].setTipo(CALLE_PRINCIPAL_VB_2);
			
			for (int i=x1-1;i<x2+2;i++)
				contenido[i][y+2].setTipo(SEPARACION);	
			
			for (int i=x1;i<x2+2;i++) 
				contenido[i][y+3].setTipo(CALLE_PRINCIPAL_VA_2);
			for (int i=x1+1;i<x2+1;i++) 
				contenido[i][y+4].setTipo(CALLE_PRINCIPAL_VA_1);	
		}
	}
	
	private void calleHorizontal(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) {
			for (int j=x1;j<x2;j++) 
				contenido[y][j].setTipo(CALLE_HD_2);
			for (int j=x1+1;j<x2-1;j++) 
				contenido[y+1][j].setTipo(CALLE_HD_1);
		}
		else if (sentido == 2) {
			for (int j=x1;j<x2;j++) 
				contenido[y][j].setTipo(CALLE_HI_1);
			for (int j=x1-1;j<x2+1;j++) 
				contenido[y+1][j].setTipo(CALLE_HI_2);
		}
		else if (sentido == 3) {
			for (int j=x1;j<x2;j++) 
				contenido[y][j].setTipo(CALLE_HD_1);
			for (int j=x1-1;j<x2+1;j++) 
				contenido[y-1][j].setTipo(CALLE_HD_2);
		}
		else if (sentido == 4) {
			for (int j=x1;j<x2;j++) 
				contenido[y][j].setTipo(CALLE_HI_1);
			for (int j=x1-1;j<x2+1;j++) 
				contenido[y+1][j].setTipo(CALLE_HI_2);
		}
	}
	
	private void calleVertical(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) {
			for (int i=x1;i<x2;i++) 
				contenido[i][y].setTipo(CALLE_VA_1);
			for (int i=x1-1;i<x2+1;i++) 
				contenido[i][y-1].setTipo(CALLE_VA_2);
		}
		else if (sentido == 2) {
			for (int i=x1;i<x2;i++) 
				contenido[i][y].setTipo(CALLE_VB_2);
			for (int i=x1+1;i<x2-1;i++)
				contenido[i][y-1].setTipo(CALLE_VB_1);
		}
		else if (sentido == 3) {
			for (int i=x1;i<x2;i++) 
				contenido[i][y].setTipo(CALLE_VA_1);
			for (int i=x1-1;i<x2+1;i++) 
				contenido[i][y-1].setTipo(CALLE_VA_2);
		}
		else if (sentido == 4) {
			for (int i=x1;i<x2;i++) 
				contenido[i][y].setTipo(CALLE_VB_1);
			for (int i=x1-1;i<x2+1;i++) 
				contenido[i][y+1].setTipo(CALLE_VB_2);
		}
	}
	
	private void callejon(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) 
			for (int j=x1;j<x2;j++) 
				contenido[y][j].setTipo(CALLEJON_HI);
		else if (sentido == 2) 
			for (int j=x1;j<x2;j++)
				contenido[y][j].setTipo(CALLEJON_HD);
		else if (sentido == 3)
			for (int i=x1;i<x2;i++) 
				contenido[i][y].setTipo(CALLEJON_VA);
		else if (sentido == 4)
			for (int i=x1;i<x2;i++) 
				contenido[i][y].setTipo(CALLEJON_VB);
	}
	
	private void semaforos(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1)
			for (int i=x1;i<x2;i++)
				contenido[i][y].setTipo(SEMAFORO_VERDE);
		else if (sentido == 2)
			for (int j=x1;j<x2;j++)
				contenido[y][j].setTipo(SEMAFORO_VERDE);
	}
	
	private void stops(int x,int y) {
		
		contenido[x][y].setTipo(STOP);
	}
	
	private void cedas(int x,int y) {
		
		contenido[x][y].setTipo(CEDA_EL_PASO);
	}
	
	private void crucesS(int x,int y) {
		
		contenido[x][y].setTipo(CRUCE);
	}
	
	private void cruces(int x1,int x2,int y1,int y2) {
		
		for (int i=x1;i<x2;i++) 
			for(int j=y1;j<y2;j++)
				contenido[i][j].setTipo(CRUCE);
		
	}
	
	public ItemsMundo[][] getContenido() {
	
		return contenido;
	}
	
	public String getTipo(int i,int j) {
		
		return contenido[i][j].getTipo();
	}
	
	public ArrayList<Coche> getCoches() {
		
		return coches;
	}

	public void simular() {
		
		generador = new GeneradorVehiculos(this,contenido,eleccion,longitud,
				moderados,normales,agresivos);
		generador.start();
		
	}
	
	public void actualizar() {
		
		setChanged();
		notifyObservers();
	}

	public void obtenerConductores(Integer agre, Integer norm,
			Integer mod) {
		
		agresivos = 0;//agre;
		normales = norm+1;
		moderados = 0;//mod;
	}
	
	
}
