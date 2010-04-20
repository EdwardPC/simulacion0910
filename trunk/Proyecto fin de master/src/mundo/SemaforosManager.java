package mundo;

public class SemaforosManager extends Thread {

	private Entorno matriz;
	private Integer longitud;
	private ItemsMundo[][] contenido;
	private Integer color;
	private Integer semaforo;
	private Integer numSemaforos;
	
	public SemaforosManager(Entorno mundo) {
		
		matriz = mundo;
		longitud = mundo.getLongitud();
		contenido = mundo.getContenido();
		color = 1;
		semaforo = 1;
		numSemaforos = 0;
		inicializar();
		matriz.actualizar(0);
	}
	
	private void inicializar() {
		
		for (int i=0;i<longitud;i++)
			for (int j=0;j<longitud;j++) {
				ItemsMundo item = contenido[i][j];
				if (item.getTipo().equals(Constantes.SEMAFORO)) {
					numSemaforos = numSemaforos+1;
					if (item.getDireccion().equals(Constantes.DERECHA))
							item.setColorSemaforo(Constantes.ROJO);
					else if (item.getDireccion().equals(Constantes.IZQUIERDA))
						item.setColorSemaforo(Constantes.VERDE);
					else if (item.getDireccion().equals(Constantes.ARRIBA))
						item.setColorSemaforo(Constantes.ROJO);
					else if (item.getDireccion().equals(Constantes.ABAJO))
						item.setColorSemaforo(Constantes.ROJO);
				}
			}
		semaforo = 2;
		color = 2;
	}
	
	public void finalizar() {
		
		try {
			this.finalize();
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public void getSemaforo() {
		
		if (semaforo == 5)
			semaforo = 1;
	}
	
	public void getColor() {
		
		if (color == 1) 
			color = color+1;
		else if (color == 2) 
			color = color+1;
		else if (color == 3) {
			color = 1;
			semaforo = semaforo+1;
		}
	}
	
	public String obtenerDireccion() {
		
		String resultado = null;
		switch(semaforo) {
			case 1:
				resultado = Constantes.DERECHA;
				break;
			case 2:
				resultado = Constantes.IZQUIERDA;
				break;
			case 3:
				resultado = Constantes.ARRIBA;
				break;
			case 4:
				resultado = Constantes.ABAJO;
				break;
		}
		return resultado;
	}
	
	public String obtenerColor() {
		
		String resultado = null;
		switch(color) {
			case 1:
				resultado = Constantes.VERDE;
				break;
			case 2:
				resultado = Constantes.AMARILLO;
				break;
			case 3:
				resultado = Constantes.ROJO;
				break;
		}
		return resultado;
	}
	
	public boolean obtenerFactorRojo() {
		
		boolean resultado = false;
		Integer contador = 0;
		for (int i=0;i<longitud;i++)
			for (int j=0;j<longitud;j++) {
				ItemsMundo item = contenido[i][j];
				if (item.getTipo().equals(Constantes.SEMAFORO))
					if (item.getColorSemaforo().equals(Constantes.ROJO))
						contador = contador+1;
			}
		System.out.println("Rojos: "+contador);
		if (contador == numSemaforos)
			resultado = true;
		return resultado;
	}
	
	public boolean obtenerFactorAmarillo() {
		
		boolean encontrado = false;
		for (int i=0;i<longitud && !encontrado;i++)
			for (int j=0;j<longitud && !encontrado;j++) {
				ItemsMundo item = contenido[i][j];
				if (item.getTipo().equals(Constantes.SEMAFORO))
					if (item.getColorSemaforo().equals(Constantes.AMARILLO))
						encontrado = true;
			}
		return encontrado;
	}
	
	public void run() {
		
		boolean factorRojo = false;
		boolean factorAmarillo = false;
		while (!matriz.getParar()) {
			try {
				if (factorRojo || factorAmarillo)
					SemaforosManager.sleep(2000 - matriz.getVelocidadSimulacion());
				else
					SemaforosManager.sleep(7000 - matriz.getVelocidadSimulacion());
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			for (int i=0;i<longitud;i++)
				for (int j=0;j<longitud;j++) {
					ItemsMundo item = contenido[i][j];
					if (item.getTipo().equals(Constantes.SEMAFORO))
						if (item.getDireccion().equals(obtenerDireccion()))
							item.setColorSemaforo(obtenerColor());
				}
			getColor();
			getSemaforo();
			factorRojo = obtenerFactorRojo();
			factorAmarillo = obtenerFactorAmarillo();
			
			/*
			for (int i=0;i<longitud;i++)
				for (int j=0;j<longitud;j++) {
					ItemsMundo item = contenido[i][j];
					if (item.getTipo().equals(Constantes.SEMAFORO))
							if (item.getDireccion().equals(Constantes.DERECHA) ||
									item.getDireccion().equals(Constantes.IZQUIERDA))
								item.setColorSemaforo(Constantes.ROJO);
				}
			try {
				SemaforosManager.sleep(1000 - matriz.getVelocidadSimulacion());
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			for (int i=0;i<longitud;i++)
				for (int j=0;j<longitud;j++) {
					ItemsMundo item = contenido[i][j];
					if (item.getTipo().equals(Constantes.SEMAFORO))
						if (item.getDireccion().equals(Constantes.ARRIBA) ||
								item.getDireccion().equals(Constantes.ABAJO))
							item.setColorSemaforo(Constantes.VERDE);
				}
			try {
				SemaforosManager.sleep(4000 - matriz.getVelocidadSimulacion());
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			for (int i=0;i<longitud;i++)
				for (int j=0;j<longitud;j++) {
					ItemsMundo item = contenido[i][j];
					if (item.getTipo().equals(Constantes.SEMAFORO))
						if (item.getDireccion().equals(Constantes.ARRIBA) ||
								item.getDireccion().equals(Constantes.ABAJO))
							item.setColorSemaforo(Constantes.AMARILLO);
				}
			try {
				SemaforosManager.sleep(1000 - matriz.getVelocidadSimulacion());
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			for (int i=0;i<longitud;i++)
				for (int j=0;j<longitud;j++) {
					ItemsMundo item = contenido[i][j];
					if (item.getTipo().equals(Constantes.SEMAFORO))
						if (item.getDireccion().equals(Constantes.ARRIBA) ||
								item.getDireccion().equals(Constantes.ABAJO))
							item.setColorSemaforo(Constantes.ROJO);
				}
			try {
				SemaforosManager.sleep(1000 - matriz.getVelocidadSimulacion());
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			for (int i=0;i<longitud;i++)
				for (int j=0;j<longitud;j++) {
					ItemsMundo item = contenido[i][j];
					if (item.getTipo().equals(Constantes.SEMAFORO))
						if (item.getDireccion().equals(Constantes.DERECHA) ||
								item.getDireccion().equals(Constantes.IZQUIERDA))
							item.setColorSemaforo(Constantes.VERDE);
				}*/
		}
		finalizar();
	}
}
