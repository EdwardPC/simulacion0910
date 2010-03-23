package mundo;

import managerXML.Punto;

public class Informacion {

	private boolean encontrado;
	private Punto punto;

	public Informacion (boolean enc,Punto p) {
		
		encontrado = enc;
		punto = p;
	}
	

	public boolean isEncontrado() {
		return encontrado;
	}

	public void setEncontrado(boolean encontrado) {
		this.encontrado = encontrado;
	}

	public Punto getPunto() {
		return punto;
	}

	public void setPunto(Punto punto) {
		this.punto = punto;
	}

}
