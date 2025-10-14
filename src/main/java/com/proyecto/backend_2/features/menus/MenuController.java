package com.proyecto.backend_2.features.menus;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.backend_2.dtos.MenuDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService service;

    @GetMapping
    public List<MenuModel> getAllMenus() {
        return service.get();
    }

    @GetMapping("/{codr}")
    public List<MenuDto> getMenusRol(@PathVariable Integer codr) {
        return service.getMenusRol(codr);
    }

    @PostMapping
    public MenuModel saveMenu(@RequestBody MenuModel menu) {
        return service.post(menu);
    }

    @PutMapping("/{id}")
    public MenuModel updateMenu(@PathVariable Integer id, @RequestBody MenuModel menu) {
        return service.put(id, menu);
    }

    @PutMapping("/{id}/{state}")
    public void changeState(@PathVariable Integer id, @PathVariable Integer state) {
        service.changeState(id, state);
    }
}
