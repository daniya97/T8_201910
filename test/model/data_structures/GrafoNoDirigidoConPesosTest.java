package model.data_structures;

import java.util.Iterator;

import junit.framework.TestCase;

public class GrafoNoDirigidoConPesosTest extends TestCase {
	
	
	
	/*
	 * Atributos 
	 */
	private IGraph<String, String> grafo;
	
	/*
	 * Escenarios
	 */
	private void setUpEscenario0() {
		grafo = new GrafoNoDirigoConPesos<>();
		grafo.addVertex("Daniel", "Ingeniería Industrial");
		grafo.addVertex("Sebastián", "Matemáticas");
		grafo.addVertex("Camilo", "Ingeniería de Sistemas");
		
		infoArco nuevoArco = new infoArco(1, 10);
		grafo.addEdge("Daniel", "Sebastián",nuevoArco);
		
	}

	
	//Caso Vacío - Manejo de Null 
	private void setUpEscenario1() {
		grafo = new GrafoNoDirigoConPesos<>();
	}
	
	
	
	public void testEscenario0(){
		
		setUpEscenario0();
		
		//Funcionalidades Básicas
		assertTrue("Debería tener 3 Vértices", grafo.V() == 3);
		assertTrue("Debería tener 1 Arco", grafo.E()== 1);
		assertTrue("No encontró correctamente la información del vértice",grafo.getInfoVertex("Daniel").equals("Ingeniería Industrial"));
		
		//Transformación a Int
		assertTrue("Daniel tiene el número 0", grafo.encontrarNumNodo("Daniel")==0);
		assertTrue("Sebastián tiene el número 1", grafo.encontrarNumNodo("Sebastián")==1);
		assertTrue("Camilo tiene el número 2", grafo.encontrarNumNodo("Camilo")==2);
		
		//Transformación a K
		assertTrue("El número 0 tiene a Daniel", grafo.encontrarNodo(0).equals("Daniel"));
		assertTrue("El número 1 tiene a Sebastián", grafo.encontrarNodo(1).equals("Sebastián"));
		assertTrue("El número 2 tiene a Camilo", grafo.encontrarNodo(2).equals("Camilo"));
		
		//Get Info y SetInfo
		assertTrue("No encontró la información correctamente", grafo.getInfoVertex("Daniel").equals("Ingeniería Industrial"));
		assertTrue("No encontró la información correctamente", grafo.getInfoVertex("Sebastián").equals("Matemáticas"));
		assertTrue("No encontró la información correctamente", grafo.getInfoVertex("Camilo").equals("Ingeniería de Sistemas"));
		assertTrue("No encontró la información correctamente", grafo.getInfoArc("Daniel", "Sebastián").darIdArco() == 1);
		assertTrue("No encontró la información correctamente", grafo.getInfoArc("Sebastián", "Daniel").darPesoArco()== 10);

		
		//Daniel se cambia de carrera
		grafo.setInfoVertex("Daniel", "Historia");
		assertTrue("No encontró la información correctamente", grafo.getInfoVertex("Daniel").equals("Historia"));
		
		
		//Agregar Vértices y Nodos
		grafo.addVertex("María", "Geociencias");
		assertTrue("Debería tener 4 Vértices", grafo.V() == 4);
		infoArco nuevoArco = new infoArco(2, 40);
		grafo.addEdge("Daniel", "María", nuevoArco);
		assertTrue("No encontró la información conrrectamente",grafo.getInfoArc("María", "Daniel").darIdArco() == 2);
		
		
		
		//Iterator
		Iterator<String> prueba = grafo.adj("Daniel");
		String actual = null;
		int contador = 0;
		while(prueba.hasNext()){
			actual = prueba.next();
			assertTrue("No funciona el iterador", actual.equals("María")||actual.equals("Sebastián"));
			contador++;
		}
		
		assertTrue("El número de nodos conectados con Daniel no es correcto", contador == 2);
		
	}
	
	public void testEscenario1(){
		
		setUpEscenario1();
		
		//Funcionalidades Básicas
		assertTrue("Debería tener 0 Vértices", grafo.V() == 0);
		assertTrue("Debería tener 0 Arco", grafo.E()== 0);
		assertTrue("No encontró correctamente la información del vértice",grafo.getInfoVertex("Daniel") == null);
		
		//Transformación a Int
		assertTrue("Daniel no existe", grafo.encontrarNumNodo("Daniel")==-1);
		assertTrue("Sebastián no existe", grafo.encontrarNumNodo("Sebastián")==-1);
		
		//Transformación a K
		assertTrue("El número 0 no tiene a nadie", grafo.encontrarNodo(0)== null);
		assertTrue("El número 1 no tiene a nadie", grafo.encontrarNodo(1) == null);
		
		
		//Get Info y SetInfo
		assertTrue("No encontró la información correctamente", grafo.getInfoVertex("Daniel") == null);
		assertTrue("No encontró la información correctamente", grafo.getInfoVertex("Sebastián") == null);
		assertTrue("No encontró la información correctamente", grafo.getInfoVertex("Camilo") == null);
		assertTrue("No encontró la información correctamente", grafo.getInfoArc("Daniel", "Sebastián") == null);
		assertTrue("No encontró la información correctamente", grafo.getInfoArc("Sebastián", "Daniel")== null);

		
		//Daniel se cambia de carrera
		grafo.setInfoVertex("Daniel", "Historia");
		assertTrue("No hay información que registrar", grafo.getInfoVertex("Daniel") == null);
		
		
		//Iterator
		Iterator<String> prueba = grafo.adj("Daniel");
		String actual = null;
		int contador = 0;
		while(prueba.hasNext()){
			actual = prueba.next();
			assertTrue("No funciona el iterador", actual.equals("María")||actual.equals("Sebastián"));
			contador++;
		}
		
		assertTrue("El número de nodos conectados con Daniel no es correcto", contador == 0);
		
	}
	
	

}
