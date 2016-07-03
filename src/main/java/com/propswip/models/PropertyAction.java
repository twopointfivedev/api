package com.propswip.models;

public class PropertyAction {
	
	String propertyRef;
	Integer score;
	Integer likes;
	Integer dislikes;
	
	public String getPropertyRef() {
		return propertyRef;
	}
	public void setPropertyRef(String propertyRef) {
		this.propertyRef = propertyRef;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public Integer getDislikes() {
		return dislikes;
	}
	public void setDislikes(Integer dislikes) {
		this.dislikes = dislikes;
	}	
}
