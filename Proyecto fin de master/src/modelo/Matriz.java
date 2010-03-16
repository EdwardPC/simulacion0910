package modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

//enum movimientos {DERECHA,IZQUIERDA,ARRIBA,ABAJO};

public class Matriz extends Observable {
	
	private static String AUTOVIA_D_HD_1 = "Autovia_D_HD_1";
	private static String AUTOVIA_D_HD_2 = "Autovia_D_HD_2";
	private static String AUTOVIA_D_HI_1 = "Autovia_D_HI_1";
	private static String AUTOVIA_D_HI_2 = "Autovia_D_HI_2";
	private static String AUTOVIA_D_VA_1 = "Autovia_D_VA_1";
	private static String AUTOVIA_D_VA_2 = "Autovia_D_VA_2";
	private static String AUTOVIA_D_VB_1 = "Autovia_D_VB_1";
	private static String AUTOVIA_D_VB_2 = "Autovia_D_VB_2";
	private static String AUTOVIA_I_HD_1 = "Autovia_I_HD_1";
	private static String AUTOVIA_I_HD_2 = "Autovia_I_HD_2";
	private static String AUTOVIA_I_HI_1 = "Autovia_I_HI_1";
	private static String AUTOVIA_I_HI_2 = "Autovia_I_HI_2";
	private static String AUTOVIA_I_VA_1 = "Autovia_I_VA_1";
	private static String AUTOVIA_I_VA_2 = "Autovia_I_VA_2";
	private static String AUTOVIA_I_VB_1 = "Autovia_I_VB_1";
	private static String AUTOVIA_I_VB_2 = "Autovia_I_VB_2";

	private static String SECUNDARIA_D_HD = "Secundaria_D_HD";
	private static String SECUNDARIA_D_HI = "Secundaria_D_HI";
	private static String SECUNDARIA_D_VA = "Secundaria_D_VA";
	private static String SECUNDARIA_D_VB = "Secundaria_D_VB";
	private static String SECUNDARIA_I_HD = "Secundaria_I_HD";
	private static String SECUNDARIA_I_HI = "Secundaria_I_HI";
	private static String SECUNDARIA_I_VA = "Secundaria_I_VA";
	private static String SECUNDARIA_I_VB = "Secundaria_I_VB";
	
	private static String CARRIL_ENTRADA_H = "Carril_entrada_H";
	private static String CARRIL_ENTRADA_V = "Carril_entrada_V";
	
	private static String CARRIL_SALIDA_H = "Carril_salida_H";
	private static String CARRIL_SALIDA_V = "Carril_salida_V";
	
	private static String CALLE_PRINCIPAL_VA_1 = "Calle_principal_VA_1";
	private static String CALLE_PRINCIPAL_VA_2 = "Calle_principal_VA_2";
	private static String CALLE_PRINCIPAL_VB_1 = "Calle_principal_VB_1";
	private static String CALLE_PRINCIPAL_VB_2 = "Calle_principal_VB_2";
	private static String CALLE_PRINCIPAL_HD_1 = "Calle_principal_HD_1";
	private static String CALLE_PRINCIPAL_HD_2 = "Calle_principal_HD_2";
	private static String CALLE_PRINCIPAL_HI_1 = "Calle_principal_HI_1";
	private static String CALLE_PRINCIPAL_HI_2 = "Calle_principal_HI_2";
	
	private static String CALLE_HD_1 = "Calle_HD_1";
	private static String CALLE_HD_2 = "Calle_HD_2";
	private static String CALLE_HI_1 = "Calle_HI_1";
	private static String CALLE_HI_2 = "Calle_HI_2";
	private static String CALLE_VA_1 = "Calle_VA_1";
	private static String CALLE_VA_2 = "Calle_VA_2";
	private static String CALLE_VB_1 = "Calle_VB_1";
	private static String CALLE_VB_2 = "Calle_VB_2";
	
	private static String CALLEJON_HD = "Callejon_HD";
	private static String CALLEJON_HI = "Callejon_HI";
	private static String CALLEJON_VA = "Callejon_VA";
	private static String CALLEJON_VB = "Callejon_VB";
	
