/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wsBus.controllers;

import com.mycompany.wsBus.domain.Post;
import com.mycompany.wsBus.domain.PostRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import java.util.List;
import javax.lang.model.util.Elements;
import javax.swing.text.Document;
import org.hibernate.cfg.Environment;
import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Element;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author root
 */
@Controller
@EnableDiscoveryClient
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository repository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listPosts(Model model) {
        model.addAttribute("posts", repository.findAll());

        return "posts/list";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable long id) {
        repository.delete(id);

        return new ModelAndView("redirect:/posts");
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newProject() {
        return "posts/new";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String newProjecttt() {
        return "posts/index";
    }

    /*
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Post> listPosts() {
        return repository.findAll();
    }*/
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model, @RequestParam("message") String comment, @RequestParam("cin") String cin,
            @RequestParam("niveau") String niveau) {
String msg ="";
        //test champ vide
        if (comment.toString().isEmpty() || cin.toString().isEmpty() || niveau.toString().isEmpty()) {
 msg="Il faut remplir tous les champs";
              model.addAttribute("ppp",msg );
           //return new ModelAndView("redirect:/posts");
       }
        
        
        final String uri = "http://192.168.8.5:2222/posts/jsonun";
        final String url = "http://192.168.8.5:7777/etudiants/jsonun";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("****************URI*****************");
        System.out.println(result);

        if (result.contains(cin)  && cin.matches("[0-9]+") && cin.length()==8)  {

            repository.save(new Post(comment, cin, niveau));
            msg="inscrit avec sucsuss";
            model.addAttribute("ppp", msg);
            System.out.println("--------- etudient ajouté ------------");
            return "posts/list";
        } else {
            msg="ETudient non enregister dans l'university";
              model.addAttribute("ppp",msg );
         
         //   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Etudient introuvable dans la base university."));

            System.out.println("--------- fin test ------------");
            return "posts/list";
        }
       

    }
/*@ExceptionHandler(Exception.class)
public ModelAndView globalExceptionHandler(Exception e) {
    ModelAndView modelAndView = new ModelAndView("error");
    modelAndView.addObject("message", e.getMessage());
    return modelAndView;
}*/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("post_id") long id, @RequestParam("message") String message,
            @RequestParam("cin") String cin, @RequestParam("niveau") String niveau) {
        Post post = repository.findOne(id);
        post.setNom(message);
        post.setCin(cin);
        post.setCircuit(niveau);

        repository.save(post);

        return new ModelAndView("redirect:/posts");

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("posts", repository.findAll());
        Post post = repository.findOne(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

}
