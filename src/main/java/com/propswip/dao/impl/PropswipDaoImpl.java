package com.propswip.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.propswip.dao.PropswipDao;
import com.propswip.api.utils.PropswipUtils;
import com.propswip.models.Property;
import com.propswip.models.PropertyAction;
import com.propswip.models.User;
import com.propswip.models.UserProperty;

import com.propswip.models.Filter;
import com.propswip.models.Location;
import com.propswip.mapper.*;

public class PropswipDaoImpl extends NamedParameterJdbcDaoSupport implements PropswipDao {

	public List<Property> getProperties(Filter filter) {

		StringBuilder query = new StringBuilder();
		String selectionFields = "select pl.*, pd.*,gl.*, age.NAME, age.COUNTRY_CODE ";
		query.append( "from propertylist pl ")
		.append("inner join propertydetails pd  on pl.PROPERTY_REF=pd.PROPERTY_REF ")
		.append("left join geo_locations gl on pd.GEO_LOCATION_REF=gl.locationId ")
		.append("left join all_locations al on gl.norm_location_id=al.id ")
		.append("left join agent age on pd.AGENT_PHONE_NO=age.PHONE_NO ")
		.append("where 1=1 and gl.norm_location_id!=0 ");
		
		setTypeFilter(query, filter);
		setAreaFilter(query, filter);
		setPriceFilter(query, filter);
		setLocFilter(query, filter);
		setRoomsFilter(query, filter);
		
		List properties = (List) getNamedParameterJdbcTemplate().query(selectionFields.concat(query.toString()), new HashMap<String, String>(), new PropertyRowMapper());
	    return properties;
	}

	private void setAreaFilter(StringBuilder query,
			Filter filter) {
		if(filter.getAreaMin() > 0){
			query.append(" and pd.GROSS_AREA > " + filter.getAreaMin());
		}
		if(filter.getAreaMax() > 0){
			query.append(" and pd.GROSS_AREA < " + filter.getAreaMax());
		}
	}

	private void setTypeFilter(StringBuilder query,
			Filter filter) {
		if(filter.getBuy() == 1){
			query.append(" and pd.PROPERTY_TYPE = 2");
		} else {
			query.append(" and pd.PROPERTY_TYPE = 1");			
		}
	}

	private void setRoomsFilter(StringBuilder query,
			Filter filter) {
		
		if(!"0".equalsIgnoreCase(filter.getBedrooms())){
						
			if (filter.getBedrooms().contains("5")) {
				query.append(" and pd.BEDROOMS > 4 ");
			} else {
				query.append(" and pd.BEDROOMS IN (");	
				query.append(filter.getBedrooms() + ") ");
			}
		}
		
		if(!"0".equalsIgnoreCase(filter.getBathrooms())){
			
			if (filter.getBathrooms().contains("5")) {
				query.append(" and pd.BATHROOMS > 4 ");
			} else {
				query.append(" and pd.BATHROOMS IN (");	
				query.append(filter.getBathrooms() + ") ");
			}
		}
	}

	private void setPriceFilter(StringBuilder query,
			Filter filter) {
		
		String fromColumn = null;
		if(filter.getBuy() == 1) {
			fromColumn = "pd.TOTALPRICE";
		}
		else {
			fromColumn = "pd.MONTHLY_RENTAL";
		}
		
		if(filter.getCostMin() > 0){
			query.append(" and " + fromColumn + " > " + filter.getCostMin());
		}
		if(filter.getCostMax() > 0){
			query.append(" and "+ fromColumn + " < "+  filter.getCostMax());
		}
		
	}

	private void setLocFilter(StringBuilder query, Filter filter) {
		if(filter.getLocation() > 0){
			query.append(" and gl.norm_location_id=  "+ filter.getLocation() + " ");
		}
	}

	public List<Location> getAllLocations() {
		
		String SQL = "SELECT * FROM all_locations";
		List locations = (List) getNamedParameterJdbcTemplate().query(SQL, new HashMap<String, String>(), new LocationRowMapper());
	    return locations;
	}

