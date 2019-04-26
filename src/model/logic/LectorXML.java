package model.logic;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.data_structures.GrafoNDPesos;
import model.data_structures.IGraph;
import model.data_structures.IQueue;
import model.data_structures.Queue;

public class LectorXML extends DefaultHandler {
	/*
	 * Atributos
	 */
	private IGraph<Integer, InfoInterseccion> grafo;
	public boolean insideWay = false;
	IQueue<Integer> wayVertexKeys = null;
	
	public LectorXML() { }
	
	/*
	 * Metodos de DefaultHandler
	 */
	public void startDocument() throws SAXException {
		grafo = new GrafoNDPesos<Integer, InfoInterseccion>();
	}
	
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		String tag = localName;
		
		if(tag.equals("node")) {
			//System.out.println("Node");
			InfoInterseccion info = new InfoInterseccion(Double.parseDouble(atts.getValue("lat")), Double.parseDouble(atts.getValue("lon")));
			grafo.addVertex(Integer.parseInt(atts.getValue("id")), info);
		}
		
		if (tag.equals("way")) {	
			insideWay = true;
			wayVertexKeys = new Queue<>(); 
		}
		
		if (tag.equals("nd") && insideWay) {
			wayVertexKeys.enqueue(Integer.parseInt(atts.getValue("ref")));
		}
		
		if (tag.equals("tag") && insideWay && atts.getValue("k").equals("highway")) {
			Integer anteriorK = null;
			
			for (Integer vertexK : wayVertexKeys) {
				if (anteriorK == null) {anteriorK = vertexK; continue;}
				
			}
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (localName.equals("way")) {
			insideWay = false;
			wayVertexKeys = null;
		}
	}
	
	public void endDocument() throws SAXException {}
	
	/*
	 * Metodos para interaccion con el Manager
	 */
}
