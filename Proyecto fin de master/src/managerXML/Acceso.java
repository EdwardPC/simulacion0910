package managerXML;

public class Acceso {

	private int x1;
	private int x2;
	private int x3;
	private int x4;
	private int y1;
	private int y2;
	private int iniX;
	private int iniY;
	private int tram1;
	private int tram2;
	private int dir1;
	private int dir2;

	public Acceso(int posX1,int posX2,int posX3,int posX4,int posY1,int posY2,
			int inix,int iniy,int tramo1,int tramo2,int dire1,int dire2) {
		
		x1 = posX1;
		x2 = posX2;
		x3 = posX3;
		x4 = posX4;
		y1 = posY1;
		y2 = posY2;
		iniX = inix;
		iniY = iniy;
		tram1 = tramo1;
		tram2 = tramo2;
		dir1 = dire1;
		dir2 = dire2;
	}

	public int getTram1() {
		return tram1;
	}

	public void setTram1(int tram1) {
		this.tram1 = tram1;
	}

	public int getTram2() {
		return tram2;
	}

	public void setTram2(int tram2) {
		this.tram2 = tram2;
	}

	public int getIniX() {
		return iniX;
	}

	public void setIniX(int iniX) {
		this.iniX = iniX;
	}

	public int getIniY() {
		return iniY;
	}

	public void setIniY(int iniY) {
		this.iniY = iniY;
	}

	public int getDir1() {
		return dir1;
	}

	public void setDir1(int dir1) {
		this.dir1 = dir1;
	}

	public int getDir2() {
		return dir2;
	}

	public void setDir2(int dir2) {
		this.dir2 = dir2;
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

	public int getX3() {
		return x3;
	}

	public void setX3(int x3) {
		this.x3 = x3;
	}

	public int getX4() {
		return x4;
	}

	public void setX4(int x4) {
		this.x4 = x4;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
}
