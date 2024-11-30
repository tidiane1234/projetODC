package com.djessoretech.view;

import com.djessoretech.controllers.PersonneControllerImplement;
import com.djessoretech.model.Personne;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class PersonneView {

    private final Scanner sc = new Scanner(System.in);
    private final PersonneControllerImplement controller = new PersonneControllerImplement();

    public void addPerson(){
        System.out.println("Ajouter une personne");

        Personne personne = create();
        controller.add(personne);
    }

    public void showPerson() {
        List<Personne> personnes = controller.findAll();
        if (personnes.isEmpty()) {
            System.out.println("La liste est vide");
            return;
        }

        System.out.println(" ------ La liste des personnes -----------");
        for (Personne personne : personnes) {
            System.out.println(personne.getId() + " - " + personne.getNom() + " " + personne.getPrenom() + " " + personne.getDateNaissance() + " " + personne.getTelephone());
        }
    }

    public void modifyPerson() {
        System.out.println("Modification des informations d'une personne");
        showPerson();

        System.out.print("Veuillez sélectionner l'ID de la personne à modifier : ");
        int choix = sc.nextInt();
        sc.nextLine();

        //  la personne existante
        Personne personneExistante = controller.searchById(choix);
        if (personneExistante == null) {
            System.out.println("Erreur : Aucune personne trouvée avec cet ID");
            return;
        }

        System.out.println("Laissez un champ vide si vous ne voulez pas le modifier le champ");

        System.out.print("Nom (" + personneExistante.getNom() + "): ");
        String nom = sc.nextLine();
        if (!nom.isEmpty()) {
            personneExistante.setNom(nom);
        }

        System.out.print("Prénom (" + personneExistante.getPrenom() + "): ");
        String prenom = sc.nextLine();
        if (!prenom.isEmpty()) {
            personneExistante.setPrenom(prenom);
        }

        System.out.print("Date de naissance (" +
                (personneExistante.getDateNaissance() != null
                        ? new SimpleDateFormat("dd/MM/yyyy").format(personneExistante.getDateNaissance())
                        : "Non renseignée") +
                ") : ");
        String dateNaissanceFr = sc.nextLine();
        if (!dateNaissanceFr.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
                dateFormat.setLenient(false);
                Date dateNaissance = dateFormat.parse(dateNaissanceFr);
                personneExistante.setDateNaissance(dateNaissance);
            } catch (ParseException e) {
                System.out.println("Erreur : Le format de la date est incorrect. Les modifications de la date ont été ignorées.");
            }
        }

        System.out.print("Téléphone (" + personneExistante.getTelephone() + "): ");
        String telephone = sc.nextLine();
        if (!telephone.isEmpty()) {
            personneExistante.setTelephone(telephone);
        }

        controller.update(choix, personneExistante);
        System.out.println("Les modifications ont été enregistrées avec succès.");
    }



    public void deletePerson() {
        System.out.println("Suppression d'une personne");
        showPerson();

        System.out.print("Veuillez entrer l'ID de la personne à supprimer : ");
        int id;
        try {
            id = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Erreur : Veuillez entrer un ID valide.");
            sc.nextLine();
            return;
        }


        Personne personne = controller.searchById(id);

        //confirmer la suppression
        System.out.println("Êtes-vous sûr de vouloir supprimer la personne suivante ? (Oui/Non)");
        System.out.println(personne.getId() + " - " + personne.getNom() + " " + personne.getPrenom());
        String confirmation = sc.nextLine();

        if (confirmation.equalsIgnoreCase("Oui")) {
            controller.delete(id);
            System.out.println("La personne a été supprimée avec succès.");
        } else {
            System.out.println("Suppression annulée.");
        }
    }




    private Personne create(){
        System.out.print("Nom: ");
        String nom = sc.nextLine();

        System.out.print("prenom: ");
        String prenom = sc.nextLine();

        System.out.print("Date de naissance (format dd/MM/yyyy) : ");
        String dateNaissanceFr = sc.nextLine();
        Date dateNaissance = null;

        if (!dateNaissanceFr.isEmpty()) {
            try {
                // Utilisation  pour le format français
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
                dateFormat.setLenient(false);
                dateNaissance = dateFormat.parse(dateNaissanceFr);
            } catch (ParseException e) {
                System.out.println("Erreur : Le format de la date est incorrect. Utilisez le format dd/MM/yyyy.");
                return null;
            }
        }

        if (dateNaissance != null) {
            SimpleDateFormat displayFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        } else {
            System.out.println("Date de naissance non renseignée.");
        }

        System.out.print("telephone: ");
        String telephone = sc.nextLine();

        if (nom.isEmpty() || prenom.isEmpty() || telephone.isEmpty()) {
            System.out.println("Erreur : Tous les champs sont obligatoires.");
            return null;
        }


        return new Personne(nom,prenom,dateNaissance,telephone);
    }
}
