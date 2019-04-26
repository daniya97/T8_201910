package model.data_structures;

public class Arco implements Comparable<Arco> {

	private int v;
	private int w;
	private infoArco informacion;
	
	 /**
     * Initializes an edge between vertices {@code v} and {@code w} of
     * the given {@code weight}.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @param  weight the weight of this edge
     * @throws IllegalArgumentException if either {@code v} or {@code w} 
     *         is a negative integer
     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
     */
    public Arco(int v, int w, infoArco pInformacion) {
        this.v = v;
        this.w = w;
        this.informacion = pInformacion;
    }

    public infoArco darInformacion(){
    	return informacion;
    }
    
    public void cambiarInformacion(infoArco pNuevaInformacion){
    	informacion = pNuevaInformacion;
    }
    
    public int darIdArco(){
    	return informacion.darIdArco();
    }
    /**
     * Returns the weight of this edge.
     *
     * @return the weight of this edge
     */
    public double weight() {
        return informacion.darPesoArco();
    }

    /**
     * Returns either endpoint of this edge.
     *
     * @return either endpoint of this edge
     */
    public int either() {
        return v;
    }

    /**
     * Returns the endpoint of this edge that is different from the given vertex.
     *
     * @param  vertex one endpoint of this edge
     * @return the other endpoint of this edge
     * @throws IllegalArgumentException if the vertex is not one of the
     *         endpoints of this edge
     */
    public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

	
	@Override
	public int compareTo(Arco arg0) {
		// TODO Auto-generated method stub
		return Double.compare(this.informacion.darPesoArco(), arg0.informacion.darPesoArco());
		
	}
		

}
