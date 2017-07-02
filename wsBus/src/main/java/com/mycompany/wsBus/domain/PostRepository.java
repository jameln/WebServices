/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wsBus.domain;



import javax.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author root
 */
@org.springframework.stereotype.Repository
@Table(name = "user")       
public interface PostRepository extends JpaRepository<Post, Long>{
    
}
