package mundo;

import agente.Conductor;

public class GeneradorTrafico extends Thread {
	
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
	private Entorno entorno;
	private Integer moderados;
	private Integer normales;
	private Integer agresivos;
	private Integer vIni;
	private Integer x;
	private Integer y;
	private Integer numIds;
	
	public GeneradorTrafico(Entorno mundo) {
		
		entorno = mundo;
		eleccion = entorno.getEleccion();
		moderados = entorno.getModerados();
		normales = entorno.getNormales();
		agresivos = entorno.getAgresivos();
		x = entorno.getInix();
		y = entorno.getIniy();
		vIni = 0;
		numIds = 0;
	}
	
	public void run() {
			
		while (!(entorno.getParar())
		&&(moderados > 0 || normales > 0 || agresivos > 0))
			if (moderados > 0) {
				if (eleccion == 0)
					vIni = VModCiudad;
				else if (eleccion == 1) 
					vIni = VModAuto;
				else if (eleccion == 2)
					vIni = VModSec;
				Coche coche = new Coche(entorno,vIni,vIni);
				Conductor conductor = new Conductor(entorno,Constantes.MODERADO,1,coche,x,y);
				entorno.getConductores().add(conductor);
				moderados = moderados - 1;
				entorno.actualizar(0);
				conductor.start();
				try {
					GeneradorTrafico.sleep(2500 -
							entorno.getVelocidadSimulacion());
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
				Coche coche = new Coche(entorno,vIni,vIni);
				Conductor conductor = new Conductor(entorno,Constantes.NORMAL,1,coche,x,y);
				entorno.getConductores().add(conductor);
				normales = normales - 1;
				entorno.actualizar(0);
				conductor.start();
				try {
					GeneradorTrafico.sleep(2500 - 
							entorno.getVelocidadSimulacion());
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
				Coche coche = new Coche(entorno,vIni,vIni);
				Conductor conductor = new Conductor(entorno,Constantes.AGRESIVO,1,coche,x,y);
				entorno.getConductores().add(conductor);
				agresivos = agresivos - 1;
				entorno.actualizar(0);
				conductor.start();
				try {
					GeneradorTrafico.sleep(2500 - 
							entorno.getVelocidadSimulacion());
				}
				catch(Exception e) {
					e.printStackTrace();
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
