package com.propswip.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;

import org.springframework.jdbc.core.RowMapper;

import com.propswip.models.Property;
import com.propswip.api.utils.PropswipUtils;

public class PropertyRowMapper implements RowMapper<Property>{

	public Property mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Property property = new Property();
		String propertyRef = (String)rs.getString(Property.Fields.PROPERTY_REF.toString());
		property.setPropertyRef(propertyRef);
		property.setPropertyDetailsUrl((String)rs.getString(Property.Fields.PROPERTY_DETAILS_URL.toString()));
		property.setTitle((String)rs.getString(Property.Fields.TITLE.toString()));
		property.setAddress((String)rs.getString(Property.Fields.formattedAddress.toString()));

		property.setLatitude((String)rs.getString(Property.Fields.latitude.toString()));
		property.setLongitude((String)rs.getString(Property.Fields.longitude.toString()));

		int locId = (Integer)rs.getInt("norm_location_id");
		property.setNeighbourhood((String)rs.getString(Property.Fields.neighbourhood.toString()));
		
		property.setMonthlyRental(rs.getInt(Property.Fields.MONTHLY_RENTAL.toString()));
		property.setBedRooms( rs.getInt(Property.Fields.BEDROOMS.toString()));
		property.setGrossArea( rs.getInt(Property.Fields.GROSS_AREA.toString()));
		property.setSaleableArea((Integer) rs.getInt(Property.Fields.SALEABLE_AREA.toString()));
		property.setUnit( rs.getString(Property.Fields.UNIT.toString()));
		//property.setCrawlTime(rs.getLong(Property.Fields.CRAWL_TIME.toString()));
		property.setTotalPrice(rs.getLong(Property.Fields.TOTALPRICE.toString()));
		property.setCurrency((String) rs.getString(Property.Fields.CURRENCY.toString()));
		property.setFurnished((String) rs.getString(Property.Fields.FURNISHED.toString()));
		property.setPropertyType(rs.getInt(Property.Fields.PROPERTY_TYPE.toString()));
		property.setBathRooms(rs.getInt(Property.Fields.BATHROOMS.toString()));
		property.setYearBuilt(rs.getInt(Property.Fields.YEAR_BUILT.toString()));
		property.setImageCount(rs.getInt(Property.Fields.IMAGE_COUNT.toString()));
		int propertyType = rs.getInt(Property.Fields.PROPERTY_TYPE.toString());
		String source = rs.getString(Property.Fields.SOURCE.toString());
		String folder = PropswipUtils.getPath(propertyRef, propertyType, source);
		property.setThumbnailPath(folder+File.separator+"small"+File.separator+propertyRef+"_1.jpg");
		property.setBigImagePath(folder + File.separator+"large"+File.separator+propertyRef+"_");
		property.setAgentPhoneNo(rs.getLong(Property.Fields.AGENT_PHONE_NO.toString()));
		property.setAgentName(rs.getString("name"));
		if(rs.getString(Property.Fields.SOURCE.toString()) !=null){
			property.setSource(rs.getString(Property.Fields.SOURCE.toString()));
		}

		return property;
	}

}
