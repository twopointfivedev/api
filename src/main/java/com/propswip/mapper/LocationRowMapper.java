package com.propswip.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.propswip.models.Location;

public class LocationRowMapper implements RowMapper<Location>{

	public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Location location = new Location();
		location.setLocationId(rs.getInt("id"));
		location.setCountry(rs.getString("country"));
		location.setRegion(rs.getString("region"));
		location.setState(rs.getString("state"));
		location.setCity(rs.getString("city"));
		location.setLocality(rs.getString("locality"));
		location.setNeighbourhood(rs.getString("neighbourhood"));
		return location;
	}
}
