package com.codextiger.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codextiger.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    Optional<Tutorial> findBySlug(String slug);

}
