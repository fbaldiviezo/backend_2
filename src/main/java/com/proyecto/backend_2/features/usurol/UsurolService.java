package com.proyecto.backend_2.features.usurol;

import org.springframework.stereotype.Service;

import com.proyecto.backend_2.dtos.UsurolRequest;
import com.proyecto.backend_2.features.roles.RolModel;
import com.proyecto.backend_2.features.roles.RolRepository;
import com.proyecto.backend_2.features.users.UserModel;
import com.proyecto.backend_2.features.users.UserRepository;
import com.proyecto.backend_2.ids.UsurolId;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsurolService {
    private final UsurolRepository repository;
    private final RolRepository rolRepository;
    private final UserRepository userRepository;

    @Transactional
    public UsurolModel save(UsurolRequest usurol) {
        UserModel user = userRepository.findById(usurol.getLogin())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el usuario"));
        RolModel rol = rolRepository.findById(usurol.getCodr())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el rol"));
        UsurolId id = new UsurolId(user.getLogin(), rol.getCodr());
        return repository.save(new UsurolModel(id, user, rol));
    }

    @Transactional
    public void delete(String login, Integer codr) {
        UsurolId id = new UsurolId(login, codr);
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No existe la clave");
        }
    }
}
