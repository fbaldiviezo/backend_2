package com.proyecto.backend_2.features.rolme;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.backend_2.dtos.RolmeRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/asign/rolme")
@RequiredArgsConstructor
public class RolmeController {
    private final RolmeService service;

    @PostMapping
    public RolmeModel saveData(@RequestBody RolmeRequest rolme) {
        return service.save(rolme);
    }

    @DeleteMapping("/{codr}/{codm}")
    public void deleteData(@PathVariable Integer codr, @PathVariable Integer codm) {
        service.delete(codr, codm);
    }
}
