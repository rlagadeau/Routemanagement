package com.example.route.management.entities;

public class Gebruikers {

    private final String emailadres;
    private final int password;

    // Constructor van de Gebruikers class.
    public Gebruikers(String naam, String achternaam, int leeftijd, String emailadres, int password) {
        // Datamembers van de Gebruikers class.
        this.emailadres = emailadres;
        this.password = password;
    }

    //Onderstaand getters en setters van de datamembers om zodoende deze te kunnen accessen vanuit een andere class.

    /*public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }*/

    public String getEmailadres() {
        return emailadres;
    }

    /*public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }*/

    public int getPassword() {
        return password;
    }

    /*public void setPassword(int password) {
        this.password = password;
    }*/

}
