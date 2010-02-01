package vista;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Cargador extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private ButtonGroup grupo;
	private JRadioButton ciudad;
	private JRadioButton autovia;
	private JRadioButton secundaria;
	
	public Cargador() {
		
		grupo = new ButtonGroup();
		ciudad = new JRadioButton("Ciudad");
		autovia = new JRadioButton("Autovía");
		secundaria = new JRadioButton("Secundaria");
		ciudad.setSelected(true);
		grupo.add(ciudad);
		grupo.add(autovia);
		grupo.add(secundaria);
		add(ciudad);
		add(autovia);
		add(secundaria);
	}
	
	public Integer getEleccion() {
		
		Integer resultado = -1;
		if (ciudad.isSelected())
			resultado = 0;
		else if (autovia.isSelected())
				resultado = 1;
		else if (secundaria.isSelected())
				resultado = 2;
		return resultado;
		
	}

}
