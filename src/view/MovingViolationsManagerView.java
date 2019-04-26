package view;

import java.io.File;

import model.data_structures.IArregloDinamico;
import model.data_structures.IColaPrioridad;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.ITablaSimOrd;
import model.data_structures.MaxHeapCP;

public class MovingViolationsManagerView 
{
	/**
	 * Constante con el numero maximo de datos maximo que se deben imprimir en consola
	 */
	public static final int N = 20;
	
	public void printMenu() {
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Proyecto 2----------------------");
		System.out.println("0. Cargar mapa (grafo) a partir del XML");
		System.out.println("1. Guardar grafo en JSON");
		System.out.println("2. Cargar grafo desde JSON");
		System.out.println("3. Crear mapa del grafo cargado");
		
		System.out.println("11. Salir");
		System.out.println("Digite el numero de opcion para ejecutar la tarea, luego presione enter: (Ej., 1):");
		
	}
	
	public void printMessage(String mensaje) {
		System.out.println(mensaje);
	}

	public void printResumenCarga(Integer[] resultados0) {
		System.out.println("Numero de Vertices: " + resultados0[0]);
		System.out.println("Numero de Arcos: " + resultados0[1]);
		
	}

	public void printReq1(boolean esSatisfactorio) {
		// TODO Auto-generated method stub
		
	}
	
	public void printReq2(boolean esSatisfactorio) {
		// TODO Auto-generated method stub
		
	}
	
	public void printMapa() {
		//TODO
	}

	public void printReq3(File htmlMapa) {
		
		
	}

}
