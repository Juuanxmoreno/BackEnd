/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.argprograma.Service;

import com.portfolio.argprograma.Entity.Educacion;
import com.portfolio.argprograma.Repository.RepositoryEducacion;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServiceEducacion {

    @Autowired
    RepositoryEducacion repositoryEducacion;

    public List<Educacion> list() {
        return repositoryEducacion.findAll();
    }

    public Optional<Educacion> getOne(int id) {
        return repositoryEducacion.findById(id);
    }

    public Optional<Educacion> getByNombreEducacion(String nombreEducacion) {
        return repositoryEducacion.findByNombreEducacion(nombreEducacion);
    }

    public Optional<Educacion> getByDescripcionEducacion(String descripcionEducacion) {
        return repositoryEducacion.findByDescripcionEducacion(descripcionEducacion);
    }

    public void save(Educacion educacion) {
        repositoryEducacion.save(educacion);
    }

    public void delete(int id) {
        repositoryEducacion.deleteById(id);
    }

    public boolean existsById(int id) {
        return repositoryEducacion.existsById(id);
    }

    public boolean existsByNombreEducacion(String nombreEducacion) {
        return repositoryEducacion.existsByNombreEducacion(nombreEducacion);
    }

    public boolean existsByDescripcionEducacion(String descripcionEducacion) {
        return repositoryEducacion.existsByDescripcionEducacion(descripcionEducacion);
    }
}
