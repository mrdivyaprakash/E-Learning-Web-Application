package com.codextiger.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.codextiger.model.Tutorial;
import com.codextiger.repository.TutorialRepository;
import com.codextiger.service.TutorialService;

@Service
public class TutorialServiceImpl implements TutorialService {

	    @Autowired
	    private TutorialRepository tutorialRepository;

//	    @Autowired
//	    private TopicRepository topicRepository;

	    @Override
	    @Cacheable(value = "tutorials", key="#slug")
	    public Tutorial getTutorialBySlug(String slug) {
	    	
//	    	to test cache is working or not 
	    	System.out.println("Fetching tutorial from DATABASE...");
	        return tutorialRepository.findBySlug(slug)
	                .orElseThrow(() -> new RuntimeException("Tutorial not found"));
	    }

//	    @Override
//	    public List<Topic> getTopicsByTutorial(Long tutorialId) {
//	        return topicRepository.findByTutorialIdOrderByTopicOrderAsc(tutorialId);
//	    }
	
	    @Override
	    @CacheEvict(value="tutorials", key="#tutorial.slug")
	    public Tutorial saveTutorial(Tutorial tutorial) {
	        return tutorialRepository.save(tutorial);
	    }

	    @Override
	    public List<Tutorial> getAllTutorials() {
	        return tutorialRepository.findAll();
	    }

}
