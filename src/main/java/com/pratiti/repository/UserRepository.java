package com.pratiti.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.pratiti.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
//	@Query("select u from Scorecard s join s.student u where s.subject.name =?1 s.level1score =?2")
//	public Optional<User> fetchUserData(String subjectName , Integer score);
}