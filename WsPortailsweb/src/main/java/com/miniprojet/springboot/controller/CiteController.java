package com.miniprojet.springboot.controller;

import com.miniprojet.springboot.domain.Cite;
import com.miniprojet.springboot.service.CiteService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@EnableDiscoveryClient
@RequestMapping("/cites")
public class CiteController {
    private CiteService citeService;

    @Autowired
    public void setCiteService(CiteService CiteService) {
        this.citeService = CiteService;
    }
    
    @RequestMapping(value = { "", "/" })
    public String index(Model model) {
        model.addAttribute("activePage", "cites");
        model.addAttribute("cites", this.citeService.getAllCites());
        return "cites/index";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET , produces = "application/json")
    public String addCiteForm(Cite cite, Model model) {
        model.addAttribute("activePage", "cites");
        return "cites/add";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST , produces = "application/json")
    public String addCite(@Valid Cite cite, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "cites");
            return "cites/add";
        }
        
        this.citeService.saveCite(cite);
        return "redirect:/confirmation";
    }
    
    @RequestMapping(value = "/view/{id}")
    public String viewCite(@PathVariable Integer id, Model model) {
        model.addAttribute("cite", this.citeService.getCiteById(id));
        model.addAttribute("activePage", "cites");
        return "cites/view";
    }
    
    @RequestMapping(value = "/edit/{id}")
    public String editCite(@PathVariable Integer id, Model model) {
        model.addAttribute("cite", this.citeService.getCiteById(id));
        model.addAttribute("activePage", "cites");
        return "cites/edit";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateCite(Cite cite) {
        System.out.println("Cite ID: " + cite.getId());
        this.citeService.saveCite(cite);
        return "redirect:/cite/view/" + cite.getId();
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteCite(@PathVariable Integer id) {
        this.citeService.deleteCite(id);
        return "redirect:/cites";
    }
    
}
