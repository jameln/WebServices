/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.foyer;
import com.mycompany.foyer.domain.Post;
import com.mycompany.foyer.domain.PostRepository;
import com.netflix.discovery.EurekaClient;
import javax.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
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
