package com.pratiti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.pratiti.entity.QuestionOption;
import com.pratiti.entity.Subject;
import com.pratiti.exception.CustomerServiceException;
import com.pratiti.model.QuestionDto;
import com.pratiti.model.SubjectDto;
import com.pratiti.repository.QuestionOptionRepository;
import com.pratiti.repository.SubjectRepository;

@Service
public class OnlineExamService {
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private QuestionOptionRepository questionOptionRepository;


	public String addSubjectWithQuestionsAndOptions(SubjectDto subjectDto) {

		Subject subject = new Subject();
		subject.setSubjectName(subjectDto.getSubjectName());
		subject.setSubjectNoOfQuestions(subjectDto.getSubjectNoOfQuestions());
		
		List<QuestionOption> questionOptions = new ArrayList<>();
		for (QuestionDto questionDto : subjectDto.getQuestions()) {
			QuestionOption questionOption = new QuestionOption();
			questionOption.setQuestionDesc(questionDto.getQuestionDesc());
			questionOption.setQuestionLevel(questionDto.getQuestionLevel());
			questionOption.setOption1(questionDto.getOption1());
			questionOption.setOption2(questionDto.getOption2());
			questionOption.setOption3(questionDto.getOption3());
			questionOption.setOption4(questionDto.getOption4());
			questionOption.setIsCorrect(questionDto.getIsCorrect());
			questionOption.setSubject(subject);
			questionOptions.add(questionOption);
		 }
		 
		 questionOptionRepository.saveAll(questionOptions);
		 subjectRepository.save(subject);
	 
		 return "Subject with questions and options added successfully";

	 }
	
	
	public String removeSubject(String subject) {
		if(subjectRepository.existsBySubjectName(subject) == true) {
			Optional<Subject> s = subjectRepository.findBySubjectName(subject);
			Subject subject1 = s.get();
			System.out.println(subject1.getSubjectId());
			questionOptionRepository.deleteBySubjectSubjectId(subject1.getSubjectId());
			return "subject deleted";
		}
		else {
			return "Subject not exists.";
		}
	}
}
