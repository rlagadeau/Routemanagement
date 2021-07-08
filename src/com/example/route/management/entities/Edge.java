package com.example.route.management.entities;

public class Edge {

    // Datamembers van de Edge class.
    public int srcVert;
    public int destVert;
    public int distance;

    // Constructor van de Edge class.
    public Edge(int srcVert, int destVert, int distance) {
        this.srcVert = srcVert;
        this.destVert = destVert;
        this.distance = distance;
    }

}
