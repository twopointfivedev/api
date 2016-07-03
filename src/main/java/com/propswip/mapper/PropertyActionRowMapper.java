package com.propswip.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.propswip.models.PropertyAction;

public class PropertyActionRowMapper implements RowMapper<PropertyAction> {

	public PropertyAction mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PropertyAction propertyAction = new PropertyAction();
		propertyAction.setPropertyRef(rs.getString("PROPERTY_REF"));
		propertyAction.setScore(rs.getInt("SCORE"));
		propertyAction.setLikes(rs.getInt("LIKES"));
		propertyAction.setDislikes(rs.getInt("DISLIKES"));
				
		return propertyAction;
	}

}
