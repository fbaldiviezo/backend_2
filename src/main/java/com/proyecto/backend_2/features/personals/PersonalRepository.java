package com.proyecto.backend_2.features.personals;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends JpaRepository<PersonalModel, Integer> {
    @Modifying
    @Query(value = "update personal set estado = :state where codp = :id;", nativeQuery = true)
    void changeState(@Param("id") Integer id, @Param("state") Integer state);

    // query para traer la cedula del usuario
    @Query(value = "select cedula from datos where codp = :id;", nativeQuery = true)
    String getCedula(@Param("id") Integer id);

    @Query(value = "select * from personal where estado = :estado;", nativeQuery = true)
    List<PersonalModel> getByState(@Param("estado") Integer estado);

    @Query(value = "select * from personal where tipo = :tipo", nativeQuery = true)
    List<PersonalModel> getByType(@Param("tipo") String tipo);

    @Query(value = "select * from personal where tipo = :tipo and estado = :estado", nativeQuery = true)
    List<PersonalModel> getByFilter(@Param("tipo") String tipo, @Param("estado") Integer estado);
}
