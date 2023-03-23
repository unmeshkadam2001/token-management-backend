package com.pratiti.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pratiti.entity.TokenDetails;
import com.pratiti.repository.CounterRepository;

import com.pratiti.entity.ServiceType;
import com.pratiti.entity.TokenDetails;
import com.pratiti.repository.CounterRepository;
import com.pratiti.repository.ServiceTypeRepository;
import com.pratiti.repository.TokenDetailsRepository;

@Service
public class TokenService {

	@Autowired
	TokenDetailsRepository tokenDetailsRepo;
	@Autowired 
	CounterRepository counterRepo;
	@Autowired
	private ServiceTypeRepository serviceTypeRepository;
	
	public String generateToken(TokenDetails tokenDetails) {
		System.out.println("we are inside service of generate token");
		tokenDetailsRepo.save(tokenDetails);
		return "Token Generated Succesfully!  Your Token Id is: "+ tokenDetails.getTokenId();
	}

	public List<TokenDetails> queueOfTokens(Integer counterId) {
		// TODO Auto-generated method stub
		Integer serviceTypeId = counterRepo.findByCounterId(counterId);
		List<TokenDetails> tempQueue = tokenDetailsRepo.findByServiceId(serviceTypeId);
		System.out.println("Service is returning the list");
		return tempQueue;
	}
	public List<ServiceType> getServicesTypesForTokenGeneration() {
		List<ServiceType>st = serviceTypeRepository.findAll();
		for(ServiceType s : st) {
			System.out.println(s.getTypeOfService());
		}
		return st;
	}

	
}
