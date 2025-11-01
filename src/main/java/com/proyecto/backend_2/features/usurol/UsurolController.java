package com.proyecto.backend_2.features.usurol;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.backend_2.dtos.UsurolRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/asign/usurol")
@RequiredArgsConstructor
public class UsurolController {
    private final UsurolService service;

    @PostMapping
    public UsurolModel saveData(@RequestBody UsurolRequest usurol) {
        return service.save(usurol);
    }

    @DeleteMapping("/{login}/{codr}")
    public void deleteData(@PathVariable String login, @PathVariable Integer codr) {
        service.delete(login, codr);
    }
}
