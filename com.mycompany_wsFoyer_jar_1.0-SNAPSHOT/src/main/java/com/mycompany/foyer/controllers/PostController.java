/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.foyer.controllers;

import com.mycompany.foyer.domain.Post;
import com.mycompany.foyer.domain.PostRepository;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Autowired;

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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model, @RequestParam("message") String comment, @RequestParam("cin") String cin,
            @RequestParam("niveau") String niveau) {
String msg ="";
       
        if (comment.toString().isEmpty() || cin.toString().isEmpty() || niveau.toString().isEmpty()) {
           msg="Il faut remplir tous les champs";
              model.addAttribute("ppp",msg );
            return "posts/list";
             //return new ModelAndView("redirect:/posts");
        }
        
        
        final String uri = "http://192.168.8.5:2222/posts/jsonun";
          final String url = "http://192.168.8.5:7777/etudiants/jsonun";
      

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
      
 System.out.println(result);
        if (result.contains(cin)  && cin.matches("[0-9]+") && cin.length()==8)  {

            repository.save(new Post(comment, cin, niveau));
            msg="inscrit avec sucsuss";
            model.addAttribute("ppp", msg);
            System.out.println("--------- etudient ajouté ------------");
            return "posts/list";
            //return "redirect:/posts";
            //  return new ModelAndView("redirect:/posts");
        } else {
            msg="Etudient non enregister dans l'université";
              model.addAttribute("ppp",msg );
                     System.out.println("--------- fin test ------------");
           return "posts/error";
              //return new ModelAndView("redirect:/error");
        }
       

    }

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
