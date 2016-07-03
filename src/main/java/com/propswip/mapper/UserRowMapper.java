package com.propswip.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.propswip.models.User;

public class UserRowMapper implements RowMapper <User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		user.setUserId(rs.getString("USER_ID"));
		user.setFbid(rs.getString("FBID"));
		user.setFbToken(rs.getString("FBTOKEN"));
		user.setEmailAddress(rs.getString("EMAIL"));
		user.setName(rs.getString("NAME"));
		user.setMobileNumber(rs.getString("PHONE_NO"));
		user.setProfilePicUrl(rs.getString("PROFILE_PIC_URL"));
		user.setLatitude(rs.getString("LATITUDE"));
		user.setLongitude(rs.getString("LONGITUDE"));
		user.setIsSignedUp(rs.getBoolean("IS_SIGNED_UP"));
		user.setIsMobileNumberVerified(rs.getBoolean("IS_PHONE_NO_VERIFIED"));
		
		return user;
	}

}