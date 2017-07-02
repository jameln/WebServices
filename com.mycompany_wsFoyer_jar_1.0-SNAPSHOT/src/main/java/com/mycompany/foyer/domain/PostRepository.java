/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.foyer.domain;



import com.mycompany.foyer.domain.Post;
import javax.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 *
 * @author root
 */
@org.springframework.stereotype.Repository
@Table(name = "user")       
public interface PostRepository extends JpaRepository<Post, Long>{
    
}
