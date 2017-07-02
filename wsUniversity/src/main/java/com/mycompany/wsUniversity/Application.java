/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wsUniversity;
import com.mycompany.wsUniversity.domain.Post;
import com.mycompany.wsUniversity.domain.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author root
 */
@SpringBootApplication
@EnableEurekaClient
@RestController

public class Application implements CommandLineRunner{
    
   
    @Autowired
  private PostRepository repository;
    
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
       
           repository.save(new Post("Nefzi"," Jamel","07377830","M2P2L DL","ISI Ariana"));
       
    }
}
