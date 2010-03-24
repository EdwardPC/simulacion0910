package vista;

import java.awt.Color;

import javax.swing.JPanel;

import mundo.Constantes;

public class Casilla extends JPanel {
	
	
	private static final long serialVersionUID = 1L;

	private String tipo;
	private String colorSemaforo;
	private String tipoConductor;
	
	public Casilla() {
	
		tipo = "";
		colorSemaforo = "";
		tipoConductor = "";
	}
	
	public String getTipoConductor() {
		return tipoConductor;
	}

	public void setTipoConductor(String tipoConductor) {
		this.tipoConductor = tipoConductor;
	}

	public void pintarCasilla() {
		
	/*if (contenido.contains("HD"))
			this.setBackground(Color.BLACK);
		else if (contenido.contains("HI"))
			this.setBackground(Color.PINK);
		else if (contenido.contains("VB"))
			this.setBackground(Color.RED);
		else if (contenido.contains("VA"))
			this.setBackground(Color.BLUE);
		/*if (contenido.contains("1"))
		this.setBackground(Color.BLACK);
	else if (contenido.contains("2"))
		this.setBackground(Color.PINK);*/
		if (tipo.equals(Constantes.EDIFICIO)) 
			this.setBackground(Color.DARK_GRAY);
		else if (tipo.equals(Constantes.SEPARACION))
			this.setBackground(Color.GRAY);
		else if (tipo.equals(Constantes.CAMPO)) 
			this.setBackground(Color.GREEN);
		else if (tipo.equals(Constantes.TIERRA))
			this.setBackground(new Color(100,50,5));
		else if (tipo.equals(Constantes.BORDE))
				this.setBackground(Color.ORANGE);
		else if (tipo.equals(Constantes.AUTOMOVIL)) {
			if (tipoConductor.equals(Constantes.AGRESIVO))
				this.setBackground(new Color(255,100,100));
			else if (tipoConductor.equals(Constantes.NORMAL))
				this.setBackground(new Color(100,100,200));
			else if (tipoConductor.equals(Constantes.MODERADO))
				this.setBackground(new Color(250,250,250));
		}
		else if (tipo.equals(Constantes.SEMAFORO)) {
			if (colorSemaforo.equals(Constantes.VERDE))
				this.setBackground(Color.GREEN);
			else if (colorSemaforo.equals(Constantes.AMARILLO))
				this.setBackground(Color.YELLOW);
			else if (colorSemaforo.equals(Constantes.ROJO))
				this.setBackground(Color.RED);
		}
		else if (tipo.equals(Constantes.STOP))
			this.setBackground(Color.MAGENTA);
		else if (tipo.equals(Constantes.CEDA_EL_PASO))
			this.setBackground(Color.BLUE);
		else
			this.setBackground(Color.BLACK);
	}
	
	public String getTipo() {
		
		return tipo;
	}
	
	public void setTipo(String datos) {
		
		tipo = datos;
	}
	
	public String getColorSemaforo() {
		return colorSemaforo;
	}

	public void setColorSemaforo(String colorSemaforo) {
		this.colorSemaforo = colorSemaforo;
	}
}
