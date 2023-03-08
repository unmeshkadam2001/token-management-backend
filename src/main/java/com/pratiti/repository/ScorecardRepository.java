package com.pratiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pratiti.entity.Scorecard;


@Repository
public interface ScorecardRepository extends JpaRepository<Scorecard, Integer> {
}