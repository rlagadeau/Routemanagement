package com.example.route.management.repositories;

import com.example.route.management.entities.Gebruikers;

import java.util.ArrayList;

public class GebruikersRepositories {

    // Onderstaand een arraylist welke gebruikers objecten zal bevatten.
    ArrayList<Gebruikers> gebruikers = new ArrayList<>();

    // Deze method maakt gebruik een linear search.
    public boolean selectgebruiker(String emailadres) {

        for (Gebruikers gebruiker : gebruikers) {
            String list_emailadres = gebruiker.getEmailadres();
            if (list_emailadres.equals(emailadres)) {
                return true;
            }
        }

        return false;
    }

    // Deze method maakt ook gebruik van een linear search.
    public boolean checkPass(int password) {

        for (Gebruikers gebruiker : gebruikers) {
            int list_password = gebruiker.getPassword();
            if (list_password == password) {
                return true;
            }
        }

        return false;
    }

    // Dit is een method voor het toevoegen van een gebruiker aan de arraylist.
    public void addgebruiker(String naam, String achternaam, int leeftijd, String emailadres, int password) {
        Gebruikers nieuwe_gebruiker = new Gebruikers(naam, achternaam, leeftijd, emailadres, password);
        gebruikers.add(nieuwe_gebruiker);
    }

}
