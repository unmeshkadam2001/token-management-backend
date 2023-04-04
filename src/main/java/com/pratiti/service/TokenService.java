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
		tokenDetails.setCount(0);
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
	
	public List<TokenDetails> queueOfTokens() {
		List<TokenDetails> catchAll = tokenDetailsRepo.findAll();
		if(catchAll.isEmpty())
			return null;
		else 
			return catchAll;		
	}
	
	public List<ServiceType> getServicesTypesForTokenGeneration() {
		List<ServiceType>st = serviceTypeRepository.findAll();
		for(ServiceType s : st) {
			System.out.println(s.getTypeOfService());
		}
		return st;
	}
	
	public String changeStatusToWaiting(int tokenId) {
		
		TokenDetails tokenDetails = tokenDetailsRepo.findById(tokenId).get();
		tokenDetails.setStatus("WAITING");
		tokenDetailsRepo.save(tokenDetails);
		
		return "status of token with token id "+ tokenId + "is changed to waiting.";
	}
	
	public String resolved(int tokenId) {

		TokenDetails tokenDetails = tokenDetailsRepo.findById(tokenId).get();
		tokenDetails.setStatus("RESOLVED");
		tokenDetailsRepo.save(tokenDetails);
		
		return "status of token with token id "+ tokenId + " is changed to resolved.";
	}

	public String processing(int tokenId) {
		
		TokenDetails tokenDetails = tokenDetailsRepo.findById(tokenId).get();
		tokenDetails.setStatus("PROCESSING");
		tokenDetailsRepo.save(tokenDetails);
		return "status of token with token id "+ tokenId + " is changed to processing.";
		
	}


	
}
