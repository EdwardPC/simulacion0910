package mundo;



public class Coche {
	
	//private ItemsMundo[][] contenido;
	
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
	
	public void setVelocidad(Integer velAct) {
		
		velocidad = velAct;
	}
	

	public void situacionActual() {
		
	}
	
	public void adelantar() {
		
	}
	
	public void volverACarril() {
		
	}
	
	public void girar() {
		
	}
	
	public void acelerar() {
		
	}
	
	public void frenar() {
		
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
		entorno.getItem(x,y).setTipo(Constantes.AUTOMOVIL);
		entorno.getItem(x,y).setConductor(tipoConductor);
	}
	
	
	
}
