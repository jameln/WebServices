package com.miniprojet.springboot.service;

import com.miniprojet.springboot.domain.Cite;
import com.miniprojet.springboot.repository.CiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CiteServiceImpl implements CiteService {

    private CiteRepository citeRepository;
    
    @Autowired
    public void setCiteRepository(CiteRepository citeRepository) {
        this.citeRepository = citeRepository;
    }
    
    @Override
    public Iterable<Cite> getAllCites() {
        return this.citeRepository.findAll();
    }
    
    @Override
    public Cite getCiteById(Integer id) {
        return this.citeRepository.findOne(id);
    }
    
    @Override
    public Cite saveCite(Cite cite) {
        return this.citeRepository.save(cite);
    }
    
    @Override
    public void deleteCite(Integer id) {
        this.citeRepository.delete(id);
    }
    
}
