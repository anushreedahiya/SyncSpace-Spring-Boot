package com.syncspace.backend.service;

import com.syncspace.backend.model.MicroTask;
import com.syncspace.backend.model.Task;
import com.syncspace.backend.repository.MicroTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MicroTaskService {

    private final MicroTaskRepository microTaskRepository;

    public MicroTaskService(MicroTaskRepository microTaskRepository) {
        this.microTaskRepository = microTaskRepository;
    }

    public List<MicroTask> getAll() {
        return microTaskRepository.findAll();
    }

    public List<MicroTask> getByTask(Task task) {
        return microTaskRepository.findByTask(task);
    }

    public MicroTask create(MicroTask microTask) {
        return microTaskRepository.save(microTask);
    }

    public Optional<MicroTask> getById(Long id) {
        return microTaskRepository.findById(id);
    }

    public MicroTask save(MicroTask microTask) {
        return microTaskRepository.save(microTask);
    }

    public void delete(Long id) {
        microTaskRepository.deleteById(id);
    }
}
