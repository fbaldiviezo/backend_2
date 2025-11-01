package com.proyecto.backend_2.features.mepro;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.backend_2.dtos.MeproRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/asign/mepro")
@RequiredArgsConstructor
public class MeproController {
    private final MeproService service;

    @PostMapping
    public MeproModel saveData(@RequestBody MeproRequest mepro) {
        return service.save(mepro);
    }

    @DeleteMapping("/{codm}/{codp}")
    public void deleteData(@PathVariable Integer codm, @PathVariable Integer codp) {
        service.delete(codm, codp);
    }
}
