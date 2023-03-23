package com.pratiti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pratiti.model.*;
import com.pratiti.entity.Counter;
import com.pratiti.entity.CounterExecutive;
import com.pratiti.entity.ServiceType;
import com.pratiti.model.ServiceDTO;
import com.pratiti.model.ServiceTypeDTO;
import com.pratiti.repository.CounterExecutiveRepository;
import com.pratiti.repository.CounterRepository;
import com.pratiti.repository.ServiceRepository;
import com.pratiti.repository.ServiceTypeRepository;

@Service
public class ManagerService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceTypeRepository serviceTypeRepository;
    @Autowired
    private CounterRepository counterRepository;
    @Autowired
    private CounterExecutiveRepository counterExecutiveRepository;
    

    public String addService(ServiceTypeDTO serviceTypeDTO) {
        // Create a new ServiceType entity
        ServiceType serviceType = new ServiceType();
        serviceType.setStatusOfServiceType("Not assigned");
        serviceType.setTypeOfService(serviceTypeDTO.getTypeOfService());

        // Create a list of Service entities
        List<com.pratiti.entity.Service> services = new ArrayList<>();
        for (ServiceDTO serviceDTO : serviceTypeDTO.getServices()) {
            com.pratiti.entity.Service service = new com.pratiti.entity.Service();
            service.setServiceName(serviceDTO.getServiceName());
            service.setStatusOfService(serviceDTO.getStatusOfService());
            service.setServiceType(serviceType);
            services.add(service);
        }

        // Set the list of services to the ServiceType entity
        serviceType.setServices(services);
        
        serviceRepository.saveAll(services);

        // Save the ServiceType entity with its associated Services
        serviceTypeRepository.save(serviceType);

        return "Servies added successfully!";
    }

	public List<ServiceType> getServicesTypes() {
		List<ServiceType>st = serviceTypeRepository.fetchAllNotAssigned("Not assigned");
		for(ServiceType s : st) {
			System.out.println(s.getTypeOfService());
		}
		return st;
	}
	
//	public String assignCounter(CounterDTO counterDTO) {
//		return "counter is assignes";
//	}
	
	@Transactional
	public String assignCounter(CounterDTO counterDTO) {
        Optional<ServiceType> serviceType = serviceTypeRepository.findById(counterDTO.getServiceTypeId());
        ServiceType s = serviceType.get();
        
        serviceRepository.updateStatusOfServiceType("assigned" , s.getId());

        CounterExecutive counterExecutive = new CounterExecutive();
        counterExecutive.setName(counterDTO.getCounterExecutiveName());
        counterExecutive.setPassword(counterDTO.getPassword());


        Counter counter = new Counter();
        counter.setServiceType(serviceType.get());
        counter.setCounterExecutive(counterExecutive);

        counterRepository.save(counter);

        return "Counter assigned successfully!";
    }
}
