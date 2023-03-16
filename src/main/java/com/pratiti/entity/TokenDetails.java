package com.pratiti.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "token_details")
public class TokenDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private int tokenId;

    @Column(name = "expected_wait_time")
    private Time expectedWaitTime;
    
    private Time tokenGenerationTime;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "service_id", referencedColumnName = "service_id")
    private Service service;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "queue_id", referencedColumnName = "queue_id")
    private TokenQueue tokenQueue;




	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	public Time getExpectedWaitTime() {
		return expectedWaitTime;
	}

	public void setExpectedWaitTime(Time expectedWaitTime) {
		this.expectedWaitTime = expectedWaitTime;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Time getTokenGenerationTime() {
		return tokenGenerationTime;
	}

	public void setTokenGenerationTime(Time tokenGenerationTime) {
		this.tokenGenerationTime = tokenGenerationTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TokenQueue getTokenQueue() {
		return tokenQueue;
	}

	public void setTokenQueue(TokenQueue tokenQueue) {
		this.tokenQueue = tokenQueue;
	}

    
}

