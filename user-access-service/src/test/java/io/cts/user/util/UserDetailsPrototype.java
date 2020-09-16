package io.cts.user.util;

import io.cts.user.model.UserDetailsEntity;

public class UserDetailsPrototype {

	public static UserDetailsEntity getUserDetail() {
		UserDetailsEntity e = new UserDetailsEntity();
		e.setUserName("user");
		e.setPassword("pwdss");
		e.setContactNo("12548798");
		e.setFirstName("fname");
		e.setLastName("lname");
		return e;
	}

	public static UserDetailsEntity getAuthenticatedUser() {
		UserDetailsEntity e = new UserDetailsEntity();
		e.setUserName("user");
		e.setPassword("pwdss");
		e.setAuthenticated(true);
		return e;
	}
	
	public static UserDetailsEntity getUnauthenticatedUser() {
		UserDetailsEntity e = new UserDetailsEntity();
		e.setUserName("user");
		e.setPassword("pwdss");
		e.setAuthenticated(false);
		return e;
	}
}
