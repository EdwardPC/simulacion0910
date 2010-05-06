package vista;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Cargador extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel agresivos;
	private JLabel normales;
	private JLabel moderados;
	private JSpinner numAgresivos;
	private JSpinner numNormales;
	private JSpinner numModerados;
	
	public Cargador() {
		
		setLayout(new GridLayout(3,2));
		agresivos = new JLabel("Número de agresivos en carril: ");
		normales = new JLabel("Número de normales en carril: ");
		moderados = new JLabel("Número de moderados en carril: ");
		Double current = new Double(0);
	    Double min = new Double(0);
	    Double max = new Double(20);
	    Double step = new Double(1);
		SpinnerNumberModel numberSpinnerModel1 = new SpinnerNumberModel(current, min, max, step);
		SpinnerNumberModel numberSpinnerModel2 = new SpinnerNumberModel(current, min, max, step);
		SpinnerNumberModel numberSpinnerModel3 = new SpinnerNumberModel(current, min, max, step);
		numAgresivos = new JSpinner(numberSpinnerModel1);
		numNormales = new JSpinner(numberSpinnerModel2);
		numModerados = new JSpinner(numberSpinnerModel3);
		add(agresivos);
		add(numAgresivos);
		add(normales);
		add(numNormales);
		add(moderados);
		add(numModerados);
	}

	public Double getNumAgresivos() {
		
		Double valor = new Double(numAgresivos.getValue().toString());
		return valor;
	}

	public Double getNumNormales() {
		
		Double valor = new Double(numNormales.getValue().toString());
		return valor;
	}

	public Double getNumModerados() {
		
		Double valor = new Double(numModerados.getValue().toString());
		return valor;
	}

}
