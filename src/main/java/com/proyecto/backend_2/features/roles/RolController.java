package com.proyecto.backend_2.features.roles;

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
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {
    private final RolService service;

    @GetMapping
    public List<RolModel> getAllRoles() {
        return service.get();
    }

    @GetMapping("/filter/{state}")
    public List<RolModel> getRolesByState(@PathVariable Integer state) {
        return service.getByState(state);
    }

    @PostMapping
    public RolModel saveRole(@RequestBody RolModel role) {
        return service.post(role);
    }

    @PutMapping("/{id}")
    public RolModel updateRole(@PathVariable Integer id, @RequestBody RolModel rol) {
        return service.put(id, rol);
    }

    @PutMapping("/{id}/{state}")
    public void changeState(@PathVariable Integer id, @PathVariable Integer state) {
        service.changeState(id, state);
    }
}
