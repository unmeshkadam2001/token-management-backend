package com.pratiti.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Scorecard")
public class Scorecard {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scorecardId;
    
    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "userId")
    private User student;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectId", referencedColumnName = "subjectId")
    private Subject subject;
    
    private Integer score;

	public Integer getScorecardId() {
		return scorecardId;
	}

	public void setScorecardId(Integer scorecardId) {
		this.scorecardId = scorecardId;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
    
    
}