	private static String CRUCE = "Cruce";
	private static String EDIFICIO = "Edificio";
	private static String CAMPO = "Campo";
	private static String TIERRA = "Tierra";
	private static String STOP = "Stop";
	private static String CEDA_EL_PASO = "Ceda";
	private static String SEMAFORO_ROJO = "Semaforo_Rojo";
	private static String SEMAFORO_AMARILLO = "Semaforo_Amarillo";
	private static String SEMAFORO_VERDE = "Semaforo_Verde";
	private static String AUTOMOVIL = "Automovil";
	private static String CAMION = "Camion";
	private static String MOTOCICLETA = "Motocicleta";
	private static String EMERGENCIA = "Emergencia";
	private static String BORDE = "Borde";
	private static String SEPARACION = "Separacion";
	
	private String[][] contenido;
	private ArrayList<Coche> coches;
	private Boolean parar;
	private Integer eleccion;
	private XMLManager manager;
	
	public void inicializar() {
		
		parar = false;
		manager = new XMLManager();
		manager.lanzarManager("mapa.xml");
		int longitud = 0;
		int limite1 = 0;
		int limite2 = 0;
		ArrayList<Atributo> atributos = manager.getAtributos();
		for (int i=0;i<atributos.size();i++) {
			Atributo atributo = atributos.get(i);
			longitud = atributo.getLongitud();
			limite1 = atributo.getLimite1();
			limite2 = atributo.getLimite2();
		}
		contenido = new String[longitud][longitud];
		coches = new ArrayList<Coche>();
		for (int i=0;i<longitud;i++)
			for (int j=0;j<longitud;j++)
				if ((j<limite1) || (i<limite1) || (i>limite2) || (j>limite2))
					contenido[i][j] = BORDE;
				else
					contenido[i][j] = TIERRA;
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
			ponerSemaforos();
			ponerStops();
			ponerCedas();
			ponerCruces();
			break;
		case 1:
			construirPaisajeCampo();
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
			construirPaisajeCampo();
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
	
	private void construirPaisajeCampo() {
		
		for (int i=60;i<70;i++)
			for(int j=60;j<75;j++)
				contenido[i][j] = CAMPO;
		for (int i=80;i<90;i++)
			for(int j=20;j<25;j++)
				contenido[i][j] = CAMPO;
		for (int i=20;i<50;i++)
			for (int j=10;j<30;j++)
				contenido[i][j] = CAMPO;
		for (int i=40;i<79;i++)
			for (int j=24;j<70;j++)
				contenido[i][j] = CAMPO;
		for (int i=30;i<50;i++)
				for (int j=60;j<80;j++)
					contenido[i][j] = CAMPO;
		for (int i=8;i<20;i++)
			for (int j=70;j<91;j++)
				contenido[i][j] = CAMPO;
		for (int i=70;i<85;i++)
			for (int j=80;j<89;j++) 
				contenido[i][j] = EDIFICIO;
		for (int i=9;i<18;i++)
			for (int j=20;j<50;j++)
				contenido[i][j] = EDIFICIO;
		for (int i=27;i<43;i++)
			for (int j=84;j<89;j++)
				contenido[i][j] = EDIFICIO;
		for (int i=80;i<90;i++)
			for (int j=40;j<67;j++)
				contenido[i][j] = EDIFICIO;
	}
	
	private void salida(int x1,int x2,int x3,int x4,int y1,int y2) {
		
		//x1=25;x2=45;y1=93;
		for (int i=x1;i<x2;i++) 
			contenido[i][y1] = CARRIL_SALIDA_V;
		//x3=85;x4=90;y2=44;
		for (int j=x3;j<x4;j++)
			contenido[y2][j] = CARRIL_SALIDA_H;
	}
	
	private void entrada(int x1,int x2,int x3,int x4,int y1,int y2) {
		
		//x1=50;x2=70;y1=6;
		for (int i=x1;i<x2;i++)
			contenido[i][y1] = CARRIL_ENTRADA_V;
		//x3=6;x4=11;y2=69
		for (int j=x3;j<x4;j++)
			contenido[y2][j] = CARRIL_ENTRADA_H;
	}
	
	private void autovia(int x1,int x2,int y,int sentido) {
		//x1=5;x2=93;y=91;sentido=1;
		if (sentido == 1) { //Izquierda
			for (int j=x1;j<x2;j++)
				contenido[y][j] = AUTOVIA_D_HI_1;
			for (int j=x1-1;j<x2;j++)
				contenido[y+1][j] = AUTOVIA_D_HI_2;
			
			for(int j=x1-1;j<x2+1;j++)
				contenido[y+2][j] = SEPARACION;
			
			for (int j=x1-2;j<x2+1;j++) 
				contenido[y+3][j] = AUTOVIA_I_HD_2;
			for (int j=x1-2;j<x2+2;j++) 
				contenido[y+4][j] = AUTOVIA_I_HD_1;
		}
		//x1=4;x2=96;y=3;sentido=2;
		else if (sentido == 2) { //Derecha
			for (int j=x1;j<x2;j++) 
				contenido[y][j] = AUTOVIA_I_HI_1;
			for (int j=x1+1;j<x2;j++) 
				contenido[y+1][j] = AUTOVIA_I_HI_2;
			
			for(int j=x1+1;j<x2-2;j++)
				contenido[y+2][j] = SEPARACION;
			
			for (int j=x1+2;j<92;j++) 
				contenido[y+3][j] = AUTOVIA_D_HD_2;
			for (int j=x1+3;j<91;j++) 
				contenido[y+4][j] = AUTOVIA_D_HD_1;
		}
		else if (sentido == 3) {
			// x1=3;x2=95;y=3;sentido=3;
			for (int i=x1;i<x2;i++) 
				contenido[i][y] = AUTOVIA_I_VB_1;
			for (int i=x1+1;i<x2-1;i++)
				contenido[i][y+1] = AUTOVIA_I_VB_2;
			
			for (int i=x1+2;i<x2-1;i++) 
				contenido[i][y+2] = SEPARACION;
			
			for (int i=x1+4;i<x2-2;i++) 
				contenido[i][y+3] = AUTOVIA_D_VA_2;
			for (int i=x1+5;i<x2-3;i++) 
				contenido[i][y+4] = AUTOVIA_D_VA_1;
		}
		else if (sentido == 4) {
			//x1=7;x2=91;y=91;sentido=4;
			for (int i=x1;i<x2;i++) 
				contenido[i][y] = AUTOVIA_D_VB_1;
			for (int i=x1-1;i<x2+1;i++) 
				contenido[i][y+1] = AUTOVIA_D_VB_2;
			
			for (int i=x1-1;i<x2+2;i++) 
				contenido[i][y+2] = SEPARACION;
			
			for (int i=x1-2;i<x2+4;i++) 
				contenido[i][y+3] = AUTOVIA_I_VA_2;
			for (int i=x1-3;i<x2+5;i++)
				contenido[i][y+4] = AUTOVIA_I_VA_1;
		}
	}
	
	private void secundaria(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) {
			//x1=5;x2=96;y=94;sentido=1;
			for (int j=x1;j<x2;j++) 
				contenido[y][j] = SECUNDARIA_D_HI;
			for (int j=x1-2;j<x2;j++) 
				contenido[y+1][j] = SECUNDARIA_I_HD;
		}
		else if (sentido == 2) {
			//x1=4;x2=96;y=3;sentido=2;
			for (int j=x1;j<x2;j++) 
				contenido[y][j] = SECUNDARIA_I_HI;
			for (int j=x1;j<x2-2;j++) 
				contenido[y+1][j] = SECUNDARIA_D_HD;
		}
		else if (sentido == 3) {
			//x1=4;x2=94;y=94;sentido=3;
			for (int i=x1;i<x2;i++) 
				contenido[i][y] = SECUNDARIA_D_VB;
			for (int i=x1;i<x2+2;i++) 
				contenido[i][y+1] = SECUNDARIA_I_VA;
		}
		else if (sentido == 4) {
			//x1=3;x2=95;y=3;sentido=4;
			for (int i=x1;i<x2;i++) 
				contenido[i][y] = SECUNDARIA_I_VB;
			for (int i=x1+2;i<x2;i++) 
				contenido[i][y+1] = SECUNDARIA_D_VA;
		}
	}
	
