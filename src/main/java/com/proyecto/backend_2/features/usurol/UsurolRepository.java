package com.proyecto.backend_2.features.usurol;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.backend_2.features.roles.RolModel;
import com.proyecto.backend_2.ids.UsurolId;

public interface UsurolRepository extends JpaRepository<UsurolModel, UsurolId> {
    @Query("SELECT ur.rolUsuario FROM UsurolModel ur WHERE LOWER(TRIM(ur.usuarioRol.login)) = LOWER(TRIM(:login))")
    List<RolModel> findRolesByLogin(@Param("login") String login);
}
