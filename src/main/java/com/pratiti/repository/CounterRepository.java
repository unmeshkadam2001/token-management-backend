package com.pratiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pratiti.entity.Counter;

public interface CounterRepository extends JpaRepository<Counter, Integer> {

	@Query("select c.serviceType.id from Counter c where c.counterId=?1")
	Integer findByCounterId(Integer counterId);
}
