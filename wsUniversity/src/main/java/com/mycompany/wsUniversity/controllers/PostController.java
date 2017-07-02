/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wsUniversity.controllers;


import com.mycompany.wsUniversity.domain.Post;
import com.mycompany.wsUniversity.domain.PostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    
    @RequestMapping(value="",method =RequestMethod.GET)
    public String listPosts(Model model){
        model.addAttribute("posts", repository.findAll());
        
        return "posts/list";
    }
    
     @RequestMapping(value="/{id}/delete",method =RequestMethod.GET)
    public ModelAndView delete(@PathVariable long id){
        repository.delete(id);
        
        return new ModelAndView("redirect:/posts");
    }
    
    @RequestMapping(value="/new", method =RequestMethod.GET)
    public String newProject(){
        return "posts/new";
    }
    
      @RequestMapping(value="/index", method =RequestMethod.GET)
    public String newProjecttt(){
        return "posts/index";
    }
     @RequestMapping(value = "jsonun", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Post> listPosts() {        
        return (List<Post>) repository.findAll();
    }
    
     @RequestMapping(value="/create",method =RequestMethod.POST)
    public ModelAndView create(@RequestParam("message") String comment,@RequestParam("prenom") String pren,
            @RequestParam("cin") String cin,@RequestParam("niveau") String niveau,@RequestParam("univer") String univer ){
        
        repository.save(new Post(comment,pren,cin,niveau,univer));
        
        return new ModelAndView("redirect:/posts");
    }
    
      @RequestMapping(value="/update",method =RequestMethod.POST)
    public ModelAndView update(@RequestParam("post_id") long id,@RequestParam("message") String message,
            @RequestParam("prenom") String pren,
            @RequestParam("cin") String cin,@RequestParam("niveau") String niveau,@RequestParam("univer") String univer){
        Post post =repository.findOne(id);
        post.setMessage(message);
        post.setPrenom(pren);
        post.setCin(cin);
        post.setNiveau(niveau);
        post.setUniver(univer);
        repository.save(post);
        
        return new ModelAndView("redirect:/posts");
        
    }
    
     @RequestMapping(value="/{id}/edit",method =RequestMethod.GET)
    public String edit(@PathVariable long id,Model model){
        model.addAttribute("posts", repository.findAll());
          Post post =repository.findOne(id);
          model.addAttribute("post", post);
        return "posts/edit";
    }
}
