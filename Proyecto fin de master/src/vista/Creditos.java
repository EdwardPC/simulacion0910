package vista;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Creditos extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel proyecto;
	private JLabel espacio;
	private JLabel autor;
	
	public Creditos() {
		setLayout(new GridLayout(3,1));
		proyecto = new JLabel("Proyecto fin de máster: Agentes Inteligentes");
		espacio = new JLabel("");
		autor = new JLabel("Autor: Alberto Fernández Isabel");
		add(proyecto);
		add(espacio);
		add(autor);
	}
}