	public User getUserForId(String userId) {
		
		String SQL = "SELECT * FROM users WHERE USER_ID = :userId LIMIT 1";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);

		List users = (List) getNamedParameterJdbcTemplate().query(SQL, params, new UserRowMapper());
	    if (users.size() > 0) {
	    	return (User) users.get(0);
	    } else {
	    	return null;
	    }
	}

	public String createUser(User user) {
		
		String query = "INSERT INTO users (USER_ID, NAME, PROFILE_PIC_URL, EMAIL, FBID, FBTOKEN, PHONE_NO, IS_PHONE_NO_VERIFIED, IS_SIGNED_UP, LATITUDE, LONGITUDE) VALUES (:userId, :name, :profilePicUrl, :email, :fbid, :fbtoken, :phoneNum, :isPhoneNumVerified, :isSignedUp, :latitude, :longitude)";
		
		String genUserId = PropswipUtils.getGeneratedString();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", genUserId);
		params.put("name", user.getName());
		params.put("profilePicUrl", user.getName());
		params.put("email", user.getEmailAddress());
		params.put("fbid", user.getFbid());
		params.put("fbtoken", user.getFbToken());	
		params.put("phoneNum", user.getMobileNumber());
		params.put("isPhoneNumVerified", user.getIsMobileNumberVerified());
		params.put("isSignedUp", user.getIsSignedUp());
		params.put("latitude", user.getLatitude());	
		params.put("longitude", user.getLongitude());	

		int result = getNamedParameterJdbcTemplate().update(query, params);
		if (result > 0) {
			return genUserId;
		} else {
			return "";
		}
	}
	
	public Boolean updatePropertyActionRecord(PropertyAction propAction) {
		
		String query = "UPDATE propertyaction SET SCORE = :score, LIKES = :likes, DISLIKES = :dislikes WHERE PROPERTY_REF = :propertyRef";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("propertyRef", propAction.getPropertyRef());
		params.put("score", propAction.getScore());
		params.put("likes", propAction.getLikes());
		params.put("dislikes", propAction.getDislikes());

		int result = getNamedParameterJdbcTemplate().update(query, params);
		return result > 0;
	}
	
	public Boolean createPropertyActionRecord(PropertyAction propAction) {
		
		String query = "INSERT INTO propertyaction (PROPERTY_REF, SCORE, LIKES, DISLIKES) VALUES (:propertyRef, :score, :likes, :dislikes)";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("propertyRef", propAction.getPropertyRef());
		params.put("score", propAction.getScore());
		params.put("likes", propAction.getLikes());
		params.put("dislikes", propAction.getDislikes());

		int result = getNamedParameterJdbcTemplate().update(query, params);
		return result > 0;
	}
	
	public Boolean createUserPropertyRecord(UserProperty userProperty) {
		
		String query = "INSERT INTO userproperty (USER_ID, PROPERTY_REF, LIKING, ACTION_TIME) VALUES (:userId, :propertyRef, :liking, :actionTime)";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userProperty.getUserId());
		params.put("propertyRef", userProperty.getPropertyRef());
		params.put("liking", userProperty.getLiking().getValue());
		params.put("actionTime", userProperty.getActionTime());

		int result = getNamedParameterJdbcTemplate().update(query, params);
		return result > 0;
	}
	
	public PropertyAction getPropertyActionForRef(String propertyRef) {
		
		String SQL = "SELECT * FROM propertyaction WHERE PROPERTY_REF = :propertyRef LIMIT 1";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("propertyRef", propertyRef);

		List actions = (List) getNamedParameterJdbcTemplate().query(SQL, params, new PropertyActionRowMapper());
		
		if (actions.size() == 0) {
			return null;
		} else {
			return (PropertyAction)actions.get(0);
		}
	}

	public Boolean updateVerifiedMobile(String mobileNumber, String userId) {
				
		String sql = "UPDATE users SET PHONE_NO = :phoneNum, IS_PHONE_NO_VERIFIED = 1 WHERE USER_ID = :userId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("phoneNum", mobileNumber);

		int result = getNamedParameterJdbcTemplate().update(sql, params);
		return result > 0;
	}
}
