package vista;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import manager.Estadisticas;

public class PanelEstadisticas extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel adelantamientos;
	private JLabel totalConductores;
	private JLabel agresivos;
	private JLabel normales;
	private JLabel moderados;
	private JLabel accidentes;
	
	public PanelEstadisticas(Estadisticas estadisticas) {
		
		this.setLayout(new GridLayout(6,1));
		totalConductores = new JLabel("Total de conductores: "+ estadisticas.getConductores());
		moderados = new JLabel("Conductores moderados: "+ estadisticas.getModerados());
		normales = new JLabel("Conductores medios: "+ estadisticas.getNormales());
		agresivos = new JLabel("Conductores agresivos:" + estadisticas.getAgresivos());
		adelantamientos = new JLabel("Intentos de adelantamiento: "+ estadisticas.getAdelantamientos());
		accidentes = new JLabel("Accidentes ocurridos: "+ estadisticas.getAccidentes());
		this.add(totalConductores);
		this.add(moderados);
		this.add(normales);
		this.add(agresivos);
		this.add(adelantamientos);
		this.add(accidentes);
	}

}
