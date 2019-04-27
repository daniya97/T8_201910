package model.logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;

import javax.xml.parsers.*;
import org.xml.sax.*;

import model.data_structures.Arco;
import model.data_structures.IGraph;
import model.data_structures.ITablaHash;
import model.data_structures.LinProbTH;
import model.data_structures.infoArco;

public class MovingViolationsManager {

	/**
	 * Lista donde se van a cargar los datos de los archivos
	 */
	private static IGraph<BigInteger, LatLonCoords> grafoIntersecciones;


	/*
	 * ************************************************************************************
	 * 	Metodos
	 * ************************************************************************************
	 */
	/**
	 * Metodo constructor
	 */
	public MovingViolationsManager()
	{

	}


	public Integer[] loadXML(String nombreXML) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		    
		SAXParser saxParser = spf.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		
		LectorXML manejadorDeEventos = new LectorXML();
		xmlReader.setContentHandler(manejadorDeEventos);
		xmlReader.parse(nombreXML);
		
		grafoIntersecciones = manejadorDeEventos.darGrafo();
		
		return new Integer[] {grafoIntersecciones.V(), grafoIntersecciones.E()};
	}


	public boolean guardarEnJson(String nombreJsonC) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean cargarDeJson(String nombreJsonG) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public File crearMapa(String nombreHTML) throws IOException{
		// TODO
	    File archivo = new File(nombreHTML);
	    if (!archivo.exists()) {
		     archivo.createNewFile();
		  }
	    
	    BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
	    
	    // Escribir Cabeza
	       
	    writer.write("<!DOCTYPE html>\n" + 
	    		"<html>\n" + 
	    		"<head>\n" + 
	    		"<meta charset=utf-8 />\n" + 
	    		"<title>Grafo generado</title>\n" + 
	    		"<meta name='viewport' content='initial-scale=1,maximum-scale=1,user-scalable=no' />\n" + 
	    		"<script src='https://api.mapbox.com/mapbox.js/v3.1.1/mapbox.js'></script>\n" + 
	    		"<link href='https://api.mapbox.com/mapbox.js/v3.1.1/mapbox.css' rel='stylesheet' /> \n" + 
	    		"<style>\n" + 
	    		" body { margin:0; padding:0; }\n" + 
	    		"#map { position:absolute; top:0; bottom:0; width:100%; }\n" + 
	    		"</style>\n" + 
	    		"</head>\n" +
	    		"<body>\n" + 
	    		"<div id='map'>\n" + 
	    		"</div>\n");
	    
	    // Inicio del script
	    writer.write("<script>\n" + 
	    		"L.mapbox.accessToken = 'pk.eyJ1IjoianVhbnBhYmxvY29ycmVhcHVlcnRhIiwiYSI6ImNqb2FjcHNjcjFuemwzcXB1M3E0YnB4bHIifQ.oXuYfXtCqmXY52b8Ystuyw';\n" + 
	    		"var map = L.mapbox.map('map', 'mapbox.streets').setView([38.9097115, -77.0289048], 17);\n" + 
	    		"var extremos = [[38.9097115, -77.0289048],[38.9097843, -77.0288552]];\n" + 
	    		"map.fitBounds(extremos);\n");
	    
	    // Agregar edges del grafo como puntos de un poligono
	    writer.write("var line_points = [");
	    
	    ITablaHash<Double, Double> edgesAgregados = new LinProbTH<>(11); // Para agregar solo una vez cada edge
	    
	    Iterable<BigInteger> iterableAdj;
	    LatLonCoords coordsAct;
	    boolean primerEl = true;
	    for (BigInteger id : grafoIntersecciones) {
	    	coordsAct = grafoIntersecciones.getInfoVertex(id);
	    	if (!primerEl) {writer.write(", ");}
	    	
	    	System.out.println("[" + coordsAct.getLat() + ", "+ coordsAct.getLon() + "]");
	    	writer.write("[" + coordsAct.getLat() + ", "+ coordsAct.getLon() + "]");
	    	
	    	primerEl = false;
			/*
			 * iterableAdj = new Iterable<BigInteger>() {
			 * 
			 * @Override public Iterator<BigInteger> iterator() { return
			 * grafoIntersecciones.adj(id); } };
			 * 
			 * for (BigInteger verAdj : iterableAdj) { arcoAct =
			 * grafoIntersecciones.getInfoArc(id, verAdj);
			 * 
			 * arcoAct.either(), arcoAct.either() }
			 */
	    }
	    writer.write("];");
	    
	    // Final
	    writer.write("\nvar polyline_options = {color: '#ff2fc6'};\n" + 
	    		"\n" + 
	    		"L.polyline(line_points, polyline_options).addTo(map);\n" + 
	    		"L.marker( [41.88949181977,-87.6882193648], { title: \"Nodo de salida\"} ).addTo(map);\n" + 
	    		"L.marker( [41.768726,-87.664069], { title: \"Nodo de llegada\"} ).addTo(map);\n" + 
	    		"</script>\n" + 
	    		"</body>\n" + 
	    		"</html>");
	    
	    writer.close();
	    
	    
		return archivo;
	}
}
