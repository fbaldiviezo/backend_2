package com.proyecto.backend_2.features.mepro;

import org.springframework.stereotype.Service;

import com.proyecto.backend_2.dtos.MeproRequest;
import com.proyecto.backend_2.features.menus.MenuModel;
import com.proyecto.backend_2.features.menus.MenuRepository;
import com.proyecto.backend_2.features.process.ProcessModel;
import com.proyecto.backend_2.features.process.ProcessRepository;
import com.proyecto.backend_2.ids.MeproId;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MeproService {
    private final MeproRepository repository;
    private final MenuRepository menuRepository;
    private final ProcessRepository processRepository;

    @Transactional
    public MeproModel save(MeproRequest mepro) {
        MenuModel menu = menuRepository.findById(mepro.getCodm())
                .orElseThrow(() -> new EntityNotFoundException("No existe el menu"));
        ProcessModel process = processRepository.findById(mepro.getCodp())
                .orElseThrow(() -> new EntityNotFoundException("No existe el proceso"));
        MeproId id = new MeproId(menu.getCodm(), process.getCodp());
        return repository.save(new MeproModel(id, menu, process));
    }

    @Transactional
    public void delete(Integer codm, Integer codp) {
        MeproId id = new MeproId(codm, codp);
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No existe la clave");
        }
    }
}
