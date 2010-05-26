package agente;

import manager.Estadisticas;
import manager.InfoGiro;
import manager.InfoSalida;
import manager.Punto;
import manager.Ruta;
import mundo.Coche;
import mundo.Constantes;
import mundo.Entorno;
import mundo.ItemsMundo;

public class Conductor extends Thread {

	private EstadoMental estadoMental;
	private Comportamiento comportamiento;
	private Coche vehiculo;
	private Entorno entorno;
	private ItemsMundo anterior;
	private ItemsMundo[][] vision;
	private Estadisticas estadisticas;
	private String direccionActual;
	private boolean adelantarEnSec;
	private boolean parar;
	private Ruta instruccionActual;
	private Integer indexRuta;
	private Integer carrilCruce;
	private Integer x;
	private Integer y;
	private Integer antX;
	private Integer antY;
	
	public Conductor(Entorno mundo,String tipo,Integer grado,Coche coche,Integer posX,Integer posY) {
		
		entorno = mundo;
		estadisticas = mundo.getEstadisticas();
		estadoMental = new EstadoMental(tipo,grado,entorno.getEleccion());
		comportamiento = new Comportamiento(estadoMental);
		vehiculo = coche;
		vehiculo.setTipoConductor(estadoMental.getTipoConductor());
		antX = posX;
		antY = posY;
		x = antX;
		y = antY;
		vision = new ItemsMundo[11][11];
		anterior = new ItemsMundo(mundo.getItem(antX,antY).getTipo(),mundo.getItem(antX,antY).isInicio());
		anterior.setDireccion(mundo.getItem(antX,antY).getDireccion());
		anterior.setConductor(mundo.getItem(antX,antY).getConductor());
		anterior.setAdelantar(mundo.getItem(antX,antY).getAdelantar());
		direccionActual = "";
		indexRuta = 0;
		carrilCruce = 0;
		instruccionActual = estadoMental.getRuta().get(indexRuta);
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
		
		tomarPosicion();
		observarMundo();
		adecuarVelocidad();
		realizarActuacion();
	}
	
	public void tomarPosicion() {
		
		if (!anterior.getTipo().equals(Constantes.AUTOMOVIL)) {
			vehiculo.devolverOriginal(antX,antY,anterior);
			
		}
		vehiculo.tomarPosicion(anterior,x,y);
		antX = x;
		antY = y;
	}
	
	public void observarMundo() {
		
		int x = antX;
		int y = antY;
		int visionX = 0;
		int visionY = 0;
		for (int i=x-5;i<=x+5;i++) {
			visionY = 0;
			for (int j=y-5;j<=y+5;j++) {
				vision[visionX][visionY] = entorno.getItem(i,j);
				visionY = visionY+1;
			}
			visionX = visionX+1;
		}
	}
	
	public void tratarVuelta() {
		
		if (entorno.getItem(x,y).getComienzoVuelta()) {
			
			Integer vueltas = estadoMental.getRuta().get(0).getNumVueltas();
			vueltas = vueltas-1;
			estadoMental.getRuta().get(0).setNumVueltas(vueltas);
		}
	}
	
	public void adecuarVelocidad() {
		
		if (estadoMental.getTipoConductor().equals(Constantes.AGRESIVO)) {
			if (vehiculo.getVelocidad() <= anterior.getVelocidadVia() && 
					!mirarAdelante(2,Constantes.AUTOMOVIL)) {
				if (estadoMental.getImpaciencia() == 1) 
					vehiculo.setVelocidad(anterior.getVelocidadVia()+10);
				else if (estadoMental.getImpaciencia() == 2) 
					vehiculo.setVelocidad(anterior.getVelocidadVia()+20);
				else if (estadoMental.getImpaciencia() == 3) 
					vehiculo.setVelocidad(anterior.getVelocidadVia()+40);
			}
		}
		else if (estadoMental.getTipoConductor().equals(Constantes.NORMAL)) {
			if (vehiculo.getVelocidad()<= anterior.getVelocidadVia() &&
					!mirarAdelante(3,Constantes.AUTOMOVIL)) {
				if (estadoMental.getImpaciencia() == 1)
					vehiculo.setVelocidad(anterior.getVelocidadVia()+5);
				else if (estadoMental.getImpaciencia() == 2)
					vehiculo.setVelocidad(anterior.getVelocidadVia()+10);
				else if (estadoMental.getImpaciencia() == 3)
					vehiculo.setVelocidad(anterior.getVelocidadVia()+15);
			}
		}
		else if (estadoMental.getTipoConductor().equals(Constantes.MODERADO)) {
			if (vehiculo.getVelocidad()>= anterior.getVelocidadVia() &&
					!mirarAdelante(5,Constantes.AUTOMOVIL)) {
				if (estadoMental.getImpaciencia() == 1)
					vehiculo.setVelocidad(anterior.getVelocidadVia()-10);
				else if (estadoMental.getImpaciencia() == 2)
					vehiculo.setVelocidad(anterior.getVelocidadVia()-5);
				else if (estadoMental.getImpaciencia() == 3)
					vehiculo.setVelocidad(anterior.getVelocidadVia());
			}
		}
	}
	
