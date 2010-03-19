package mundo;


public class Coche extends Thread {
	
	
	
	private ItemsMundo[][] contenido;
	private Matriz matriz;
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
		anterior = new ItemsMundo(contenido[antX][antY].getTipo(),contenido[antX][antY].isInicio());
		anterior.setDireccion(contenido[antX][antY].getDireccion());
		contenido[antX][antY].setTipo(Constantes.AUTOMOVIL);
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
		anterior.setTipo(contenido[x][y].getTipo());
		anterior.setDireccion(contenido[x][y].getDireccion());
		anterior.setTramo(contenido[x][y].getTramo());
		contenido[x][y].setTipo(Constantes.AUTOMOVIL);
		antX = x;
		antY = y;
		if (anterior.getTipo().contains("Autovia") || anterior.getTipo().contains("Secundaria") || 
				anterior.getTipo().contains("Calle")) {
			if (contenido[x][y].getDireccion().equals(Constantes.DERECHA)) {
				y = y+1;
			}
			else if (contenido[x][y].getDireccion().equals(Constantes.IZQUIERDA)) {
				y = y-1;
			}
			else if (contenido[x][y].getDireccion().equals(Constantes.ARRIBA)) {
				x = x-1;
			}
			else if (contenido[x][y].getDireccion().equals(Constantes.ABAJO)) {
				x = x+1;
			}
		}
		else if (anterior.getTipo().equals(Constantes.CRUCE)) {
			if (anterior.getDireccion().equals(Constantes.ABAJO))
				x = x+1;
			else if (anterior.getDireccion().equals(Constantes.ARRIBA))
				x = x-1;
			else if (anterior.getDireccion().equals(Constantes.DERECHA))
				y = y+1;
			else if (anterior.getDireccion().equals(Constantes.IZQUIERDA))
				y = y-1;
		}
		else if (anterior.getTipo().equals(Constantes.SEMAFORO)) {
				if (anterior.getColorSemaforo().equals(Constantes.VERDE)) {
					if (anterior.getDireccion().equals(Constantes.ABAJO))
						x = x+1;
					else if (anterior.getDireccion().equals(Constantes.ARRIBA))
						x = x-1;
					else if (anterior.getDireccion().equals(Constantes.DERECHA))
						y = y+1;
					else if (anterior.getDireccion().equals(Constantes.IZQUIERDA))
						y = y-1;
				}
		}
		else if (anterior.getTipo().equals(Constantes.CARRIL_ENTRADA)) {
			if (anterior.getDireccion().equals(Constantes.DERECHA)) {
				if (!(contenido[x][y+1].getTipo().equals(Constantes.CARRIL_ENTRADA)) &&
						!(contenido[x][y+1].getTipo().equals(Constantes.AUTOMOVIL)) && 
						!(contenido[x+1][y].getTipo().equals(Constantes.AUTOMOVIL))) {
					if (anterior.getTramo() == 2)
						x = x-1;
					else 
						x = x+1;
				}
				else if (contenido[x][y+1].getTipo().equals(Constantes.CARRIL_ENTRADA)) {
					y = y+1;
				}
			}
			else if (anterior.getDireccion().equals(Constantes.IZQUIERDA)) {
				System.out.println(anterior.getTramo());
				if (!(contenido[x][y-1].getTipo().equals(Constantes.CARRIL_ENTRADA)) &&
						!(contenido[x][y-1].getTipo().equals(Constantes.AUTOMOVIL)) && 
						!(contenido[x+1][y].getTipo().equals(Constantes.AUTOMOVIL))) {
					if (anterior.getTramo() == 2)
						x = x-1;
					else 
						x = x+1;
				}
				else if (contenido[x][y-1].getTipo().equals(Constantes.CARRIL_ENTRADA)) {
					y = y-1;
				}
			}
			else if (anterior.getDireccion().equals(Constantes.ARRIBA)) {
				System.out.println(anterior.getTramo());
				if (!(contenido[x-1][y].getTipo().equals(Constantes.CARRIL_ENTRADA)) &&
						!(contenido[x-1][y].getTipo().equals(Constantes.AUTOMOVIL)) && 
						!(contenido[x][y-1].getTipo().equals(Constantes.AUTOMOVIL))) {
					if (anterior.getTramo() == 2)
						y = y+1;
					else 
						y = y-1;
				}
				else if (contenido[x-1][y].getTipo().equals(Constantes.CARRIL_ENTRADA)) {
					x = x-1;
				}
			}
			else if (anterior.getDireccion().equals(Constantes.ABAJO)) {
				if (!(contenido[x+1][y].getTipo().equals(Constantes.CARRIL_ENTRADA)) &&
						!(contenido[x+1][y].getTipo().equals(Constantes.AUTOMOVIL)) && 
						!(contenido[x][y+1].getTipo().equals(Constantes.AUTOMOVIL))) {
					if (anterior.getTramo() == 2)
						y = y-1;
					else 
						y = y+1;
				}
				else if (contenido[x+1][y].getTipo().equals(Constantes.CARRIL_ENTRADA)) {
					x = x+1;
				}
			}
		}
		/*else if (siguiente.equals(AUTOMOVIL))
			tratarAdelantamiento();
		*/	
	}
	
	private void tratarAdelantamiento() {
		
		if (direccion.equals(Constantes.ABAJO))
			y = y+1;
		else if (direccion.equals(Constantes.ARRIBA))
			y = y-1;
		else if (direccion.equals(Constantes.DERECHA))
			x = x-1;
		else if (direccion.equals(Constantes.IZQUIERDA))
			x = x+1;
		
	}
	
	public void run() {
		
		while (!matriz.getFinalizar())
			while(!matriz.getParar()) {
				avanzar();
				matriz.actualizar();
				try {
					Coche.sleep(300-velocidad-matriz.getVelocidadSimulacion());
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
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
