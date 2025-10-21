package com.proyecto.backend_2.features.process;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends JpaRepository<ProcessModel, Integer> {
    @Modifying
    @Query(value = "update procesos set estado = :state where codm = :id;", nativeQuery = true)
    void changeState(@Param("id") Integer id, @Param("state") Integer state);

    @Query(value = "select * from procesos where estado = :state;", nativeQuery = true)
    List<ProcessModel> getByState(@Param("state") Integer state);
}
