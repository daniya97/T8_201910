package model.logic;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.data_structures.GrafoNDPesos;
import model.data_structures.IGraph;
import model.data_structures.IQueue;
import model.data_structures.Queue;
import model.data_structures.infoArco;

public class LectorXML extends DefaultHandler {
	/*
	 * Atributos
	 */
	private IGraph<Integer, LatLonCoords> grafo;
	public boolean insideWay = false;
	IQueue<Integer> wayVertexKeys = null;
	int wayId;
	
	
	public LectorXML() { }
	
	/*
	 * Metodos de DefaultHandler
	 */
	public void startDocument() throws SAXException {
		grafo = new GrafoNDPesos<Integer, LatLonCoords>();
	}
	
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		String tag = localName;
		
		if(tag.equals("node")) {
			//System.out.println("Node");
			LatLonCoords info = new LatLonCoords(Double.parseDouble(atts.getValue("lat")), Double.parseDouble(atts.getValue("lon")));
			grafo.addVertex(Integer.parseInt(atts.getValue("id")), info);
		}
		
		if (tag.equals("way")) {	
			insideWay = true;
			wayVertexKeys = new Queue<>(); 
			wayId = Integer.parseInt(atts.getValue("id"));
		}
		
		if (tag.equals("nd") && insideWay) {
			wayVertexKeys.enqueue(Integer.parseInt(atts.getValue("ref")));
		}
		
		if (tag.equals("tag") && insideWay && atts.getValue("k").equals("highway")) {
			Integer anteriorK = null;
			LatLonCoords coordsAnt = new LatLonCoords(-1, -1);
			LatLonCoords coordsAct;
			
			for (Integer vertexK : wayVertexKeys) {
				if (anteriorK == null) {
					anteriorK = vertexK; 
					coordsAnt = new LatLonCoords(grafo.getInfoVertex(anteriorK).getLat(),
							 grafo.getInfoVertex(anteriorK).getLon());
					
					continue;
				}
				
				coordsAct = new LatLonCoords(grafo.getInfoVertex(vertexK).getLat(),
						 					 grafo.getInfoVertex(vertexK).getLon());
				
				grafo.addEdge(anteriorK, vertexK, new infoArco(wayId, coordsAnt.haversineD(coordsAct)));
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
	public IGraph<Integer, LatLonCoords> darGrafo(){
		return grafo;
	}
}
