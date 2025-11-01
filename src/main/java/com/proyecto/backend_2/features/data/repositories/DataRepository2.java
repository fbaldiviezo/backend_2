package com.proyecto.backend_2.features.data.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DataRepository2 {
    private final JdbcTemplate template;
    String sql;

    public void updateCedula(Integer codp, String newCedula) {
        sql = "update datos set cedula = ? where codp = ?";
        template.update(sql, newCedula, codp);
    }
}
