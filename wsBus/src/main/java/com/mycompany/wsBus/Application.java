/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wsBus;
import com.mycompany.wsBus.domain.Post;
import com.mycompany.wsBus.domain.PostRepository;
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
       for(int i=0;i<2;i++){
           repository.save(new Post("nefzi"," 07377830","tunis"));
       }
    }
}
