package com.pratiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratiti.entity.CounterExecutive;

public interface CounterExecutiveRepository extends JpaRepository<CounterExecutive, Integer> {

}
