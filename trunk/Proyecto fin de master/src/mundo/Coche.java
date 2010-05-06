package mundo;

import manager.InfoGiro;
import manager.InfoSalida;
import manager.Punto;



public class Coche {
	
	private Entorno entorno;
	private Integer velocidad;
	private Integer velMaxVehiculo;
	private Integer contadorCruce;
	private String tipoConductor;
	
	

	public Coche(Entorno mundo,Integer velIni,Integer velMax) {
		
		entorno = mundo;
		velMaxVehiculo = velMax;
		velocidad = velIni;
		contadorCruce = 0;
	}
	
	public String getTipoConductor() {
		
		return tipoConductor;
	}

	public void setTipoConductor(String tipoConductor) {
		
		this.tipoConductor = tipoConductor;
	}
	
	public Integer getVelocidad() {
		
		return velocidad;
	}
	
	public void setVelocidad(Integer velocidad) {
		
		if (velocidad <= velMaxVehiculo)
			this.velocidad = velocidad;
		else
			this.velocidad = velMaxVehiculo;
	}
	
	public void acelerar(Integer vel) {
		
		if (velocidad+vel <= velMaxVehiculo)
			velocidad = velocidad+vel;
		else
			velocidad = velMaxVehiculo;
	}
	
	public void frenar(Integer vel) {
		
		velocidad = velocidad - vel;
	}
	
	public Integer getVelMaxVehiculo() {
		return velMaxVehiculo;
	}

	public void setVelMaxVehiculo(Integer velMaxVehiculo) {
		this.velMaxVehiculo = velMaxVehiculo;
	}
	
	public Punto adelantar(String direccionActual,int x,int y) {
		
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
		Punto punto = new Punto(x,y);
		return punto;
	}
	
	public Punto volverACarril(String direccionActual,int x,int y) {
		
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
		Punto punto = new Punto(x,y);
		return punto;
	}
	
	public Punto pasoSemaforo(String direccionActual,int x,int y) {
 		
 		if (direccionActual.equals(Constantes.DERECHA))
 			y = y+1;
 		else if (direccionActual.equals(Constantes.IZQUIERDA))
 				y = y-1;
 			else if (direccionActual.equals(Constantes.ARRIBA))
 					x = x-1;
 				else if (direccionActual.equals(Constantes.ABAJO))
 						x = x+1;
 		Punto punto = new Punto(x,y);
 		return punto;
 	}

	public InfoGiro girar(String direccion,String instruccion,int x,int y) {
		
		if (instruccion.equals(Constantes.DERECHA)) 
			y = y+1;
		else if (instruccion.equals(Constantes.IZQUIERDA)) 
			y = y-1;
		else if (instruccion.equals(Constantes.ARRIBA)) 
			x = x-1;
		else if (instruccion.equals(Constantes.ABAJO)) 
			x = x+1;
		direccion = instruccion;
		InfoGiro posicion = new InfoGiro(x,y,direccion);
		return posicion; 
	}
	
