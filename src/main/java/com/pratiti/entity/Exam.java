package com.pratiti.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userId")
    private User user;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "questionId", referencedColumnName = "questionId")
    private QuestionOption questionOption;
    
    private Integer selectedAnswer;
    
    private Integer level1Score;
    private Integer level2Score;
    private Integer level3Score;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public QuestionOption getQuestionOption() {
		return questionOption;
	}
	public void setQuestionOption(QuestionOption questionOption) {
		this.questionOption = questionOption;
	}
	public Integer getSelectedAnswer() {
		return selectedAnswer;
	}
	public void setSelectedAnswer(Integer selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
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