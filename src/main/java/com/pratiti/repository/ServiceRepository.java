package com.pratiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratiti.entity.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

}
