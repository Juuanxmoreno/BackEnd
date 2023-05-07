/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.argprograma.Repository;

import com.portfolio.argprograma.Entity.Skills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositorySkills extends JpaRepository<Skills, Integer>{
    public Optional<Skills> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
