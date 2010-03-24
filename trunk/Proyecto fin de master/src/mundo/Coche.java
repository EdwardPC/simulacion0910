package mundo;

import java.util.Random;

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
	private Integer velIni;
	private String direccionActual;
	private String tipoConductor;
	private boolean adelantarEnSec;
	private boolean parar;
	
	public Coche(Modelo matrix,Integer comienzoX,Integer comienzoY,
			Integer velocidadIni,String conductor) {
		
		matriz = matrix;
		contenido = matriz.getContenido();
		antX = comienzoX;
		antY = comienzoY;
		x = antX;
		y = antY;
		velocidad = velocidadIni;
		velIni = velocidadIni;
		tipoConductor = conductor;
		parar = matriz.getParar();
		vision = new ItemsMundo[5][5];
		anterior = new ItemsMundo(contenido[antX][antY].getTipo(),contenido[antX][antY].isInicio());
		anterior.setDireccion(contenido[antX][antY].getDireccion());
		anterior.setConductor(contenido[antX][antY].getConductor());
		anterior.setAdelantar(contenido[antX][antY].getAdelantar());
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
		anterior.setAdelantar(contenido[x][y].getAdelantar());
		contenido[x][y].setTipo(Constantes.AUTOMOVIL);
		contenido[x][y].setConductor(tipoConductor);
		antX = x;
		antY = y;
		observarMundo();
		if (probarSalida())
			salir();
		else if (mirarAdelante(2,Constantes.SEMAFORO)) 
			tratarSemaforo();
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
		else if (anterior.getTipo().equals(Constantes.CARRIL_SALIDA))
			tratarSalida();
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
		
		if (anterior.getDireccion().equals(Constantes.DERECHA)) {
			if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL) &&
				anterior.getNumCarril() == 1)
				tratarAdelantamiento();
			else if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
				!mirarDerecha(Constantes.AUTOMOVIL) &&
				anterior.getNumCarril() == 2)
				tratarVolverACarril();
			else 
				y = y+1;
		}	
		else if (anterior.getDireccion().equals(Constantes.IZQUIERDA)) {
			if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL) &&
				anterior.getNumCarril() == 1)
				tratarAdelantamiento();
			else if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
				!mirarDerecha(Constantes.AUTOMOVIL) &&
				anterior.getNumCarril() == 2)
				tratarVolverACarril();
			else 
				y = y-1;
		}	
		else if (anterior.getDireccion().equals(Constantes.ARRIBA)) {
			if (mirarAdelante(2,Constantes.AUTOMOVIL) &&
				!mirarIzquierda(Constantes.AUTOMOVIL) &&
				anterior.getNumCarril() == 1)
				tratarAdelantamiento();
			else if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
				!mirarDerecha(Constantes.AUTOMOVIL) &&
				anterior.getNumCarril() == 2)
				tratarVolverACarril();
			else
				x = x-1;
		}	
		else if (anterior.getDireccion().equals(Constantes.ABAJO)) {
			if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL) &&
				anterior.getNumCarril() == 1)
				tratarAdelantamiento();
			else if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
				!mirarDerecha(Constantes.AUTOMOVIL) &&
				anterior.getNumCarril() == 2)
				tratarVolverACarril();
			else 
				x = x+1;
		}	
		direccionActual = anterior.getDireccion();
	}
	
	private void circularCiudad() {
		
		if (anterior.getDireccion().equals(Constantes.DERECHA)) {
			if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL) &&
				anterior.getNumCarril() == 1)
				tratarAdelantamiento();
			else if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
				!mirarDerecha(Constantes.AUTOMOVIL) &&
				anterior.getNumCarril() == 2)
				tratarVolverACarril();
			else 
				y = y+1;
		}	
		else if (anterior.getDireccion().equals(Constantes.IZQUIERDA)) {
			if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL) &&
				anterior.getNumCarril() == 1)
				tratarAdelantamiento();
			else if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
				!mirarDerecha(Constantes.AUTOMOVIL) &&
				anterior.getNumCarril() == 2)
				tratarVolverACarril();
			else 
				y = y-1;
		}	
		else if (anterior.getDireccion().equals(Constantes.ARRIBA)) {
			if (mirarAdelante(2,Constantes.AUTOMOVIL) &&
				!mirarIzquierda(Constantes.AUTOMOVIL) &&
				anterior.getNumCarril() == 1)
				tratarAdelantamiento();
			else if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
				!mirarDerecha(Constantes.AUTOMOVIL) &&
				anterior.getNumCarril() == 2)
				tratarVolverACarril();
			else
				x = x-1;
		}	
		else if (anterior.getDireccion().equals(Constantes.ABAJO)) {
			if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL) &&
				anterior.getNumCarril() == 1)
				tratarAdelantamiento();
			else if (!mirarAtras(2,Constantes.AUTOMOVIL) &&
				!mirarDerecha(Constantes.AUTOMOVIL) &&
				anterior.getNumCarril() == 2)
				tratarVolverACarril();
			else 
				x = x+1;
		}
		direccionActual = anterior.getDireccion();
	}
	
	private void circularSecundaria() {
		
		if (anterior.getDireccion().equals(Constantes.DERECHA)) {
			if (adelantarEnSec) {
				direccionActual = Constantes.IZQUIERDA;
				if (!mirarAdelante(2,Constantes.AUTOMOVIL) &&
					mirarDerecha(Constantes.AUTOMOVIL) && 
					anterior.getAdelantar()) {
					y = y-1;
				}
				else {
					tratarVolverACarril();
					adelantarEnSec = false;
				}
			}
			else if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL)) {
				adelantarEnSec = tratarAdelantamiento();
			}
			else 
				y = y+1;
		}	
		else if (anterior.getDireccion().equals(Constantes.IZQUIERDA)) {
			if (adelantarEnSec) {
				direccionActual = Constantes.DERECHA;
				if (!mirarAdelante(2,Constantes.AUTOMOVIL) &&
					mirarDerecha(Constantes.AUTOMOVIL) &&
					anterior.getAdelantar()) {
					y = y+1;
				}
				else {
					tratarVolverACarril();
					adelantarEnSec = false;
				}
			}
			else if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL)) {
				adelantarEnSec = tratarAdelantamiento();
			}
			else 
				y = y-1;
		}	
		else if (anterior.getDireccion().equals(Constantes.ARRIBA)) {
			if (adelantarEnSec) {
				direccionActual = Constantes.ABAJO;
				if (!mirarAdelante(2,Constantes.AUTOMOVIL) &&
					mirarDerecha(Constantes.AUTOMOVIL) &&
					anterior.getAdelantar()) {
					x = x+1;
				}
				else {
					tratarVolverACarril();
					adelantarEnSec = false;
				}
			}
			else if (mirarAdelante(2,Constantes.AUTOMOVIL) &&
				!mirarIzquierda(Constantes.AUTOMOVIL)) {
				adelantarEnSec = tratarAdelantamiento();
			}
			else 
				x = x-1;
		}	
		else if (anterior.getDireccion().equals(Constantes.ABAJO)) {
			if (adelantarEnSec) {
				direccionActual = Constantes.ARRIBA;
				if (!mirarAdelante(2,Constantes.AUTOMOVIL) &&
					mirarDerecha(Constantes.AUTOMOVIL) &&
					anterior.getAdelantar()) {
					x = x-1;
				}
				else {
					tratarVolverACarril();
					adelantarEnSec = false;
				}
			}
			else if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarIzquierda(Constantes.AUTOMOVIL)) {
				adelantarEnSec = tratarAdelantamiento();
			}
			else 
				x = x+1;
		}
		direccionActual = anterior.getDireccion();
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
		direccionActual = anterior.getDireccion();
	}
	
	private boolean probarSalida() {
		
		boolean encontrado = false;
		Random random = new Random();
		Integer respuesta = random.nextInt(2);
		//if (deboSalir()) {
			if (anterior.getDireccion().equals(Constantes.DERECHA)) {
				if (contenido[x+1][y].getTipo().equals(Constantes.CARRIL_SALIDA) &&
						contenido[x+1][y].isSalida()) {
					encontrado = true;
				}
			}
			else if (anterior.getDireccion().equals(Constantes.IZQUIERDA)) {
				if (contenido[x-1][y].getTipo().equals(Constantes.CARRIL_SALIDA) &&
						contenido[x-1][y].isSalida()) {
					encontrado = true;
				}
			}
			else if (anterior.getDireccion().equals(Constantes.ARRIBA)) {
				if (contenido[x][y+1].getTipo().equals(Constantes.CARRIL_SALIDA) &&
						contenido[x][y+1].isSalida()) {
					encontrado = true;
				}
			}
			else if (anterior.getDireccion().equals(Constantes.ABAJO)) {
				if (contenido[x][y-1].getTipo().equals(Constantes.CARRIL_SALIDA) &&
						contenido[x][y-1].isSalida()) {
					encontrado = true;
					System.out.println("OK salida");
				}
			}
		//}
		if (encontrado && respuesta == 1)
			return true;
		return false;
	}
	
	public void salir() {
		
		if (anterior.getDireccion().equals(Constantes.DERECHA)) 
				x = x+1;
		else if (anterior.getDireccion().equals(Constantes.IZQUIERDA))
				x = x-1;
		else if (anterior.getDireccion().equals(Constantes.ARRIBA)) 
				y = y+1;
		else if (anterior.getDireccion().equals(Constantes.ABAJO))
				y = y-1;
	}
	
	private void tratarSalida() {
		
		System.out.println(anterior.getDireccion());
		if (anterior.getDireccion().equals(Constantes.DERECHA)) {
			if (!(contenido[x][y+1].getTipo().equals(Constantes.CARRIL_SALIDA)) &&
					!(contenido[x][y+1].getTipo().equals(Constantes.AUTOMOVIL))) {
				if (anterior.getTramo() == 2)
					x = x+1;
				else 
					parar = true;
			}
			else if (contenido[x][y+1].getTipo().equals(Constantes.CARRIL_SALIDA)) {
				y = y+1;
			}
		}
		else if (anterior.getDireccion().equals(Constantes.IZQUIERDA)) {
			if (!(contenido[x][y-1].getTipo().equals(Constantes.CARRIL_SALIDA)) &&
					!(contenido[x][y-1].getTipo().equals(Constantes.AUTOMOVIL))) {
				System.out.println("Dentro giro");
				if (anterior.getTramo() == 1)
					x = x-1;
				else 
					parar = true;
			}
			else if (contenido[x][y-1].getTipo().equals(Constantes.CARRIL_SALIDA)) {
				System.out.println("ok sa");
				y = y-1;
			}
		}
		else if (anterior.getDireccion().equals(Constantes.ARRIBA)) {
			if (!(contenido[x-1][y].getTipo().equals(Constantes.CARRIL_SALIDA)) &&
					!(contenido[x-1][y].getTipo().equals(Constantes.AUTOMOVIL))) {
				if (anterior.getTramo() == 1)
					y = y+1;
				else 
					parar = true;
			}
			else if (contenido[x-1][y].getTipo().equals(Constantes.CARRIL_SALIDA)) {
				x = x-1;
			}
		}
		else if (anterior.getDireccion().equals(Constantes.ABAJO)) {
			if (!(contenido[x+1][y].getTipo().equals(Constantes.CARRIL_SALIDA)) &&
					!(contenido[x+1][y].getTipo().equals(Constantes.AUTOMOVIL))) {
				if (anterior.getTramo() == 1)
					y = y-1;
				else 
					parar = true;
			}
			else if (contenido[x+1][y].getTipo().equals(Constantes.CARRIL_SALIDA)) {
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
	
	private boolean tratarAdelantamiento() {
		
		System.out.println("Intento adelantar");
		Random random = new Random();
		Integer respuesta = random.nextInt(2);
		if (respuesta == 1 && anterior.getAdelantar()) {
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
			return true;
		}
		velocidad = velocidad/2;
		return false;
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
		
		while(!parar && !matriz.getParar()) {
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
		
		contenido[antX][antY].setTipo(anterior.getTipo());
		contenido[antX][antY].setConductor(anterior.getConductor());
		matriz.actualizar(0);
		try {
			
			this.finalize();
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
