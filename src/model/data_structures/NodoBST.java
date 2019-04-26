package model.data_structures;

public class NodoBST<K,V> {
	/**
     * Llave del nodo
     */
	private K key;
	
	/**
     * Valor del nodo (Value)
     */
	private V value;
	
	/**
     * Se guarda el hijo izquierdo
     */
	private NodoBST<K, V> izquierda;
	
	/**
     * Se guarda el hijo derecho
     */
	private NodoBST<K, V> derecha;
	
	/**
     * 'Altura' del nodo
     */
	private int tamano;
	
	
	/**
     * Color del link que llega al nodo
     */
	private boolean color;
	
	
	
	
	/**
     * Constructor
     */
	public NodoBST(K pKey, V pValue,boolean pColor, int pTamano){
		
		key = pKey;
		value = pValue;
		tamano = pTamano;
		color = pColor;
	}
	
	/**
     * Retorna el tama�o (size)
     */
	public int darTamano(){
		return tamano;
	}
	
	/**
     * Retorna la llave
     */
	public K darKey(){
		return key;
	}
	
	/**
     * Retorna el hijo izquierdo
     */
	public NodoBST<K, V> darIzquierda(){
		return izquierda;
	}
	
	
	/**
     * Retorna el hijo derecho del nodo
     */
	public NodoBST<K, V> darDerecha(){
		return derecha;
	}
	
	/**
     * Retorna el valor value del nodo
     */
	public V darValor(){
		return value;
	}
	
	/**
     * M�todo para cambiar el color del nodo
     */
	public void asignarColor(boolean pColor){
		color = pColor;
	}
	
	
	/**
     * M�todo para asignar un elemento como hijo izquierdo del nodo
     */
	public void asignarIzquierda(NodoBST<K, V> pIzquierda){
		izquierda = pIzquierda;
	}
	
	
	/**
     * M�todo para asignar un elemento como hijo derecho del nodo
     */
	public void asignarDerecha(NodoBST<K, V> pDerecha){
		derecha = pDerecha;
	}
	
	
	/**
     * M�todo para asignarle valor al Nodo
     * Si ya tiene uno, se reemplaza
     */
	public void asignarValor(V pValor){
		value = pValor;
	}
	
	
	/**
     * Retorna el color del nodo
     */
	public boolean darColor(){
		return color;
	}
	
	/**
     * M�todo para modificar el tama�o del nodo
     */
	public void asignarTamano(int pTamano){
		tamano = pTamano;
	}
	
	
	/**
     * M�todo para asignar la llave del nodo
     */
	public void asignarKey(K pKey){
		key = pKey;
	}
	
	
	
	
}
