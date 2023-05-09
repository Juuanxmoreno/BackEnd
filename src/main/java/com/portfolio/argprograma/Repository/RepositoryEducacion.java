/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.argprograma.Repository;

import com.portfolio.argprograma.Entity.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryEducacion extends JpaRepository<Educacion, Integer> {

    public Optional<Educacion> findByNombreEducacion(String nombreEducacion);

    public Optional<Educacion> findByDescripcionEducacion(String descripcionEducacion);

    public boolean existsByNombreEducacion(String nombreEducacion);

    public boolean existsByDescripcionEducacion(String descripcionEducacion);
}
