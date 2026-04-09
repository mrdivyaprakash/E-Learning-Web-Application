package com.codextiger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codextiger.model.QuizQuestion;

public interface QuizRepository extends JpaRepository<QuizQuestion, Long> {
    List<QuizQuestion> findByTopicId(Long topicId);

}
