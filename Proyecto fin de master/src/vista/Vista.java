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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

import controlador.Controlador;


public class Vista extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private Controlador controlador;
	
	private Trafico trafico;
	
	private JMenuBar barraDeMenu;

	private JMenu opcionesSimulacion;
	private JMenu estadisticasSimulacion;
	private JMenu ayudaSimulacion;

	private JMenuItem cargarSimulacion;
	private JMenuItem parametrosSimulacion;
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
	private JLabel lsemaforoR;
	private JLabel lsemaforoA;
	private JLabel lsemaforoV;
	private JLabel lcedas;
	private JLabel lstops;
	private JLabel lmoderados;
	private JLabel lnormales;
	private JLabel lagresivos;
	
	private JLabel imSemaforoR;
	private JLabel imSemaforoA;
	private JLabel imSemaforoV;
	private JLabel imCedas;
	private JLabel imStops;
	private JLabel imModerados;
	private JLabel imNormales;
	private JLabel imAgresivos;
	
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

	
	public Vista(Controlador control) {
		
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
    	contenidos.setLayout(new GridLayout(8,2));
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
		setSize(850,650);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width/2)-400,(screenSize.height/2)-350);
		setVisible(true);
	}
	
	private void inicializarMenu() {
		
		barraDeMenu = new JMenuBar();
		opcionesSimulacion = new JMenu();
		estadisticasSimulacion = new JMenu();
		ayudaSimulacion = new JMenu();
		cargarSimulacion = new JMenuItem("Cargar la simulacion");
		parametrosSimulacion = new JMenuItem("Indicar parametros");
		lanzarSimulacion = new JMenuItem("Lanzar la simulacion");
		finalizarSimulacion = new JMenuItem("Finalizar la simulacion");
		visualizarEstadisticas = new JMenuItem("Visualizar los resultados");
		mostrarAyuda = new JMenuItem("Consultar manual");
		mostrarCreditos = new JMenuItem("Creditos");
		opcionesSimulacion.setText("Opciones de simulacion");
		estadisticasSimulacion.setText("Estadisticas de simulacion");
		ayudaSimulacion.setText("Ayuda");
		barraDeMenu.add(opcionesSimulacion);
		barraDeMenu.add(estadisticasSimulacion);
		barraDeMenu.add(ayudaSimulacion);
		opcionesSimulacion.add(cargarSimulacion);
		opcionesSimulacion.add(parametrosSimulacion);
		opcionesSimulacion.add(lanzarSimulacion);
		opcionesSimulacion.add(finalizarSimulacion);
		estadisticasSimulacion.add(visualizarEstadisticas);
		ayudaSimulacion.add(mostrarAyuda);
		ayudaSimulacion.add(mostrarCreditos);
		setJMenuBar(barraDeMenu);
	}
	
	private void inicializarLeyendas() {
	
		lsemaforoR = new JLabel(" Semaforo Rojo: ");
		lsemaforoA = new JLabel(" Semaforo Amarillo: ");
		lsemaforoV = new JLabel(" Semaforo Verde: ");
		lcedas = new JLabel(" Ceda el paso: ");
		lstops = new JLabel(" Stop: ");
		lagresivos = new JLabel(" Agresivos: ");
		lnormales = new JLabel(" Normales: ");
		lmoderados = new JLabel(" Moderados: ");
		
		imSemaforoR = new JLabel(new ImageIcon("./images/SemaforoRojo.jpg"));
		imSemaforoA = new JLabel(new ImageIcon("./images/SemaforoAmarillo.jpg"));
		imSemaforoV = new JLabel(new ImageIcon("./images/SemaforoVerde.jpg"));
		imCedas = new JLabel(new ImageIcon("./images/Ceda.jpg"));
		imStops = new JLabel(new ImageIcon("./images/Stop.jpg"));
		imAgresivos = new JLabel(new ImageIcon("./images/Agresivo.jpg"));
		imNormales = new JLabel(new ImageIcon("./images/Normal.jpg"));
		imModerados = new JLabel(new ImageIcon("./images/Moderado.jpg"));
		contenidos.add(lsemaforoR);
		contenidos.add(imSemaforoR);
		contenidos.add(lsemaforoA);
		contenidos.add(imSemaforoA);
		contenidos.add(lsemaforoV);
		contenidos.add(imSemaforoV);
		contenidos.add(lcedas);
		contenidos.add(imCedas);
		contenidos.add(lstops);
		contenidos.add(imStops);
		contenidos.add(lagresivos);
		contenidos.add(imAgresivos);
		contenidos.add(lnormales);
		contenidos.add(imNormales);
		contenidos.add(lmoderados);
		contenidos.add(imModerados);
		leyendas.add(contenidos,BorderLayout.CENTER);
	}

	private void inicializarBotones() {
		
		cargar = new JButton("Cargar");
		comenzar = new JButton("Simular");
		finalizar = new JButton("Finalizar");
		coches = new JButton("Parametros");
		lvelocidad = new JLabel("Velocidad de simulacion:");
		velocidad = new JSlider(0,100,0);
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
		cargar.addActionListener(oyenteCargar);
		coches.addActionListener(oyenteCoches);
		parametrosSimulacion.addActionListener(oyenteCoches);
		comenzar.addActionListener(oyenteComenzar);
		lanzarSimulacion.addActionListener(oyenteComenzar);
		finalizar.addActionListener(oyenteFinalizar);
		finalizarSimulacion.addActionListener(oyenteFinalizar);
		
		visualizarEstadisticas.addActionListener(oyenteEstadisticas);
		mostrarAyuda.addActionListener(oyenteAyuda);
		mostrarCreditos.addActionListener(oyenteCreditos);
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
		    chooser.setCurrentDirectory(new java.io.File("./xml/Calzadas"));
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
				velocidad.setValue(0);
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
			Integer eleccion = 0;
			Object[] opciones = {"Aceptar"};
			eleccion = JOptionPane.showOptionDialog(getParent(),
				   cargador,
				    "Elegir los conductores de cada entrada: ",
				    JOptionPane.YES_OPTION,
				    JOptionPane.PLAIN_MESSAGE,
				    null,opciones,
				    opciones[0]);
			if (eleccion == JOptionPane.OK_OPTION) {
				controlador.pasarConductores(cargador.getNumAgresivos(),cargador.getNumNormales(),cargador.getNumModerados());
				CargadorImpaciencia impaciencia = new CargadorImpaciencia();
				eleccion = JOptionPane.showOptionDialog(getParent(),
						   impaciencia,
						    "Elegir la impaciencia de los conductores: ",
						    JOptionPane.YES_OPTION,
						    JOptionPane.PLAIN_MESSAGE,
						    null,opciones,
						    opciones[0]);
				if (eleccion == JOptionPane.OK_OPTION) {
					controlador.pasarImpaciencia(impaciencia.seleccion());
					CargadorCoches coches = new CargadorCoches();
					eleccion = JOptionPane.showOptionDialog(getParent(),
							   coches,
							   "Elegir el tipo de vehiculo: ",
							    JOptionPane.YES_OPTION,
							    JOptionPane.PLAIN_MESSAGE,
							    null,opciones,
							    opciones[0]);
					if (eleccion == JOptionPane.OK_OPTION) {
						controlador.pasarTipoVehiculos(coches.seleccion());
						CargadorLongitud longitud = new CargadorLongitud();
						eleccion = JOptionPane.showOptionDialog(getParent(),
								   longitud,
								   "Elegir la longitud de la simulacion: ",
								    JOptionPane.YES_OPTION,
								    JOptionPane.PLAIN_MESSAGE,
								    null,opciones,
								    opciones[0]);
						if (eleccion == JOptionPane.OK_OPTION)
							controlador.pasarLongitudSimulacion(longitud.seleccion());
					}
				}
			}
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
			
			controlador.getMatriz().setParar(true);
			//finalizar.setEnabled(false);
			//cargar.setEnabled(true);
		}
	}
	
	class OyenteEstadisticas implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			System.out.println("Estadisticas");
			PanelEstadisticas estadisticas = new PanelEstadisticas(controlador.getMatriz().getEstadisticas());
			JOptionPane.showMessageDialog(null,estadisticas,"Estadisticas de la simulacion",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	class OyenteAyuda implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			PanelAyuda ayuda = new PanelAyuda();
			JOptionPane.showMessageDialog(null,ayuda,"Manual de usuario",JOptionPane.INFORMATION_MESSAGE);
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
			
			controlador.setVelocidad(velocidad.getValue());
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		Integer valor = (Integer)arg1;
		if (valor == 0)
			trafico.dibujarMapa();
		else if (valor == 1)
			JOptionPane.showMessageDialog(null,"El mapa cargado presenta problemas en su realizacion.","Error en mapa",JOptionPane.INFORMATION_MESSAGE);
		
	}
}
