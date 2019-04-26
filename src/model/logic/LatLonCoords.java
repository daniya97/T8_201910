package model.logic;

public class LatLonCoords {

	private double lon;
	private double lat;
	
	public LatLonCoords(double plat, double plon) {
		lon = plon;
		lat = plat;
	}
	
	public double getLat() {return lat;}
	
	public double getLon() {return lon;}
	
	public double haversineD(LatLonCoords coords2) {
		// TODO
		return 0;
	}
	
}
