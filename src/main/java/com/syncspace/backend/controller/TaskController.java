package com.syncspace.backend.controller;

import com.syncspace.backend.model.Task;
import com.syncspace.backend.model.User;
import com.syncspace.backend.service.TaskService;
import com.syncspace.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    // GET all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    // GET tasks for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksForUser(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(taskService.getTasksForUser(user.get()));
    }

    // GET task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTask(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestParam Long userId,
                                           @RequestParam String title,
                                           @RequestParam String description) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isEmpty()) return ResponseEntity.notFound().build();
        Task task = taskService.createTask(user.get(), title, description);
        return ResponseEntity.ok(task);
    }

    // PUT update task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,
                                           @RequestBody Task taskDetails) {
        return taskService.getTask(id)
                .map(task -> {
                    task.setTitle(taskDetails.getTitle());
                    task.setDescription(taskDetails.getDescription());
                    return ResponseEntity.ok(taskService.saveTask(task));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
