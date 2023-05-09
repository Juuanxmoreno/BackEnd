/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.argprograma.Controller;

import com.portfolio.argprograma.Dto.DtoExperiencia;
import com.portfolio.argprograma.Entity.Experiencia;
import com.portfolio.argprograma.Security.Controller.Mensaje;
import com.portfolio.argprograma.Service.ServiceExperiencia;
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
@RequestMapping("experiencia")
@CrossOrigin(origins = {"http://localhost:4200", "https://portfolio-argprograma-jcm.web.app"})
public class ControllerExperiencia {

    @Autowired
    ServiceExperiencia serviceExperiencia;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list() {
        List<Experiencia> list = serviceExperiencia.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!serviceExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = serviceExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoExperiencia dtoExperiencia) {
        if (StringUtils.isBlank(dtoExperiencia.getNombreExperiencia())) {
            return new ResponseEntity(new Mensaje("Este campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (serviceExperiencia.ExistsByNombreExperiencia(dtoExperiencia.getNombreExperiencia())) {
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        }

        Experiencia experiencia = new Experiencia(dtoExperiencia.getNombreExperiencia(), dtoExperiencia.getDescripcionExperiencia());
        serviceExperiencia.save(experiencia);

        return new ResponseEntity(new Mensaje("Experiencia añadida"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoExperiencia dtoExperiencia) {
        if (!serviceExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("Este ID no existe"), HttpStatus.BAD_REQUEST);

        if (serviceExperiencia.ExistsByNombreExperiencia(dtoExperiencia.getNombreExperiencia()) && serviceExperiencia.getByNombreExperiencia(dtoExperiencia.getNombreExperiencia()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa Experiencia ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoExperiencia.getNombreExperiencia())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Experiencia experiencia = serviceExperiencia.getOne(id).get();
        experiencia.setNombreExperiencia(dtoExperiencia.getNombreExperiencia());
        experiencia.setDescripcionExperiencia(dtoExperiencia.getDescripcionExperiencia());

        serviceExperiencia.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!serviceExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("Este ID no existe"), HttpStatus.BAD_REQUEST);
        
        serviceExperiencia.delete(id);
        
        return new ResponseEntity(new Mensaje("Experiencia eliminada"), HttpStatus.OK);
    }
}
