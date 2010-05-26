package mundo;

import java.util.ArrayList;
import java.util.Random;

import manager.Estadisticas;
import manager.Punto;

import agente.Conductor;

public class GeneradorTrafico extends Thread {
	
	private static final Integer VCiudad = 30;
	private static final Integer VAuto = 120;
	private static final Integer VSec = 100;
	private static final Integer vMax1 = 150;
	private static final Integer vMax2 = 220;
	
	private Integer eleccion;
	private Entorno entorno;
	private Integer moderados;
	private Integer normales;
	private Integer agresivos;
	private Integer vIni;
	private Integer vMax;
	private ArrayList<Punto> inicios;
	private Estadisticas estadisticas;
	
	public GeneradorTrafico(Entorno mundo) {
		
		entorno = mundo;
		estadisticas = entorno.getEstadisticas();
		eleccion = entorno.getEleccion();
		moderados = entorno.getModerados();
		normales = entorno.getNormales();
		agresivos = entorno.getAgresivos();
		inicios = entorno.getInicios();
		vIni = 0;
		if (!mundo.getTipoVehiculos())
			vMax = vMax1;
		else
			vMax = vMax2;
	}
	
	public void run() {
			
		if (eleccion == 0)
			vIni = VCiudad;
		else if (eleccion == 1) 
			vIni = VAuto;
		else if (eleccion == 2)
			vIni = VSec;
		
		while (!(entorno.getParar())
		&&(moderados > 0 || normales > 0 || agresivos > 0))
			if (moderados > 0) {
				for (int i=0;i<inicios.size();i++) {
					Punto p = inicios.get(i);
					Coche coche = new Coche(entorno,vIni,vMax);
					Conductor conductor = new Conductor(entorno,Constantes.MODERADO,entorno.getImpaciencia(),coche,p.getX(),p.getY());
					entorno.getConductores().add(conductor);
					estadisticas.setConductores(estadisticas.getConductores()+1);
					estadisticas.setModerados(estadisticas.getModerados()+1);
					entorno.actualizar(0);
					conductor.start();	
				}
				moderados = moderados - 1;
				try {
					GeneradorTrafico.sleep(2500 -
						entorno.getVelocidadSimulacion());
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			else if (normales > 0) {
				for (int i=0;i<inicios.size();i++) {
					Punto p = inicios.get(i);
					Coche coche = new Coche(entorno,vIni,vMax);
					Conductor conductor = new Conductor(entorno,Constantes.NORMAL,entorno.getImpaciencia(),coche,p.getX(),p.getY());
					entorno.getConductores().add(conductor);
					estadisticas.setConductores(estadisticas.getConductores()+1);
					estadisticas.setNormales(estadisticas.getNormales()+1);
					entorno.actualizar(0);
					conductor.start();
				}
				normales = normales - 1;
				try {
					GeneradorTrafico.sleep(2500 - 
						entorno.getVelocidadSimulacion());
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}			
			else if (agresivos > 0) {
				for (int i=0;i<inicios.size();i++) {
					Punto p = inicios.get(i);
					Coche coche = new Coche(entorno,vIni,vMax);
					Conductor conductor = new Conductor(entorno,Constantes.AGRESIVO,entorno.getImpaciencia(),coche,p.getX(),p.getY());
					entorno.getConductores().add(conductor);
					estadisticas.setConductores(estadisticas.getConductores()+1);
					estadisticas.setAgresivos(estadisticas.getAgresivos()+1);
					entorno.actualizar(0);
					conductor.start();
				}
				agresivos = agresivos - 1;
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
