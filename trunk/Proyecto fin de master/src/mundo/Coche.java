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
	private String tipoConductor;
	
	public Coche(Matriz matrix,Integer comienzoX,Integer comienzoY,
			Integer velocidadIni,String conductor) {
		
		matriz = matrix;
		contenido = matriz.getContenido();
		antX = comienzoX;
		antY = comienzoY;
		x = antX;
		y = antY;
		velocidad = velocidadIni;
		tipoConductor = conductor;
		anterior = new ItemsMundo(contenido[antX][antY].getTipo(),contenido[antX][antY].isInicio());
		anterior.setDireccion(contenido[antX][antY].getDireccion());
		anterior.setConductor(contenido[antX][antY].getConductor());
		contenido[antX][antY].setTipo(Constantes.AUTOMOVIL);
		contenido[antX][antY].setConductor(tipoConductor);
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
		contenido[antX][antY].setConductor(anterior.getConductor());
		anterior.setTipo(contenido[x][y].getTipo());
		anterior.setDireccion(contenido[x][y].getDireccion());
		anterior.setTramo(contenido[x][y].getTramo());
		anterior.setConductor(contenido[x][y].getConductor());
		contenido[x][y].setTipo(Constantes.AUTOMOVIL);
		contenido[x][y].setConductor(tipoConductor);
		antX = x;
		antY = y;
		if (anterior.getTipo().equals(Constantes.AUTOVIA) || anterior.getTipo().equals(Constantes.SECUNDARIA) || 
				anterior.getTipo().contains(Constantes.CALLE)) {
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
		tratarAdelantamiento();
		volverACarril();
		
	}
	
	private void tratarAdelantamiento() {
		
		if (/*estadoMental().adelantar()*/false) {
			if (direccion.equals(Constantes.ABAJO))
				y = y+1;
			else if (direccion.equals(Constantes.ARRIBA))
				y = y-1;
			else if (direccion.equals(Constantes.DERECHA))
				x = x-1;
			else if (direccion.equals(Constantes.IZQUIERDA))
				x = x+1;
		}
	}
		
	private void volverACarril() {
			
		if (/*estadoMental().volverACarril()*/false) {
			if (direccion.equals(Constantes.ABAJO))
				y = y-1;
			else if (direccion.equals(Constantes.ARRIBA))
				y = y+1;
			else if (direccion.equals(Constantes.DERECHA))
				x = x+1;
			else if (direccion.equals(Constantes.IZQUIERDA))
				x = x-1;
		}
	}
	
	public void run() {
		
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
