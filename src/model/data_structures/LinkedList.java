package model.data_structures;

import java.util.Iterator;
/**
 * Lista encadenada implementa ILinkedList
 */
public class LinkedList <K> implements ILinkedList<K>{
	
	
	/**
	 * Guarda el primer nodo
	 */
	private Nodo<K> primero;
	
	/**
	 * Constructor
	 */
	public LinkedList(K primerNodo) {
		primero = new Nodo<>(primerNodo);
	}
	
	@Override
	public Iterator<K> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * Se agregan elementos en la primera posición pues es más eficiente
	 */
	@Override
	public void agregarElementoPrimeraPosicion(K pNuevo){
		
		Nodo<K> nuevo = new Nodo<>(pNuevo);
		K auxiliar = primero.darObjeto();
		
		
		if (primero.equals(null))
			primero = nuevo;
		else
		{
		nuevo.cambiarSiguiente(primero);
		primero = nuevo;
		}
		
		
	}
	

	/**
	 * Se obtienen el tamaño de la lista
	 */
	@Override
	public int darTamanoLista() {
	
		int contador = 0;
		
		Nodo<K> actual = primero;
		while(actual.darObjeto() !=null)
		{
			contador ++;
			actual = actual.darSiguiente();
		}
		
		return contador;
	}


	/**
	 * Se obtiene el primer nodo
	 */
	@Override
	public Nodo<K> darPrimerNodo() {
		return primero;
	}
	

}
