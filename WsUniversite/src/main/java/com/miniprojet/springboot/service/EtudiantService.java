package com.miniprojet.springboot.service;

import com.miniprojet.springboot.domain.Etudiant;

public interface EtudiantService {
    Iterable<Etudiant> getAllEtudiants();
    Etudiant getEtudiantById(Integer id);
    Etudiant saveEtudiant(Etudiant etudiant);
    void deleteEtudiant(Integer id);
}
