
package com.pratiti.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TokenDTO{
    private int tokenId;

    @DateTimeFormat(style = "hh:mm")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm")
    private Date expectedWaitTime;
    

    @DateTimeFormat(style = "hh:mm")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm")
    private Date tokenGenerationTime;
    
    private String serviceDescription;
    private String status;
    private int count;
    private int serviceId;

    
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	public Date getExpectedWaitTime() {
		return expectedWaitTime;
	}

	public void setExpectedWaitTime(Date expectedWaitTime) {
		this.expectedWaitTime = expectedWaitTime;
	}

	public Date getTokenGenerationTime() {
		return tokenGenerationTime;
	}

	public void setTokenGenerationTime(Date tokenGenerationTime) {
		this.tokenGenerationTime = tokenGenerationTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	

    
}

