package com.propswip.models;

public class User {
	
	private String userId;
	private String fbid;
	private String fbToken;
	private String name;
	private String emailAddress;
	private String mobileNumber;	
	private Boolean isMobileNumberVerified;
	private String profilePicUrl;
	private Boolean isSignedUp;
	private String latitude;
	private String longitude;

	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFbid() {
		return fbid;
	}
	public void setFbid(String fbid) {
		this.fbid = fbid;
	}
	public String getFbToken() {
		return fbToken;
	}
	public void setFbToken(String fbToken) {
		this.fbToken = fbToken;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Boolean getIsMobileNumberVerified() {
		return isMobileNumberVerified;
	}
	public void setIsMobileNumberVerified(Boolean isMobileNumberVerified) {
		this.isMobileNumberVerified = isMobileNumberVerified;
	}
	public String getProfilePicUrl() {
		return profilePicUrl;
	}
	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}
	public Boolean getIsSignedUp() {
		return isSignedUp;
	}
	public void setIsSignedUp(Boolean isSignedUp) {
		this.isSignedUp = isSignedUp;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
