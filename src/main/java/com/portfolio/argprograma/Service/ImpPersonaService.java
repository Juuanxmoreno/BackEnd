package com.portfolio.argprograma.Service;

import com.portfolio.argprograma.Entity.Persona;
import com.portfolio.argprograma.Repository.IPersonaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpPersonaService {

    @Autowired
    IPersonaRepository ipersonaRepository;

    public List<Persona> list() {
        return ipersonaRepository.findAll();
    }

    public Optional<Persona> getOne(int id) {
        return ipersonaRepository.findById(id);
    }

    public Optional<Persona> getByNombrePersona(String nombrePersona) {
        return ipersonaRepository.findByNombre(nombrePersona); 
    }

    public void save(Persona persona) {
        ipersonaRepository.save(persona);
    }

    public void delete(int id) {
        ipersonaRepository.deleteById(id);
    }

    public boolean existById(int id) {
        return ipersonaRepository.existsById(id);
    }

    public boolean ExistByNombrePersona(String nombrePersona) {
        return ipersonaRepository.existsByNombre(nombrePersona);
    }

}
