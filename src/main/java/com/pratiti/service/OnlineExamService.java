package com.pratiti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pratiti.entity.Exam;
import com.pratiti.entity.QuestionOption;
import com.pratiti.entity.Scorecard;
import com.pratiti.entity.Subject;
import com.pratiti.entity.User;
import com.pratiti.exception.CustomerServiceException;
import com.pratiti.model.QuestionDto;
import com.pratiti.model.SubjectDto;
import com.pratiti.model.UserDetails;
import com.pratiti.model.UserParameter;
import com.pratiti.repository.ExamRepository;
import com.pratiti.repository.QuestionOptionRepository;
import com.pratiti.repository.ScorecardRepository;
import com.pratiti.repository.SubjectRepository;
import com.pratiti.repository.UserRepository;

@Service
public class OnlineExamService {
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private QuestionOptionRepository questionOptionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ScorecardRepository scorecardRepository;
	@Autowired
	private ExamRepository examRepository;


	public String addSubjectWithQuestionsAndOptions(SubjectDto subjectDto) {

		Subject subject = new Subject();
		subject.setSubjectName(subjectDto.getSubjectName());
		
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
			questionOption.setStatus("active");
			questionOptions.add(questionOption);
		 }
		 
		 questionOptionRepository.saveAll(questionOptions);
		 subjectRepository.save(subject);
	 
		 return "Subject with questions and options added successfully";

	 }
	
	@Transactional
	public String removeSubject(String subject) {
		if(subjectRepository.existsBySubjectName(subject) == true) {
			Optional<Subject> s = subjectRepository.findBySubjectName(subject);
			Subject subject1 = s.get();
			System.out.println(subject1.getSubjectId());
			questionOptionRepository.updateBysubjectId("inactive" , subject1.getSubjectId());
			return "subject deleted";
		}
		else {
			return "Subject not exists.";
		}
	}

//	public List<User> getUsersBySubjectAndScore(String subjectName, Integer level, Integer levelScore) {
//		List<User> users = new ArrayList<>();
//		if(level==1) {
//			users = userRepository.fetchByNameAndLevelAndLevel1Score(subjectName, levelScore);
//		}
//		else if(level==2) {
//			users = userRepository.fetchByNameAndLevelAndLevel2Score(subjectName, levelScore);
//		}
//		else {
//			users = userRepository.fetchByNameAndLevelAndLevel3Score(subjectName, levelScore);
//		}
//		
//		return users;
//	}

	public String updateTheExamTable(Integer selectedAnswer, Integer questionId, Integer userid) {
		Exam exam = new Exam();
		Optional<QuestionOption> q = questionOptionRepository.findById(questionId);
		Optional<User> u = userRepository.findById(userid);
		QuestionOption questionOption = q.get();
		User user = u.get()	;
		exam.setQuestionOption(questionOption);
		exam.setUser(user);
		exam.setSelectedAnswer(selectedAnswer);
		examRepository.save(exam);
		return "Updated";
	}

	public List<UserDetails> getUsersScoreBySubject(String subjectName) {
		// TODO Auto-generated method stub
		List<UserDetails> list = new ArrayList<>();
		List<User> users = new ArrayList<>();
		users = userRepository.fetchByName(subjectName);
		Optional<Subject> s = subjectRepository.findBySubjectName(subjectName);
		Integer id = s.get().getSubjectId();
		System.out.println(id);
		for(User u : users) {
			UserDetails userDetails = new UserDetails();
			Optional<Scorecard>sc = scorecardRepository.findBySubjectIdAndUserId(id , u.getUserId());
			userDetails.setCity(u.getCity());
			userDetails.setEmail(u.getEmail());
			userDetails.setName(u.getName());
			userDetails.setQualification(u.getQualification());
			userDetails.setState(u.getState());
			userDetails.setLevel1Score(sc.get().getLevel1Score());
			userDetails.setLevel2Score(sc.get().getLevel2Score());;
			userDetails.setLevel3Score(sc.get().getLevel3Score());
			list.add(userDetails);
		}
		return list;
	}


	
	
}