	public InfoSalida atravesarCruce(String direccion,String instruccion,int numCarril,int x,int y) {
		
		
		boolean finCruce = false;
		if (direccion.equals(Constantes.ABAJO)) {
			//System.out.println("Cont: "+contadorCruce);
			if (instruccion.equals(Constantes.DERECHA)) {
				if (numCarril == 1) {
					y = y-1;
					finCruce = true;
				}
				else if (numCarril == 2) {
					if (contadorCruce == 0) {
						x = x+1;
						y = y-1;
						contadorCruce = contadorCruce+1;
						finCruce = false;
					}
					else if (contadorCruce == 1) {
						y = y-1;
						contadorCruce = 0;
						finCruce = true;
					}	
				}
			}
			else if (instruccion.equals(Constantes.IZQUIERDA)) {
				if (numCarril == 1) {
					if (contadorCruce < 4) {
						x = x+1;
						y = y+1;
						contadorCruce = contadorCruce+1;
						finCruce = false;
					}
					else {
						y = y+1;
						contadorCruce = 0;
						finCruce = true;
					}
				}
				else if (numCarril == 2) {
					if (contadorCruce < 3) {
						x = x+1;
						y = y+1;
						contadorCruce = contadorCruce+1;
						finCruce = false;
					}
					else {
						y = y+1;
						contadorCruce = 0;
						finCruce = true;
					}
				}
			}
		}
		else if (direccion.equals(Constantes.ARRIBA)) {
			if (instruccion.equals(Constantes.DERECHA)) {
				if (numCarril == 1) {
					y = y+1;
					finCruce = true;
				}
				else if (numCarril == 2) {
					if (contadorCruce == 0) {
						x = x-1;
						y = y+1;
						contadorCruce = contadorCruce+1;
						finCruce = false;
					}
					else if (contadorCruce == 1) {
						y = y+1;
						contadorCruce = 0;
						finCruce = true;
					}	
				}
			}
			else if (instruccion.equals(Constantes.IZQUIERDA)) {
				if (numCarril == 1) {
					if (contadorCruce < 4) {
						x = x-1;
						y = y-1;
						contadorCruce = contadorCruce+1;
						finCruce = false;
					}
					else {
						y = y-1;
						contadorCruce = 0;
						finCruce = true;
					}
				}
				else if (numCarril == 2) {
					if (contadorCruce < 3) {
						x = x-1;
						y = y-1;
						contadorCruce = contadorCruce+1;
						finCruce = false;
					}
					else {
						y = y-1;
						contadorCruce = 0;
						finCruce = true;
					}
				}
			}
		}
		else if (direccion.equals(Constantes.IZQUIERDA)) {
			if (instruccion.equals(Constantes.ARRIBA)) {
				if (numCarril == 1) {
					x = x-1;
					finCruce = true;
				}
				else if (numCarril == 2) {
					if (contadorCruce == 0) {
						x = x-1;
						y = y-1;
						contadorCruce = contadorCruce+1;
						finCruce = false;
					}
					else if (contadorCruce == 1) {
						x = x-1;
						contadorCruce = 0;
						finCruce = true;
					}	
				}
			}
			else if (instruccion.equals(Constantes.ABAJO)) {
				if (numCarril == 1) {
					if (contadorCruce < 4) {
						x = x+1;
						y = y-1;
						contadorCruce = contadorCruce+1;
						finCruce = false;
					}
					else {
						x = x+1;
						contadorCruce = 0;
						finCruce = true;
					}
				}
				else if (numCarril == 2) {
					if (contadorCruce < 3) {
						x = x+1;
						y = y-1;
						contadorCruce = contadorCruce+1;
						finCruce = false;
					}
					else {
						x = x+1;
						contadorCruce = 0;
						finCruce = true;
					}
				}
			}
		}	
		else if (direccion.equals(Constantes.DERECHA)) {
			if (instruccion.equals(Constantes.ABAJO)) {
				if (numCarril == 1) {
					x = x+1;
					finCruce = true;
				}
				else if (numCarril == 2) {
					if (contadorCruce == 0) {
						x = x+1;
						y = y+1;
						contadorCruce = contadorCruce+1;
						finCruce = false;
					}
					else if (contadorCruce == 1) {
						x = x+1;
						contadorCruce = 0;
						finCruce = true;
					}	
				}
			}
			else if (instruccion.equals(Constantes.ARRIBA)) {
				if (numCarril == 1) {
					if (contadorCruce < 4) {
						x = x-1;
						y = y+1;
						contadorCruce = contadorCruce+1;
						finCruce = false;
					}
					else {
						x = x-1;
						contadorCruce = 0;
						finCruce = true;
					}
				}
				else if (numCarril == 2) {
					if (contadorCruce < 3) {
						x = x-1;
						y = y+1;
						contadorCruce = contadorCruce+1;
						finCruce = false;
					}
					else {
						x = x-1;
						contadorCruce = 0;
						finCruce = true;
					}
				}
			}	
		}
		InfoSalida posicion = new InfoSalida(x,y,finCruce);
		return posicion;
	}
	
	public Punto continuarCarril(String direccionActual,Integer x,Integer y) {
		
		if (direccionActual.equals(Constantes.DERECHA)) 
			y = y+1;
		else if (direccionActual.equals(Constantes.IZQUIERDA))
			y = y-1;
		else if (direccionActual.equals(Constantes.ARRIBA)) 
			x = x-1;
		else if (direccionActual.equals(Constantes.ABAJO))
			x = x+1;
		Punto posicion = new Punto(x,y);
		return posicion;
	}
	
	public Punto tomarSalida(String direccionActual,Integer x,Integer y) {
		
		if (direccionActual.equals(Constantes.DERECHA)) 
				x = x+1;
		else if (direccionActual.equals(Constantes.IZQUIERDA))
				x = x-1;
		else if (direccionActual.equals(Constantes.ARRIBA)) 
				y = y+1;
		else if (direccionActual.equals(Constantes.ABAJO))
				y = y-1;
		
		Punto posicion = new Punto(x,y);
		return posicion;
	}
	
	public Punto tratarIncorporacion(ItemsMundo anterior,Integer x,Integer y) {
		
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
		Punto posicion = new Punto(x,y);
		return posicion;
	}
	
	public InfoSalida tratarSalida(ItemsMundo anterior,Boolean parar,Integer x,Integer y) {
		
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
				if (anterior.getTramo() == 1)
					x = x-1;
				else 
					parar = true;
			}
			else if (entorno.getItem(x,y-1).getTipo().equals(Constantes.CARRIL_SALIDA)) {
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
		InfoSalida info = new InfoSalida(x,y,parar);
		return info;
	}

	public void devolverOriginal(int x,int y,ItemsMundo anterior) {
		
		entorno.getItem(x,y).setTipo(anterior.getTipo());
		entorno.getItem(x,y).setConductor(anterior.getConductor());
		entorno.getItem(x,y).setColorSemaforo(anterior.getColorSemaforo());
	}
	
	public void tomarPosicion(ItemsMundo anterior, int x,int y) {
		
		anterior.setTipo(entorno.getItem(x,y).getTipo());
		anterior.setDireccion(entorno.getItem(x,y).getDireccion());
		anterior.setTramo(entorno.getItem(x,y).getTramo());
		anterior.setConductor(entorno.getItem(x,y).getConductor());
		anterior.setColorSemaforo(entorno.getItem(x,y).getColorSemaforo());
		anterior.setNumCarril(entorno.getItem(x,y).getNumCarril());
		anterior.setSentido(entorno.getItem(x,y).getSentido());
		anterior.setAdelantar(entorno.getItem(x,y).getAdelantar());
		anterior.setVelocidadVia(entorno.getItem(x,y).getVelocidadVia());
		entorno.getItem(x,y).setTipo(Constantes.AUTOMOVIL);
		entorno.getItem(x,y).setConductor(tipoConductor);
	}
	
	
}
