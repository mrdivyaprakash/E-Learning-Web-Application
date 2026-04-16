package com.codextiger.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codextiger.exception.ResourceNotFoundException;
import com.codextiger.model.Topic;
import com.codextiger.model.Tutorial;
import com.codextiger.repository.TopicRepository;
import com.codextiger.repository.TutorialRepository;
import com.codextiger.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService{

	 @Autowired
	    private TopicRepository topicRepository;

	    @Autowired
	    private TutorialRepository tutorialRepository;

	    @Override
	    public Topic saveTopic(Long tutorialId, Topic topic) {

	        Tutorial tutorial = tutorialRepository.findById(tutorialId)
	                .orElseThrow(() -> new RuntimeException("Tutorial not found"));

	        topic.setTutorial(tutorial);
	        return topicRepository.save(topic);
	    }

	    @Override
	    public List<Topic> getTopicsByTutorial(Long tutorialId) {
	        return topicRepository.findAll()
	                .stream()
	                .filter(t -> t.getTutorial().getId().equals(tutorialId))
	                .toList();
	    }

		@Override
		public Topic getTopic(Long id) {
			return topicRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Topic not found"));
		}

		
		
}
