package com.proyecto.backend_2.features.process;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/process")
@RequiredArgsConstructor
public class ProcessController {
    private final ProcessService service;

    @GetMapping
    public List<ProcessModel> getAllProcesses() {
        return service.get();
    }

    @GetMapping("/filter/{state}")
    public List<ProcessModel> getProcessByState(@PathVariable Integer state) {
        return service.getByState(state);
    }

    @PostMapping
    public ProcessModel saveProcess(@RequestBody ProcessModel post) {
        return service.post(post);
    }

    @PutMapping("/{id}")
    public ProcessModel updateProccess(@PathVariable Integer id, @RequestBody ProcessModel put) {
        return service.put(id, put);
    }

    @PutMapping("/{id}/{state}")
    public void changeState(@PathVariable Integer id, @PathVariable Integer state) {
        service.changeState(id, state);
    }
}
