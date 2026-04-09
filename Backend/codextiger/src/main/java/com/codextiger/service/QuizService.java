package com.codextiger.service;

import java.util.List;

import com.codextiger.model.QuizQuestion;

public interface QuizService {

	QuizQuestion saveQuestion(Long topicId, QuizQuestion question);
	
	List<QuizQuestion> getQuiz(long id);
}
