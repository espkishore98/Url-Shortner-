package com.shortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shortner.domain.User;

public interface UserRepository extends JpaRepository<com.shortner.domain.User, Long> {

	@Query(value = "select u from User u where u.externalId=:userExternalId", nativeQuery = false)
	public User getUserByExternalId(String userExternalId);

	@Query(value = "select u from User u where u.emailId=:emailId", nativeQuery = false)
	public User userLoginWithEmail(String emailId);

}
