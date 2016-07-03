package com.propswip.models;

public class UserProperty {
	  
	public enum Liking {
		UNDEFINED(0),
		LIKED(1),
		DISLIKED(2);
		
	    private final int value;
	    private Liking(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }

	}
	
	Integer id;
	String userId;
	String propertyRef;
	Liking liking;
	Long actionTime;
	  
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPropertyRef() {
		return propertyRef;
	}
	public void setPropertyRef(String propertyRef) {
		this.propertyRef = propertyRef;
	}
	public Liking getLiking() {
		return liking;
	}
	public void setLiking(Liking liking) {
		this.liking = liking;
	}
	public Long getActionTime() {
		return actionTime;
	}
	public void setActionTime(Long actionTime) {
		this.actionTime = actionTime;
	}
	  
}
