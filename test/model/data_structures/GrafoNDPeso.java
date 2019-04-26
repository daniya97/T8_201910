package model.data_structures;

import java.util.Iterator;

import junit.framework.TestCase;

public class GrafoNDPeso extends TestCase {
	
	
	
	/*
	 * Atributos 
	 */
	private IGraph<String, String> grafo;
	
	/*
	 * Escenarios
	 */
	private void setUpEscenario0() {
		grafo = new GrafoNDPesos<>();
		grafo.addVertex("Daniel", "Ingenier�a Industrial");
		grafo.addVertex("Sebasti�n", "Matem�ticas");
		grafo.addVertex("Camilo", "Ingenier�a de Sistemas");
		
		infoArco nuevoArco = new infoArco(1, 10);
		grafo.addEdge("Daniel", "Sebasti�n",nuevoArco);
		
	}

	
	//Caso Vac�o - Manejo de Null 
	private void setUpEscenario1() {
		grafo = new GrafoNDPesos<>();
	}
	
	
	
	public void testEscenario0(){
		
		setUpEscenario0();
		
		//Funcionalidades B�sicas
		assertTrue("Deber�a tener 3 V�rtices", grafo.V() == 3);
		assertTrue("Deber�a tener 1 Arco", grafo.E()== 1);
		assertTrue("No encontr� correctamente la informaci�n del v�rtice",grafo.getInfoVertex("Daniel").equals("Ingenier�a Industrial"));
		
		//Transformaci�n a Int
		assertTrue("Daniel tiene el n�mero 0", grafo.encontrarNumNodo("Daniel")==0);
		assertTrue("Sebasti�n tiene el n�mero 1", grafo.encontrarNumNodo("Sebasti�n")==1);
		assertTrue("Camilo tiene el n�mero 2", grafo.encontrarNumNodo("Camilo")==2);
		
		//Transformaci�n a K
		assertTrue("El n�mero 0 tiene a Daniel", grafo.encontrarNodo(0).equals("Daniel"));
		assertTrue("El n�mero 1 tiene a Sebasti�n", grafo.encontrarNodo(1).equals("Sebasti�n"));
		assertTrue("El n�mero 2 tiene a Camilo", grafo.encontrarNodo(2).equals("Camilo"));
		
		//Get Info y SetInfo
		assertTrue("No encontr� la informaci�n correctamente", grafo.getInfoVertex("Daniel").equals("Ingenier�a Industrial"));
		assertTrue("No encontr� la informaci�n correctamente", grafo.getInfoVertex("Sebasti�n").equals("Matem�ticas"));
		assertTrue("No encontr� la informaci�n correctamente", grafo.getInfoVertex("Camilo").equals("Ingenier�a de Sistemas"));
		assertTrue("No encontr� la informaci�n correctamente", grafo.getInfoArc("Daniel", "Sebasti�n").darIdArco() == 1);
		assertTrue("No encontr� la informaci�n correctamente", grafo.getInfoArc("Sebasti�n", "Daniel").darPesoArco()== 10);

		
		//Daniel se cambia de carrera
		grafo.setInfoVertex("Daniel", "Historia");
		assertTrue("No encontr� la informaci�n correctamente", grafo.getInfoVertex("Daniel").equals("Historia"));
		
		
		//Agregar V�rtices y Nodos
		grafo.addVertex("Mar�a", "Geociencias");
		assertTrue("Deber�a tener 4 V�rtices", grafo.V() == 4);
		infoArco nuevoArco = new infoArco(2, 40);
		grafo.addEdge("Daniel", "Mar�a", nuevoArco);
		assertTrue("No encontr� la informaci�n conrrectamente",grafo.getInfoArc("Mar�a", "Daniel").darIdArco() == 2);
		
		
		
		//Iterator
		Iterator<String> prueba = grafo.adj("Daniel");
		String actual = null;
		int contador = 0;
		while(prueba.hasNext()){
			actual = prueba.next();
			assertTrue("No funciona el iterador", actual.equals("Mar�a")||actual.equals("Sebasti�n"));
			contador++;
		}
		
		assertTrue("El n�mero de nodos conectados con Daniel no es correcto", contador == 2);
		
	}
	
	public void testEscenario1(){
		
		setUpEscenario1();
		
		//Funcionalidades B�sicas
		assertTrue("Deber�a tener 0 V�rtices", grafo.V() == 0);
		assertTrue("Deber�a tener 0 Arco", grafo.E()== 0);
		assertTrue("No encontr� correctamente la informaci�n del v�rtice",grafo.getInfoVertex("Daniel") == null);
		
		//Transformaci�n a Int
		assertTrue("Daniel no existe", grafo.encontrarNumNodo("Daniel")==-1);
		assertTrue("Sebasti�n no existe", grafo.encontrarNumNodo("Sebasti�n")==-1);
		
		//Transformaci�n a K
		assertTrue("El n�mero 0 no tiene a nadie", grafo.encontrarNodo(0)== null);
		assertTrue("El n�mero 1 no tiene a nadie", grafo.encontrarNodo(1) == null);
		
		
		//Get Info y SetInfo
		assertTrue("No encontr� la informaci�n correctamente", grafo.getInfoVertex("Daniel") == null);
		assertTrue("No encontr� la informaci�n correctamente", grafo.getInfoVertex("Sebasti�n") == null);
		assertTrue("No encontr� la informaci�n correctamente", grafo.getInfoVertex("Camilo") == null);
		assertTrue("No encontr� la informaci�n correctamente", grafo.getInfoArc("Daniel", "Sebasti�n") == null);
		assertTrue("No encontr� la informaci�n correctamente", grafo.getInfoArc("Sebasti�n", "Daniel")== null);

		
		//Daniel se cambia de carrera
		grafo.setInfoVertex("Daniel", "Historia");
		assertTrue("No hay informaci�n que registrar", grafo.getInfoVertex("Daniel") == null);
		
		
		//Iterator
		Iterator<String> prueba = grafo.adj("Daniel");
		String actual = null;
		int contador = 0;
		while(prueba.hasNext()){
			actual = prueba.next();
			assertTrue("No funciona el iterador", actual.equals("Mar�a")||actual.equals("Sebasti�n"));
			contador++;
		}
		
		assertTrue("El n�mero de nodos conectados con Daniel no es correcto", contador == 0);
		
	}
	
	

}
