package com.codextiger.service;

import java.util.List;

import com.codextiger.model.Tutorial;

public interface TutorialService {

	  Tutorial getTutorialBySlug(String slug);
	    Tutorial saveTutorial(Tutorial tutorial);
	    List<Tutorial> getAllTutorials();
	    
}
