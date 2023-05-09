/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.argprograma.Service;

import com.portfolio.argprograma.Entity.Skills;
import com.portfolio.argprograma.Repository.RepositorySkills;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ServiceSkills {
    @Autowired 
    RepositorySkills repositorySkills;
    
    public List<Skills> list(){
        return repositorySkills.findAll();
    }
    
    public Optional<Skills> getOne(int id){
        return repositorySkills.findById(id);
    }
    
    public Optional<Skills> getByNombre(String nombre){
        return repositorySkills.findByNombre(nombre);
    }
    
    public void save(Skills skill){
        repositorySkills.save(skill);
    }
    
    public boolean existsById(int id){
        return repositorySkills.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return repositorySkills.existsByNombre(nombre);
    }
    
    public void delete(int id){
        repositorySkills.deleteById(id);
    }
    
}
