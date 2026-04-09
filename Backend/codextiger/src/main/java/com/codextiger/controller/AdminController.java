package com.codextiger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codextiger.model.QuizQuestion;
import com.codextiger.model.Topic;
import com.codextiger.model.Tutorial;
import com.codextiger.service.QuizService;
import com.codextiger.service.TopicService;
import com.codextiger.service.TutorialService;

@RestController
@RequestMapping("/api/admin")
//@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AdminController {

	
	 @Autowired
	    private TutorialService tutorialService;

	    @Autowired
	    private TopicService topicService;

	    @Autowired
	    private QuizService quizService;

	    @PostMapping("/tutorial")
	    public Tutorial addTutorial(@RequestBody Tutorial tutorial) {
	        return tutorialService.saveTutorial(tutorial);
	    }

	    @GetMapping("/tutorials")
	    public List<Tutorial> getAllTutorials() {
	        return tutorialService.getAllTutorials();
	    }

	    
	    @PostMapping("/topic/{tutorialId}")
	    public Topic addTopic(@PathVariable Long tutorialId,
	                          @RequestBody Topic topic) {
	        return topicService.saveTopic(tutorialId, topic);
	    }

	    @PostMapping("/quiz/{topicId}")
	    public QuizQuestion addQuiz(@PathVariable Long topicId,
	                                @RequestBody QuizQuestion question) {
	        return quizService.saveQuestion(topicId, question);
	    }
}
