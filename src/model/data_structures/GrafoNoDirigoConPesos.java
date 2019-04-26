package model.data_structures;


import java.util.Iterator;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import javax.swing.tree.DefaultTreeCellEditor.EditorContainer;

public class GrafoNoDirigoConPesos<K,V> implements IGraph<K, V> {

	private int V;
	private int E;
	private ITablaHash<K, Integer> tablaNodoANum;
	private IArregloDinamico<V> informacionNodos;
	private IArregloDinamico<K> tablaNumANodo;
	private ITablaHash<Integer, LinkedList<Arco>> adj;
	
	private static final int cte = 10;
	
	

	
	public GrafoNoDirigoConPesos() {
	V = 0;
	E = 0;
	
	//REVISAR QUE TABLA Y EL TAMAÑO INICIAL
	tablaNodoANum = new LinProbTH<>(cte);
	informacionNodos = new ArregloDinamico<>(cte);
	tablaNumANodo = new ArregloDinamico<>(cte);
	adj = new SepChainTH<>(cte);
	}
	
	@Override
	public Iterator<K> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int V() {
		return V;
	}

	@Override
	public int E() {
		return E;
	}

	@Override
	public void addVertex(K idVertex, V infoVertex) {
		// TODO Auto-generated method stub
		tablaNodoANum.put(idVertex, V);
		informacionNodos.agregar(infoVertex);
		tablaNumANodo.agregar(idVertex);
		V++;
		//NO SE SI TOCA HACER ALGO CON LOS ARCOS
	}

	@Override
	public void addEdge(K idVertexIni, K idVertexFin, infoArco infoArc) {
		// TODO Auto-generated method stub
		int nodoInicial = tablaNodoANum.get(idVertexIni); 
		int nodoFinal = tablaNodoANum.get(idVertexFin); 
		
		Arco nuevo = new Arco(nodoInicial, nodoFinal, infoArc);
		//Nodo inicial
		if(adj.get(nodoInicial) == null){
			LinkedList<Arco> nuevaLista = new LinkedList<Arco>(nuevo);
			adj.put(nodoInicial, nuevaLista);
			
		}else{
			adj.get(nodoInicial).agregarElementoPrimeraPosicion(nuevo);
		}
		
		//Nodo Final
		if(adj.get(nodoFinal) == null){
			LinkedList<Arco> nuevaLista = new LinkedList<>(nuevo);
			adj.put(nodoFinal, nuevaLista);
			
		}else{
			adj.get(nodoFinal).agregarElementoPrimeraPosicion(nuevo);
		}
		
		
		E++;
	}

	@Override
	public V getInfoVertex(K idVertex) {
		
		int numNodo = tablaNodoANum.get(idVertex);
		// TODO Auto-generated method stub
		return informacionNodos.darObjeto(numNodo);
	}

	@Override
	public void setInfoVertex(K idVertex, V infoVertex) {
		// TODO Auto-generated method stub
		int numNodo = tablaNodoANum.get(idVertex);
		informacionNodos.cambiarEnPos(numNodo, infoVertex);
	}

	@Override
	public infoArco getInfoArc(K idVertexIni, K idVertexFin) {
		int nodoInicial = tablaNodoANum.get(idVertexIni); 
		int nodoFinal = tablaNodoANum.get(idVertexFin); 
		infoArco respuesta = null;
		LinkedList<Arco> aux = adj.get(nodoInicial);
		
		if(aux==null) return null;
		for(Arco e: aux){
			if(e.other(nodoInicial)==nodoFinal){
				return e.darInformacion();
			}
		}
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInfoArc(K idVertexIni, K idVertexFin, infoArco infoArc) {
		
		int nodoInicial = tablaNodoANum.get(idVertexIni); 
		int nodoFinal = tablaNodoANum.get(idVertexFin); 
		LinkedList<Arco> aux = adj.get(nodoInicial);
		
		if(aux==null) return;
		for(Arco e: aux){
			if(e.other(nodoInicial)==nodoFinal){
				e.cambiarInformacion(infoArc);
				return;
			}
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<K> adj(K idVertex) {
		
		return new Iterator<K>() {

			int numNodo = tablaNodoANum.get(idVertex);
			LinkedList<Arco> aux = adj.get(numNodo);
			Nodo<Arco> siguiente = aux.darPrimerNodo();
			
			@Override
			public boolean hasNext() {
				if(siguiente!=null){
					return true;
				}else{
					return false;
				}
			}

			@Override
			public K next() {
			K auxiliar = 	tablaNumANodo.darObjeto(siguiente.darObjeto().other(numNodo));
			siguiente = siguiente.darSiguiente();
			return auxiliar;
				// TODO Auto-generated method stub
			}
		};
		// TODO Auto-generated method stub
	}


}
