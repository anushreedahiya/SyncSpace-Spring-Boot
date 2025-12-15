package com.syncspace.backend.repository;

import com.syncspace.backend.model.Task;
import com.syncspace.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}



