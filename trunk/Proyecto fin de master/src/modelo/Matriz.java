package modelo;

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
	
	public void inicializar() {
		
		parar = false;
		contenido = new String[100][100];
		coches = new ArrayList<Coche>();
		for (int i=0;i<100;i++)
			for (int j=0;j<100;j++)
				if ((j<3) || (i<3) || (i>95) || (j>95))
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
	
	public void rellenarMatriz(Integer tipo) {
		
		eleccion = tipo;
		switch(tipo) {
		case 0:
			construirCallesPrincipales();
			callesHorizontales();
			callesVerticales();
			ponerSemaforos();
			ponerStops();
			ponerCedas();
			ponerCruces();
			break;
		case 1:
			construirAutoviaArriba();
			construirAutoviaAbajo();
			construirAutoviaIzquierda();
			construirAutoviaDerecha();
			construirAccesosAutovia();
			construirPaisajeCampo();
			break;
		case 2:
			construirSecundariaArriba();
			construirSecundariaAbajo();
			construirSecundariaIzquierda();
			construirSecundariaDerecha();
			construirPaisajeCampo();
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
	
	private void construirAccesosAutovia() {
		
		for (int i=25;i<45;i++) 
			contenido[i][90] = CARRIL_SALIDA_V;
		for (int j=85;j<90;j++)
			contenido[44][j] = CARRIL_SALIDA_H;
		for (int i=50;i<70;i++)
			contenido[i][8] = CARRIL_ENTRADA_V;
		for (int j=9;j<14;j++)
			contenido[69][j] = CARRIL_ENTRADA_H;
		
		
	}

	private void construirAutoviaAbajo() {
		
		for (int j=5;j<93;j++) 
			contenido[91][j] = AUTOVIA_D_HI_1;
		for (int j=4;j<93;j++) 
			contenido[92][j] = AUTOVIA_D_HI_2;
		
		for(int j=4;j<94;j++)
			contenido[93][j] = SEPARACION;
		
		for (int j=3;j<94;j++) 
			contenido[94][j] = AUTOVIA_I_HD_2;
		for (int j=3;j<95;j++) 
			contenido[95][j] = AUTOVIA_I_HD_1;
		
		
	}
	
	private void construirAutoviaArriba() {
		
		for (int j=4;j<96;j++) 
			contenido[3][j] = AUTOVIA_I_HI_1;
		for (int j=5;j<96;j++) 
			contenido[4][j] = AUTOVIA_I_HI_2;
		
		for(int j=5;j<94;j++)
			contenido[5][j] = SEPARACION;
		
		for (int j=6;j<92;j++) 
			contenido[6][j] = AUTOVIA_D_HD_2;
		for (int j=7;j<91;j++) 
			contenido[7][j] = AUTOVIA_D_HD_1;
		
	}
	
	private void construirAutoviaDerecha() {
		
		for (int i=7;i<91;i++) 
			contenido[i][91] = AUTOVIA_D_VB_1;
		for (int i=6;i<92;i++) 
			contenido[i][92] = AUTOVIA_D_VB_2;
		
		for (int i=6;i<93;i++) 
			contenido[i][93] = SEPARACION;
		
		for (int i=5;i<95;i++) 
			contenido[i][94] = AUTOVIA_I_VA_2;
		for (int i=4;i<96;i++)
			contenido[i][95] = AUTOVIA_I_VA_1;
	}
	
	private void construirAutoviaIzquierda() {
		
		for (int i=3;i<95;i++) 
			contenido[i][3] = AUTOVIA_I_VB_1;
		for (int i=4;i<94;i++)
			contenido[i][4] = AUTOVIA_I_VB_2;
		
		for (int i=6;i<94;i++) 
			contenido[i][5] = SEPARACION;
		
		for (int i=7;i<93;i++) 
			contenido[i][6] = AUTOVIA_D_VA_2;
		for (int i=8;i<92;i++) 
			contenido[i][7] = AUTOVIA_D_VA_1;
		
	}
	
	private void construirSecundariaAbajo() {
		
		for (int j=5;j<96;j++) 
			contenido[94][j] = SECUNDARIA_D_HI;
		for (int j=3;j<96;j++) 
			contenido[95][j] = SECUNDARIA_I_HD;
	}
	
	private void construirSecundariaArriba() {
		
		for (int j=4;j<96;j++) 
			contenido[3][j] = SECUNDARIA_I_HI;
		for (int j=4;j<94;j++) 
			contenido[4][j] = SECUNDARIA_D_HD;
	}

	private void construirSecundariaDerecha() {
	
		for (int i=4;i<94;i++) 
			contenido[i][94] = SECUNDARIA_D_VB;
		for (int i=4;i<96;i++) 
			contenido[i][95] = SECUNDARIA_I_VA;
	}

	private void construirSecundariaIzquierda() {
	
		for (int i=3;i<95;i++) 
			contenido[i][3] = SECUNDARIA_I_VB;
		for (int i=5;i<95;i++) 
			contenido[i][4] = SECUNDARIA_D_VA;
	}
	
	private void construirCallesPrincipales() {
		
		for (int j=7;j<93;j++) 
			contenido[48][j] = CALLE_PRINCIPAL_HI_1;
		for (int j=6;j<94;j++) 
			contenido[49][j] = CALLE_PRINCIPAL_HI_2;
		
		for (int j=5;j<94;j++)
			contenido[50][j] = SEPARACION;	
		
		for (int j=5;j<93;j++)
			contenido[51][j] = CALLE_PRINCIPAL_HD_2;
		for (int j=6;j<92;j++)
			contenido[52][j] = CALLE_PRINCIPAL_HD_1;
			
		for (int i=6;i<92;i++) 
			contenido[i][48] = CALLE_PRINCIPAL_VB_1;
		for (int i=5;i<93;i++) 
			contenido[i][49] = CALLE_PRINCIPAL_VB_2;
		
		for (int i=5;i<94;i++)
			contenido[i][50] = SEPARACION;	
		
		for (int i=6;i<94;i++) 
			contenido[i][51] = CALLE_PRINCIPAL_VA_2;
		for (int i=7;i<93;i++) 
			contenido[i][52] = CALLE_PRINCIPAL_VA_1;		          
	}
	
	private void callesHorizontales() {
	
		//Horizontales
		for (int j=51;j<93;j++) 
			contenido[5][j] = CALLE_HD_2;
		for (int j=52;j<92;j++) 
			contenido[6][j] = CALLE_HD_1;
		
		for (int j=53;j<93;j++) 
			contenido[92][j] = CALLE_HI_1;
		for (int j=52;j<94;j++) 
			contenido[93][j] = CALLE_HI_2;
	
		for (int j=53;j<92;j++) {
			contenido[30][j] = CALLEJON_HI;
			contenido[80][j] = CALLEJON_HD;
		}
		
		for (int j=6;j<48;j++) 
			contenido[6][j] = CALLE_HD_1;
		for (int j=5;j<49;j++) 
			contenido[5][j] = CALLE_HD_2;
		
		for (int j=7;j<49;j++) 
			contenido[92][j] = CALLE_HI_1;
		for (int j=6;j<50;j++) 
			contenido[93][j] = CALLE_HI_2;
	
		for(int j=7;j<48;j++) {
			contenido[40][j] = CALLEJON_HD;
			contenido[72][j] = CALLEJON_HI;
		}
	}
	
	private void callesVerticales() {
		
		//Verticales
		for (int i=53;i<93;i++) 
			contenido[i][6] = CALLE_VA_1;
		for (int i=52;i<94;i++) 
			contenido[i][5] = CALLE_VA_2;
		
		for (int i=51;i<93;i++) 
			contenido[i][93] = CALLE_VB_2;
		for (int i=52;i<92;i++)
			contenido[i][92] = CALLE_VB_1;
		
		for (int i=53;i<92;i++) {
			contenido[i][30] = CALLEJON_VA;
			contenido[i][80] = CALLEJON_VB;
		}
		
		for (int i=7;i<49;i++) 
			contenido[i][6] = CALLE_VA_1;
		for (int i=6;i<50;i++) 
			contenido[i][5] = CALLE_VA_2;
		
		for (int i=6;i<48;i++) 
			contenido[i][92] = CALLE_VB_1;
		for (int i=5;i<49;i++) 
			contenido[i][93] = CALLE_VB_2;
		
		for (int i=7;i<48;i++) {
			contenido[i][40] = CALLEJON_VA;
			contenido[i][72] = CALLEJON_VB;
		}
		
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
