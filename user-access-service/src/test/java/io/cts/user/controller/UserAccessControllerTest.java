package io.cts.user.controller;

import static io.cts.user.util.UserDetailsPrototype.getUserDetail;
import static io.cts.user.util.UserDetailsPrototype.getAuthenticatedUser;
import static io.cts.user.util.UserDetailsPrototype.getUnauthenticatedUser;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.cts.user.service.UserAccessService;

/**
 * @author Karthi
 *
 */
@ExtendWith(SpringExtension.class)
public class UserAccessControllerTest {

	@Mock
	private UserAccessService userDetailsService;

	@InjectMocks
	private UserAccessController userDetailsController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testLogon() throws Exception {
		when(userDetailsService.authenticateUser(getUserDetail())).thenReturn(getAuthenticatedUser());
		ResponseEntity<String> response = userDetailsController.logon(getUserDetail());
		assertTrue(response.getBody() != null && response.getBody().contains("Authenticated"));
		verify(userDetailsService, times(1)).authenticateUser(getUserDetail());
	}
	

	@Test
	void testLogonFailure() throws Exception {
		when(userDetailsService.authenticateUser(getUserDetail())).thenReturn(getUnauthenticatedUser());
		ResponseEntity<String> response = userDetailsController.logon(getUserDetail());
		assertTrue(response.getBody() != null && response.getBody().contains("Login Failed"));
		verify(userDetailsService, times(1)).authenticateUser(getUserDetail());
	}

	@Test
	void testRegisterUser() throws Exception {
		ResponseEntity<String> response = userDetailsController.registerUser(getUserDetail());
		assertTrue(response.getBody() != null && response.getBody().contains("User Registered"));
		verify(userDetailsService, times(1)).registerUserDetails(getUserDetail());
	}
}
