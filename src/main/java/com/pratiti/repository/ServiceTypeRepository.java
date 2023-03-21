package com.pratiti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pratiti.entity.ServiceType;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer> {
	@Query("select st from ServiceType st")
	List<ServiceType> fetchAll();
}
