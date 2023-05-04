/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.argprograma.Service;

import com.portfolio.argprograma.Entity.Experiencia;
import com.portfolio.argprograma.Repository.RepositoryExperiencia;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServiceExperiencia {
    @Autowired
    RepositoryExperiencia repositoryExperiencia;
    
    public List<Experiencia> list() {
        return repositoryExperiencia.findAll();
    }
    
    public Optional<Experiencia> getOne(int id){
        return repositoryExperiencia.findById(id);
    }
    
    public Optional<Experiencia> getByNombreExperiencia(String nombreExperiencia){
        return repositoryExperiencia.findByNombreExperiencia(nombreExperiencia);
    }
    
    public void save(Experiencia experiencia){
        repositoryExperiencia.save(experiencia);
    }
    
    public void delete(int id){
        repositoryExperiencia.deleteById(id);
    }
    
    public boolean existById(int id){
        return repositoryExperiencia.existsById(id);
    }
    
    public boolean ExistByNombreExperiencia(String nombreExperiencia){
        return repositoryExperiencia.existsByNombreExperiencia(nombreExperiencia);
    }
}
