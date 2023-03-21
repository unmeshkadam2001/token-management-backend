package com.pratiti.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratiti.entity.CounterExecutive;
import com.pratiti.exception.CustomerServiceException;
import com.pratiti.repository.CounterExecutiveRepository;


@Service
public class CounterExecutiveService {

	@Autowired
	CounterExecutiveRepository counterExecutiveRepository;
	
	public CounterExecutive login(String name, String password) {
		
		List<CounterExecutive> allList = counterExecutiveRepository.findAll();
		
		for(CounterExecutive a : allList) {
			if(a.getName().equals(name) && a.getPassword().equals(password)) {
				return a;
			}			
		}
		return null;
	}
		
//		Optional<CounterExecutive> counterExecutiveData = counterExecutiveRepository.findByName(name);
//		if(counterExecutiveData.isPresent()) {
//			CounterExecutive counterExecutive = counterExecutiveData.get();
//		
//				if(counterExecutiveRepository.existsByName(name) && counterExecutiveRepository.existsByPassword(password))
//				{
//					return counterExecutive;
//				}
//				else if(counterExecutiveRepository.existsByName(name)){
//					throw new CustomerServiceException("Enter Correct Password and try again :)");
//				}
//		}
//		else 
//			throw new CustomerServiceException("Account with given credentials doesn't exist..!");
}
