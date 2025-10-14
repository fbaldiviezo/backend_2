package com.proyecto.backend_2.features.rolme;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.backend_2.features.menus.MenuModel;
import com.proyecto.backend_2.ids.RolmeId;

public interface RolmeRepository extends JpaRepository<RolmeModel, RolmeId> {
    @Query("SELECT m FROM RolmeModel rm JOIN MenuModel m ON rm.id.codm = m.codm WHERE rm.id.codr = :rol")
    List<MenuModel> findMenuByRol(@Param("rol") int rol);
}
