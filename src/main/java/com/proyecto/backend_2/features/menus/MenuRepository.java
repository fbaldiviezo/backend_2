package com.proyecto.backend_2.features.menus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.backend_2.dtos.ProcessDto;

public interface MenuRepository extends JpaRepository<MenuModel, Integer> {
    @Modifying
    @Query(value = "update menus set estado = :state where codm = :id;", nativeQuery = true)
    void changeState(@Param("id") Integer id, @Param("state") Integer state);

    // Query para obtener todos los menus relacionados a un rol
    @Query(value = "select m.* from menus m join rolme rm on rm.codm = m.codm where rm.codr = :xcodr", nativeQuery = true)
    List<MenuModel> getMenusRol(@Param("xcodr") Integer xcodr);

    // Query para obtener todos lo procesos relacionados a ese menu
    @Query(value = "select p.nombre as nombre,p.ayuda as ayuda, p.enlace as enlace from procesos p"
            + " join mepro mp on mp.codp = p.codp where mp.codm = :xcodm", nativeQuery = true)
    List<ProcessDto> getProcesosMenu(@Param("xcodm") Integer xcodm);

    @Query(value = "select * from menus where estado = :state;", nativeQuery = true)
    List<MenuModel> getByState(@Param("state") Integer state);
}
