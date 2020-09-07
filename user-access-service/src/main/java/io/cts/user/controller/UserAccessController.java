package io.cts.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cts.user.model.UserDetailsEntity;
import io.cts.user.service.UserAccessService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class UserAccessController.
 * @author Karthi
 */
@RestController
@RequestMapping("user")
public class UserAccessController {

	@Autowired
	private UserAccessService userDetailsService;

	/**
	 * logon Method
	 * @param userDetailsEntity
	 * @return
	 * @throws Exception
	 */
	@PostMapping(path = "/authenticate")
	@ApiOperation(value = "LogOnUser", notes = "Log On Application")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<String> logon(@RequestBody UserDetailsEntity userDetailsEntity) throws Exception {
		UserDetailsEntity entity = userDetailsService.authenticateUser(userDetailsEntity);
		if (entity.isAuthenticated()) {
			return new ResponseEntity<String>("Authenticated" , HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("Login Failed" , HttpStatus.FORBIDDEN);
		}

	}
	/**
	 * Method registerUser
	 * @param userDetailsEntity
	 * @return 
	 * @throws Exception
	 */
	@PostMapping(path = "/register")
	@ApiOperation(value = "RegisterUser", notes = "Register User Application")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<String> registerUser(@RequestBody UserDetailsEntity userDetailsEntity) throws Exception {		
		userDetailsService.registerUserDetails(userDetailsEntity);
		return new ResponseEntity<String>("User Registered" , HttpStatus.CREATED);
	}

}
