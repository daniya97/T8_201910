package model.logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;

import javax.xml.parsers.*;
import org.xml.sax.*;

import com.google.gson.Gson;

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
		
		
		Gson gson = new Gson();
		infoArco<Integer> nuevo = new infoArco<Integer>(1, 2, 0, 1);
		infoArco<Integer> nuevo2 = new infoArco<Integer>(2, 3, 0, 2);
		
		
		Arco nuevoFinal = new Arco(0, 1, nuevo);
		Arco nuevoFinal2 =  new Arco(0,2,nuevo2);
		
		Arco[] s = {nuevoFinal, nuevoFinal2};
		
		
		String ss = gson.toJson(s);
		System.out.println(ss);
		
		
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
	    
	    // Agregar edges del grafo como lineas en el mapa	    
	    ITablaHash<BigInteger[], Boolean> edgesAgregados = new LinProbTH<>(11); // Para agregar solo una vez cada edge
	    
	    Iterable<BigInteger> iterableAdj;
	    BigInteger id1; LatLonCoords coords1;
	    BigInteger id2; LatLonCoords coords2;
	    infoArco<BigInteger> arcoAct;
	    
	    //boolean primerEl = true;
	    for (BigInteger id : grafoIntersecciones) {
			
			iterableAdj = new Iterable<BigInteger>() {		
				@Override public Iterator<BigInteger> iterator() { 
				return grafoIntersecciones.adj(id); } };

			for (BigInteger verAdj : iterableAdj) {
				arcoAct = grafoIntersecciones.getInfoArc(id, verAdj);
				
				id1 = arcoAct.darKEither();
				coords1 = grafoIntersecciones.getInfoVertex(id1);
				id2 = arcoAct.darKOther(id1);
				coords2 = grafoIntersecciones.getInfoVertex(id2);
				
				if (edgesAgregados.get(new BigInteger[] {id1, id2}) != null) continue;
				else edgesAgregados.put(new BigInteger[] {id1, id2}, true);
				
				writer.write("var line_points = [[" + coords1.getLat() + ", " + coords1.getLon() + "] "
											 + ",[" + coords2.getLat() + ", " + coords2.getLon() + "]];\n");
				writer.write("var polyline_options = {color: '#ff2fc6'};\n" + 
			    		"L.polyline(line_points, polyline_options).addTo(map);\n\n");
			}
	    }
	    
	    // Final
	    writer.write(
	    		"L.marker( [41.88949181977,-87.6882193648], { title: \"Nodo de salida\"} ).addTo(map);\n" + 
	    		"L.marker( [41.768726,-87.664069], { title: \"Nodo de llegada\"} ).addTo(map);\n" + 
	    		"</script>\n" + 
	    		"</body>\n" + 
	    		"</html>");
	    
	    writer.close();
	    
	    
		return archivo;
	}
}
