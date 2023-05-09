/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.argprograma.Controller;

import com.portfolio.argprograma.Dto.DtoSkills;
import com.portfolio.argprograma.Entity.Skills;
import com.portfolio.argprograma.Security.Controller.Mensaje;
import com.portfolio.argprograma.Service.ServiceSkills;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://portfolio-argprograma-jcm.web.app"})
@RequestMapping("/skills")
public class ControllerSkills {

    @Autowired
    ServiceSkills serviceSkills;

    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list() {
        List<Skills> list = serviceSkills.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id) {
        if (!serviceSkills.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Skills skills = serviceSkills.getOne(id).get();
        return new ResponseEntity(skills, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoSkills dtoSkills) {
        if (StringUtils.isBlank(dtoSkills.getNombre())) {
            return new ResponseEntity(new Mensaje("Este campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (serviceSkills.existsByNombre(dtoSkills.getNombre())) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }

        Skills skills = new Skills(dtoSkills.getNombre(), dtoSkills.getPorcentaje());
        serviceSkills.save(skills);

        return new ResponseEntity(new Mensaje("Skill añadida"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoSkills dtoSkills) {
        if (!serviceSkills.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (serviceSkills.existsByNombre(dtoSkills.getNombre()) && serviceSkills.getByNombre(dtoSkills.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoSkills.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Skills skills = serviceSkills.getOne(id).get();
        skills.setNombre(dtoSkills.getNombre());
        skills.setPorcentaje(dtoSkills.getPorcentaje());

        serviceSkills.save(skills);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!serviceSkills.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este ID no existe"), HttpStatus.BAD_REQUEST);
        }

        serviceSkills.delete(id);

        return new ResponseEntity(new Mensaje("Skill eliminada"), HttpStatus.OK);
    }
}
