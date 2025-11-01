package com.proyecto.backend_2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolesByUsersDto {
    private Integer codr;
    private String nombre;
    private Integer estado;
    private Boolean relacion;
}
