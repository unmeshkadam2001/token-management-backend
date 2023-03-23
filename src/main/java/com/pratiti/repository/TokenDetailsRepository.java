package com.pratiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratiti.entity.TokenDetails;

public interface TokenDetailsRepository extends JpaRepository<TokenDetails, Integer> {

}
