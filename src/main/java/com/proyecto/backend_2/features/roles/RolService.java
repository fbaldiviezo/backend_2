package com.proyecto.backend_2.features.roles;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.backend_2.dtos.RolesByUsersDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolService {
    private final RolRepository repository;

    public List<RolModel> get() {
        return repository.findAll();
    }

    public List<RolModel> getByState(Integer estado) {
        if (estado == 2) {
            return repository.findAll();
        }
        return repository.getByState(estado);
    }

    public RolModel post(RolModel post) {
        return repository.save(post);
    }

    public RolModel put(Integer id, RolModel put) {
        put.setCodr(id);
        return repository.save(put);
    }

    @Transactional
    public void changeState(Integer id, Integer state) {
        repository.changeState(id, state);
    }

    // obtener roles segun el usuario
    public List<RolesByUsersDto> filterRoles(Integer state, String login) {
        if (state == 2) {
            return repository.getAsignedRoles(login);
        }
        if (state == 3) {
            return repository.getUnsignedRoles(login);
        }
        return repository.getRolesByUser(login);
    }

    public List<RolesByUsersDto> filterRolesByUsers(String login) {
        return repository.getRolesByUser(login);
    }
}
