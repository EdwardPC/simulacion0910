package vista;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

import controlador.Controlador;


public class Ventana extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private Controlador controlador;
	
	private Trafico trafico;
	
	private JMenuBar barraDeMenu;

	private JMenu opcionesSimulacion;
	private JMenu estadisticasSimulacion;
	private JMenu ayudaSimulacion;

	private JMenuItem cargarSimulacion;
	private JMenuItem lanzarSimulacion;
	private JMenuItem finalizarSimulacion;
	private JMenuItem visualizarEstadisticas;
	private JMenuItem mostrarAyuda;
	private JMenuItem mostrarCreditos;
	
	private JButton comenzar;
	private JButton cargar;
	private JButton finalizar;
	private JButton coches;
	
	private JSlider velocidad;
	
	private JLabel lvelocidad;
	private JLabel lsemaforos;
	private JLabel lcedas;
	private JLabel lstops;
	
	private JPanel botones;
	private JPanel leyendas;
	private JPanel slider;
	private JPanel contenidos;
	
	private OyenteComenzar oyenteComenzar;
	private OyenteFinalizar oyenteFinalizar;
	private OyenteCargar oyenteCargar;
	private OyenteCoches oyenteCoches;
	private OyenteEstadisticas oyenteEstadisticas;
	private OyenteAyuda oyenteAyuda;
	private OyenteCreditos oyenteCreditos;
	private OyenteVelocidad oyenteVelocidad;

	
	public Ventana(Controlador control) {
		
		setLayout(new BorderLayout());
		controlador = control;
		trafico = new Trafico(controlador);
    	addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evento){
				doExit();
			}
		});
    	botones = new JPanel();
    	leyendas = new JPanel();
    	slider = new JPanel();
    	contenidos = new JPanel();
    	botones.setLayout(new FlowLayout(FlowLayout.LEFT));
    	contenidos.setLayout(new GridLayout(3,2));
    	slider.setLayout(new GridLayout(2,1));
    	leyendas.setLayout(new BorderLayout());
    	inicializarMenu();
    	inicializarBotones();
    	inicializarLeyendas();
    	inicializarOyentes();
    	add(botones,BorderLayout.NORTH);
		add(trafico,BorderLayout.CENTER);
		add(leyendas,BorderLayout.EAST);
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(800,650);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width/2)-400,(screenSize.height/2)-350);
		setVisible(true);
	}
	
	private void inicializarMenu() {
		
		barraDeMenu = new JMenuBar();
		opcionesSimulacion = new JMenu();
		estadisticasSimulacion = new JMenu();
		ayudaSimulacion = new JMenu();
		cargarSimulacion = new JMenuItem("Cargar la simulación");
		lanzarSimulacion = new JMenuItem("Lanzar la simulación");
		finalizarSimulacion = new JMenuItem("Finalizar la simulación");
		visualizarEstadisticas = new JMenuItem("Visualizar los resultados");
		mostrarAyuda = new JMenuItem("Consultar manual");
		mostrarCreditos = new JMenuItem("Créditos");
		opcionesSimulacion.setText("Opciones de simulación");
		estadisticasSimulacion.setText("Estadísticas de simulación");
		ayudaSimulacion.setText("Ayuda");
		barraDeMenu.add(opcionesSimulacion);
		barraDeMenu.add(estadisticasSimulacion);
		barraDeMenu.add(ayudaSimulacion);
		opcionesSimulacion.add(cargarSimulacion);
		opcionesSimulacion.add(lanzarSimulacion);
		opcionesSimulacion.add(finalizarSimulacion);
		estadisticasSimulacion.add(visualizarEstadisticas);
		ayudaSimulacion.add(mostrarAyuda);
		ayudaSimulacion.add(mostrarCreditos);
		setJMenuBar(barraDeMenu);
	}
	
	private void inicializarLeyendas() {
	
		lsemaforos = new JLabel("semaforo: ");
		lcedas = new JLabel("ceda el paso: ");
		lstops = new JLabel("Stop: ");
		
		contenidos.add(lsemaforos);
		contenidos.add(new JPanel());
		contenidos.add(lcedas);
		contenidos.add(new JPanel());
		contenidos.add(lstops);
		contenidos.add(new JPanel());
		leyendas.add(contenidos,BorderLayout.CENTER);
	}

	private void inicializarBotones() {
		
		cargar = new JButton("Cargar");
		comenzar = new JButton("Simular");
		finalizar = new JButton("Finalizar");
		coches = new JButton("Coches");
		lvelocidad = new JLabel("Velocidad de simulación:");
		velocidad = new JSlider(0,150,0);
		//comenzar.setEnabled(false);
		//finalizar.setEnabled(false);
		slider.add(lvelocidad);
		slider.add(velocidad);
		botones.add(cargar);
		botones.add(coches);
		botones.add(comenzar);
		botones.add(finalizar);
		botones.add(slider);
	}
	
	private void inicializarOyentes() {
		
		oyenteComenzar = new OyenteComenzar();
		oyenteFinalizar = new OyenteFinalizar();
		oyenteCargar = new OyenteCargar();
		oyenteCoches = new OyenteCoches();
		oyenteEstadisticas = new OyenteEstadisticas();
		oyenteAyuda = new OyenteAyuda();
		oyenteCreditos = new OyenteCreditos();
		oyenteVelocidad = new OyenteVelocidad();
		
		cargarSimulacion.addActionListener(oyenteCargar);
		visualizarEstadisticas.addActionListener(oyenteEstadisticas);
		mostrarAyuda.addActionListener(oyenteAyuda);
		mostrarCreditos.addActionListener(oyenteCreditos);
		cargar.addActionListener(oyenteCargar);
		coches.addActionListener(oyenteCoches);
		comenzar.addActionListener(oyenteComenzar);
		finalizar.addActionListener(oyenteFinalizar);
		lanzarSimulacion.addActionListener(oyenteComenzar);
		finalizarSimulacion.addActionListener(oyenteFinalizar);
		velocidad.addChangeListener(oyenteVelocidad);
	}
	
	private void doExit() {
		
		try{
			System.exit(0);
		}
		catch(Throwable e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	class OyenteCargar implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			
			JFileChooser chooser = new JFileChooser();
		    chooser.setCurrentDirectory(new java.io.File("./xml"));
		    chooser.setDialogTitle("Seleccione fichero XML:");
		    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		    FileFilter filter1 = new FiltroXML("xml", new String[] { "XML" });
		    chooser.setFileFilter(filter1);
		    chooser.setAcceptAllFileFilterUsed(false);  
		    if (chooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) { 
		    	File fichero = chooser.getSelectedFile();
		    	int eleccion = 0;
		    	if (fichero.getName().contains("Autovia"))
		    		eleccion = 1;
		    	else if (fichero.getName().contains("Secundaria"))
		    		eleccion = 2;
		    	controlador.rellenarMatriz(eleccion,fichero);
				controlador.getMatriz().setParar(true);
		    }
		    else {
		      System.out.println("No Selection ");
		    }
			//comenzar.setEnabled(true);
			//cargar.setEnabled(false);
		}
	}
	class OyenteCoches implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			
			Cargador cargador = new Cargador();
			JOptionPane.showMessageDialog(getParent(),cargador,"Elegir los conductores: ",JOptionPane.INFORMATION_MESSAGE);
			controlador.pasarConductores(cargador.getNumAgresivos(),cargador.getNumNormales(),cargador.getNumModerados());
		}
	}
	class OyenteComenzar implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			System.out.println("Comenzar");
			//finalizar.setEnabled(true);
			//comenzar.setEnabled(false);
			controlador.getMatriz().setParar(false);
			controlador.simular();
		}
	}
	
	class OyenteFinalizar implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			System.out.println("Finalizar");
			controlador.getMatriz().setParar(true);
			//finalizar.setEnabled(false);
			//cargar.setEnabled(true);
		}
	}
	
	class OyenteEstadisticas implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			System.out.println("Estadisticas");
		}
	}
	
	class OyenteAyuda implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			System.out.println("Ayuda");
		}
	}
	
	class OyenteCreditos implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			Creditos creditos = new Creditos();
			JOptionPane.showMessageDialog(null,creditos,"Creditos",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	class OyenteVelocidad implements ChangeListener {

		public void stateChanged(ChangeEvent arg0) {
			
			System.out.println(velocidad.getValue());
			controlador.setVelocidad(velocidad.getValue());
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		//System.out.println("Actualizo update");
		trafico.dibujarMapa();
		
	}
}
