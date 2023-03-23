package com.pratiti.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.entity.TokenDetails;
import com.pratiti.model.TokenDTO;
import com.pratiti.repository.TokenDetailsRepository;
import com.pratiti.service.TokenService;

@RestController
@CrossOrigin
public class TokenController {

	@Autowired
	private TokenDetailsRepository tokenDetailsRepo;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/generateToken")
	public String insertTokenInDatabase(@RequestBody TokenDetails tokenDetails) {
		System.out.println("we are inside generating token");
		return tokenService.generateToken(tokenDetails);				
	}
	
	@GetMapping("/requestingQueue")
	public Queue<TokenDTO> returnQueueOfTokens(@RequestParam("counterId") Integer counterId ){
		List<TokenDetails> list = tokenService.queueOfTokens(counterId);
		List<TokenDTO> list2 = new LinkedList<>();
		Queue<TokenDTO> q = new LinkedList<>();
		for(TokenDetails t : list) {
			TokenDTO obj = new TokenDTO();
			obj.setServiceId(t.getService().getServiceId());
			obj.setExpectedWaitTime(t.getExpectedWaitTime());
			obj.setStatus(t.getStatus());
			obj.setTokenGenerationTime(t.getTokenGenerationTime());
			obj.setTokenId(t.getTokenId());
			obj.setServiceDescription(t.getService().getServiceName());
			list2.add(obj);
			if(t.getStatus().equals("ACTIVE"))
				q.add(obj);
		}
		System.out.println("Controller is returning the list");
		
		return q;				
	}
	
	
	
	
	
	
	
	
	
	
	
}
