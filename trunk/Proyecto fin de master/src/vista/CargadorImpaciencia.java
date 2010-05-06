package vista;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class CargadorImpaciencia extends JPanel {

	private JRadioButton impacientes;
	private JRadioButton medios;
	private JRadioButton pacientes;
	private JRadioButton aleatorio;
	private ButtonGroup grupo;
	
	public CargadorImpaciencia() {
		
		pacientes = new JRadioButton("Pacientes",false);
		medios = new JRadioButton("Medios",false);
		impacientes = new JRadioButton("Impacientes",false);
		aleatorio = new JRadioButton("Aleatorios",true);
		grupo = new ButtonGroup();
		grupo.add(impacientes);
		grupo.add(medios);
		grupo.add(pacientes);
		grupo.add(aleatorio);
		add(impacientes);
		add(medios);
		add(pacientes);
		add(aleatorio);
	}
	
	public Integer seleccion() {
		
		Integer seleccion = 0;
		if (pacientes.isSelected())
			seleccion = 1;
		else if (medios.isSelected())
			seleccion = 2;
		else if (impacientes.isSelected())
			seleccion = 3;
		else if (aleatorio.isSelected())
			seleccion = 4;
		System.out.println("oK "+seleccion);
		return seleccion;
	}
}