	public void realizarActuacion() {
		
		if (!anterior.getDireccion().equals("")) 
			direccionActual = anterior.getDireccion();

		switch (entorno.getEleccion()) {
		case 0:
			if (anterior.getNumCarril() != 0)
				carrilCruce = anterior.getNumCarril();
			if (anterior.getTipo().equals(Constantes.CRUCE)) {
				if (tratarPasoCruce()) {
					InfoSalida p = vehiculo.atravesarCruce(direccionActual,instruccionActual.getAccion(),carrilCruce,x,y);
					x = p.getX();
					y = p.getY();
					if (p.getParar()) 
						avanzarRuta();
				}
				else {
					Punto p = vehiculo.continuarCarril(direccionActual,x,y);
					x = p.getX();
					y = p.getY();
				}
			}
			else if (mirarAdelante(1,Constantes.SEMAFORO)) 
				tratarSemaforo();
			else if (anterior.getTipo().equals(Constantes.SEMAFORO)) {
				Punto p = vehiculo.continuarCarril(direccionActual,x,y);
				x = p.getX();
				y = p.getY();
			}
			else if (anterior.getTipo().contains(Constantes.CALLE))
				if (tratarCambioDireccion()) {
					InfoGiro p = vehiculo.girar(direccionActual,instruccionActual.getAccion(),x,y);
					x = p.getX();
					y = p.getY();
					direccionActual = p.getDireccion();
					avanzarRuta();
				}
				else
					circularCiudad();
			else if (anterior.getTipo().equals(Constantes.CRUCE_SIMPLE)) {
				tratarCeda();
			}
			else if (anterior.getTipo().equals(Constantes.CEDA_EL_PASO))
				tratarCeda();
			else if (anterior.getTipo().equals(Constantes.STOP))
				tratarStop();
			else if (anterior.getTipo().equals(Constantes.CARRIL_ENTRADA)) {
				Punto p = vehiculo.tratarIncorporacion(anterior,x,y);
				x = p.getX();
				y = p.getY();
			}
			else if (anterior.getTipo().equals(Constantes.AUTOMOVIL))
				estadisticas.setAccidentes(estadisticas.getAccidentes()+1);
			break;
		case 1:
			tratarVuelta();
			if (probarSalida()) {
				Punto p =  vehiculo.tomarSalida(direccionActual,x,y);
				x = p.getX();
				y = p.getY();
			}
			else if (anterior.getTipo().equals(Constantes.AUTOVIA)) 
				circularAutovia();
			else if (anterior.getTipo().equals(Constantes.CARRIL_ENTRADA)) {
				Punto p = vehiculo.tratarIncorporacion(anterior,x,y);
				x = p.getX();
				y = p.getY();
			}
			else if (anterior.getTipo().equals(Constantes.CARRIL_SALIDA)) {
				InfoSalida info = vehiculo.tratarSalida(anterior,parar,x,y);
				x = info.getX();
				y = info.getY();
				parar = info.getParar();
			}
			else if (anterior.getTipo().equals(Constantes.AUTOMOVIL))
				estadisticas.setAccidentes(estadisticas.getAccidentes()+1);
			break;
		case 2:
			tratarVuelta();
			if (probarSalida()) {
				Punto p =  vehiculo.tomarSalida(direccionActual,x,y);
				x = p.getX();
				y = p.getY();
			}
			else if (anterior.getTipo().equals(Constantes.SECUNDARIA))
				circularSecundaria();
			else if (anterior.getTipo().equals(Constantes.CARRIL_ENTRADA)) {
				Punto p = vehiculo.tratarIncorporacion(anterior,x,y);
				x = p.getX();
				y = p.getY();
			}
			else if (anterior.getTipo().equals(Constantes.CARRIL_SALIDA)) {
				InfoSalida info = vehiculo.tratarSalida(anterior,parar,x,y);
				x = info.getX();
				y = info.getY();
				parar = info.getParar();
			}
			else if (anterior.getTipo().equals(Constantes.AUTOMOVIL))
				estadisticas.setAccidentes(estadisticas.getAccidentes()+1);
			break;
		}
	}

