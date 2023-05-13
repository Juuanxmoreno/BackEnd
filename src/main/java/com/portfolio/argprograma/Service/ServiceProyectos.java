/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.argprograma.Service;

import com.portfolio.argprograma.Entity.Proyectos;
import com.portfolio.argprograma.Repository.RepositoryProyectos;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServiceProyectos {
    
    @Autowired
    RepositoryProyectos repositoryProyectos;
    
    public List<Proyectos> list() {
        return repositoryProyectos.findAll();
    }

    public Optional<Proyectos> getOne(int id) {
        return repositoryProyectos.findById(id);
    }

    public Optional<Proyectos> getByNombreProyecto(String nombre) {
        return repositoryProyectos.findByNombre(nombre);
    }

    public void save(Proyectos proyectos) {
        repositoryProyectos.save(proyectos);
    }

    public void delete(int id) {
        repositoryProyectos.deleteById(id);
    }

    public boolean existsById(int id) {
        return repositoryProyectos.existsById(id);
    }

    public boolean ExistsByNombreProyecto(String nombre) {
        return repositoryProyectos.existsByNombre(nombre);
    }

}
