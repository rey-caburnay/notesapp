package com.exam.ota.notes.model;

import javax.validation.constraints.NotBlank;

public class Note {
    private Long id;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Body is required")
    private String body;

    // Constructors, getters, and setters
    public Note() {}

    public Note(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
