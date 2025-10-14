package com.proyecto.backend_2.features.mepro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.backend_2.features.process.ProcessModel;
import com.proyecto.backend_2.ids.MeproId;

@Repository
public interface MeproRepository extends JpaRepository<MeproModel, MeproId> {
    @Query("SELECT p FROM ProcessModel p JOIN MeproModel mp ON mp.id.codp = p.codp WHERE mp.id.codm = :codm")
    List<ProcessModel> findByMenuCodm(@Param("codm") int codm);
}
