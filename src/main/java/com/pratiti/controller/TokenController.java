package com.pratiti.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.entity.ServiceType;
import com.pratiti.entity.TokenDetails;
import com.pratiti.model.ServiceDTO;
import com.pratiti.model.TokenDTO;
import com.pratiti.model.ServiceTypeDTO;
import com.pratiti.service.TokenService;

@RestController
@CrossOrigin
public class TokenController {

	@Autowired
	private TokenService tokenService;

	@PostMapping("/generateToken")
	public Integer insertTokenInDatabase(@RequestBody TokenDetails tokenDetails) {
		System.out.println("we are inside generating token");
		return tokenService.generateToken(tokenDetails);
	}

	@GetMapping("/requestingQueue")
	public Queue<TokenDTO> returnQueueOfTokens(@RequestParam("counterId") Integer counterId) {
		List<TokenDetails> list = tokenService.queueOfTokens(counterId);
		List<TokenDTO> list2 = new LinkedList<>();
		Queue<TokenDTO> q = new LinkedList<>();
		for (TokenDetails t : list) {
			TokenDTO obj = new TokenDTO();
			obj.setServiceId(t.getService().getServiceId());
			obj.setExpectedWaitTime(t.getExpectedWaitTime());
			obj.setStatus(t.getStatus());
			obj.setTokenGenerationTime(t.getTokenGenerationTime());
			obj.setTokenId(t.getTokenId());
			obj.setServiceDescription(t.getService().getServiceName());
			list2.add(obj);
			if (t.getStatus().equals("ACTIVE") && t.getCount()<3) {
				q.add(obj);
			}
		}
		System.out.println("Controller is returning the list");
		return q;
	}

	@GetMapping("/requestingWaitingQueue")
	public Queue<TokenDTO> returnWaitingQueueOfTokens(@RequestParam(name="counterId",required=false) Integer counterId) {
		
		List<TokenDTO> list2 = new LinkedList<>();
		List<TokenDetails> list = new LinkedList<>();
		
		Queue<TokenDTO> waitingQueue = new LinkedList<>();
		
		if(counterId != null) {
			list = tokenService.queueOfTokens(counterId);			
		}else {
			list = tokenService.queueOfTokens();
		}
		
		for (TokenDetails t : list) {
			TokenDTO obj = new TokenDTO();
			obj.setServiceId(t.getService().getServiceId());
			obj.setExpectedWaitTime(t.getExpectedWaitTime());
			obj.setStatus(t.getStatus());
			obj.setTokenGenerationTime(t.getTokenGenerationTime());
			obj.setTokenId(t.getTokenId());
			obj.setServiceDescription(t.getService().getServiceName());
			list2.add(obj);
			if (t.getStatus().equals("WAITING")) {
				waitingQueue.add(obj);
			}
		}
		System.out.println("Controller is returning the waitingQueue");
		return waitingQueue;
	}
	
	@GetMapping("/requestingCatchAllQueue")
	public Queue<TokenDTO> returnCatchAllQueueOfTokens() {
		
		List<TokenDetails> list= tokenService.queueOfTokens();
		List<TokenDTO> list2 = new LinkedList<>();
		Queue<TokenDTO> catchAllQueue = new LinkedList<>();
		
		for(TokenDetails t : list) {
			TokenDTO obj = new TokenDTO();
			obj.setServiceId(t.getService().getServiceId());
			obj.setExpectedWaitTime(t.getExpectedWaitTime());
			obj.setStatus(t.getStatus());
			obj.setTokenGenerationTime(t.getTokenGenerationTime());
			obj.setTokenId(t.getTokenId());
			obj.setServiceDescription(t.getService().getServiceName());
			list2.add(obj);
			if (t.getStatus().equals("ACTIVE")) {
				catchAllQueue.add(obj);
			}
		}
		System.out.println("Controller is returning the CatchAllQueue");
		return catchAllQueue;		
	}
		
	@GetMapping("/getServicesTypesForTokenGeneration")
	public List<ServiceTypeDTO> getServicesTypesForTokenGeneration() {
		List<ServiceType> serviceTypes = tokenService.getServicesTypesForTokenGeneration();
		List<ServiceTypeDTO> serviceTypeDTOs = new ArrayList<>();
		for (ServiceType serviceType : serviceTypes) {
			ServiceTypeDTO serviceTypeDTO = new ServiceTypeDTO();
			serviceTypeDTO.setId(serviceType.getId());
			serviceTypeDTO.setTypeOfService(serviceType.getTypeOfService());
			List<ServiceDTO> serviceDTOs = new ArrayList<>();
			for (com.pratiti.entity.Service service : serviceType.getServices()) {
				ServiceDTO serviceDTO = new ServiceDTO();
				serviceDTO.setId(service.getServiceId());
				serviceDTO.setServiceName(service.getServiceName());
				serviceDTO.setStatusOfService(service.getStatusOfService());
				serviceDTOs.add(serviceDTO);
			}
			serviceTypeDTO.setServices(serviceDTOs);
			serviceTypeDTOs.add(serviceTypeDTO);
		}
		return serviceTypeDTOs;
	}

	@GetMapping("/statusWaiting")
	public String changeStatusToWaiting(@RequestParam("tokenId") Integer tokenId) {
		String msg = tokenService.changeStatusToWaiting(tokenId);
		return msg;
	}

	@GetMapping("/resolved")
	public String resolved(@RequestParam("tokenId") Integer tokenId) {
		String msg = tokenService.resolved(tokenId);
		return msg;
	}
	@GetMapping("/processing")
	public String processing(@RequestParam("tokenId") Integer tokenId) {
		String msg = tokenService.processing(tokenId);
		return msg;
	}

	
	@GetMapping("/requestingSpecificTokenBasedOnTokenId")
	public TokenDTO requestingSpecificTokenBasedOnTokenId(@RequestParam("tokenId") Integer tokenId) {
		TokenDetails tokenDetails = new TokenDetails();
		tokenDetails = tokenService.requestingSpecificTokenBasedOnTokenId(tokenId);
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setTokenId(tokenDetails.getTokenId());
		tokenDTO.setTokenGenerationTime(tokenDetails.getTokenGenerationTime());
		tokenDTO.setServiceId(tokenDetails.getService().getServiceId());
		tokenDTO.setServiceDescription(tokenDetails.getService().getServiceName());
		tokenDTO.setStatus(tokenDetails.getStatus());
		tokenDTO.setExpectedWaitTime(tokenDetails.getExpectedWaitTime());
		return tokenDTO;
	}
	

}
