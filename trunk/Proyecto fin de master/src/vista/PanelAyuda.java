package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelAyuda extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextArea texto;
	private JScrollPane scroll;
	
	public PanelAyuda() {
		
		this.setLayout(new BorderLayout());
		File fichero = new File("./Ayuda.txt");
		texto = new JTextArea();
		texto.setLineWrap(true);
		texto.setWrapStyleWord(true);
		texto.setEditable(false);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fichero));
			String linea = reader.readLine();
			while (linea != null) {
				texto.append(linea + "\n");
				linea = reader.readLine();
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		scroll = new JScrollPane();
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(texto);
		scroll.setVisible(true);
		add(scroll,BorderLayout.CENTER);
		setPreferredSize(new Dimension(460,400));
	}
}
