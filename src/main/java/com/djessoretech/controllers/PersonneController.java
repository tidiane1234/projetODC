package com.djessoretech.controllers;

import com.djessoretech.model.Personne;

import java.util.List;


public interface PersonneController {

    void add(Personne personne);

    public List<Personne> findAll() ;

    void update(int id, Personne personne);

    void delete(int id);

    Personne searchById(int id);

}
