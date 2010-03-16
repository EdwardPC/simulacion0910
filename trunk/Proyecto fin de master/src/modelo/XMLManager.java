package modelo;

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
	
	public XMLManager() {
		
		listadoTramos = new ArrayList<Tramo>();
		listadoEntradas = new ArrayList<Acceso>();
		listadoSalidas = new ArrayList<Acceso>();
		listadoPrincipales = new ArrayList<Tramo>();
		listadoHorizontales = new ArrayList<Tramo>();
		listadoVerticales = new ArrayList<Tramo>();
		listadoCallejones = new ArrayList<Tramo>();
		listadoAtributos = new ArrayList<Atributo>();
	}
	
	public void lanzarManager(String file) {
		
		parsearArchivoXml(file);
        parsearDocumento();
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
	
	private void parsearArchivoXml(String file) {
    	
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
	 }
	    
	 public Tramo obtenerTramo(Element elemento) {
	   
		 int sentido = obtenerEntero(elemento,"sentido");
	     int posX1 = obtenerEntero(elemento, "posX1");
	     int posX2 = obtenerEntero(elemento, "posX2");
	     int posY = obtenerEntero(elemento, "posY");
	     Tramo d = new Tramo(sentido,posX1,posX2,posY);
	     return d;   
	 }
	    
	 public Acceso obtenerEntrada(Element elemento) {
	 
	     int posX1 = obtenerEntero(elemento, "posX1");
	     int posX2 = obtenerEntero(elemento, "posX2");
	     int posX3 = obtenerEntero(elemento, "posX3");
	     int posX4 = obtenerEntero(elemento, "posX4");
	     int posY1 = obtenerEntero(elemento, "posY1");
	     int posY2 = obtenerEntero(elemento, "posY2");
	     Acceso d = new Acceso(posX1,posX2,posX3,posX4,posY1,posY2);
	     return d;    
	 }
	    
	 public Acceso obtenerSalida(Element elemento) {
	    	
		 int posX1 = obtenerEntero(elemento, "posX1");
	     int posX2 = obtenerEntero(elemento, "posX2");
	     int posX3 = obtenerEntero(elemento, "posX3");
	     int posX4 = obtenerEntero(elemento, "posX4");
	     int posY1 = obtenerEntero(elemento, "posY1");
	     int posY2 = obtenerEntero(elemento, "posY2");
	     Acceso d = new Acceso(posX1,posX2,posX3,posX4,posY1,posY2);
	     return d;  
	 }   
	    
	 public Atributo obtenerAtributo(Element elemento) {
		 
		 int longitud = obtenerEntero(elemento, "longitud");
		 int limite1 = obtenerEntero(elemento, "limite1");
		 int limite2 = obtenerEntero(elemento, "limite2");
		 Atributo d = new Atributo(longitud,limite1,limite2);
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
}
