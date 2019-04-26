package model.data_structures;

import java.util.Iterator;

public interface IGraph<K,V> extends Iterable<K>{
	
	
	/**
	 * Retorna el número de vertices en el grafo
	 * @return num vertices
	 */
	int V();
	
	/**
	 * Retorna el número de arcos en el grafo
	 * @return num arcos
	 */
	int E();
	
	/**
	 * Adiciona un vértice con un ID único. La información está en infovertex
	 */
	void addVertex(K idVertex, V infoVertex);
	
	
	/**
	 * Adiciona un arco no dirigido entre iD vertexIni y idVertexFin con un ID único. La información está en infoArc
	 */
	void addEdge(K idVertexIni, K idVertexFin, infoArco infoArc);
	
	
	/**
	 * Obtener la información de un vértice
	 */
	V getInfoVertex(K idVertex);
	
	
	/**
	 * Modificar la información del vértice idVertex
	 */
	void setInfoVertex(K idVertex, V infoVertex);
	
	/**
	 * Obtiene la información acerca de un arco
	 */
	infoArco getInfoArc(K idVertexIni, K idVertexFin);
	
	/**
	 * Modificar la información del arco eentre los vértices idVertexIni e idVertexFin
	 */
	void setInfoArc(K idVertexIni, K idVertexFin, infoArco infoArc);
	
	/**
	 * Retorna los identificadores de los vértices adyacentes a idVertex
	 */
	Iterator<K> adj(K idVertex);
	
	

}
