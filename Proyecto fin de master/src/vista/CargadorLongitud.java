package vista;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class CargadorLongitud extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JRadioButton corta;
	private JRadioButton media;
	private JRadioButton larga;
	private ButtonGroup grupo;
	
	public CargadorLongitud() {
		
		corta = new JRadioButton("Corta");
		media = new JRadioButton("Media");
		larga = new JRadioButton("Larga");
		grupo = new ButtonGroup();
		grupo.add(corta);
		grupo.add(media);
		grupo.add(larga);
		add(corta);
		add(media);
		add(larga);
	}
	
	public Integer seleccion() {
		
		Integer resultado = 0;
		if (corta.isSelected())
			resultado = 1;
		else if (media.isSelected())
			resultado = 2;
		else if (larga.isSelected())
			resultado = 3;
		return resultado;
	}
}
