package com.propswip.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.propswip.models.Filter;
import com.propswip.models.Location;
import com.propswip.models.MoblieVerification;
import com.propswip.models.Property;
import com.propswip.models.User;
import com.propswip.models.UserProperty;
import com.propswip.services.PropswipService;

@RestController
@RequestMapping("/v1")
public class ApiResource {

	@Autowired
	private PropswipService propswipService;

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String getGreeting(@PathVariable String name) {
		String result = "Hello "+name;
		return result;
	}

	@RequestMapping(value = "/locations/all", method = RequestMethod.GET)
	public List<Location> getAllLocations() {
		
		return this.propswipService.getAllLocations();
	}

	@RequestMapping(value = "/properties", method = RequestMethod.GET, produces = "application/json")
	public List<Property> getPropertiesForFilter(Filter filter) {
		
		return this.propswipService.getProperties(filter);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
  
		String userId = this.propswipService.createUser(user);
        
        User createdUser = this.propswipService.getUserForId(userId);
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/useraction", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Void> userAction(@RequestBody UserProperty userProperty, UriComponentsBuilder ucBuilder) {

		userProperty.setActionTime(System.currentTimeMillis());
		
		this.propswipService.processUserPropertyAction(userProperty);
		
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
 
    @RequestMapping(value = "/verifiedmobile/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Void> updateVerifiedMobile(@PathVariable("userId") String userId, @RequestBody MoblieVerification verification) {
    	
    	String mobileNumber = verification.getMobileNumber();
    	Boolean ret = this.propswipService.updateVerifiedMobile(mobileNumber, userId);
        
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }

	public PropswipService getPropswipService() {
		return propswipService;
	}

	public void setPropswipService(PropswipService propswipService) {
		this.propswipService = propswipService;
	}
	
	
}
