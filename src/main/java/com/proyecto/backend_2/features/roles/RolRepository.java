package com.proyecto.backend_2.features.roles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<RolModel, Integer> {
    @Modifying
    @Query(value = "update roles set estado = :state where codr = :id;", nativeQuery = true)
    void changeState(@Param("id") Integer id, @Param("state") Integer state);

    @Query(value = "select * from roles where estado = :state;", nativeQuery = true)
    List<RolModel> getByState(@Param("state") Integer state);
}
