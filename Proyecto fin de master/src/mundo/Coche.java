package mundo;

import managerXML.Punto;



public class Coche {
	
	private Entorno entorno;
	private Integer velocidad;
	private Integer velMaxVehiculo;
	private String tipoConductor;
	
	

	public Coche(Entorno mundo,Integer velIni,Integer velMax) {
		
		entorno = mundo;
		velMaxVehiculo = velMax;
		velocidad = velIni;
		
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
		
		this.velocidad = velocidad;
	}
	
	public void acelerar(Integer vel) {
		
		velocidad = velocidad+vel;
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

	public void girar() {
		
	}
	
	public void atravesarCruce(String direccionActual,int x,int y) {
		
		if (direccionActual.equals(Constantes.ABAJO)) 
			x = x+1;
		else if (direccionActual.equals(Constantes.ARRIBA))
			x = x-1;
		else if (direccionActual.equals(Constantes.DERECHA)) 
			y = y+1;
		else if (direccionActual.equals(Constantes.IZQUIERDA))
			y = y-1;
	}
	
	public Punto tomarSalida(ItemsMundo anterior,Integer x,Integer y) {
		
		System.out.println(anterior.getDireccion());
		if (anterior.getDireccion().equals(Constantes.DERECHA)) 
				x = x+1;
		else if (anterior.getDireccion().equals(Constantes.IZQUIERDA))
				x = x-1;
		else if (anterior.getDireccion().equals(Constantes.ARRIBA)) 
				y = y+1;
		else if (anterior.getDireccion().equals(Constantes.ABAJO))
				y = y-1;
		
		Punto posicion = new Punto(x,y);
		return posicion;
	}

	public void devolverOriginal(int x,int y,String tipo,String conductor) {
		
		entorno.getItem(x,y).setTipo(tipo);
		entorno.getItem(x,y).setConductor(conductor);
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
