package agente;

import java.util.ArrayList;
import java.util.Random;

public class EstadoMental {

	private ArrayList<String> ruta;
	private String tipoConductor;
	private Integer ansiedad;
	
	public EstadoMental(String tipo,Integer impaciencia,Integer eleccion) {
		
		tipoConductor = tipo;
		ansiedad = impaciencia;
		seleccionRuta(eleccion);
	}
	
	private void seleccionRuta(Integer eleccion) {
		
		ruta = new ArrayList<String>();
		Random random = new Random();
		Integer numVueltas = 1;//random.nextInt(2);
		switch(eleccion) {
		case 0:
			
			break;
		case 1:
			ruta.add(numVueltas.toString());
			break;
		case 2:
			ruta.add(numVueltas.toString());
			break;
		}
	}
	
	public boolean salir() {
		
		Integer opcion = new Integer(ruta.get(0));
		return opcion <= 0;
	}

	public ArrayList<String> getRuta() {
		return ruta;
	}

	public void setRuta(ArrayList<String> ruta) {
		this.ruta = ruta;
	}

	public String getTipoConductor() {
		return tipoConductor;
	}

	public void setTipoConductor(String tipoConductor) {
		this.tipoConductor = tipoConductor;
	}

	public Integer getAnsiedad() {
		return ansiedad;
	}

	public void setAnsiedad(Integer ansiedad) {
		this.ansiedad = ansiedad;
	}
}
