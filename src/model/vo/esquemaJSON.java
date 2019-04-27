package model.vo;

import model.data_structures.Arco;
import model.data_structures.LinkedList;

public class esquemaJSON<K> {
	
	private K id;
//	private int lat;
//	private int lon;
	private  K[] adj;
	
	
	public esquemaJSON(K pId,K[] pAdj){
		id = pId;
//		lat = pLat;
//		lon = pLon;
		adj = pAdj;
	}
	
	
}
