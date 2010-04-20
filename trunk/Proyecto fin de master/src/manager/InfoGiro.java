package manager;

public class InfoGiro {

	private Integer x;
	private Integer y;
	private String direccion;
	
	public InfoGiro(Integer x,Integer y,String direccion) {
		
		this.x = x;
		this.y = y;
		this.direccion = direccion;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
