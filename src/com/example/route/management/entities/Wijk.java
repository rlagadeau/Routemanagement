package com.example.route.management.entities;

// Deze class stelt in technische termen de Vertex class voor.
public class Wijk {

    // Datamembers van de Wijk class.
    public String naam;
    public int inwoners;
    public int winkels;
    public boolean isInTree;
    public boolean wasVisited;

    // Constructor van de Wijk class.
    public Wijk(String naam, int inwoners, int winkels) {
        this.naam = naam;
        this.isInTree = false;
        this.wasVisited = false;
        this.inwoners = inwoners;
        this.winkels = winkels;
    }

}
