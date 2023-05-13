/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.argprograma.Controller;

import com.portfolio.argprograma.Dto.DtoProyectos;
import com.portfolio.argprograma.Entity.Proyectos;
import com.portfolio.argprograma.Security.Controller.Mensaje;
import com.portfolio.argprograma.Service.ServiceProyectos;
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
@RequestMapping("/proyectos")
@CrossOrigin(origins = {"http://localhost:4200", "https://portfolio-argprograma-jcm.web.app"})
public class ControllerProyectos {
    
    @Autowired
    ServiceProyectos serviceProyectos;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list() {
        List<Proyectos> list = serviceProyectos.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id) {
        if (!serviceProyectos.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }

        Proyectos proyectos = serviceProyectos.getOne(id).get();
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }

    @PostMapping("/create")
    public String createProyectos(@RequestBody Proyectos proyectos) {
        serviceProyectos.save(proyectos);
        return "El proyecto fue creado correctamente";
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProyectos dtoProyectos) {
        if (!serviceProyectos.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if (serviceProyectos.ExistsByNombreProyecto(dtoProyectos.getNombre()) && serviceProyectos.getByNombreProyecto(dtoProyectos.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoProyectos.getNombre())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Proyectos proyectos = serviceProyectos.getOne(id).get();

        proyectos.setNombre(dtoProyectos.getNombre());
        proyectos.setDescripcion(dtoProyectos.getDescripcion());
        proyectos.setImagen(dtoProyectos.getImagen());

        serviceProyectos.save(proyectos);

        return new ResponseEntity(new Mensaje("Proyectos actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!serviceProyectos.existsById(id))
            return new ResponseEntity(new Mensaje("Este ID no existe"), HttpStatus.BAD_REQUEST);
        
        serviceProyectos.delete(id);
        
        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }

}
