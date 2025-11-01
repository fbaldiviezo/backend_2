package com.proyecto.backend_2.features.personals;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.backend_2.dtos.PersonalInfoUserDto;
import com.proyecto.backend_2.dtos.PersonalRequestDto;
import com.proyecto.backend_2.dtos.UpdatePersonalRequestDto;
import com.proyecto.backend_2.features.data.DataModel;
import com.proyecto.backend_2.features.data.repositories.DataRepository;
import com.proyecto.backend_2.features.data.repositories.DataRepository2;
import com.proyecto.backend_2.ids.DataId;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonalService {
    private final PersonalRepository repository;
    private final DataRepository dataRepository;
    private final DataRepository2 dataRepository2;

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

    @Transactional
    public PersonalModel put(UpdatePersonalRequestDto put, int id) {
        if (put.getNewCedula() == null || put.getNewCedula().trim().isEmpty()) {
            throw new IllegalArgumentException("La nueva cédula ('newcedula') no puede ser nula o vacía.");
        }
        PersonalModel p = repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("No se encontró el registro de Personal con codp: " + id));
        p.setNombre(put.getNombre());
        p.setAp(put.getAp());
        p.setAm(put.getAm());
        p.setEstado(put.getEstado());
        p.setFnac(put.getFnac());
        p.setEcivil(put.getEcivil());
        p.setGenero(put.getGenero());
        p.setDirec(put.getDirec());
        p.setTelf(put.getTelf());
        p.setTipo(put.getTipo());
        p.setFoto(put.getFoto());
        PersonalModel actualizado = repository.save(p);
        dataRepository2.updateCedula(id, put.getNewCedula());
        return actualizado;
    }

    @Transactional
    public void changeState(Integer id, Integer state) {
        repository.changeState(id, state);
    }

    public List<PersonalInfoUserDto> getPersonalInfoUser() {
        return repository.getPersonalInfo();
    }
}