	public void circularAutovia() {
		
		if (anterior.getDireccion().equals(Constantes.DERECHA)) {
			if (anterior.getNumCarril() == 1 && 
				mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarCarrilIzquierdo(3,7,Constantes.AUTOMOVIL))
					tratarAdelantamiento();
			else if (anterior.getNumCarril() == 2 &&
					!mirarCarrilDerecho(3,7,Constantes.AUTOMOVIL)) {
					tratarVolverACarril(1);
			}
			else if (!mirarAdelante(2,Constantes.AUTOMOVIL)) {
					Punto p = vehiculo.continuarCarril(direccionActual,x,y);
					x = p.getX();
					y = p.getY();
			}
		}	
		else if (anterior.getDireccion().equals(Constantes.IZQUIERDA)) {
			if (anterior.getNumCarril() == 1 && 
					mirarAdelante(2,Constantes.AUTOMOVIL) && 
					!mirarCarrilIzquierdo(3,7,Constantes.AUTOMOVIL))
						tratarAdelantamiento();
				else if (anterior.getNumCarril() == 2 &&
						!mirarCarrilDerecho(3,7,Constantes.AUTOMOVIL)) {
						tratarVolverACarril(1);
				}
				else if (!mirarAdelante(2,Constantes.AUTOMOVIL)) {
						Punto p = vehiculo.continuarCarril(direccionActual,x,y);
						x = p.getX();
						y = p.getY();
				}
		}	
		else if (anterior.getDireccion().equals(Constantes.ARRIBA)) {
			if (anterior.getNumCarril() == 1 && 
					mirarAdelante(2,Constantes.AUTOMOVIL) && 
					!mirarCarrilIzquierdo(3,7,Constantes.AUTOMOVIL))
						tratarAdelantamiento();
				else if (anterior.getNumCarril() == 2 &&
						!mirarCarrilDerecho(3,7,Constantes.AUTOMOVIL)) {
						tratarVolverACarril(1);
				}
				else if (!mirarAdelante(2,Constantes.AUTOMOVIL)) {
					Punto p = vehiculo.continuarCarril(direccionActual,x,y);
					x = p.getX();
					y = p.getY();
				}
		}	
		else if (anterior.getDireccion().equals(Constantes.ABAJO)) {
			if (anterior.getNumCarril() == 1 && 
					mirarAdelante(2,Constantes.AUTOMOVIL) && 
					!mirarCarrilIzquierdo(3,7,Constantes.AUTOMOVIL))
						tratarAdelantamiento();
				else if (anterior.getNumCarril() == 2 &&
						!mirarCarrilDerecho(3,7,Constantes.AUTOMOVIL)) {
						tratarVolverACarril(1);
				}
				else if (!mirarAdelante(2,Constantes.AUTOMOVIL)) {
					Punto p = vehiculo.continuarCarril(direccionActual,x,y);
					x = p.getX();
					y = p.getY();
				}
		}	
	}
	
	public void circularCiudad() {
		
		if (anterior.getDireccion().equals(Constantes.DERECHA)) {
			if (anterior.getNumCarril() == 1 && 
				mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarCarrilIzquierdo(3,7,Constantes.AUTOMOVIL))
					tratarAdelantamiento();
			else if (anterior.getNumCarril() == 2 &&
					!mirarCarrilDerecho(3,7,Constantes.AUTOMOVIL)) {
					System.out.println("OK carril");
					tratarVolverACarril(1);
			}
			else if (!mirarAdelante(2,Constantes.AUTOMOVIL))
				y = y+1;
		}	
		else if (anterior.getDireccion().equals(Constantes.IZQUIERDA)) {
			if (anterior.getNumCarril() == 1 && 
					mirarAdelante(2,Constantes.AUTOMOVIL) && 
					!mirarCarrilIzquierdo(3,7,Constantes.AUTOMOVIL))
						tratarAdelantamiento();
				else if (anterior.getNumCarril() == 2 &&
						!mirarCarrilDerecho(3,7,Constantes.AUTOMOVIL)) {
						System.out.println("OK carril");
						tratarVolverACarril(1);
				}
				else if (!mirarAdelante(2,Constantes.AUTOMOVIL))
					y = y-1;
		}	
		else if (anterior.getDireccion().equals(Constantes.ARRIBA)) {
			if (anterior.getNumCarril() == 1 && 
					mirarAdelante(2,Constantes.AUTOMOVIL) && 
					!mirarCarrilIzquierdo(3,7,Constantes.AUTOMOVIL))
						tratarAdelantamiento();
				else if (anterior.getNumCarril() == 2 &&
						!mirarCarrilDerecho(3,7,Constantes.AUTOMOVIL)) {
						System.out.println("OK carril");
						tratarVolverACarril(1);
				}
				else if (!mirarAdelante(2,Constantes.AUTOMOVIL))
					x = x-1;
		}	
		else if (anterior.getDireccion().equals(Constantes.ABAJO)) {
			if (anterior.getNumCarril() == 1 && 
					mirarAdelante(2,Constantes.AUTOMOVIL) && 
					!mirarCarrilIzquierdo(3,7,Constantes.AUTOMOVIL))
						tratarAdelantamiento();
				else if (anterior.getNumCarril() == 2 &&
						!mirarCarrilDerecho(3,7,Constantes.AUTOMOVIL)) {
						System.out.println("OK carril");
						tratarVolverACarril(1);
				}
				else if (!mirarAdelante(2,Constantes.AUTOMOVIL))
					x = x+1;
		}	
	}
	
