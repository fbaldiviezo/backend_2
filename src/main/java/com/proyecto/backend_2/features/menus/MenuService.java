package com.proyecto.backend_2.features.menus;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.backend_2.dtos.MenuDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository repository;

    public List<MenuModel> get() {
        return repository.findAll();
    }

    public List<MenuModel> getByState(Integer estado) {
        if (estado == 2) {
            return repository.findAll();
        }
        return repository.getByState(estado);
    }

    public MenuModel post(MenuModel post) {
        return repository.save(post);
    }

    public MenuModel put(Integer id, MenuModel put) {
        put.setCodm(id);
        return repository.save(put);
    }

    @Transactional
    public void changeState(Integer id, Integer state) {
        repository.changeState(id, state);
    }

    public List<MenuDto> getMenusRol(Integer codr) {
        List<MenuModel> menus = repository.getMenusRol(codr);
        List<MenuDto> menuRol = new ArrayList<>();
        for (MenuModel x : menus) {
            menuRol.add(new MenuDto(x.getCodm(), x.getNombre(), repository.getProcesosMenu(x.getCodm())));
        }
        return menuRol;
    }
}
