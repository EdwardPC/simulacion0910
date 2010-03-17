package mundo;


public class Coche extends Thread {
	
	private ItemsMundo[][] contenido;
	
	private Matriz matriz;
	
	private static String AUTOMOVIL = "Automovil";
	
	private String aux;
	
	private Integer x;
	
	private Integer y;
	
	private Integer antX;
	
	private Integer antY;
	
	private Integer velocidad;
	
	private String direccion;
	
	public Coche(Matriz matrix,Integer comienzoX,Integer comienzoY,Integer velocidadIni) {
		
		matriz = matrix;
		contenido = matriz.getContenido();
		antX = comienzoX;
		antY = comienzoY;
		x = antX;
		y = antY;
		velocidad = velocidadIni;
		aux = contenido[antX][antY].getInformacion();
		contenido[antX][antY].setInformacion(AUTOMOVIL);
	}
	
	public Integer getVelocidad() {
		
		return velocidad;
	}
	
	public void setVelocidad(Integer velAct) {
		
		velocidad = velAct;
	}
	
	public String getDireccion() {
		
		return direccion;
	}
	
	public void setDireccion(String sentido) {
		
		direccion = sentido;
	}
	
	public void setAnterior(String ant) {
		
		aux = ant;
	}
	
	public String getAnterior() {
		
		return aux;
	}
	
	public void avanzar() {
		
		contenido[antX][antY].setInformacion(aux);
		//System.out.println("X: "+ antX+" Y: "+antY+ "Aux: "+aux);
		aux = contenido[x][y].getInformacion();
		//System.out.println("Aux nuevo: "+aux);
		contenido[x][y].setInformacion(AUTOMOVIL);
		//System.out.println("X actual: "+ x+" Y actual: "+y+ ": "+contenido[x][y].toString());
		antX = x;
		antY = y;
		if (aux.contains("HD")) {
			y = y+1;
			direccion = "Derecha";
		}
		else if (aux.contains("HI")) {
			y = y-1;
			direccion = "Izquierda";
		}
		else if (aux.contains("VA")) {
			x = x-1;
			direccion = "Arriba";
		}
		else if (aux.contains("VB")) {
			x = x+1;
			direccion = "Abajo";
		}
		else if (aux.contains("Cruce") || aux.contains("Semaforo")) {
			if (direccion.equals("Abajo"))
				x = x+1;
			else if (direccion.equals("Arriba"))
				x = x-1;
			else if (direccion.equals("Derecha"))
				y = y+1;
			else if (direccion.equals("Izquierda"))
				y = y-1;
		}
		else if (aux.contains("Carril_entrada")) 
			y = y-1;
	}
	
	public void run() {
		
		while(!matriz.getParar()) {
			avanzar();
			matriz.actualizar();
			try {
					Coche.sleep(300-velocidad);
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
