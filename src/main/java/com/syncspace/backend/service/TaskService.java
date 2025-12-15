package com.syncspace.backend.service;

import com.syncspace.backend.model.Task;
import com.syncspace.backend.model.User;
import com.syncspace.backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksForUser(User user) {
        return taskRepository.findByUser(user);
    }

    public Optional<Task> getTask(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(User user, String title, String description) {
        Task task = new Task();
        task.setUser(user);
        task.setTitle(title);
        task.setDescription(description);
        return taskRepository.save(task);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
