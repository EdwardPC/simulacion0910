package managerXML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLManager {

	 private Document dom;
	 private ArrayList<Tramo> listadoTramos; 
	 private ArrayList<Acceso> listadoEntradas;
	 private ArrayList<Acceso> listadoSalidas;
	 private ArrayList<Tramo> listadoPrincipales;
	 private ArrayList<Tramo> listadoHorizontales;
	 private ArrayList<Tramo> listadoVerticales;
	 private ArrayList<Tramo> listadoCallejones;
	 private ArrayList<Atributo> listadoAtributos;
	 private ArrayList<Contenido> listadoCampos;
	 private ArrayList<Contenido> listadoEdificios;
	 private ArrayList<Punto> listadoStops;
	 private ArrayList<Punto> listadoCedas;
	 private ArrayList<Tramo> listadoSemaforos;
	 private ArrayList<Punto> listadoCrucesSimples;
	 private ArrayList<Contenido> listadoCruces;
	
	public XMLManager() {
		
		listadoTramos = new ArrayList<Tramo>();
		listadoEntradas = new ArrayList<Acceso>();
		listadoSalidas = new ArrayList<Acceso>();
		listadoPrincipales = new ArrayList<Tramo>();
		listadoHorizontales = new ArrayList<Tramo>();
		listadoVerticales = new ArrayList<Tramo>();
		listadoCallejones = new ArrayList<Tramo>();
		listadoAtributos = new ArrayList<Atributo>();
		listadoCampos = new ArrayList<Contenido>();
		listadoEdificios = new ArrayList<Contenido>();
		listadoStops = new ArrayList<Punto>();
		listadoCedas = new ArrayList<Punto>();
		listadoSemaforos = new ArrayList<Tramo>();
		listadoCrucesSimples = new ArrayList<Punto>();
		listadoCruces = new ArrayList<Contenido>();
	}
	
	public void lanzarManager(File file) {
		
		parsearArchivoXml(file);
        parsearDocumento();
	}
	
	private void parsearArchivoXml(File file) {
	    	
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	     try {
	    	 DocumentBuilder db = dbf.newDocumentBuilder();
	         dom = db.parse(file);
	     } 
	     catch (ParserConfigurationException pce) { 	
	    	 pce.printStackTrace();
	     } 
	     catch (SAXException se) {
	         se.printStackTrace();
	     }
	     catch (IOException ioe) {
	         ioe.printStackTrace();
	     }
	}  
	 
	 private void parsearDocumento() {
	    	
		 Element docEle = dom.getDocumentElement();
	     NodeList nl = docEle.getElementsByTagName("tramo");
	     if (nl != null && nl.getLength() > 0) {
	    	 for (int i = 0; i < nl.getLength(); i++) {
	                Element elemento = (Element) nl.item(i);
	                Tramo tramo = obtenerTramo(elemento);
	                listadoTramos.add(tramo);
	         }
	     }   
	     nl = docEle.getElementsByTagName("entrada");
	     if (nl != null && nl.getLength() > 0) {
	    	 for (int i = 0; i < nl.getLength(); i++) {
	                Element elemento = (Element) nl.item(i);
	                Acceso entrada = obtenerEntrada(elemento);
	                listadoEntradas.add(entrada);
	         }
	     }   
	     nl = docEle.getElementsByTagName("salida");
	     if (nl != null && nl.getLength() > 0) {
	    	 for (int i = 0; i < nl.getLength(); i++) {
	                Element elemento = (Element) nl.item(i);
	                Acceso salida = obtenerSalida(elemento);
	                listadoSalidas.add(salida);
	         }
	     }
	     nl = docEle.getElementsByTagName("principal");
	     if (nl != null && nl.getLength() > 0) {
	    	 for (int i = 0; i < nl.getLength(); i++) {
	                Element elemento = (Element) nl.item(i);
	                Tramo tramo = obtenerTramo(elemento);
	                listadoPrincipales.add(tramo);
	         }
	     }  
	     nl = docEle.getElementsByTagName("horizontal");
	     if (nl != null && nl.getLength() > 0) {
	    	 for (int i = 0; i < nl.getLength(); i++) {
	                Element elemento = (Element) nl.item(i);
	                Tramo tramo = obtenerTramo(elemento);
	                listadoHorizontales.add(tramo);
	         }
	     }  
	     nl = docEle.getElementsByTagName("vertical");
	     if (nl != null && nl.getLength() > 0) {
	    	 for (int i = 0; i < nl.getLength(); i++) {
	                Element elemento = (Element) nl.item(i);
	                Tramo tramo = obtenerTramo(elemento);
	                listadoVerticales.add(tramo);
	         }
	     } 
	     nl = docEle.getElementsByTagName("callejon");
	     if (nl != null && nl.getLength() > 0) {
	    	 for (int i = 0; i < nl.getLength(); i++) {
	                Element elemento = (Element) nl.item(i);
	                Tramo tramo = obtenerTramo(elemento);
	                listadoCallejones.add(tramo);
	         }
	     } 
	     nl = docEle.getElementsByTagName("atributos");
	     if (nl != null && nl.getLength() > 0) {
	    	 for (int i = 0; i < nl.getLength(); i++) {
	                Element elemento = (Element) nl.item(i);
	                Atributo atributo = obtenerAtributo(elemento);
	                listadoAtributos.add(atributo);
	         }
	     } 
	     nl = docEle.getElementsByTagName("edificio");
	     if (nl != null && nl.getLength() > 0) {
	    	 for (int i = 0; i < nl.getLength(); i++) {
	                Element elemento = (Element) nl.item(i);
	                Contenido edificio = obtenerContenido(elemento);
	                listadoEdificios.add(edificio);
	         }
	     } 
	     nl = docEle.getElementsByTagName("campo");
	     if (nl != null && nl.getLength() > 0) {
	    	 for (int i = 0; i < nl.getLength(); i++) {
	                Element elemento = (Element) nl.item(i);
	                Contenido campo = obtenerContenido(elemento);
	                listadoCampos.add(campo);
	         }
	     } 
	     nl = docEle.getElementsByTagName("semaforo");
	     if (nl != null && nl.getLength() > 0) {
	    	 for (int i = 0; i < nl.getLength(); i++) {
	                Element elemento = (Element) nl.item(i);
	                Tramo semaforo = obtenerTramo(elemento);
	                listadoSemaforos.add(semaforo);
	         }
	     } 
	     nl = docEle.getElementsByTagName("stop");
	     if (nl != null && nl.getLength() > 0) {
	    	 for (int i = 0; i < nl.getLength(); i++) {
	                Element elemento = (Element) nl.item(i);
	                Punto stop = obtenerPunto(elemento);
	                listadoStops.add(stop);
	         }
	     } 
	     nl = docEle.getElementsByTagName("ceda");
	     if (nl != null && nl.getLength() > 0) {
	    	 for (int i = 0; i < nl.getLength(); i++) {
	                Element elemento = (Element) nl.item(i);
	                Punto ceda = obtenerPunto(elemento);
	                listadoCedas.add(ceda);
	         }
	     } 
	     nl = docEle.getElementsByTagName("cruceS");
	     if (nl != null && nl.getLength() > 0) {
	    	 for (int i = 0; i < nl.getLength(); i++) {
	                Element elemento = (Element) nl.item(i);
	                Punto cruceS = obtenerPunto(elemento);
	                listadoCrucesSimples.add(cruceS);
	         }
	     } 
	     nl = docEle.getElementsByTagName("cruce");
	     if (nl != null && nl.getLength() > 0) {
	    	 for (int i = 0; i < nl.getLength(); i++) {
	                Element elemento = (Element) nl.item(i);
	                Contenido cruce = obtenerContenido(elemento);
	                listadoCruces.add(cruce);
	         }
	     } 
	 }
	    
	 private Tramo obtenerTramo(Element elemento) {
	   
		 int sentido = obtenerEntero(elemento,"sentido");
	     int posX1 = obtenerEntero(elemento, "posX1");
	     int posX2 = obtenerEntero(elemento, "posX2");
	     int posY = obtenerEntero(elemento, "posY");
	     Tramo d = new Tramo(sentido,posX1,posX2,posY);
	     return d;   
	 }
	    
	 private Acceso obtenerEntrada(Element elemento) {
	 
	     int posX1 = obtenerEntero(elemento, "posX1");
	     int posX2 = obtenerEntero(elemento, "posX2");
	     int posX3 = obtenerEntero(elemento, "posX3");
	     int posX4 = obtenerEntero(elemento, "posX4");
	     int posY1 = obtenerEntero(elemento, "posY1");
	     int posY2 = obtenerEntero(elemento, "posY2");
	     int dir1 = obtenerEntero(elemento, "direccion1");
	     int dir2 = obtenerEntero(elemento, "direccion2");
	     Acceso d = new Acceso(posX1,posX2,posX3,posX4,posY1,posY2,dir1,dir2);
	     return d;    
	 }
	    
	 private Acceso obtenerSalida(Element elemento) {
	    	
		 int posX1 = obtenerEntero(elemento, "posX1");
	     int posX2 = obtenerEntero(elemento, "posX2");
	     int posX3 = obtenerEntero(elemento, "posX3");
	     int posX4 = obtenerEntero(elemento, "posX4");
	     int posY1 = obtenerEntero(elemento, "posY1");
	     int posY2 = obtenerEntero(elemento, "posY2");
	     int dir1 = obtenerEntero(elemento, "direccion1");
	     int dir2 = obtenerEntero(elemento, "direccion2");
	     Acceso d = new Acceso(posX1,posX2,posX3,posX4,posY1,posY2,dir1,dir2);
	     return d;  
	 }   
	    
	private Atributo obtenerAtributo(Element elemento) {
		 
		 int longitud = obtenerEntero(elemento, "longitud");
		 int limite1 = obtenerEntero(elemento, "limite1");
		 int limite2 = obtenerEntero(elemento, "limite2");
		 Atributo d = new Atributo(longitud,limite1,limite2);
		 return d;
	}
	 
	private Contenido obtenerContenido(Element elemento) {
		
		int x1 = obtenerEntero(elemento, "posX1");
		int x2 = obtenerEntero(elemento, "posX2");
		int y1 = obtenerEntero(elemento, "posY1");
		int y2 = obtenerEntero(elemento, "posY2");
		Contenido d = new Contenido(x1,x2,y1,y2);
		return d;
	}
	
	private Punto obtenerPunto(Element elemento) {
		
		int x = obtenerEntero(elemento, "posX");
		int y = obtenerEntero(elemento, "posY");
		Punto d = new Punto(x,y);
		return d;
	}

	private int obtenerEntero(Element elemento, String nombreEtiqueta) {
	    	
		 return Integer.parseInt(obtenerTexto(elemento, nombreEtiqueta));
	}
	    
	private String obtenerTexto(Element elemento, String nombreEtiqueta) {
	    	
		String texto = null;
        NodeList nl = elemento.getElementsByTagName(nombreEtiqueta);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            texto = el.getFirstChild().getNodeValue();
            
        }
        return texto;  
	}
	    
	public ArrayList<Tramo> getTramos() {
    	
    	return listadoTramos;
    }    
	
	public ArrayList<Tramo> getPrincipales() {
		
		return listadoPrincipales;
	}
	
	public ArrayList<Tramo> getHorizontales() {
		
		return listadoHorizontales;
	}
	
	public ArrayList<Tramo> getVerticales() {
		
		return listadoVerticales;
	}
	
	public ArrayList<Tramo> getCallejones() {
		
		return listadoCallejones;
	}
	
	public ArrayList<Acceso> getEntradas() {
    	
    	return listadoEntradas;
    }
	
	public ArrayList<Acceso> getSalidas() {
    	
    	return listadoSalidas;
    }
	
	public ArrayList<Atributo> getAtributos() {
		
		return listadoAtributos;
	}
	
	public ArrayList<Contenido> getCampos() {
		
		return listadoCampos;
	}
	
	public ArrayList<Contenido> getEdificios() {
		
		return listadoEdificios;
	}
	
	public ArrayList<Punto> getStops() {
		return listadoStops;
	}

	public ArrayList<Punto> getCedas() {
		return listadoCedas;
	}

	public ArrayList<Tramo> getSemaforos() {
		return listadoSemaforos;
	}

	public ArrayList<Punto> getCrucesSimples() {
		return listadoCrucesSimples;
	}

	public ArrayList<Contenido> getCruces() {
		return listadoCruces;
	}
}
