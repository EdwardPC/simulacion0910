package modelo;

public class Atributo {
	
	private int longitud;
	private int limite1;
	private int limite2;
	
	public Atributo(int tam,int lim1,int lim2) {
		
		longitud = tam;
		limite1 = lim1;
		limite2 = lim2;
	}


	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int tam) {
		this.longitud = tam;
	}

	public int getLimite1() {
		return limite1;
	}

	public void setLimite1(int limite1) {
		this.limite1 = limite1;
	}

	public int getLimite2() {
		return limite2;
	}

	public void setLimite2(int limite2) {
		this.limite2 = limite2;
	}
}
