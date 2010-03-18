package vista;

import java.awt.Color;

import javax.swing.JPanel;

import mundo.Constantes;

public class Casilla extends JPanel {
	
	
	private static final long serialVersionUID = 1L;

	private String contenido;
	
	public Casilla() {
	
		contenido = "";
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
		if (contenido.equals(Constantes.EDIFICIO)) 
			this.setBackground(Color.DARK_GRAY);
		else if (contenido.equals(Constantes.SEPARACION))
			this.setBackground(Color.GRAY);
		else if (contenido.equals(Constantes.CAMPO)) 
			this.setBackground(Color.GREEN);
		else if (contenido.equals(Constantes.TIERRA))
			this.setBackground(new Color(100,50,5));
		else if (contenido.equals(Constantes.BORDE))
				this.setBackground(Color.ORANGE);
		else if (contenido.equals(Constantes.AUTOMOVIL))
			this.setBackground(new Color(255,255,255));
		else if (contenido.equals(Constantes.SEMAFORO))
			this.setBackground(Color.GREEN);
		else if (contenido.equals(Constantes.STOP))
			this.setBackground(Color.MAGENTA);
		else if (contenido.equals(Constantes.CEDA_EL_PASO))
			this.setBackground(Color.BLUE);
		else
			this.setBackground(Color.BLACK);
	}
	
	public String getContenido() {
		
		return contenido;
	}
	
	public void setContenido(String datos) {
		
		contenido = datos;
	}
}
