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
import com.pratiti.model.CounterDTO;
import com.pratiti.model.ServiceDTO;
import com.pratiti.model.ServiceTypeDTO;
import com.pratiti.service.ManagerService;

@RestController
@CrossOrigin
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @PostMapping(path="/addService")
    public String addService(@RequestBody ServiceTypeDTO serviceTypeDTO) {
        return managerService.addService(serviceTypeDTO);
    }
    
//    @GetMapping(path="/getServicesTypes")
//    public List<ServiceType> getServicesTypes(){
//    	return managerService.getServicesTypes();
//    }
    
    @GetMapping(path="/getServicesTypes")
    public List<ServiceTypeDTO> getServicesTypes(){
        List<ServiceType> serviceTypes = managerService.getServicesTypes();
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
    
    @PostMapping(path="/assignCounter")
    public String assignCounter(@RequestBody CounterDTO counterDTO) {
    	return managerService.assignCounter(counterDTO);
    }

    
}
