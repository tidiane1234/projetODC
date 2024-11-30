package com.djessoretech.controllers;

import com.djessoretech.database.Connect;
import com.djessoretech.model.Personne;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

import java.util.List;


public class PersonneControllerImplement implements PersonneController{
    private final Session session = Connect.getSessionFactory().openSession();
    @Override
    public void add(Personne personne) {
        if (personne == null || personne.getNom() == null || personne.getNom().isEmpty()) {
            throw new RuntimeException("Une tache doit obligatoirement avoir un nom");
        }

        Transaction transaction = session.beginTransaction();
        try{
            Personne personne1 = new Personne(
                    personne.getNom(),
                    personne.getPrenom(),
                    personne.getDateNaissance(),
                    personne.getTelephone()
            );

            session.save(personne1);
            transaction.commit();
            System.out.println("la personne a ete ajouté avec succès");
        }catch (Exception e){
            transaction.rollback();
            System.out.println("Impossible d'ajouter une personne " + e.getMessage());
        }

    }

    @Override
    public List<Personne> findAll() {
        String sql = "SELECT * FROM personne";
        return session.createNativeQuery(sql, Personne.class).getResultList();
    }

    @Override
    public void update(int id, Personne personne) {
        Transaction transaction = session.beginTransaction();
        try {
            Personne personne1 = searchById(id);

            personne1.setNom(personne.getNom());
            personne1.setPrenom(personne.getPrenom());
            personne1.setDateNaissance(personne.getDateNaissance());
            personne1.setTelephone(personne.getTelephone());

            session.update(personne1);
            transaction.commit();

            System.out.println("la personne a été Modifiée avec succès");
        }catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void delete(int id) {
        Transaction transaction = session.beginTransaction();
        try {
            Personne personne =  searchById(id);
            session.delete(personne);

            transaction.commit();
            System.out.println("la personne a été supprimé avec succes");
        }catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Personne searchById(int id) {

        Personne personne = session.get(Personne.class, id);

        if (personne == null) {
            throw new RuntimeException("cette personne n'existe pas encore");
        }

        return personne;
    }


}
