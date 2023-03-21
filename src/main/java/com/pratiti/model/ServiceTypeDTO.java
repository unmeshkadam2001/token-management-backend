package com.pratiti.model;

import java.util.List;

import com.pratiti.entity.Service;
import com.pratiti.entity.ServiceType;

public class ServiceTypeDTO {
	
	private Integer id;
	private String typeOfService;
	private List<ServiceDTO> services;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeOfService() {
		return typeOfService;
	}
	public void setTypeOfService(String typeOfService) {
		this.typeOfService = typeOfService;
	}
	public List<ServiceDTO> getServices() {
		return services;
	}
	public void setServices(List<ServiceDTO> services) {
		this.services = services;
	}
	
	
}
