package com.codextiger.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codextiger.model.QuizQuestion;
import com.codextiger.model.Topic;
import com.codextiger.repository.QuizRepository;
import com.codextiger.repository.TopicRepository;
import com.codextiger.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public QuizQuestion saveQuestion(Long topicId, QuizQuestion question) {

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        question.setTopic(topic);
        return quizRepository.save(question);
    }

	@Override
	public List<QuizQuestion> getQuiz(long id) {
		return quizRepository.findByTopicId(id);
	}

}
