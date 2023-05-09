/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.argprograma.Controller;

import com.portfolio.argprograma.Dto.DtoEducacion;
import com.portfolio.argprograma.Entity.Educacion;
import com.portfolio.argprograma.Security.Controller.Mensaje;
import com.portfolio.argprograma.Service.ServiceEducacion;
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
@RequestMapping("/educacion")
@CrossOrigin(origins = {"http://localhost:4200", "https://portfolio-argprograma-jcm.web.app"})
public class ControllerEducacion {

    @Autowired
    ServiceEducacion serviceEducacion;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = serviceEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!serviceEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        serviceEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Educación eliminada"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        if(!serviceEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = serviceEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoEducacion) {
        if (StringUtils.isBlank(dtoEducacion.getNombreEducacion())) {
            return new ResponseEntity(new Mensaje("Este campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoEducacion.getDescripcionEducacion())) {
            return new ResponseEntity(new Mensaje("Este campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (serviceEducacion.existsByNombreEducacion(dtoEducacion.getNombreEducacion())) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = new Educacion(dtoEducacion.getNombreEducacion(), dtoEducacion.getDescripcionEducacion());

        serviceEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Educacion creada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoEducacion dtoEducacion) {
        if (!serviceEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if (serviceEducacion.existsByNombreEducacion(dtoEducacion.getNombreEducacion()) && serviceEducacion.getByNombreEducacion(dtoEducacion.getNombreEducacion()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese Nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (serviceEducacion.existsByDescripcionEducacion(dtoEducacion.getDescripcionEducacion()) && serviceEducacion.getByDescripcionEducacion(dtoEducacion.getDescripcionEducacion()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa descripcion ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoEducacion.getNombreEducacion())){
            return new ResponseEntity(new Mensaje("El campo no puede estar en blanco"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = serviceEducacion.getOne(id).get();
        
        educacion.setNombreEducacion(dtoEducacion.getNombreEducacion());
        educacion.setDescripcionEducacion(dtoEducacion.getDescripcionEducacion());
        
        serviceEducacion.save(educacion);
        
        return new ResponseEntity(new Mensaje("Se actualizó la educacion"), HttpStatus.OK);
    }
}
