package com.example.route.management.app;

import com.example.route.management.services.LoginService;

public class Application {
    public static void main(String[] args) {

        //////////////////////////////////////////
        //  Application: Route Management       //
        //  Version: 1.0.0                      //
        //////////////////////////////////////////

        // Onderstaand wordt een object aangemaakt om het inlogscherm op te roepen.
        LoginService inloggen = new LoginService();
        inloggen.login();
    }
}
