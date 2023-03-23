package com.pratiti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.entity.ServiceType;
import com.pratiti.entity.TokenDetails;
import com.pratiti.model.ServiceDTO;
import com.pratiti.model.ServiceTypeDTO;
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
	
}
