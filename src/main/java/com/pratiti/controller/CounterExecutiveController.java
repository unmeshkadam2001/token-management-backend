package com.pratiti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.entity.CounterExecutive;
import com.pratiti.exception.CustomerServiceException;
import com.pratiti.model.LoginData;
import com.pratiti.model.LoginStatus;
import com.pratiti.service.CounterExecutiveService;

@RestController
@CrossOrigin
public class CounterExecutiveController {

	@Autowired
	CounterExecutiveService counterExecutiveService;
	
	@PostMapping("/login")
	public LoginStatus loginCounterExecutive(@RequestBody LoginData loginData ) {
		LoginStatus status = new LoginStatus();
		System.out.println(loginData.getName());
		
		try {
			CounterExecutive counterExecutive = counterExecutiveService.login(loginData.getName(), loginData.getPassword());
			if(counterExecutive == null) {
				status.setMessage("Credentials Entered By you doesn't  matched...!");
				status.setStatus(false);
				return status;
			}
			status.setName(counterExecutive.getName());
			status.setId(counterExecutive.getCounterExecutiveId());
			status.setMessage("Credentials Matched Successfully...!");
			status.setStatus(true);
		}catch(CustomerServiceException e) {
			status.setMessage("Credentials Entered By you doesn't  matched...!");
			status.setStatus(false);
		}
		return status;		
	}
	
	@GetMapping("/CEId")
	public Integer getCEId(@RequestParam("id")Integer CEid) {
		
		
		return counterExecutiveService.getCounterExecutiveId(CEid);
	}
}
