/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.argprograma.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSkills {

    private String nombre;
    private int porcentaje;

    public DtoSkills() {
    }

    public DtoSkills(String nombre, int porcentaje) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

}
