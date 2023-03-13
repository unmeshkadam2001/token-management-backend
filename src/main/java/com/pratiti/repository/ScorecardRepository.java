package com.pratiti.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pratiti.entity.Scorecard;


@Repository
public interface ScorecardRepository extends JpaRepository<Scorecard, Integer> {

	@Query("select sc from Scorecard sc where sc.subject.subjectId=?1 and sc.student.userId=?2")
	Optional<Scorecard> findBySubjectIdAndUserId(Integer id, Integer userId);
	 
	
}