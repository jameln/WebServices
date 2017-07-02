package com.miniprojet.springboot.service;

import com.miniprojet.springboot.domain.Etudiant;
import com.miniprojet.springboot.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    private EtudiantRepository etudiantRepository;
    
    @Autowired
    public void setEtudiantRepository(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }
    
    @Override
    public Iterable<Etudiant> getAllEtudiants() {
        return this.etudiantRepository.findAll();
    }
    
    @Override
    public Etudiant getEtudiantById(Integer id) {
        return this.etudiantRepository.findOne(id);
    }
    
    @Override
    public Etudiant saveEtudiant(Etudiant etudiant) {
        return this.etudiantRepository.save(etudiant);
    }
    
    @Override
    public void deleteEtudiant(Integer id) {
        this.etudiantRepository.delete(id);
    }
    
}
