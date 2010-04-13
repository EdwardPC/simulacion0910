package agente;

import java.util.ArrayList;
import java.util.Random;

import mundo.Coche;
import mundo.Constantes;
import mundo.Entorno;
import mundo.ItemsMundo;

public class Conductor extends Thread {

	private ArrayList<String> ruta;
	private String tipoConductor;
	private Integer ansiedad;
	private Coche vehiculo;
	private Entorno entorno;
	private ItemsMundo anterior;
	private ItemsMundo[][] vision;
	private String direccionActual;
	private boolean adelantarEnSec;
	private boolean parar;
	private Integer x;
	private Integer y;
	private Integer antX;
	private Integer antY;
	
	public Conductor(Entorno mundo,String tipo,Integer grado,Coche coche,Integer posX,Integer posY) {
		
		entorno = mundo;
		tipoConductor = tipo;
		ansiedad = grado;
		vehiculo = coche;
		vehiculo.setTipoConductor(tipoConductor);
		antX = posX;
		antY = posY;
		x = antX;
		y = antY;
		vision = new ItemsMundo[5][5];
		anterior = new ItemsMundo(mundo.getItem(antX,antY).getTipo(),mundo.getItem(antX,antY).isInicio());
		anterior.setDireccion(mundo.getItem(antX,antY).getDireccion());
		anterior.setConductor(mundo.getItem(antX,antY).getConductor());
		anterior.setAdelantar(mundo.getItem(antX,antY).getAdelantar());
		direccionActual = "";
		mundo.getItem(antX,antY).setTipo(Constantes.AUTOMOVIL);
		mundo.getItem(antX,antY).setConductor(tipoConductor);
		adelantarEnSec = false;
		parar = false;
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
	
	public void run() {
		
		while(!entorno.getParar() && !parar) {
			avanzar();
			entorno.actualizar(0);
			Integer velActual = 300-vehiculo.getVelocidad()-entorno.getVelocidadSimulacion();
			vehiculo.setVelocidad(velActual);
			try {
					Conductor.sleep(velActual);
				}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		finalizar();
	}
	
	public void avanzar() {
		
		if (!anterior.getTipo().equals(Constantes.AUTOMOVIL)) {
			vehiculo.devolverOriginal(antX,antY,anterior.getTipo(),anterior.getConductor());
			
		}
		vehiculo.tomarPosicion(anterior,x,y);
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
				vision[visionX][visionY] = entorno.getItem(i,j);
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
			if (entorno.getItem(x+1,y).getColorSemaforo().equals(Constantes.VERDE))
				x = x+1;
		}
		else if (direccionActual.equals(Constantes.ARRIBA)) {
			if (entorno.getItem(x-1,y).getColorSemaforo().equals(Constantes.VERDE))
				x = x-1;
		}
		else if (direccionActual.equals(Constantes.DERECHA)) {
			if (entorno.getItem(x,y+1).getColorSemaforo().equals(Constantes.VERDE))
				y = y+1;
		}
		else if (direccionActual.equals(Constantes.IZQUIERDA)) {
			if (entorno.getItem(x,y-1).getColorSemaforo().equals(Constantes.VERDE))
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
			if (!(entorno.getItem(x,y+1).getTipo().equals(Constantes.CARRIL_ENTRADA)) &&
					!(entorno.getItem(x,y+1).getTipo().equals(Constantes.AUTOMOVIL)) && 
					!(entorno.getItem(x+1,y).getTipo().equals(Constantes.AUTOMOVIL))) {
				if (anterior.getTramo() == 2)
					x = x-1;
				else 
					x = x+1;
			}
			else if (entorno.getItem(x,y+1).getTipo().equals(Constantes.CARRIL_ENTRADA)) {
				y = y+1;
			}
		}
		else if (anterior.getDireccion().equals(Constantes.IZQUIERDA)) {
			if (!(entorno.getItem(x,y-1).getTipo().equals(Constantes.CARRIL_ENTRADA)) &&
					!(entorno.getItem(x,y-1).getTipo().equals(Constantes.AUTOMOVIL)) && 
					!(entorno.getItem(x+1,y).getTipo().equals(Constantes.AUTOMOVIL))) {
				if (anterior.getTramo() == 2)
					x = x-1;
				else 
					x = x+1;
			}
			else if (entorno.getItem(x,y-1).getTipo().equals(Constantes.CARRIL_ENTRADA)) {
				y = y-1;
			}
		}
		else if (anterior.getDireccion().equals(Constantes.ARRIBA)) {
			if (!(entorno.getItem(x-1,y).getTipo().equals(Constantes.CARRIL_ENTRADA)) &&
					!(entorno.getItem(x-1,y).getTipo().equals(Constantes.AUTOMOVIL)) && 
					!(entorno.getItem(x,y-1).getTipo().equals(Constantes.AUTOMOVIL))) {
				if (anterior.getTramo() == 2)
					y = y+1;
				else 
					y = y-1;
			}
			else if (entorno.getItem(x-1,y).getTipo().equals(Constantes.CARRIL_ENTRADA)) {
				x = x-1;
			}
		}
		else if (anterior.getDireccion().equals(Constantes.ABAJO)) {
			if (!(entorno.getItem(x+1,y).getTipo().equals(Constantes.CARRIL_ENTRADA)) &&
					!(entorno.getItem(x+1,y).getTipo().equals(Constantes.AUTOMOVIL)) && 
					!(entorno.getItem(x,y+1).getTipo().equals(Constantes.AUTOMOVIL))) {
				if (anterior.getTramo() == 2)
					y = y-1;
				else 
					y = y+1;
			}
			else if (entorno.getItem(x+1,y).getTipo().equals(Constantes.CARRIL_ENTRADA)) {
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
				if (entorno.getItem(x+1,y).getTipo().equals(Constantes.CARRIL_SALIDA) &&
						entorno.getItem(x+1,y).isSalida()) {
					encontrado = true;
				}
			}
			else if (anterior.getDireccion().equals(Constantes.IZQUIERDA)) {
				if (entorno.getItem(x-1,y).getTipo().equals(Constantes.CARRIL_SALIDA) &&
						entorno.getItem(x-1,y).isSalida()) {
					encontrado = true;
				}
			}
			else if (anterior.getDireccion().equals(Constantes.ARRIBA)) {
				if (entorno.getItem(x,y+1).getTipo().equals(Constantes.CARRIL_SALIDA) &&
						entorno.getItem(x,y+1).isSalida()) {
					encontrado = true;
				}
			}
			else if (anterior.getDireccion().equals(Constantes.ABAJO)) {
				if (entorno.getItem(x,y-1).getTipo().equals(Constantes.CARRIL_SALIDA) &&
						entorno.getItem(x,y-1).isSalida()) {
					encontrado = true;
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
			if (!(entorno.getItem(x,y+1).getTipo().equals(Constantes.CARRIL_SALIDA)) &&
					!(entorno.getItem(x,y+1).getTipo().equals(Constantes.AUTOMOVIL))) {
				if (anterior.getTramo() == 2)
					x = x+1;
				else 
					parar = true;
			}
			else if (entorno.getItem(x,y+1).getTipo().equals(Constantes.CARRIL_SALIDA)) {
				y = y+1;
			}
		}
		else if (anterior.getDireccion().equals(Constantes.IZQUIERDA)) {
			if (!(entorno.getItem(x,y-1).getTipo().equals(Constantes.CARRIL_SALIDA)) &&
					!(entorno.getItem(x,y-1).getTipo().equals(Constantes.AUTOMOVIL))) {
				System.out.println("Dentro giro");
				if (anterior.getTramo() == 1)
					x = x-1;
				else 
					parar = true;
			}
			else if (entorno.getItem(x,y-1).getTipo().equals(Constantes.CARRIL_SALIDA)) {
				System.out.println("ok sa");
				y = y-1;
			}
		}
		else if (anterior.getDireccion().equals(Constantes.ARRIBA)) {
			if (!(entorno.getItem(x-1,y).getTipo().equals(Constantes.CARRIL_SALIDA)) &&
					!(entorno.getItem(x-1,y).getTipo().equals(Constantes.AUTOMOVIL))) {
				if (anterior.getTramo() == 1)
					y = y+1;
				else 
					parar = true;
			}
			else if (entorno.getItem(x-1,y).getTipo().equals(Constantes.CARRIL_SALIDA)) {
				x = x-1;
			}
		}
		else if (anterior.getDireccion().equals(Constantes.ABAJO)) {
			if (!(entorno.getItem(x+1,y).getTipo().equals(Constantes.CARRIL_SALIDA)) &&
					!(entorno.getItem(x+1,y).getTipo().equals(Constantes.AUTOMOVIL))) {
				if (anterior.getTramo() == 1)
					y = y-1;
				else 
					parar = true;
			}
			else if (entorno.getItem(x+1,y).getTipo().equals(Constantes.CARRIL_SALIDA)) {
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
		vehiculo.setVelocidad(vehiculo.getVelocidad()/2);
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
	
	public void finalizar() {
		
		entorno.getItem(antX,antY).setTipo(anterior.getTipo());
		entorno.getItem(antX,antY).setConductor(anterior.getConductor());
		entorno.actualizar(0);
		try {
			
			this.finalize();
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	
}
