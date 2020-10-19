package com.shortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shortner.domain.Shorter;

@Repository
public interface ShorterRepository extends JpaRepository<Shorter, Long> {

	@Query(value = "select u.longUrl from Shorter u where u.shortname=:randomString", nativeQuery = false)
	public String getuserBasedOnRandom(String randomString);

}
