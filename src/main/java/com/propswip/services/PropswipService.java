package com.propswip.services;

import java.util.List;

import com.propswip.models.Filter;
import com.propswip.models.Location;
import com.propswip.models.Property;
import com.propswip.models.User;
import com.propswip.models.UserProperty;

public interface PropswipService {

  	public List<Property> getProperties(Filter filter);

	public List<Location> getAllLocations();
	
	public String createUser(User user);
	
	public User getUserForId(String userId);
	
	public Boolean processUserPropertyAction(UserProperty userProperty);
	
	public Boolean updateVerifiedMobile(String mobileNumber, String userId);
}
