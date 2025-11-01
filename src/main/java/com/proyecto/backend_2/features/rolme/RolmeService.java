package com.proyecto.backend_2.features.rolme;

import org.springframework.stereotype.Service;

import com.proyecto.backend_2.dtos.RolmeRequest;
import com.proyecto.backend_2.features.menus.MenuModel;
import com.proyecto.backend_2.features.menus.MenuRepository;
import com.proyecto.backend_2.features.roles.RolModel;
import com.proyecto.backend_2.features.roles.RolRepository;
import com.proyecto.backend_2.ids.RolmeId;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolmeService {
    private final RolmeRepository repository;
    private final MenuRepository menuRepository;
    private final RolRepository rolRepository;

    @Transactional
    public RolmeModel save(RolmeRequest rolme) {
        RolModel rol = rolRepository.findById(rolme.getCodr())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el rol"));
        MenuModel menu = menuRepository.findById(rolme.getCodm())
                .orElseThrow(() -> new EntityNotFoundException("NO se encontro el menu"));
        RolmeId id = new RolmeId(rol.getCodr(), menu.getCodm());
        return repository.save(new RolmeModel(id, rol, menu));
    }

    @Transactional
    public void delete(Integer codr, Integer codm) {
        RolmeId id = new RolmeId(codr, codm);
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No existe la clave");
        }
    }
}