	private void callePrincipal(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) {
			//x1=7;x2=93;y=48;sentido=1;
			for (int j=x1;j<x2;j++) 
				contenido[y][j] = CALLE_PRINCIPAL_HI_1;
			for (int j=x1-1;j<x2+1;j++) 
				contenido[y+1][j] = CALLE_PRINCIPAL_HI_2;
			
			for (int j=x1-2;j<x2+1;j++)
				contenido[y+2][j] = SEPARACION;	
			
			for (int j=x1-2;j<x2;j++)
				contenido[y+3][j] = CALLE_PRINCIPAL_HD_2;
			for (int j=x1-1;j<x2-1;j++)
				contenido[y+4][j] = CALLE_PRINCIPAL_HD_1;
		}
		else if (sentido == 2) {
			//x1=6;x2=92;y=48;sentido=2;
			for (int i=x1;i<x2;i++) 
				contenido[i][y] = CALLE_PRINCIPAL_VB_1;
			for (int i=x1-1;i<x2+1;i++) 
				contenido[i][y+1] = CALLE_PRINCIPAL_VB_2;
			
			for (int i=x1-1;i<x2+2;i++)
				contenido[i][y+2] = SEPARACION;	
			
			for (int i=x1;i<x2+2;i++) 
				contenido[i][y+3] = CALLE_PRINCIPAL_VA_2;
			for (int i=x1+1;i<x2+1;i++) 
				contenido[i][y+4] = CALLE_PRINCIPAL_VA_1;	
		}
	}
	
	private void calleHorizontal(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) {
			//x1=51;x2=93;y=5;sentido=1;
			for (int j=x1;j<x2;j++) 
				contenido[y][j] = CALLE_HD_2;
			for (int j=x1+1;j<x2-1;j++) 
				contenido[y+1][j] = CALLE_HD_1;
		}
		else if (sentido == 2) {
			//x1=53;x2=93;y=92;sentido=2;
			for (int j=x1;j<x2;j++) 
				contenido[y][j] = CALLE_HI_1;
			for (int j=x1-1;j<x2+1;j++) 
				contenido[y+1][j] = CALLE_HI_2;
		}
		else if (sentido == 3) {
			//x1=6;x2=48;y=6;sentido=3;
			for (int j=x1;j<x2;j++) 
				contenido[y][j] = CALLE_HD_1;
			for (int j=x1-1;j<x2+1;j++) 
				contenido[y-1][j] = CALLE_HD_2;
		}
		else if (sentido == 4) {
			//x1=7;x2=49;y=92;sentido=4;
			for (int j=x1;j<x2;j++) 
				contenido[y][j] = CALLE_HI_1;
			for (int j=x1-1;j<x2+1;j++) 
				contenido[y+1][j] = CALLE_HI_2;
		}
	}
	
	private void calleVertical(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) {
			//x1=53;x2=93;y=6;
			for (int i=x1;i<x2;i++) 
				contenido[i][y] = CALLE_VA_1;
			for (int i=x1-1;i<x2+1;i++) 
				contenido[i][y-1] = CALLE_VA_2;
		}
		else if (sentido == 2) {
			//x1=51;x2=93;y=93;
			for (int i=x1;i<x2;i++) 
				contenido[i][y] = CALLE_VB_2;
			for (int i=x1+1;i<x2-1;i++)
				contenido[i][y-1] = CALLE_VB_1;
		}
		else if (sentido == 3) {
			//x1=7;x2=49;y=6;
			for (int i=x1;i<x2;i++) 
				contenido[i][y] = CALLE_VA_1;
			for (int i=x1-1;i<x2+1;i++) 
				contenido[i][y-1] = CALLE_VA_2;
		}
		else if (sentido == 4) {
			//x1=6;x2=48;y=92;
			for (int i=x1;i<x2;i++) 
				contenido[i][y] = CALLE_VB_1;
			for (int i=x1-1;i<x2+1;i++) 
				contenido[i][y+1] = CALLE_VB_2;
		}
	}
	
	private void callejon(int x1,int x2,int y,int sentido) {
		
		if (sentido == 1) 
			//x1=53;x2=92;y=30;
			//x1=7;x2=48;y=72;
			for (int j=x1;j<x2;j++) 
				contenido[y][j] = CALLEJON_HI;
		else if (sentido == 2) 
			//x1=53;x2=92;y=80;
			//x1=7;x2=48;y=40
			for (int j=53;j<92;j++)
				contenido[80][j] = CALLEJON_HD;
		else if (sentido == 3)
			//x1=53;x2=92;y=30;
			//x1=7;x2=48;y=40;
			for (int i=x1;i<x2;i++) 
				contenido[i][y] = CALLEJON_VA;
		else if (sentido == 4)
			//x1=53;x2=92;y=80;
			//x1=7;x2=48;y=72
			for (int i=x1;i<x2;i++) 
				contenido[i][y] = CALLEJON_VB;
	}
	
	private void ponerSemaforos() {
		
		for (int i=48;i<50;i++)
			contenido[i][53] = SEMAFORO_VERDE;
		for (int i=51;i<53;i++)
			contenido[i][47] = SEMAFORO_VERDE;
		for (int j=48;j<50;j++)
			contenido[47][j] = SEMAFORO_VERDE;
		for (int j=51;j<53;j++)
			contenido[53][j] = SEMAFORO_VERDE;
	}
	
	private void ponerStops() {
		
		contenido[47][72] = STOP;
		contenido[53][80] = STOP;
		contenido[53][30] = STOP;
		contenido[47][40] = STOP;
		contenido[80][53] = STOP;
		contenido[30][53] = STOP;
		contenido[72][47] = STOP;
		contenido[40][47] = STOP;
	}
	
	private void ponerCedas() {
		
		contenido[7][72] = CEDA_EL_PASO;
		contenido[91][80] = CEDA_EL_PASO;
		contenido[91][30] = CEDA_EL_PASO;
		contenido[7][40] = CEDA_EL_PASO;
		contenido[80][91] = CEDA_EL_PASO;
		contenido[30][91] = CEDA_EL_PASO;
		contenido[72][7] = CEDA_EL_PASO;
		contenido[40][7] = CEDA_EL_PASO;
	}
	
	private void ponerCruces() {
		
		for (int i=48;i<53 ;i++) 
			for(int j=48;j<53;j++)
				contenido[i][j] = CRUCE;
		
		contenido[72][30] = CRUCE;
		contenido[40][40] = CRUCE;
		contenido[30][72] = CRUCE;
		contenido[80][80] = CRUCE;
	}
	
	public String[][] getContenido() {
	
		return contenido;
	}
	
	public String getPosicion(int i,int j) {
		
		return contenido[i][j];
	}
	
	public ArrayList<Coche> getCoches() {
		
		return coches;
	}

	public void simular() {
		
		if (eleccion == 0) {
			crearCoche(5,20,200);
		}
		else if (eleccion == 1) {
			crearCoche(3,80,200);
			crearCoche(4,42,180);
			crearCoche(6,66,90);
			crearCoche(7,20,120);
		}
		else if (eleccion == 2) {
			crearCoche(4,7,100);
			crearCoche(3,20,100);
		}
		//crearCoche(6,66,400);
		//crearCoche(7,20,120);
		actualizar();
		for(int i=0;i<coches.size();i++)  
			coches.get(i).start();
	}
	
	public void finalizar() {
		
		for(int i=0;i<coches.size();i++)  
			coches.get(i).finalizar();
	}
	
	public void actualizar() {
		
		setChanged();
		notifyObservers();
	}
	
	private void crearCoche(int i,int j,int velocidad) {
		
		Coche coche = new Coche(this,i,j,velocidad);
		coches.add(coche);	
	}
	
	
}
