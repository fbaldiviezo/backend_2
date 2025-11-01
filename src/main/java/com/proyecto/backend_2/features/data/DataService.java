package com.proyecto.backend_2.features.data;

import org.springframework.stereotype.Service;

import com.proyecto.backend_2.dtos.DataRequest;
import com.proyecto.backend_2.features.data.repositories.DataRepository;
import com.proyecto.backend_2.features.data.repositories.DataRepository2;
import com.proyecto.backend_2.features.personals.PersonalModel;
import com.proyecto.backend_2.features.personals.PersonalRepository;
import com.proyecto.backend_2.ids.DataId;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataService {
    private final DataRepository repository;
    private final DataRepository2 repository2;
    private final PersonalRepository personalRepository;

    @Transactional
    public DataModel save(DataRequest data) {
        PersonalModel p = personalRepository.findById(data.getCodp())
                .orElseThrow(
                        () -> new EntityNotFoundException("No se encontró el personal con codp: " + data.getCodp()));
        DataId id = new DataId(p.getCodp(), data.getCedula());
        return repository.save(new DataModel(id, p));
    }

    @Transactional
    public void update(Integer codp, String oldCedula, String newCedula) {
        DataId id = new DataId(codp, oldCedula);
        if (repository.existsById(id)) {
            repository2.updateCedula(codp, newCedula);
        } else {
            throw new EntityNotFoundException("No existe la clave buscada");
        }
    }
}
