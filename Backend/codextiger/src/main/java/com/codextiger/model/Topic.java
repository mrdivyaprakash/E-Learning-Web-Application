package com.codextiger.model;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "topics")
public class Topic {

	
	
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;

	    @Column(columnDefinition = "TEXT")
	    private String content;

	    private String videoUrl;
	    private Integer topicOrder;

	    @ManyToOne
	    @JoinColumn(name = "tutorial_id")
	    private Tutorial tutorial;

	    // getters setters
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getVideoUrl() {
			return videoUrl;
		}

		public void setVideoUrl(String videoUrl) {
			this.videoUrl = videoUrl;
		}

		public Integer getTopicOrder() {
			return topicOrder;
		}

		public void setTopicOrder(Integer topicOrder) {
			this.topicOrder = topicOrder;
		}

		public Tutorial getTutorial() {
			return tutorial;
		}

		public void setTutorial(Tutorial tutorial) {
			this.tutorial = tutorial;
		}

	   
	    
	    
	

}
