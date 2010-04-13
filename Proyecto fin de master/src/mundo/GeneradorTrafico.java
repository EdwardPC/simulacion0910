package mundo;

import java.util.Random;

import agente.Conductor;

public class GeneradorTrafico extends Thread {
	
	private static final Integer VCiudad = 30;
	private static final Integer VAuto = 120;
	private static final Integer VSec = 100;
	
	private Integer eleccion;
	private Entorno entorno;
	private Integer moderados;
	private Integer normales;
	private Integer agresivos;
	private Integer vIni;
	private Integer x;
	private Integer y;
	
	public GeneradorTrafico(Entorno mundo) {
		
		entorno = mundo;
		eleccion = entorno.getEleccion();
		moderados = entorno.getModerados();
		normales = entorno.getNormales();
		agresivos = entorno.getAgresivos();
		x = entorno.getInix();
		y = entorno.getIniy();
		vIni = 0;
	}
	
	public void run() {
			
		Random impaciencia = new Random();
		if (eleccion == 0)
			vIni = VCiudad;
		else if (eleccion == 1) 
			vIni = VAuto;
		else if (eleccion == 2)
			vIni = VSec;
		
		while (!(entorno.getParar())
		&&(moderados > 0 || normales > 0 || agresivos > 0))
			if (moderados > 0) {
				Coche coche = new Coche(entorno,vIni,vIni);
				Conductor conductor = new Conductor(entorno,Constantes.MODERADO,impaciencia.nextInt(3)+1,coche,x,y);
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
				Coche coche = new Coche(entorno,vIni,vIni);
				Conductor conductor = new Conductor(entorno,Constantes.NORMAL,impaciencia.nextInt(3)+1,coche,x,y);
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
				Coche coche = new Coche(entorno,vIni,vIni);
				Conductor conductor = new Conductor(entorno,Constantes.AGRESIVO,impaciencia.nextInt(3)+1,coche,x,y);
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
