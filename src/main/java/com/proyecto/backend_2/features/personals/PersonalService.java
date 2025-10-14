package com.proyecto.backend_2.features.personals;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.backend_2.dtos.PersonalRequestDto;
import com.proyecto.backend_2.features.data.DataModel;
import com.proyecto.backend_2.features.data.DataRepository;
import com.proyecto.backend_2.ids.DataId;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonalService {
    private final PersonalRepository repository;
    private final DataRepository dataRepository;

    public List<PersonalModel> get() {
        return repository.findAll();
    }

    public List<PersonalModel> getPersonalByFilter(String tipo, Integer estado) {
        boolean tipoTodos = (tipo == null || tipo.equalsIgnoreCase("T"));
        boolean estadoTodos = (estado == null || estado == 2);

        if (tipoTodos && estadoTodos) {
            return repository.findAll();
        }
        if (!tipoTodos && estadoTodos) {
            return repository.getByType(tipo);
        }
        if (tipoTodos && !estadoTodos) {
            return repository.getByState(estado);
        }
        return repository.getByFilter(tipo, estado);
    }

    public PersonalModel post(PersonalRequestDto post) {
        PersonalModel p = repository.save(post.getPersona());
        if (post.getCedula() != null) {
            DataId id = new DataId(p.getCodp(), post.getCedula());
            dataRepository.save(new DataModel(id, p));
        }
        return p;
    }

    public PersonalModel put(PersonalModel put, int id) {
        put.setCodp(id);
        return repository.save(put);
    }

    @Transactional
    public void changeState(Integer id, Integer state) {
        repository.changeState(id, state);
    }
}
