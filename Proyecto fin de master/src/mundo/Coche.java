package mundo;

public class Coche extends Thread {
	
	private ItemsMundo[][] contenido;
	private ItemsMundo[][] vision;
	private Modelo matriz;
	private ItemsMundo anterior;
	private Integer x;
	private Integer y;
	private Integer antX;
	private Integer antY;
	private Integer velocidad;
	private String direccionActual;
	private String tipoConductor;
	private boolean adelantarEnSec;
	
	public Coche(Modelo matrix,Integer comienzoX,Integer comienzoY,
			Integer velocidadIni,String conductor) {
		
		matriz = matrix;
		contenido = matriz.getContenido();
		antX = comienzoX;
		antY = comienzoY;
		x = antX;
		y = antY;
		velocidad = velocidadIni;
		tipoConductor = conductor;
		vision = new ItemsMundo[5][5];
		anterior = new ItemsMundo(contenido[antX][antY].getTipo(),contenido[antX][antY].isInicio());
		anterior.setDireccion(contenido[antX][antY].getDireccion());
		anterior.setConductor(contenido[antX][antY].getConductor());
		direccionActual = "";
		contenido[antX][antY].setTipo(Constantes.AUTOMOVIL);
		contenido[antX][antY].setConductor(tipoConductor);
		adelantarEnSec = false;
	}
	
	public Integer getVelocidad() {
		
		return velocidad;
	}
	
	public void setVelocidad(Integer velAct) {
		
		velocidad = velAct;
	}
	
	public String getDireccion() {
		
		return direccionActual;
	}
	
	public void setDireccion(String sentido) {
		
		direccionActual = sentido;
	}
	
	public void setAnterior(ItemsMundo ant) {
		
		anterior = ant;
	}
	
	public ItemsMundo getAnterior() {
		
		return anterior;
	}
	
	public void avanzar() {
		
		if (!anterior.getTipo().equals(Constantes.AUTOMOVIL)) {
			contenido[antX][antY].setTipo(anterior.getTipo());
			contenido[antX][antY].setConductor(anterior.getConductor());
		}
		anterior.setTipo(contenido[x][y].getTipo());
		anterior.setDireccion(contenido[x][y].getDireccion());
		anterior.setTramo(contenido[x][y].getTramo());
		anterior.setConductor(contenido[x][y].getConductor());
		anterior.setColorSemaforo(contenido[x][y].getColorSemaforo());
		anterior.setNumCarril(contenido[x][y].getNumCarril());
		anterior.setSentido(contenido[x][y].getSentido());
		contenido[x][y].setTipo(Constantes.AUTOMOVIL);
		contenido[x][y].setConductor(tipoConductor);
		antX = x;
		antY = y;
		observarMundo();
		if (mirarAdelante(2,Constantes.SEMAFORO)) {
			tratarSemaforo();
			System.out.println("veo semaforo");
		}
		else if (anterior.getTipo().equals(Constantes.SEMAFORO))
			pasoSemaforo();
		else if (anterior.getTipo().equals(Constantes.AUTOVIA))
			circularAutovia();
		else if (anterior.getTipo().equals(Constantes.SECUNDARIA))
			circularSecundaria();
		else if (anterior.getTipo().contains(Constantes.CALLE))
			circularCiudad();
		else if (anterior.getTipo().equals(Constantes.CRUCE)) 
			tratarPasoCruce();
		else if (anterior.getTipo().equals(Constantes.CARRIL_ENTRADA)) 
			tratarIncorporacion();
		/*else if (anterior.getTipo().equals(Constantes.AUTOMOVIL))
			try {
				Coche.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	*/
	}
	
	private void observarMundo() {
		
		int x = antX;
		int y = antY;
		int visionX = 0;
		int visionY = 0;
		for (int i=x-2;i<=x+2;i++) {
			visionY = 0;
			for (int j=y-2;j<=y+2;j++) {
				vision[visionX][visionY] = contenido[i][j];
				visionY = visionY+1;
			}
			visionX = visionX+1;
		}		
	}

