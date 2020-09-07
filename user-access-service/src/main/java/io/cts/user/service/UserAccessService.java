package io.cts.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.cts.user.model.UserDetailsEntity;
import io.cts.user.repository.UserDetailsRepository;

/**
 * The class UserAccessService
 * @author Karthi
 *
 */
@Service
public class UserAccessService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public UserDetailsEntity authenticateUser(UserDetailsEntity userDetailsEntity) {
		UserDetailsEntity entity = userDetailsRepository.findByUserName(userDetailsEntity.getUserName());

		if (userDetailsEntity.getPassword() != null
				&& userDetailsEntity.getPassword().equals(entity.getPassword())) {
			entity.setAuthenticated(true);
		} else {
			entity.setAuthenticated(false);
		}
		return entity;
	}

	public void registerUserDetails(UserDetailsEntity userDetailsEntity) {
		userDetailsRepository.save(userDetailsEntity);
	}

}
