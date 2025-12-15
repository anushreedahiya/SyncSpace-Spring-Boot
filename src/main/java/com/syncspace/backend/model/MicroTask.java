package com.syncspace.backend.model;

import jakarta.persistence.*;

@Entity
public class MicroTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String status;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    // Default constructor
    public MicroTask() {}

    // Parameterized constructor
    public MicroTask(String title, String description, String status, Task task) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.task = task;
    }

    // Getters and Setters
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

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Task getTask() {
        return task;
    }
    public void setTask(Task task) {
        this.task = task;
    }
}
