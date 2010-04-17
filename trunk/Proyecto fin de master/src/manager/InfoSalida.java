package manager;

public class InfoSalida {

	private Integer x;
	private Integer y;
	private Boolean parar;
	
	public InfoSalida(Integer x,Integer y,Boolean parar) {
		
		this.x = x;
		this.y = y;
		this.parar = parar;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Boolean getParar() {
		return parar;
	}

	public void setParar(Boolean parar) {
		this.parar = parar;
	}
	
}

