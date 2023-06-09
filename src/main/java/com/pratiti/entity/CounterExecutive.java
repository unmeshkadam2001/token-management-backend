package com.pratiti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "counter_executive")
public class CounterExecutive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counter_executive_id")
    private int counterExecutiveId;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

	public int getCounterExecutiveId() {
		return counterExecutiveId;
	}

	public void setCounterExecutiveId(int counterExecutiveId) {
		this.counterExecutiveId = counterExecutiveId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    
}
