package com.djessoretech;

import com.djessoretech.view.PersonneView;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        PersonneView view = new PersonneView();
        view.addPerson();
        System.out.printf("-------------------------------------");
        view.modifyPerson();
        view.showPerson();
        System.out.printf("-------------------------------------");
        view.deletePerson();

    }
}

        git config --global user.email "tidianeb163@gmail.com"