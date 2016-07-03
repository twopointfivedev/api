package com.propswip.models;

public class Filter {
	
	private Integer buy; //1 for buy, 0/NULL for Rent
	private Integer location;
	private Integer costMin;
	private Integer costMax;
	private Integer areaMin;
	private Integer areaMax;
	private Integer bedrooms;
	private Integer bathrooms;
	
	public Integer getBuy() {
		return buy;
	}
	public void setBuy(Integer buy) {
		this.buy = buy;
	}
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	public Integer getCostMin() {
		return costMin;
	}
	public void setCostMin(Integer costMin) {
		this.costMin = costMin;
	}
	public Integer getCostMax() {
		return costMax;
	}
	public void setCostMax(Integer costMax) {
		this.costMax = costMax;
	}
	public Integer getAreaMin() {
		return areaMin;
	}
	public void setAreaMin(Integer areaMin) {
		this.areaMin = areaMin;
	}
	public Integer getAreaMax() {
		return areaMax;
	}
	public void setAreaMax(Integer areaMax) {
		this.areaMax = areaMax;
	}
	public Integer getBedrooms() {
		return bedrooms;
	}
	public void setBedrooms(Integer bedrooms) {
		this.bedrooms = bedrooms;
	}
	public Integer getBathrooms() {
		return bathrooms;
	}
	public void setBathrooms(Integer bathrooms) {
		this.bathrooms = bathrooms;
	}
}
