package mundo;


public class Coche extends Thread {
	
	
	private static final String ARRIBA = "Arriba";
	private static final String ABAJO = "Abajo";
	private static final String DERECHA = "Derecha";
	private static final String IZQUIERDA = "Izquierda";
	
	private ItemsMundo[][] contenido;
	private Matriz matriz;
	private static String AUTOMOVIL = "Automovil";
	private ItemsMundo anterior;
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
		direccion = contenido[antX][antY].getDireccion();
		anterior = new ItemsMundo(contenido[antX][antY].getTipo(),contenido[antX][antY].isInicio());
		contenido[antX][antY].setTipo(AUTOMOVIL);
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
	
	public void setAnterior(ItemsMundo ant) {
		
		anterior = ant;
	}
	
	public ItemsMundo getAnterior() {
		
		return anterior;
	}
	
	public void avanzar() {
		
		contenido[antX][antY].setTipo(anterior.getTipo());
		//System.out.println("X: "+ antX+" Y: "+antY+ "Aux: "+aux);
		anterior.setTipo(contenido[x][y].getTipo());
		//System.out.println("Aux nuevo: "+aux);
		contenido[x][y].setTipo(AUTOMOVIL);
		//System.out.println("X actual: "+ x+" Y actual: "+y+ ": "+contenido[x][y].toString());
		antX = x;
		antY = y;
		if (anterior.getTipo().contains("Autovia") || anterior.getTipo().contains("Secundaria") || 
				anterior.getTipo().contains("Calle")) {
			if (contenido[x][y].getDireccion().equals(DERECHA)) 
				y = y+1;
			else if (contenido[x][y].getDireccion().equals(IZQUIERDA)) 
				y = y-1;
			else if (contenido[x][y].getDireccion().equals(ARRIBA)) 
				x = x-1;
			else if (contenido[x][y].getDireccion().equals(ABAJO)) 
				x = x+1;
		}
		else if (anterior.getTipo().contains("Cruce") || anterior.getTipo().contains("Semaforo")) {
			if (anterior.getDireccion().equals(ABAJO))
				x = x+1;
			else if (direccion.equals(ARRIBA))
				x = x-1;
			else if (direccion.equals(DERECHA))
				y = y+1;
			else if (direccion.equals(IZQUIERDA))
				y = y-1;
		}
		else if (anterior.getTipo().contains("Carril_entrada")) {
			if (!(contenido[x-1][y].getTipo().contains("Carril_entrada")) &&
					!(contenido[x-1][y].getTipo().equals(AUTOMOVIL)) && 
					!(contenido[x][y-1].getTipo().equals(AUTOMOVIL)))
				y = y-1;
			else if (!(contenido[x-1][y].getTipo().equals(AUTOMOVIL)) && 
					(contenido[x-1][y].getDireccion().equals(ARRIBA)))
				x = x-1;
			else if (!(contenido[x][y-1].getTipo().equals(AUTOMOVIL)) &&
					(contenido[x][y-1].getDireccion().equals(IZQUIERDA)))
				y = y-1;
		}
		/*else if (siguiente.equals(AUTOMOVIL))
			tratarAdelantamiento();
		*/	
	}
	
	private void tratarAdelantamiento() {
		
		if (direccion.equals(ABAJO))
			y = y+1;
		else if (direccion.equals(ARRIBA))
			y = y-1;
		else if (direccion.equals(DERECHA))
			x = x-1;
		else if (direccion.equals(IZQUIERDA))
			x = x+1;
		
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
