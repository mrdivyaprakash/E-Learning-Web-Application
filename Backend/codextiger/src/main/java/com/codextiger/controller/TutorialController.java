package com.codextiger.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codextiger.dto.TutorialDTO;
import com.codextiger.model.QuizQuestion;
import com.codextiger.model.Topic;
import com.codextiger.model.Tutorial;
import com.codextiger.service.QuizService;
import com.codextiger.service.TopicService;
import com.codextiger.service.TutorialService;

//@Controller
@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
public class TutorialController {
	
	
	  @Autowired
	    private TutorialService tutorialService;
	  @Autowired
	    private TopicService  topicService;
	    
	    @Autowired
	    private QuizService quizService;

//	    get tutorial by slug
	    
	    @GetMapping("/tutorial/{slug}")
	    public TutorialDTO getTutorial(@PathVariable String slug) {

	        Tutorial tutorial = tutorialService.getTutorialBySlug(slug);

	        return new TutorialDTO(
	                tutorial.getId(),
	                tutorial.getTitle(),
	                tutorial.getSlug()
	        );
	    }
	    
//	    get topic by tutorialID
	    @GetMapping("/topics/{tutorialId}")
	    public List<Topic> getTopics(@PathVariable Long tutorialId) {
	        return topicService.getTopicsByTutorial(tutorialId);
	    }

//	    get topic by topic id
	    
	    @GetMapping("/topic/{id}")
	    public Topic getTopic(@PathVariable Long id) {
	        return topicService.getTopic(id);
	    }

// get Quiz by topic id
	    
	    @GetMapping("/quiz/topic/{topicId}")
	    public List<QuizQuestion> getQuiz(@PathVariable Long topicId) {
	        return quizService.getQuiz(topicId);
	    }
	
	

	}



