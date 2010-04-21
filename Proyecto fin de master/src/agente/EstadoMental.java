package agente;

import java.util.ArrayList;
import java.util.Random;

public class EstadoMental {

	private ArrayList<String> ruta;
	private String tipoConductor;
	private Integer impaciencia;
	
	public EstadoMental(String tipo,Integer ansiedad,Integer eleccion) {
		
		tipoConductor = tipo;
		impaciencia = 1;//ansiedad;
		seleccionRuta(eleccion);
	}
	
	private void seleccionRuta(Integer eleccion) {
		
		ruta = new ArrayList<String>();
		Random random = new Random();
		Integer numVueltas = 1;//random.nextInt(10)+1;
		switch(eleccion) {
		case 0:
			ruta.add("Derecha");
			ruta.add("Izquierda");
			ruta.add("Izquierda");
			ruta.add("Derecha");
			ruta.add("Arriba");
			break;
		case 1:
			ruta.add(numVueltas.toString());
			break;
		case 2:
			ruta.add(numVueltas.toString());
			break;
		}
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

	public Integer getImpaciencia() {
		return impaciencia;
	}

	public void setImpaciencia(Integer ansiedad) {
		this.impaciencia = ansiedad;
	}
}
