package model.logic;

import java.io.IOException;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import model.data_structures.IGraph;

public class MovingViolationsManager {

	/**
	 * Lista donde se van a cargar los datos de los archivos
	 */
	private static IGraph<Integer, InfoInterseccion> grafoIntersecciones;


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
		
		return null;
	}


	public boolean guardarEnJson(String nombreJsonC) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean cargarDeJson(String nombreJsonG) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
