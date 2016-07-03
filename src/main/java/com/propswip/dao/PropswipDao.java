package com.propswip.dao;

import java.util.List;

import com.propswip.models.Location;
import com.propswip.models.Property;
import com.propswip.models.PropertyAction;
import com.propswip.models.User;
import com.propswip.models.Filter;
import com.propswip.models.UserProperty;

public interface PropswipDao {

  	public List<Property> getProperties(Filter filter);

	public List<Location> getAllLocations();

	public String createUser(User user);

	public User getUserForId(String userId);
	
	public Boolean createUserPropertyRecord(UserProperty userProperty);

	public Boolean createPropertyActionRecord(PropertyAction propAction);
	
	public Boolean updatePropertyActionRecord(PropertyAction propAction);

	public PropertyAction getPropertyActionForRef(String propertyRef);
	
	public Boolean updateVerifiedMobile(String mobileNumber, String userId);
}
