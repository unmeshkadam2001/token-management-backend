package com.pratiti.model;

public class UserDetails {
	private String name;
	private String email;
	private String password;
	private String city;
	private String state;
	private String qualification;
	private Integer level1Score;
    private Integer level2Score;
    private Integer level3Score;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public Integer getLevel1Score() {
		return level1Score;
	}
	public void setLevel1Score(Integer level1Score) {
		this.level1Score = level1Score;
	}
	public Integer getLevel2Score() {
		return level2Score;
	}
	public void setLevel2Score(Integer level2Score) {
		this.level2Score = level2Score;
	}
	public Integer getLevel3Score() {
		return level3Score;
	}
	public void setLevel3Score(Integer level3Score) {
		this.level3Score = level3Score;
	}
}
