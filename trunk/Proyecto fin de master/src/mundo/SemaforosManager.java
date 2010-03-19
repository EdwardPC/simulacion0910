package mundo;

public class SemaforosManager extends Thread {

	private Matriz matriz;
	private Integer longitud;
	private ItemsMundo[][] contenido;
	
	public SemaforosManager(Matriz mundo) {
		
		matriz = mundo;
		longitud = mundo.getLongitud();
		contenido = mundo.getContenido();
		inicializar();
		matriz.actualizar();
	}
	
	private void inicializar() {
		
		for (int i=0;i<longitud;i++)
			for (int j=0;j<longitud;j++) {
				ItemsMundo item = contenido[i][j];
				if (item.getTipo().equals(Constantes.SEMAFORO))
					if (item.getDireccion().equals(Constantes.DERECHA) ||
							item.getDireccion().equals(Constantes.IZQUIERDA))
						item.setColorSemaforo(Constantes.VERDE);
					else if (item.getDireccion().equals(Constantes.ARRIBA) ||
							item.getDireccion().equals(Constantes.ABAJO))
						item.setColorSemaforo(Constantes.ROJO);
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
	
	public void run() {
		
		while (!matriz.getParar()) {
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
						if (item.getDireccion().equals(Constantes.DERECHA) ||
								item.getDireccion().equals(Constantes.IZQUIERDA))
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
				}
		}
		finalizar();
	}
}
