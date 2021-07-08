package com.example.route.management.services;

import com.example.route.management.repositories.GebruikersRepositories;

import java.util.Scanner;
import java.util.regex.Pattern;

public class LoginService {

    // Hier wordt een object aangemaakt van de GebruikersRepositories class.
    // Met de beschikbare methods kunnen er gebruikers gezocht en toegevoegd worden.
    GebruikersRepositories gebruiker = new GebruikersRepositories();

    // Deze method roept het inlogscherm op.
    public void login() {

        // Onderstaand worden gebruikers toegevoegd onload (start).
        gebruiker.addgebruiker("John", "Doe", 24, "john.doe@example.com", 12345678);
        gebruiker.addgebruiker("Jimmy", "Doe", 25, "jimmy.doe@example.com", 12345678);

        // Dit is een scanner object om gebruikers input te ontvangen.
        Scanner input = new Scanner(System.in);

        System.out.println("--- Welkom bij het Route Management systeem ---");
        System.out.println("\r");
        System.out.println("--- Gebruik voor deze console app het emailadres: john.doe@example.com en het wachtwoord: 12345678 ---");
        System.out.println("\r");
        System.out.println("--- Login ---");
        System.out.println("Voer uw emailadress in:");
        String emailadres = input.nextLine();
        if(isValide(emailadres)){
            boolean emailCheck = gebruiker.selectgebruiker(emailadres.toLowerCase());

            if(emailCheck) {
                System.out.println("Voer uw password in:");
                int password = input.nextInt();
                if(password != 0){
                    boolean passwordCheck = gebruiker.checkPass(password);

                    if(passwordCheck) {
                        System.out.println("Succesvolle Authenticatie !");
                        System.out.println("\r");
                        MenuService menuscherm = new MenuService();
                        menuscherm.menu();
                    }else {
                        System.out.println("Authenticatie error. Probeer het opnieuw door het cijfer 1 in te voeren.");
                        int redirection = input.nextInt();
                        if(redirection == 1){
                            login();
                        }
                    }
                }
            }else {
                System.out.println("\r");
                System.out.println("U komt niet voor in het systeem. Klik onderstaand het cijfer 1 voor het opnieuw uitvoeren van de authenticatie of cijfer 2 voor het registreren.");
                int redirection = input.nextInt();
                if(redirection == 1){
                    login();
                }else if(redirection == 2) {
                    registreren();
                }else{
                    System.out.println("Uw input is ongeldig !");
                }
            }
        } else{
            System.out.println("Uw input is ongeldig. Klik onderstaand het cijfer 1 voor het opnieuw uitvoeren van de authenticatie.");
            int redirection = input.nextInt();
            if(redirection == 1){
                login();
            }else{
                System.out.println("Uw input is ongeldig !");
            }
        }

    }

    // Bij het oproepen van deze method kan een nieuwe gebruiker zich registreren.
    public void registreren() {

        // Dit is een scanner object om gebruikers input te ontvangen.
        Scanner input = new Scanner(System.in);

        System.out.println("\r");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Registreer u om toegang te verkrijgen tot het Route Management systeem.");
        System.out.println("\r");
        System.out.println("--- Registratie ---");

        // Onderstaand zal de voornaam ingevuld moeten worden.
        System.out.println("Voer uw naam in:");
        String naam = input.nextLine();
        if(naam.length() == 0) {
            System.out.println("Uw naam kan niet leeg zijn");
            return;
        }

        // Onderstaand zal de achternaam ingevuld moeten worden.
        System.out.println("Voer uw Achternaam in:");
        String Achternaam = input.nextLine();
        if (Achternaam.length() == 0) {
            System.out.println("Uw achternaam kan niet leeg zijn");
            return;
        }

        // Onderstaand zal de leeftijd ingevuld moeten worden.
        System.out.println("Voer uw leeftijd in:");
        int leeftijd = input.nextInt();
        if (leeftijd == 0) {
            System.out.println("Uw leeftijd kan niet leeg zijn");
            return;
        }

        input.nextLine();
        // Onderstaand zal de e-mailadres ingevuld moeten worden.
        System.out.println("Voer uw e-mailadres in:");
        String emailadres = input.nextLine();
        if (emailadres.length() == 0) {
            System.out.println("Uw e-mailadres kan niet leeg zijn");
            return;
        }

        // Onderstaand zal de password ingevuld moeten worden.
        System.out.println("Voer uw password in:");
        int password = input.nextInt();
        if (password == 0) {
            System.out.println("Uw password kan niet leeg zijn");
            return;
        }

        // Onderstaand wordt de nieuwe gebruiker toegevoegd.
        gebruiker.addgebruiker(naam, Achternaam, leeftijd, emailadres, password);
        System.out.println("Uw registratie is succesvol !");
        System.out.println("Klik onderstaand het cijfer 1 om naar het inlog scherm te gaan.");
        int redirection = input.nextInt();
        if(redirection == 1) {
            login();
        }

    }

    // Deze method valideert als een correcte e-mailadres is ingevoerd.
    public boolean isValide(String emailadres) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (emailadres == null)
            return false;

        return pat.matcher(emailadres).matches();
    }

}
