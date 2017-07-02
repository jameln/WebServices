package com.miniprojet.springboot.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity()
@Table(name = "cite")
public class Cite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
     @Size(min = 2, message = "cite must be at least 2 characters.")
     
    private Integer cite;
     @Size(min = 2, message = "cite must be at least 2 characters.")
    private Integer id_etudiant;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCite() {
        return cite;
    }

    public void setCite(Integer cite) {
        this.cite = cite;
    }

    public Integer getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(Integer id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

}
