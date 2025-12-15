package com.syncspace.backend.controller;

import com.syncspace.backend.model.MicroTask;
import com.syncspace.backend.model.Task;
import com.syncspace.backend.service.MicroTaskService;
import com.syncspace.backend.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/microtasks")
public class MicroTaskController {

    private final MicroTaskService microTaskService;
    private final TaskService taskService;

    public MicroTaskController(MicroTaskService microTaskService, TaskService taskService) {
        this.microTaskService = microTaskService;
        this.taskService = taskService;
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<MicroTask>> getMicroTasks(@PathVariable Long taskId) {
        Optional<Task> task = taskService.getTask(taskId);
        if (task.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(microTaskService.getByTask(task.get()));
    }

    @PostMapping("/task/{taskId}")
    public ResponseEntity<MicroTask> createMicroTask(@PathVariable Long taskId, @RequestBody MicroTask microTask) {
        Optional<Task> task = taskService.getTask(taskId);
        if (task.isEmpty()) return ResponseEntity.notFound().build();
        microTask.setTask(task.get());
        return ResponseEntity.ok(microTaskService.create(microTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MicroTask> updateMicroTask(@PathVariable Long id, @RequestBody MicroTask microTask) {
        Optional<MicroTask> existing = microTaskService.getById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();
        MicroTask m = existing.get();
        m.setTitle(microTask.getTitle());
        m.setDescription(microTask.getDescription());
        m.setStatus(microTask.getStatus());
        return ResponseEntity.ok(microTaskService.save(m));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMicroTask(@PathVariable Long id) {
        microTaskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
