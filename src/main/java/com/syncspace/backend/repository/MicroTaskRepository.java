package com.syncspace.backend.repository;

import com.syncspace.backend.model.MicroTask;
import com.syncspace.backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MicroTaskRepository extends JpaRepository<MicroTask, Long> {
    List<MicroTask> findByTask(Task task);
}
