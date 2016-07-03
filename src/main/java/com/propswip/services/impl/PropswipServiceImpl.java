package com.propswip.services.impl;

import java.util.List;

import com.propswip.dao.PropswipDao;
import com.propswip.models.Filter;
import com.propswip.models.Location;
import com.propswip.models.Property;
import com.propswip.models.PropertyAction;
import com.propswip.models.User;
import com.propswip.models.UserProperty;
import com.propswip.models.UserProperty.Liking;
import com.propswip.services.PropswipService;

public class PropswipServiceImpl implements PropswipService{

	private PropswipDao propswipDao;

	public PropswipDao getPropswipDao() {
		return propswipDao;
	}

	public void setPropswipDao(PropswipDao propswipDao) {
		this.propswipDao = propswipDao;
	}

	public List<Property> getProperties(Filter filter) {
		return this.propswipDao.getProperties(filter);
	}	
	
	public List<Location> getAllLocations() {
		return this.propswipDao.getAllLocations();
	}
	
	public String createUser(User user) {
		return this.propswipDao.createUser(user);
	}
	
	public User getUserForId(String userId) {
		return this.propswipDao.getUserForId(userId);
	}
	
	public Boolean updateVerifiedMobile(String mobileNumber, String userId) {
		
		return propswipDao.updateVerifiedMobile(mobileNumber, userId);
	}

	public Boolean processUserPropertyAction(UserProperty userProperty) {
		
		this.propswipDao.createUserPropertyRecord(userProperty);
		
		PropertyAction propertyAction = this.propswipDao.getPropertyActionForRef(userProperty.getPropertyRef());
		if (propertyAction == null) {
			
			propertyAction = new PropertyAction();
			propertyAction.setPropertyRef(userProperty.getPropertyRef());
			if (userProperty.getLiking() == Liking.LIKED) {
				propertyAction.setScore(+1);
				propertyAction.setLikes(1);
				propertyAction.setDislikes(0);
			} else if (userProperty.getLiking() == Liking.DISLIKED) {
				propertyAction.setScore(-1);
				propertyAction.setLikes(0);
				propertyAction.setDislikes(1);
			}
			
			return this.propswipDao.createPropertyActionRecord(propertyAction);
			
		} else {
			
			if (userProperty.getLiking() == Liking.LIKED) {
				propertyAction.setScore(propertyAction.getScore() + 1);
				propertyAction.setLikes(propertyAction.getLikes() + 1);
			} else if (userProperty.getLiking() == Liking.DISLIKED) {
				propertyAction.setScore(propertyAction.getScore() - 1);
				propertyAction.setDislikes(propertyAction.getDislikes() + 1);
			}
			
			return this.propswipDao.updatePropertyActionRecord(propertyAction);
		}		
	}	
}
