package com.djessoretech.model;

import java.time.LocalDate;
import java.util.Date;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "personne")
@NoArgsConstructor
public class Personne {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "dateNaissance")
    private Date dateNaissance;

    @Column(name = "telephone")
    private String telephone;



    public Personne(String nom, String prenom, Date dateNaissance, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return  "id : " + id + '\'' +
                "nom= : " + nom  + '\''+
                "prenom : " + prenom + '\'' +
                ", dateNaissance : " + dateNaissance + '\'' +
                ", telephone : " + telephone + '\'';
    }
}
