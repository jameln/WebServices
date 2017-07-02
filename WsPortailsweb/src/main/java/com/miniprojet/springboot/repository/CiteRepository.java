package com.miniprojet.springboot.repository;

import com.miniprojet.springboot.domain.Cite;
import org.springframework.data.repository.CrudRepository;

public interface CiteRepository extends CrudRepository<Cite, Integer>{
    
}
