package mundo;

public class GeneradorVehiculos extends Thread {
	
	private static final Integer VModCiudad = 30;
	private static final Integer VModAuto = 100;
	private static final Integer VModSec = 80;
	private static final Integer VNormCiudad = 50;
	private static final Integer VNormAuto = 120;
	private static final Integer VNormSec = 100;
	private static final Integer VAgreCiudad = 70;
	private static final Integer VAgreAuto = 150;
	private static final Integer VAgreSec = 120;
	
	private Integer eleccion;
	private Matriz matriz;
	private ItemsMundo[][] contenido;
	private Integer moderados;
	private Integer normales;
	private Integer agresivos;
	private Integer longitud;
	private Integer vIni;
	
	public GeneradorVehiculos(Matriz mundo) {
		
		matriz = mundo;
		contenido = mundo.getContenido();
		eleccion = mundo.getEleccion();
		longitud = mundo.getLongitud();
		moderados = mundo.getModerados();
		normales = mundo.getNormales();
		agresivos = mundo.getAgresivos();
		vIni = 0;
	}
	
	public void run() {
			for (int i=longitud-1;i>=0;i--)
				for (int j=longitud-1;j>=0;j--) 
					if (contenido[i][j].isInicio()) {
						if (moderados > 0) {
							if (eleccion == 0)
								vIni = VModCiudad;
							else if (eleccion == 1) 
								vIni = VModAuto;
							else if (eleccion == 2)
								vIni = VModSec;
							Coche coche = new Coche(matriz,i,j,vIni);
							matriz.getCoches().add(coche);
							moderados = moderados - 1;
							matriz.actualizar();
							coche.start();
							try {
								GeneradorVehiculos.sleep(1500 -
									matriz.getVelocidadSimulacion());
							}
							catch(Exception e) {
								e.printStackTrace();
							}
						}
						else if (normales > 0) {
							if (eleccion == 0)
								vIni = VNormCiudad;
							else if (eleccion == 1) 
								vIni = VNormAuto;
							else if (eleccion == 2)
								vIni = VNormSec;
							Coche coche = new Coche(matriz,i,j,vIni);
							matriz.getCoches().add(coche);
							normales = normales - 1;
							matriz.actualizar();
							coche.start();
							try {
								GeneradorVehiculos.sleep(1500 - 
									matriz.getVelocidadSimulacion());
							}
							catch(Exception e) {
								e.printStackTrace();
							}
						}
						else if (agresivos > 0) {
							if (eleccion == 0)
								vIni = VAgreCiudad;
							else if (eleccion == 1) 
								vIni = VAgreAuto;
							else if (eleccion == 2)
								vIni = VAgreSec;
							Coche coche = new Coche(matriz,i,j,vIni);
							matriz.getCoches().add(coche);
							agresivos = agresivos - 1;
							matriz.actualizar();
							coche.start();
							try {
								GeneradorVehiculos.sleep(1500 - 
									matriz.getVelocidadSimulacion());
							}
							catch(Exception e) {
								e.printStackTrace();
							}
						}
					
				}
	}
	
	public void finalizar() {
		
		try {
			this.finalize();
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
