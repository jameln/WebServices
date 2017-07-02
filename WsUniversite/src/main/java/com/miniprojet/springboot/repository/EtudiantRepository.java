package com.miniprojet.springboot.repository;

import com.miniprojet.springboot.domain.Etudiant;
import org.springframework.data.repository.CrudRepository;

public interface EtudiantRepository extends CrudRepository<Etudiant, Integer>{
    
}
