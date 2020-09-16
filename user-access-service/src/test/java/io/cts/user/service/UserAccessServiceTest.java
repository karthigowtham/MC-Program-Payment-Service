package io.cts.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.cts.user.util.UserDetailsPrototype.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.cts.user.model.UserDetailsEntity;
import io.cts.user.repository.UserDetailsRepository;


/**
 * @author Karthi
 *
 */
@ExtendWith(SpringExtension.class)
public class UserAccessServiceTest {

	@Mock
	private UserDetailsRepository userDetailsRepository;

	@InjectMocks
	private UserAccessService userAccessService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testAuthenticateUser() {
		when(userDetailsRepository.findByUserName("user")).thenReturn(getUserDetail());
		UserDetailsEntity userDetails = userAccessService.authenticateUser(getUserDetail());
		assertTrue(userDetails != null && userDetails.isAuthenticated());
		verify(userDetailsRepository, times(1)).findByUserName("user");
	}
	
	@Test
	void testUnAuthenticateUser() {
		when(userDetailsRepository.findByUserName("user")).thenReturn(getUserDetail());	
		UserDetailsEntity details = new UserDetailsEntity();
		details.setUserName("user");
		details.setPassword("nothing");
		UserDetailsEntity userDetails = userAccessService.authenticateUser(details);
		assertFalse(userDetails != null && userDetails.isAuthenticated());
		verify(userDetailsRepository, times(1)).findByUserName("user");
	}

	@Test
	void testRegisterUserDetails() {
		when(userDetailsRepository.save(getUserDetail())).thenReturn(getUserDetail());
		userAccessService.registerUserDetails(getUserDetail());		
		verify(userDetailsRepository, times(1)).save(getUserDetail());
	}
}
