package modelo;

public class Tramo {

	int sentido;
	int x1;
	int x2;
	int y;
	
	public Tramo(int sent,int posX1,int posX2,int posY) {
		
		sentido = sent;
		x1 = posX1;
		x2 = posX2;
		y = posY;
	}

	public int getSentido() {
		return sentido;
	}

	public void setSentido(int sentido) {
		this.sentido = sentido;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	
}
