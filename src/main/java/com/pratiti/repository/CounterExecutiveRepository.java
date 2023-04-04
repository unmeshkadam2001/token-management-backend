package com.pratiti.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pratiti.entity.CounterExecutive;

public interface CounterExecutiveRepository extends JpaRepository<CounterExecutive, Integer> {

	public boolean existsByPassword(String password);

	public boolean existsByName(String name);

	public Optional<CounterExecutive> findByName(String name);
}
