package agente;

import java.util.Random;

import managerXML.Punto;
import mundo.Coche;
import mundo.Constantes;
import mundo.Entorno;
import mundo.ItemsMundo;

public class Conductor extends Thread {

	private EstadoMental estadoMental;
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
		estadoMental = new EstadoMental(tipo,grado,entorno.getEleccion());
		vehiculo = coche;
		vehiculo.setTipoConductor(estadoMental.getTipoConductor());
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
		mundo.getItem(antX,antY).setConductor(estadoMental.getTipoConductor());
		adelantarEnSec = false;
		parar = false;
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
		tratarVuelta();
		adecuarVelocidad();
		if (probarSalida()) {
			Punto p =  vehiculo.tomarSalida(anterior,x,y);
			x = p.getX();
			y = p.getY();
		}
		else if (mirarAdelante(2,Constantes.SEMAFORO)) 
			tratarSemaforo();
		else if (anterior.getTipo().equals(Constantes.SEMAFORO)) {
			Punto p = vehiculo.pasoSemaforo(direccionActual,x,y);
			x = p.getX();
			y = p.getY();
		}
		else if (anterior.getTipo().equals(Constantes.AUTOVIA)) 
			circularAutovia();
		else if (anterior.getTipo().equals(Constantes.SECUNDARIA))
			circularSecundaria();
		else if (anterior.getTipo().contains(Constantes.CALLE))
			circularCiudad();
		else if (anterior.getTipo().equals(Constantes.CRUCE)) 
			vehiculo.atravesarCruce(direccionActual,x,y);
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
	
	public void observarMundo() {
		
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
	
	public void tratarVuelta() {
		
		if (entorno.getItem(x,y).getComienzoVuelta()) {
			Integer vueltas = new Integer(estadoMental.getRuta().get(0));
			System.out.println(vueltas);
			vueltas = vueltas-1;
			estadoMental.getRuta().remove(0);
			estadoMental.getRuta().add(vueltas.toString());
		}
	}
	
	public void adecuarVelocidad() {
		
		if (estadoMental.getTipoConductor().equals(Constantes.AGRESIVO)) {
			System.out.println("Vehiculo: "+vehiculo.getVelocidad());
			System.out.println("Via: "+anterior.getVelocidadVia());
			if (vehiculo.getVelocidad() <= anterior.getVelocidadVia() && 
					!mirarAdelante(2,Constantes.AUTOMOVIL)) {
				System.out.println("OK auto");
				if (estadoMental.getAnsiedad() == 1) {
					vehiculo.setVelocidad(anterior.getVelocidadVia()+10);
					System.out.println("1 "+vehiculo.getVelocidad());
				}
				else if (estadoMental.getAnsiedad() == 2) {
					vehiculo.setVelocidad(anterior.getVelocidadVia()+20);
					System.out.println("2 "+vehiculo.getVelocidad());
				}
				else if (estadoMental.getAnsiedad() == 3) {
					vehiculo.setVelocidad(anterior.getVelocidadVia()+40);
					System.out.println("3 "+vehiculo.getVelocidad());
				}
			}
		}
		else if (estadoMental.getTipoConductor().equals(Constantes.NORMAL)) {
			if (vehiculo.getVelocidad()<= anterior.getVelocidadVia() &&
					!mirarAdelante(2,Constantes.AUTOMOVIL)) {
				if (estadoMental.getAnsiedad() == 1)
					vehiculo.setVelocidad(anterior.getVelocidadVia()+5);
				else if (estadoMental.getAnsiedad() == 2)
					vehiculo.setVelocidad(anterior.getVelocidadVia()+10);
				else if (estadoMental.getAnsiedad() == 3)
					vehiculo.setVelocidad(anterior.getVelocidadVia()+15);
			}
		}
		else if (estadoMental.getTipoConductor().equals(Constantes.MODERADO)) {
			if (vehiculo.getVelocidad()>= anterior.getVelocidadVia() &&
					!mirarAdelante(2,Constantes.AUTOMOVIL)) {
				if (estadoMental.getAnsiedad() == 1)
					vehiculo.setVelocidad(anterior.getVelocidadVia()-10);
				else if (estadoMental.getAnsiedad() == 2)
					vehiculo.setVelocidad(anterior.getVelocidadVia()-5);
				else if (estadoMental.getAnsiedad() == 3)
					vehiculo.setVelocidad(anterior.getVelocidadVia());
			}
		}
	}

	public void circularAutovia() {
		
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
	
	public void circularCiudad() {
		
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
	
	public void circularSecundaria() {
		
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
 	
	
	
 	public void tratarSemaforo() {
		
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

	public boolean tratarAdelantamiento() {
		
		boolean opcion = false;
		if (estadoMental.deboAdelantar())
			opcion = true;
		if (opcion && anterior.getAdelantar()) {
			System.out.println("Vaya tela");
			vehiculo.setVelocidad(vehiculo.getVelocidad()+estadoMental.velocidadAdelantamiento());
			Punto p = vehiculo.adelantar(direccionActual,x,y);
			x = p.getX();
			y = p.getY();
			
		}
		return opcion;
	}
		
	public void tratarVolverACarril() {
			
		boolean opcion = true;
		if (estadoMental.deboVolverACarril())
			opcion = true;
		if (opcion) {
			vehiculo.setVelocidad(anterior.getVelocidadVia());
			Punto p = vehiculo.volverACarril(direccionActual,x,y);
			x = p.getX();
			y = p.getY();
		}
	}
	
	public void tratarIncorporacion() {
		
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
	
	public void tratarSalida() {
		
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
 
	public boolean probarSalida() {
		
		boolean encontrado = false;	
		if (estadoMental.salir()) {
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
		}
		return encontrado;
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
	
	public void finalizar() {
		
		vehiculo.devolverOriginal(antX,antY,anterior.getTipo(),anterior.getConductor());
		entorno.actualizar(0);
		try {
			
			this.finalize();
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	
}
