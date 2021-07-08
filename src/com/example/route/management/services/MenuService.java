package com.example.route.management.services;

import com.example.route.management.repositories.WijkRepositories;

import java.util.Scanner;

public class MenuService {

    int menuWelkom = 0;
    WijkRepositories wijken = new WijkRepositories();

    // Onderstaand is een method voor het oproepen van de menu.
    public void menu(){

        Scanner menuInput = new Scanner(System.in);

        if(menuWelkom == 0) {

            // Onderstaand worden Vertices toegevoegd.
            wijken.addVertex("Beekhuizen", 1450, 8);
            wijken.addVertex("Blauwgrond", 1644, 12);
            wijken.addVertex("Centrum", 2201, 19);
            wijken.addVertex("Flora", 1833, 14);
            wijken.addVertex("Latour", 1987, 21);
            wijken.addVertex("Livorno", 1326, 6);

            // Onderstaand worden edges toegevoegd.
            // De weight stelt kilometers voor.
            wijken.addEdge(0, 1, 8);  // Beekhuizen-Blauwgrond  8
            wijken.addEdge(0, 3, 7);  // Beekhuizen-Flora  7
            wijken.addEdge(1, 2, 5); // Blauwgrond-Centrum 5
            wijken.addEdge(1, 3, 3);  // Blauwgrond-Flora  3
            wijken.addEdge(1, 4, 8);  // Blauwgrond-Latour  8
            wijken.addEdge(2, 3, 9);  // Centrum-Flora  9
            wijken.addEdge(2, 4, 3);  // Centrum-Latour  3
            wijken.addEdge(2, 5, 7);  // Centrum-Livorno  7
            wijken.addEdge(3, 4, 4); // Flora-Latour 4
            wijken.addEdge(4, 5, 6);  // Latour-Livorno  6


            System.out.println("--- Welkom bij het menu scherm van het Route management systeem ---");
            System.out.println("\r");
        }

        menuWelkom++;

        System.out.println("Klik één van de onderstaande cijfers om de operatie uit te voeren.");
        System.out.println("1. Overzicht van alle wijken/ressorten in het district Paramaribo.");
        System.out.println("2. Overzicht verbonden wijken in Paramaribo.");
        System.out.println("3. Overzicht van de snelste/korste route tussen de wijken in Paramaribo.");
        System.out.println("4. Het in kaart(toevoegen) brengen van een wijk/ressort in Paramaribo.");
        System.out.println("5. Zoek naar alle wijken startende vanaf wijk Beekhuizen (Methode 1).");
        System.out.println("6. Zoek naar alle wijken startende vanaf wijk Beekhuizen (Methode 2).");

        System.out.println("\r");
        System.out.println("Klik één van de onderstaande cijfers om de (rapportage) daarvan te bezichtigen.");
        System.out.println("7. Aantal wijken/ressorten in Paramaribo voor het leveren van brood en bollen.");
        System.out.println("8. Aantal wegen tussen de wijken/ressorten in Paramaribo voor het leveren van brood en bollen.");
        System.out.println("9. De afstand in kilometers tussen de wijken/ressorten in Paramaribo voor het leveren van brood en bollen.");
        System.out.println("\r");
        System.out.println("Klik het onderstaande cijfer om uit te loggen en het programma af te sluiten.");
        System.out.println("10. Afsluiten.");
        System.out.println("\r");
        System.out.println("Hier voert u het cijfer in:");
        int operatie = menuInput.nextInt();

        switch(operatie){
            case 1:
                wijken.displayAllVertices();
                break;
            case 2:
                wijken.displayAllConnVertices();
                break;
            case 3:
                wijken.mstw();
                break;
            case 4:
                menuInput.nextLine();
                System.out.println("Voer onderstaand de wijk/ressort in.");
                String districtNaam = menuInput.nextLine();
                System.out.println("Voer onderstaand de aantal inwoners in de wijk/ressort in.");
                int aantalInwoners = menuInput.nextInt();
                System.out.println("Voer onderstaand de aantal winkels in de wijk/ressort in.");
                int aantalWinkels = menuInput.nextInt();
                System.out.println("Voer de afstand(kilometers) in vanuit de laatste wijk/ressort.");
                int afstandvlwijk = menuInput.nextInt();
                if(districtNaam.length() != 0){
                    wijken.addVertex(districtNaam,aantalInwoners,aantalWinkels);
                    wijken.addEdge(wijken.getnVerts() - 2, wijken.getnVerts() - 1, afstandvlwijk);
                    System.out.println("Wijk succesvol toegevoegd !");
                }
                break;
            case 5:
                wijken.dfs();
                break;
            case 6:
                wijken.bfs();
                 break;
            case 7:
                 wijken.numberOfVertices();
                 break;
            case 8:
                 wijken.numberOfEdges();
                 break;
            case 9:
                wijken.numberOfKm();
                break;
            case 10:
                System.exit(1);
                break;
            default:
                System.out.println("Operatie niet succesvol. Klik het cijfer 0 om het menu scherm opnieuw op te starten.");
                int redirection = menuInput.nextInt();
                if(redirection == 0){
                    menuWelkom = 0;
                    menu();
                }

        }

        System.out.println("\r");
        System.out.println("Klik het cijfer 0 om een andere operatie uit te voeren.");
        int redirection = menuInput.nextInt();
        if(redirection == 0){
            menu();
        }

    }
}
