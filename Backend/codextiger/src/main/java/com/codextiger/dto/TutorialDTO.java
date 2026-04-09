package com.codextiger.dto;

public class TutorialDTO {

	private Long id;
    private String title;
    private String slug;

    public TutorialDTO(Long id, String title, String slug) {
        this.id = id;
        this.title = title;
        this.slug = slug;
    }
    
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getSlug() { return slug; }
}
