package com.miniprojet.springboot.controller;

import com.miniprojet.springboot.domain.Etudiant;
import com.miniprojet.springboot.service.EtudiantService;
import com.miniprojet.springboot.service.EtudiantService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@EnableDiscoveryClient    
@RequestMapping("/etudiants")
 
public class EtudiantController {
    private EtudiantService etudiantService;
 
    @Autowired
    
    public void setEtudiantService(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }
  
    
      
      @RequestMapping(value = "jsonun", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Etudiant> listPosts() {        
      return (List<Etudiant>) this.etudiantService.getAllEtudiants();
    }
    
    
    
    @RequestMapping(value = { "", "/" })
    public String index(Model model) {
        model.addAttribute("activePage", "etudiants");
        model.addAttribute("etudiants", this.etudiantService.getAllEtudiants());
        return "etudiants/index";
        
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET , produces = "application/json")
    public String addEtudiantForm(Etudiant etudiant, Model model) {
        model.addAttribute("activePage", "etudiants");
        return "etudiants/add";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST , produces = "application/json")
    public String addEtudiant(@Valid Etudiant etudiant, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "etudiants");
            return "etudiants/add";
        }
        
        this.etudiantService.saveEtudiant(etudiant);
        return "redirect:/confirmation";
    }
    
    @RequestMapping(value = "/view/{id}")
    public String viewEtudiant(@PathVariable Integer id, Model model) {
        model.addAttribute("etudiant", this.etudiantService.getEtudiantById(id));
        model.addAttribute("activePage", "etudiants");
        return "etudiants/view";
    }
    
    @RequestMapping(value = "/edit/{id}")
    public String editEtudiant(@PathVariable Integer id, Model model) {
        model.addAttribute("etudiant", this.etudiantService.getEtudiantById(id));
        model.addAttribute("activePage", "etudiants");
        return "etudiants/edit";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateEtudiant(Etudiant etudiant) {
        System.out.println("Etudiant ID: " + etudiant.getId());
        this.etudiantService.saveEtudiant(etudiant);
        return "redirect:/etudiants/view/" + etudiant.getId();
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteEtudiant(@PathVariable Integer id) {
        this.etudiantService.deleteEtudiant(id);
        return "redirect:/etudiants";
    }
    
}