	public void circularSecundaria() {
		
		if (anterior.getDireccion().equals(Constantes.DERECHA)) {
			if (adelantarEnSec) {
				direccionActual = Constantes.IZQUIERDA;
				if (anterior.getAdelantar() && 
					!mirarAdelante(5,Constantes.AUTOMOVIL) &&
					mirarCarrilDerecho(2,8,Constantes.AUTOMOVIL)) {
						Punto p = vehiculo.continuarCarril(direccionActual,x,y);
						x = p.getX();
						y = p.getY();
				}	
				else if (mirarAdelante(5,Constantes.AUTOMOVIL)) {
						if (!mirarCarrilDerecho(4,6,Constantes.AUTOMOVIL)) 
							adelantarEnSec = tratarVolverACarril(3);
						else {
							vehiculo.frenar(30);
							vehiculo.continuarCarril(direccionActual,x,y);
						}
				}
				else if (!anterior.getAdelantar() && 
					!mirarCarrilDerecho(4,6,Constantes.AUTOMOVIL))
						adelantarEnSec = tratarVolverACarril(2);
				else if (!mirarCarrilDerecho(2,8,Constantes.AUTOMOVIL))
						adelantarEnSec = tratarVolverACarril(1);	
			}
			else if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarCarrilIzquierdo(0,10,Constantes.AUTOMOVIL) &&
				anterior.getAdelantar()) 
					adelantarEnSec = tratarAdelantamiento();
			else if (!mirarAdelante(2,Constantes.AUTOMOVIL)) {
					Punto p = vehiculo.continuarCarril(direccionActual,x,y);
					x = p.getX();
					y = p.getY();
			}
		}	
		else if (anterior.getDireccion().equals(Constantes.IZQUIERDA)) {
			if (adelantarEnSec) {
				direccionActual = Constantes.DERECHA;
				if (anterior.getAdelantar() && 
					!mirarAdelante(5,Constantes.AUTOMOVIL) &&
					mirarCarrilDerecho(2,8,Constantes.AUTOMOVIL)) {
						Punto p = vehiculo.continuarCarril(direccionActual,x,y);
						x = p.getX();
						y = p.getY();
				}	
				else if (mirarAdelante(5,Constantes.AUTOMOVIL)) {
					if (!mirarCarrilDerecho(4,6,Constantes.AUTOMOVIL)) 
						adelantarEnSec = tratarVolverACarril(3);
					else {
						vehiculo.frenar(30);
						vehiculo.continuarCarril(direccionActual,x,y);
					}
				}
				else if (!anterior.getAdelantar() && 
					!mirarCarrilDerecho(4,6,Constantes.AUTOMOVIL))
						adelantarEnSec = tratarVolverACarril(2);
				else if (!mirarCarrilDerecho(2,8,Constantes.AUTOMOVIL))
						adelantarEnSec = tratarVolverACarril(1);	
			}
			else if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarCarrilIzquierdo(0,10,Constantes.AUTOMOVIL) &&
				anterior.getAdelantar()) 
					adelantarEnSec = tratarAdelantamiento();
			else if (!mirarAdelante(2,Constantes.AUTOMOVIL)) {
				Punto p = vehiculo.continuarCarril(direccionActual,x,y);
				x = p.getX();
				y = p.getY();
			}			
		}	
		else if (anterior.getDireccion().equals(Constantes.ARRIBA)) {
			if (adelantarEnSec) {
				direccionActual = Constantes.ABAJO;
				if (anterior.getAdelantar() && 
					!mirarAdelante(5,Constantes.AUTOMOVIL) &&
					mirarCarrilDerecho(2,8,Constantes.AUTOMOVIL)) {
						Punto p = vehiculo.continuarCarril(direccionActual,x,y);
						x = p.getX();
						y = p.getY();
				}	
				else if (mirarAdelante(5,Constantes.AUTOMOVIL)) {
					if (!mirarCarrilDerecho(4,6,Constantes.AUTOMOVIL)) 
						adelantarEnSec = tratarVolverACarril(3);
					else {
						vehiculo.frenar(30);
						vehiculo.continuarCarril(direccionActual,x,y);
					}
				}
				else if (!anterior.getAdelantar() && 
					!mirarCarrilDerecho(4,6,Constantes.AUTOMOVIL))
						adelantarEnSec = tratarVolverACarril(2);
				else if (!mirarCarrilDerecho(2,8,Constantes.AUTOMOVIL))
						adelantarEnSec = tratarVolverACarril(1);	
			}
			else if (mirarAdelante(2,Constantes.AUTOMOVIL) &&
				!mirarCarrilIzquierdo(0,10,Constantes.AUTOMOVIL) &&
				anterior.getAdelantar()) 
					adelantarEnSec = tratarAdelantamiento();
			else if (!mirarAdelante(2,Constantes.AUTOMOVIL)) {
					Punto p = vehiculo.continuarCarril(direccionActual,x,y);
					x = p.getX();
					y = p.getY();
			}
		}	
		else if (anterior.getDireccion().equals(Constantes.ABAJO)) {
			if (adelantarEnSec) {
				direccionActual = Constantes.ARRIBA;
				if (anterior.getAdelantar() && 
					!mirarAdelante(5,Constantes.AUTOMOVIL) &&
					mirarCarrilDerecho(2,8,Constantes.AUTOMOVIL)) {
						Punto p = vehiculo.continuarCarril(direccionActual,x,y);
						x = p.getX();
						y = p.getY();
				}
				else if (mirarAdelante(5,Constantes.AUTOMOVIL)) {
					if (!mirarCarrilDerecho(4,6,Constantes.AUTOMOVIL)) 
						adelantarEnSec = tratarVolverACarril(3);
					else {
						vehiculo.frenar(30);
						vehiculo.continuarCarril(direccionActual,x,y);
					}
				}
				else if (!anterior.getAdelantar() && 
					!mirarCarrilDerecho(4,6,Constantes.AUTOMOVIL))
						adelantarEnSec = tratarVolverACarril(2);
				else if (!mirarCarrilDerecho(2,8,Constantes.AUTOMOVIL))
						adelantarEnSec = tratarVolverACarril(1);	
			}
			else if (mirarAdelante(2,Constantes.AUTOMOVIL) && 
				!mirarCarrilIzquierdo(0,10,Constantes.AUTOMOVIL) && 
				anterior.getAdelantar()) 
					adelantarEnSec = tratarAdelantamiento();
			else if (!mirarAdelante(2,Constantes.AUTOMOVIL)) {
					Punto p = vehiculo.continuarCarril(direccionActual,x,y);
					x = p.getX();
					y = p.getY();
			}
		}
	}
	
	public void avanzarRuta() {
		
		indexRuta = indexRuta+1;
		if (indexRuta < estadoMental.getRuta().size())
			instruccionActual = estadoMental.getRuta().get(indexRuta);
		else 
			parar = true;
	}
	
	public boolean tratarCambioDireccion() {
		
		boolean encontrado = false;
		if (direccionActual.equals(Constantes.ARRIBA)) {
			if (entorno.getItem(x,y+1).getDireccion().equals(instruccionActual.getAccion()) && 
				entorno.getItem(x,y+1).getTipo().equals(instruccionActual.getTipoCalzada()))	
				encontrado = true;
		}	
		else if (direccionActual.equals(Constantes.ABAJO)) {
			if (entorno.getItem(x,y-1).getDireccion().equals(instruccionActual.getAccion()) && 
				entorno.getItem(x,y-1).getTipo().equals(instruccionActual.getTipoCalzada()))
				encontrado = true;
		}
		else if (direccionActual.equals(Constantes.DERECHA)) {
			if (entorno.getItem(x+1,y).getDireccion().equals(instruccionActual.getAccion()) && 
				entorno.getItem(x+1,y).getTipo().equals(instruccionActual.getTipoCalzada())) 
				encontrado = true;
		}
		else if (direccionActual.equals(Constantes.IZQUIERDA)) {
			if (entorno.getItem(x-1,y).getDireccion().equals(instruccionActual.getAccion()) && 
				entorno.getItem(x-1,y).getTipo().equals(instruccionActual.getTipoCalzada()))
				encontrado = true;
		}	
		return encontrado;
	}
	
 	public void tratarSemaforo() {
		
 		String color = "";
 		if (direccionActual.equals(Constantes.ABAJO)) { 
 			color = entorno.getItem(x+1,y).getColorSemaforo();
			if (comportamiento.deboSaltarSemaforo(color)) {
				Punto p = vehiculo.continuarCarril(direccionActual,x,y);
				x = p.getX();
				y = p.getY();
			}
		}
		else if (direccionActual.equals(Constantes.ARRIBA)) {
			color = entorno.getItem(x-1,y).getColorSemaforo();
			if (comportamiento.deboSaltarSemaforo(color)) {
				Punto p = vehiculo.continuarCarril(direccionActual,x,y);
				x = p.getX();
				y = p.getY();
			}
		}
		else if (direccionActual.equals(Constantes.DERECHA)) {
			color = entorno.getItem(x,y+1).getColorSemaforo();
			if (comportamiento.deboSaltarSemaforo(color)) {
				Punto p = vehiculo.continuarCarril(direccionActual,x,y);
				x = p.getX();
				y = p.getY();
			}
		}
		else if (direccionActual.equals(Constantes.IZQUIERDA)) {
			color = entorno.getItem(x,y-1).getColorSemaforo();
			if (comportamiento.deboSaltarSemaforo(color)) {
				Punto p = vehiculo.continuarCarril(direccionActual,x,y);
				x = p.getX();
				y = p.getY();
			}
		}	
	}
 	
 	public void tratarCeda() {
 		
 		boolean encontradoAd = false;
 		boolean encontradoIz = false;
 		boolean encontradoDer = false;
 		boolean encontradoPeligroso = false;
 		encontradoAd = mirarAdelante(1,Constantes.AUTOMOVIL);
 		encontradoIz = mirarIzquierda(6,4,Constantes.AUTOMOVIL);
 		encontradoDer = mirarDerecha(6,2,Constantes.AUTOMOVIL);
 		encontradoPeligroso = mirarIzquierda(6,2,Constantes.AUTOMOVIL);
 		if (encontradoAd || encontradoPeligroso) {
 			if (!comportamiento.deboCeder(3)) {
 				Punto p = vehiculo.continuarCarril(direccionActual,x,y);
	 			x = p.getX();
	 			y = p.getY();
 			}
 		}
 		else if (encontradoIz) {
 			if (!comportamiento.deboCeder(2)) {
 				Punto p = vehiculo.continuarCarril(direccionActual,x,y);
	 			x = p.getX();
	 			y = p.getY();
 			}
 		}
 		else if (encontradoDer) {
 			if (!comportamiento.deboCeder(1)) {
 				Punto p = vehiculo.continuarCarril(direccionActual,x,y);
 	 			x = p.getX();
 	 			y = p.getY();
 			}
 		}
 		else {
 			Punto p = vehiculo.continuarCarril(direccionActual,x,y);
 			x = p.getX();
 			y = p.getY();
 		}
 	}
 	
 	public void tratarStop() {
 		
 		boolean encontradoAd = false;
 		boolean encontradoIz = false;
 		boolean encontradoDer = false;
 		boolean encontradoPeligroso = false;
 		encontradoAd = mirarAdelante(1,Constantes.AUTOMOVIL);
 		encontradoIz = mirarIzquierda(6,4,Constantes.AUTOMOVIL);
 		encontradoDer = mirarDerecha(6,2,Constantes.AUTOMOVIL);
 		encontradoPeligroso = mirarIzquierda(6,2,Constantes.AUTOMOVIL);
 		if (encontradoAd || encontradoPeligroso) {
 			if (!comportamiento.deboParar(3)) {
 				Punto p = vehiculo.continuarCarril(direccionActual,x,y);
	 			x = p.getX();
	 			y = p.getY();
 			}
 		}
 		else if (encontradoIz) {
 			if (!comportamiento.deboParar(2)) {
 				Punto p = vehiculo.continuarCarril(direccionActual,x,y);
	 			x = p.getX();
	 			y = p.getY();
 			}
 		}
 		else if (encontradoDer) {
 			if (!comportamiento.deboParar(1)) {
 				Punto p = vehiculo.continuarCarril(direccionActual,x,y);
 	 			x = p.getX();
 	 			y = p.getY();
 			}
 		}
 		else if (!comportamiento.deboParar(0)){
 			Punto p = vehiculo.continuarCarril(direccionActual,x,y);
 			x = p.getX();
 			y = p.getY();
 		}
 	}
 	
 	public boolean tratarPasoCruce() {
 		
 		boolean opcion = false;
 		if (direccionActual.equals(Constantes.DERECHA) || 
 			direccionActual.equals(Constantes.IZQUIERDA)) {
 			if (instruccionActual.getTipoCalzada().equals(Constantes.CRUCE) && 
 				(instruccionActual.getAccion().equals(Constantes.ARRIBA) ||
 				instruccionActual.getAccion().equals(Constantes.ABAJO)))
 				opcion = true;
 		
 		}
 		else if (direccionActual.equals(Constantes.ARRIBA) || 
 				direccionActual.equals(Constantes.ABAJO)) {
 				if (instruccionActual.getTipoCalzada().equals(Constantes.CRUCE) && 
 					(instruccionActual.getAccion().equals(Constantes.DERECHA) ||
 					instruccionActual.getAccion().equals(Constantes.IZQUIERDA)))
 					opcion = true;
 		}
 		return opcion;
 	}

	public boolean tratarAdelantamiento() {
		
		boolean opcion = false;
		if (comportamiento.deboAdelantar())
			opcion = true;
		if (opcion) {
			vehiculo.setVelocidad(vehiculo.getVelocidad()+comportamiento.velocidadAdelantamiento());
			Punto p = vehiculo.adelantar(direccionActual,x,y);
			x = p.getX();
			y = p.getY();
		}
		else if (!mirarAdelante(2,Constantes.AUTOMOVIL)) {
			Punto p = vehiculo.continuarCarril(direccionActual,x,y);
			x = p.getX();
			y = p.getY();
		}
		return opcion;
	}
		
	public boolean tratarVolverACarril(Integer peligrosidad) {
			
		boolean opcion = comportamiento.deboVolverACarril(peligrosidad);
		if (opcion) {
			vehiculo.setVelocidad(anterior.getVelocidadVia());
			Punto p = vehiculo.volverACarril(direccionActual,x,y);
			x = p.getX();
			y = p.getY();
			estadisticas.setAdelantamientos(estadisticas.getAdelantamientos()+1);
		}
		else {
			Punto p = vehiculo.continuarCarril(direccionActual,x,y);
			x = p.getX();
			y = p.getY();
		}
		return !opcion;
	}
	
	public boolean probarSalida() {
		
		boolean encontrado = false;	
		if (comportamiento.salir()) {
			if (mirarDerecha(5,1,Constantes.CARRIL_SALIDA)) 
				encontrado = true;
		}
		return encontrado;
	}
	
	public boolean mirarAdelante(int pos,String item) {
		
		boolean encontrado = false;
		
		if (direccionActual.equals(Constantes.DERECHA)) {
			for (int j=6;j<=5+pos && !encontrado;j++) {;
				if (vision[5][j].getTipo().equals(item))
					encontrado = true;
			}
		}
		else if (direccionActual.equals(Constantes.IZQUIERDA)) {
			for (int j=4;j>=5-pos && !encontrado;j--) {
				if (vision[5][j].getTipo().equals(item))
					encontrado = true;
			}
		}
		else if (direccionActual.equals(Constantes.ARRIBA)) {
			for (int i=4;i>=5-pos && !encontrado;i--) {
				if (vision[i][5].getTipo().equals(item))
					encontrado = true;
			}
		}
		else if (direccionActual.equals(Constantes.ABAJO)) {
			for (int i=6;i<=5+pos && !encontrado;i++) {
				if (vision[i][5].getTipo().equals(item))
					encontrado = true;
			}
		}
		return encontrado;
	}
	
	public boolean mirarAtras(int pos,String item) {
		
		boolean encontrado = false;
		if (direccionActual.equals(Constantes.DERECHA)) {
			for (int j=4;j>=5-pos && !encontrado;j--) {
				if (vision[5][j].getTipo().equals(item))
					encontrado = true;
			}
		}
		else if (direccionActual.equals(Constantes.IZQUIERDA)) {
			for (int j=6;j<=5+pos && !encontrado;j++) {
				if (vision[5][j].getTipo().equals(item))
					encontrado = true;
			}
		}
		else if (direccionActual.equals(Constantes.ARRIBA)) {
			for (int i=6;i<=5+pos && !encontrado;i++) {
				if (vision[i][5].getTipo().equals(item))
					encontrado = true;
			}
		}
		else if (direccionActual.equals(Constantes.ABAJO)) {
			for (int i=4;i>=5-pos && !encontrado;i++) {
				if (vision[i][5].getTipo().equals(item))
					encontrado = true;
			}
		}
		return encontrado;
	}
	
	public boolean mirarDerecha(int pos1,int pos2,String item) {
		
		boolean encontrado = false;
		if (direccionActual.equals(Constantes.ARRIBA)) {
			for (int j=6;j<=5+pos2 && !encontrado;j++)
				if (vision[pos1][j].getTipo().equals(item))
					encontrado = true;
		}
		else if (direccionActual.equals(Constantes.ABAJO)) {
			for (int j=4;j>=5-pos2 && !encontrado;j--) 
				if (vision[pos1][j].getTipo().equals(item))
					encontrado = true;
		}
		else if (direccionActual.equals(Constantes.IZQUIERDA)) {
			for (int i=4;i>=5-pos2 && !encontrado;i--)
				if (vision [i][pos1].getTipo().equals(item))
					encontrado = true;
		}
		else if (direccionActual.equals(Constantes.DERECHA)) {
			for (int i=6;i<=5+pos2 && !encontrado;i++)
				if (vision [i][pos1].getTipo().equals(item))
					encontrado = true;
		}
		return encontrado;
	}
	
	public boolean mirarIzquierda(int pos1,int pos2,String item) {
		
		boolean encontrado = false;
		int pos11 = 0;
		int pos12 = 0;
		if (pos1 > 5) {
			pos11 = pos1;
			int aux = pos1-5;
			pos12 = 5-aux;
		}
		else {
			pos11 = pos1;
			pos12 = pos1;
		}
		if (direccionActual.equals(Constantes.ARRIBA)) {
			for (int j=4;j>=5-pos2 && !encontrado;j--) 
				if (vision[pos12][j].getTipo().equals(item))
					encontrado = true;
		}
		else if (direccionActual.equals(Constantes.ABAJO)) {
			for (int j=6;j<=5+pos2 && !encontrado;j++)
				if (vision[pos11][j].getTipo().equals(item))
					encontrado = true;
		}
		else if (direccionActual.equals(Constantes.IZQUIERDA)) {
			for (int i=6;i<=5+pos2 && !encontrado;i++)
				if (vision [i][pos12].getTipo().equals(item))
					encontrado = true;
		}
		else if (direccionActual.equals(Constantes.DERECHA)) {
			for (int i=4;i>=5-pos2 && !encontrado;i--)
				if (vision [i][pos11].getTipo().equals(item))
					encontrado = true;
		}
		return encontrado;
	}
	
	public boolean mirarCarrilDerecho(int pos1,int pos2,String item) {
		
		boolean encontrado = false;
		if (direccionActual.equals(Constantes.DERECHA)) {
			for (int j=pos1;j<=pos2 && !encontrado;j++)
				if (vision[6][j].getTipo().equals(item))
					encontrado = true;
		}
		else if (direccionActual.equals(Constantes.IZQUIERDA)) {
			for (int j=pos1;j<=pos2 && !encontrado;j++)
				if (vision[4][j].getTipo().equals(item))
					encontrado = true;
		}
		else if (direccionActual.equals(Constantes.ARRIBA)) {
			for (int i=pos1;i<=pos2 && !encontrado;i++)
				if (vision [i][6].getTipo().equals(item))
					encontrado = true;
		}
		else if (direccionActual.equals(Constantes.ABAJO)) {
			for (int i=pos1;i<=pos2 && !encontrado;i++)
				if (vision [i][4].getTipo().equals(item))
					encontrado = true;
		}
		return encontrado;
	}
	
	public boolean mirarCarrilIzquierdo(int pos1,int pos2,String item) {
		
		boolean encontrado = false;
		if (direccionActual.equals(Constantes.DERECHA)) {
			for (int j=pos1;j<=pos2 && !encontrado;j++)
				if (vision[4][j].getTipo().equals(item))
					encontrado = true;
		}
		else if (direccionActual.equals(Constantes.IZQUIERDA)) {
			for (int j=pos1;j<=pos2 && !encontrado;j++)
				if (vision[6][j].getTipo().equals(item))
					encontrado = true;
		}
		else if (direccionActual.equals(Constantes.ARRIBA)) {
			for (int i=pos1;i<=pos2 && !encontrado;i++)
				if (vision [i][4].getTipo().equals(item))
					encontrado = true;
		}
		else if (direccionActual.equals(Constantes.ABAJO)) {
			for (int i=pos1;i<=pos2 && !encontrado;i++)
				if (vision [i][6].getTipo().equals(item))
					encontrado = true;
		}
		return encontrado;
	}
	
	public void finalizar() {
		
		vehiculo.devolverOriginal(antX,antY,anterior);
		entorno.actualizar(0);
		try {
			
			this.finalize();
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	
}
