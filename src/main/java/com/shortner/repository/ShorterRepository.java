package com.shortner.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shortner.domain.Shorter;

@Repository
public interface ShorterRepository extends JpaRepository<Shorter, Long> {

	@Query(value = "select u.longUrl from Shorter u where u.shortname=:randomString", nativeQuery = false)
	public String getuserBasedOnRandom(String randomString);
	
	@Query(value = "select u from Shorter u where u.longUrl=:longUrl and u.user.id=:id", nativeQuery = false)
	public Shorter urlCheck(Long id, String longUrl);
	
	
	@Query(value = "select u from Shorter u where u.shortname=:shortUrl and u.user.id=:id and u.id=:shortUrlId", nativeQuery = false)
	public Shorter shortUrlDetails(Long id, Long shortUrlId,String shortUrl);

	@Query(value = "select u from Shorter u where u.shortname=:shortName and u.user.id=:id", nativeQuery = false)
	public Shorter shortUrlCheck(Long id, String shortName);
	
	@Query(value = "select u from Shorter u where u.user.id=:userId", nativeQuery = false)
	public List<Shorter> getUrlsById(Long userId);

}
