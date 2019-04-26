package Estructuras;

public class infoArco {
	
	private int idArco;
	private double pesoArco;
	
	public infoArco(int pIdArco, double pPesoArco){
		idArco = pIdArco;
		pesoArco = pPesoArco;
	}
	
	public int darIdArco(){
		return idArco;
	}
	
	public double darPesoArco(){
		return pesoArco;
	}
	
}
