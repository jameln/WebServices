package com.miniprojet.springboot.service;

import com.miniprojet.springboot.domain.Cite;

public interface CiteService {
    Iterable<Cite> getAllCites();
    Cite getCiteById(Integer id);
    Cite saveCite(Cite cite);
    void deleteCite(Integer id);
}
