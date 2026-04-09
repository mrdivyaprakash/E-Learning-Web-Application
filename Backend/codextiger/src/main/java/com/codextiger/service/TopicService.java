package com.codextiger.service;

import java.util.List;

import com.codextiger.model.Topic;

public interface TopicService {

	 Topic saveTopic(Long tutorialId, Topic topic);
	    List<Topic> getTopicsByTutorial(Long tutorialId);
	    Topic getTopic(Long id);
}