	private void circularAutovia() {
		
		if (contenido[x][y].getDireccion().equals(Constantes.DERECHA)) {
			if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL) &&
				contenido[x][y].getNumCarril() == 1)
				tratarAdelantamiento();
			else if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
				!mirarDerecha(Constantes.AUTOMOVIL) &&
				contenido[x][y].getNumCarril() == 2)
				tratarVolverACarril();
			else 
				y = y+1;
			direccionActual = contenido[x][y].getDireccion();
		}	
		else if (contenido[x][y].getDireccion().equals(Constantes.IZQUIERDA)) {
			if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL) &&
				contenido[x][y].getNumCarril() == 1)
				tratarAdelantamiento();
			else if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
				!mirarDerecha(Constantes.AUTOMOVIL) &&
				contenido[x][y].getNumCarril() == 2)
				tratarVolverACarril();
			else 
				y = y-1;
			direccionActual = contenido[x][y].getDireccion();
		}	
		else if (contenido[x][y].getDireccion().equals(Constantes.ARRIBA)) {
			if (mirarAdelante(2,Constantes.AUTOMOVIL) &&
				!mirarIzquierda(Constantes.AUTOMOVIL) &&
				contenido[x][y].getNumCarril() == 1)
				tratarAdelantamiento();
			else if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
				!mirarDerecha(Constantes.AUTOMOVIL) &&
				contenido[x][y].getNumCarril() == 2)
				tratarVolverACarril();
			else
				x = x-1;
			direccionActual = contenido[x][y].getDireccion();
		}	
		else if (contenido[x][y].getDireccion().equals(Constantes.ABAJO)) {
			if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL) &&
				contenido[x][y].getNumCarril() == 1)
				tratarAdelantamiento();
			else if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
				!mirarDerecha(Constantes.AUTOMOVIL) &&
				contenido[x][y].getNumCarril() == 2)
				tratarVolverACarril();
			else 
				x = x+1;
			direccionActual = contenido[x][y].getDireccion();
		}	
	}
	
	private void circularCiudad() {
		
		System.out.println(direccionActual);
		if (contenido[x][y].getDireccion().equals(Constantes.DERECHA)) {
			if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL) &&
				contenido[x][y].getNumCarril() == 1)
				tratarAdelantamiento();
			else if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
				!mirarDerecha(Constantes.AUTOMOVIL) &&
				contenido[x][y].getNumCarril() == 2)
				tratarVolverACarril();
			else 
				y = y+1;
			direccionActual = contenido[x][y].getDireccion();
		}	
		else if (contenido[x][y].getDireccion().equals(Constantes.IZQUIERDA)) {
			if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL) &&
				contenido[x][y].getNumCarril() == 1)
				tratarAdelantamiento();
			else if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
				!mirarDerecha(Constantes.AUTOMOVIL) &&
				contenido[x][y].getNumCarril() == 2)
				tratarVolverACarril();
			else 
				y = y-1;
			direccionActual = contenido[x][y].getDireccion();
		}	
		else if (contenido[x][y].getDireccion().equals(Constantes.ARRIBA)) {
			if (mirarAdelante(2,Constantes.AUTOMOVIL) &&
				!mirarIzquierda(Constantes.AUTOMOVIL) &&
				contenido[x][y].getNumCarril() == 1)
				tratarAdelantamiento();
			else if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
				!mirarDerecha(Constantes.AUTOMOVIL) &&
				contenido[x][y].getNumCarril() == 2)
				tratarVolverACarril();
			else
				x = x-1;
			direccionActual = contenido[x][y].getDireccion();
		}	
		else if (contenido[x][y].getDireccion().equals(Constantes.ABAJO)) {
			if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL) &&
				contenido[x][y].getNumCarril() == 1)
				tratarAdelantamiento();
			else if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
				!mirarDerecha(Constantes.AUTOMOVIL) &&
				contenido[x][y].getNumCarril() == 2)
				tratarVolverACarril();
			else 
				x = x+1;
			direccionActual = contenido[x][y].getDireccion();
		}	
	}
	
	private void circularSecundaria() {
		
		if (contenido[x][y].getDireccion().equals(Constantes.DERECHA)) {
			if (adelantarEnSec) {
				direccionActual = Constantes.IZQUIERDA;
				if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
					mirarIzquierda(Constantes.AUTOMOVIL)) {
					y = y-1;
				}
				else {
					tratarVolverACarril();
					adelantarEnSec = false;
				}
			}
			else if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL)) {
				tratarAdelantamiento();
				adelantarEnSec = true;
			}
			else 
				y = y+1;
			direccionActual = contenido[x][y].getDireccion();
		}	
		else if (contenido[x][y].getDireccion().equals(Constantes.IZQUIERDA)) {
			if (adelantarEnSec) {
				direccionActual = Constantes.DERECHA;
				if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
					mirarIzquierda(Constantes.AUTOMOVIL)) {
					y = y+1;
				}
				else {
					tratarVolverACarril();
					adelantarEnSec = false;
				}
			}
			else if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL)) {
				tratarAdelantamiento();
				adelantarEnSec = true;
			}
			else 
				y = y-1;
			direccionActual = contenido[x][y].getDireccion();
		}	
		else if (contenido[x][y].getDireccion().equals(Constantes.ARRIBA)) {
			if (adelantarEnSec) {
				direccionActual = Constantes.ABAJO;
				if (!mirarAdelante(2,Constantes.AUTOMOVIL) &&
					mirarDerecha(Constantes.AUTOMOVIL)) {
					x = x+1;
				}
				else {
					tratarVolverACarril();
					adelantarEnSec = false;
				}
			}
			else if (mirarAdelante(2,Constantes.AUTOMOVIL) &&
				!mirarIzquierda(Constantes.AUTOMOVIL)) {
				tratarAdelantamiento();
				adelantarEnSec = true;
			}
			else 
				x = x-1;
			direccionActual = contenido[x][y].getDireccion();
		}	
		else if (contenido[x][y].getDireccion().equals(Constantes.ABAJO)) {
			if (adelantarEnSec) {
				direccionActual = Constantes.ARRIBA;
				if (!mirarAdelante(2,Constantes.AUTOMOVIL) &&
					mirarDerecha(Constantes.AUTOMOVIL)) {
					x = x-1;
				}
				else {
					tratarVolverACarril();
					adelantarEnSec = false;
				}
			}
			else if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL)) {
				tratarAdelantamiento();
				adelantarEnSec = true;
			}
			else 
				x = x+1;
			direccionActual = contenido[x][y].getDireccion();
		}	
	}
 	
	private void tratarPasoCruce() {
		
		if (direccionActual.equals(Constantes.ABAJO)) 
			x = x+1;
		else if (direccionActual.equals(Constantes.ARRIBA))
			x = x-1;
		else if (direccionActual.equals(Constantes.DERECHA)) 
			y = y+1;
		else if (direccionActual.equals(Constantes.IZQUIERDA))
			y = y-1;
	}
	
 	private void tratarSemaforo() {
		
 		if (direccionActual.equals(Constantes.ABAJO)) {
			if (contenido[x+1][y].getColorSemaforo().equals(Constantes.VERDE))
				x = x+1;
		}
		else if (direccionActual.equals(Constantes.ARRIBA)) {
			if (contenido[x-1][y].getColorSemaforo().equals(Constantes.VERDE))
				x = x-1;
		}
		else if (direccionActual.equals(Constantes.DERECHA)) {
			if (contenido[x][y+1].getColorSemaforo().equals(Constantes.VERDE))
				y = y+1;
		}
		else if (direccionActual.equals(Constantes.IZQUIERDA)) {
			if (contenido[x][y-1].getColorSemaforo().equals(Constantes.VERDE))
				y = y-1;
		}	
	}
 	
 	private void pasoSemaforo() {
 		
 		if (direccionActual.equals(Constantes.DERECHA))
 			y = y+1;
 		else if (direccionActual.equals(Constantes.IZQUIERDA))
 				y = y-1;
 			else if (direccionActual.equals(Constantes.ARRIBA))
 					x = x-1;
 				else if (direccionActual.equals(Constantes.ABAJO))
 						x = x+1;
 	}
 	
	private void tratarIncorporacion() {
		
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
	
	private boolean mirarAdelante(int pos,String item) {
		
		boolean encontrado = false;
		if (direccionActual.equals(Constantes.DERECHA)) {
			if (vision[pos][3].getTipo().equals(item))
				encontrado = true;
		}
		else if (direccionActual.equals(Constantes.IZQUIERDA)) {
			if (vision[pos][1].getTipo().equals(item))
				encontrado = true;
		}
		else if (direccionActual.equals(Constantes.ARRIBA)) {
			if (vision[1][pos].getTipo().equals(item))
				encontrado = true;
		}
		else if (direccionActual.equals(Constantes.ABAJO)) {
			if (vision[3][pos].getTipo().equals(item))
				encontrado = true;
		}
		return encontrado;
	}
	
	private boolean mirarAtras(int pos,String item) {
		
		boolean encontrado = false;
		if (direccionActual.equals(Constantes.DERECHA)) {
			for (int j=0;j<2 && !encontrado;j++)
				if (vision[pos][j].getTipo().equals(item))
					encontrado = true;
		}
		else if (direccionActual.equals(Constantes.IZQUIERDA)) {
			for (int j=3;j<5 && !encontrado;j++)
				if (vision[pos][j].getTipo().equals(item))
					encontrado = true;
		}
		else if (direccionActual.equals(Constantes.ARRIBA)) {
			for (int i=3;i<5 && !encontrado;i++)
				if (vision[i][pos].getTipo().equals(item))
					encontrado = true;
		}
		else if (direccionActual.equals(Constantes.ABAJO)) {
			for (int i=0;i<2 && !encontrado;i++)
				if (vision[i][pos].getTipo().equals(item))
					encontrado = true;
		}
		return encontrado;
	}
	
	private boolean mirarDerecha(String item) {
		
		boolean encontrado = false;
		if (direccionActual.equals(Constantes.ARRIBA)) {
			for (int i=0;i<5 && !encontrado;i++)
				for (int j=3;j<5 && !encontrado;j++)
					if (vision[i][j].getTipo().equals(item))
						encontrado = true;
		}
		else if (direccionActual.equals(Constantes.ABAJO)) {
			for (int i=0;i<5 && !encontrado;i++)
				for (int j=0;j<2 && !encontrado;j++)
					if (vision[i][j].getTipo().equals(item))
						encontrado = true;
		}
		else if (direccionActual.equals(Constantes.IZQUIERDA)) {
			for (int i=0;i<2 && !encontrado;i++)
				for (int j=0;j<5 && !encontrado;j++)
					if (vision[i][j].getTipo().equals(item))
						encontrado = true;
		}
		else if (direccionActual.equals(Constantes.DERECHA)) {
			for (int i=3;i<5 && !encontrado;i++)
				for (int j=0;j<5 && !encontrado;j++)
					if (vision[i][j].getTipo().equals(item))
						encontrado = true;
		}
		return encontrado;
	}
	
	private boolean mirarIzquierda(String item) {
		
		boolean encontrado = false;
		if (direccionActual.equals(Constantes.ABAJO)) {
			for (int i=0;i<5 && !encontrado;i++)
				for (int j=3;j<5 && !encontrado;j++)
					if (vision[i][j].getTipo().equals(item))
						encontrado = true;
		}
		else if (direccionActual.equals(Constantes.ARRIBA)) {
			for (int i=0;i<5 && !encontrado;i++)
				for (int j=0;j<2 && !encontrado;j++)
					if (vision[i][j].getTipo().equals(item))
						encontrado = true;
		}
		else if (direccionActual.equals(Constantes.DERECHA)) {
			for (int i=0;i<2 && !encontrado;i++)
				for (int j=0;j<5 && !encontrado;j++)
					if (vision[i][j].getTipo().equals(item))
						encontrado = true;
		}
		else if (direccionActual.equals(Constantes.IZQUIERDA)) {
			for (int i=3;i<5 && !encontrado;i++)
				for (int j=0;j<5 && !encontrado;j++)
					if (vision[i][j].getTipo().equals(item))
						encontrado = true;
		}
		return encontrado;
	}
	
	private void tratarAdelantamiento() {
		
		System.out.println("Intento adelantar");
		if (/*estadoMental().adelantar()*/true) {
			if (direccionActual.equals(Constantes.ABAJO)) {
				x = x+1;
				y = y+1;
			}
			else if (direccionActual.equals(Constantes.ARRIBA)) {
				x = x-1;
				y = y-1;
			}
			else if (direccionActual.equals(Constantes.DERECHA)) {
				x = x-1;
				y = y+1;
			}
			else if (direccionActual.equals(Constantes.IZQUIERDA)) {
				x = x+1;
				y = y-1;
			}
		}
	}
		
	private void tratarVolverACarril() {
			
		if (/*estadoMental().volverACarril()*/true) {
			if (direccionActual.equals(Constantes.ABAJO)) {
				x = x+1;
				y = y-1;
			}
			else if (direccionActual.equals(Constantes.ARRIBA)) {
				x = x-1;
				y = y+1;
			}
			else if (direccionActual.equals(Constantes.DERECHA)) {
				x = x+1;
				y = y+1;
			}
			else if (direccionActual.equals(Constantes.IZQUIERDA)) {
				x = x-1;
				y = y-1;
			}
		}
	}
	
	public void run() {
		
		while(!matriz.getParar()) {
			avanzar();
			matriz.actualizar(0);
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
