package com.codextiger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codextiger.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByTutorialIdOrderByTopicOrderAsc(Long tutorialId);

}
