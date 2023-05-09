/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.argprograma.Repository;

import com.portfolio.argprograma.Entity.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryExperiencia extends JpaRepository<Experiencia, Integer> {

    public Optional<Experiencia> findByNombreExperiencia(String nombreExperiencia);

    public boolean existsByNombreExperiencia(String nombreExperiencia);
}
