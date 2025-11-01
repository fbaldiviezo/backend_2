package com.proyecto.backend_2.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePersonalRequestDto {
    private String oldCedula;
    private String newCedula;
    private Integer codp;
    private String nombre;
    private String ap;
    private String am;
    private Integer estado;
    private LocalDate fnac;
    private String ecivil;
    private String genero;
    private String direc;
    private String telf;
    private String tipo;
    private String foto;
}
