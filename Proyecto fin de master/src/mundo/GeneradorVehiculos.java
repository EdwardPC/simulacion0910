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
	
	
	public GeneradorVehiculos() {
		
	}
	
	public GeneradorVehiculos(Matriz mundo,ItemsMundo[][] cont,Integer elec,Integer tam,
			Integer mod,Integer norm,Integer agre) {
		
		matriz = mundo;
		contenido = cont;
		eleccion = elec;
		longitud = tam;
		moderados = mod;
		normales = norm;
		agresivos = agre;
		vIni = 0;
	}
	
	public void run() {
		
		for (int i=0;i<longitud;i++)
			for (int j=0;j<longitud;j++) 
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
							GeneradorVehiculos.sleep(1000);
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
					else if (normales >0) {
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
							GeneradorVehiculos.sleep(1000);
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
					else if (agresivos >0) {
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
							GeneradorVehiculos.sleep(1000);
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
					
				}
		finalizar();
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
