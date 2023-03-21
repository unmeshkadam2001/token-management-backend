package com.pratiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratiti.entity.Counter;

public interface CounterRepository extends JpaRepository<Counter, Integer> {

}
