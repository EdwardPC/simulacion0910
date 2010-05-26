package vista;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class CargadorCoches extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JRadioButton lentos;
	private JRadioButton rapidos;
	private ButtonGroup grupo;
	
	public CargadorCoches() {
		
		lentos = new JRadioButton("Lentos",true);
		rapidos = new JRadioButton("Rapidos",false);
		grupo = new ButtonGroup();
		grupo.add(lentos);
		grupo.add(rapidos);
		add(rapidos);
		add(lentos);
	}
	
	public boolean seleccion() {
		
		if (lentos.isSelected())
			return false;
		else
			return true;
			
	}
}
