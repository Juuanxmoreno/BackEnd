package com.portfolio.argprograma.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    private String nombre;
    
    @NotNull
    private String apellido;
    
    @NotNull
    private String acercaDe;
    
    private String img;       

    public Persona() {
    }

    public Persona(String nombre, String apellido, String acercaDe, String img) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.acercaDe = acercaDe;
        this.img = img;
    }
    
    
}