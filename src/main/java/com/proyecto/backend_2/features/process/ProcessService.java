package com.proyecto.backend_2.features.process;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcessService {
    private final ProcessRepository repository;

    public List<ProcessModel> get() {
        return repository.findAll();
    }

    public List<ProcessModel> getByState(Integer estado) {
        if (estado == 2) {
            return repository.findAll();
        }
        return repository.getByState(estado);
    }

    public ProcessModel post(ProcessModel post) {
        return repository.save(post);
    }

    public ProcessModel put(Integer id, ProcessModel put) {
        put.setCodp(id);
        return repository.save(put);
    }

    @Transactional
    public void changeState(Integer id, Integer state) {
        repository.changeState(id, state);
    }
}
