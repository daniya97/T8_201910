package model.logic;

public class VertexSummary {
	private Long id;
	private Double lat;
	private Double lon;
	private Long[] adj;
	
	public Long getId() {
		return id;
	}
	
	public Long[] getAdj() {
		return adj;
	}
	
	public Double getLat() {
		return lat;
	}
	
	public Double getLon() {
		return lon;
	}
}
