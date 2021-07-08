package com.example.route.management.repositories;

import com.example.route.management.entities.*;

public class WijkRepositories {

    // Onderstaand Stack en Queue objecten.
    Stack theStack = new Stack();
    Queue theQueue = new Queue(50);

    private final int INFINITY = 1000000;
    private final Wijk[] vertexList;       // lijst met vertices
    private final int[][] adjMat;         // adjacency matrix
    private int nVerts;            // aantal vertices
    private int currentVert;
    private final PriorityQ thePQ;

    // Constructor van de WijkRepositories class.
    public WijkRepositories() {
        // Datamembers van de WijkRepositories class.
        int MAX_VERTS = 20;
        vertexList = new Wijk[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];      // adjacency matrix
        nVerts = 0;
        // adjacency bepalen
        for(int j = 0; j< MAX_VERTS; j++) {
            // matrix zetten naar 0.
            for (int k = 0; k < MAX_VERTS; k++) {
                adjMat[j][k] = INFINITY;
            }
        }

        thePQ = new PriorityQ();
    }

    // Getter van de nVerts datamember.
    public int getnVerts() { return nVerts; }

    // Onderstaand een method om vertices toe te voegen.
    public void addVertex(String naam, int inwoners, int winkels) {
        vertexList[nVerts++] = new Wijk(naam, inwoners, winkels);
    }

    // Onderstaand een method om Edges toe te voegen.
    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = weight;
        adjMat[end][start] = weight;
    }

    // Onderstaand een method om een Vertex te bezichtigen.
    public void displayVertex(int v) {
        System.out.print(vertexList[v].naam);
    }

    // Onderstaand een minimum spanning tree method.
    public void mstw() {
        currentVert = 0;        // start bij 0
        // aantal vertices in de tree
        int nTree = 0;

        // Zolang alle vertices niet in de tree zijn.
        while(nTree < nVerts-1) {
            // Huidige vertex in de tree opslaan.
            vertexList[currentVert].isInTree = true;
            nTree++;

            // Edges toevoegen in PQ als ze adjacent zijn met de huidige vertex.
            // Voor elke vertex,
            for (int j = 0; j < nVerts; j++) {
                // Overslaan als het gelijk is aan de huidige(current) vertex.
                if (j == currentVert) {
                    continue;
                }

                // Overslaan als het in de tree voorkomt.
                if (vertexList[j].isInTree) {
                    continue;
                }

                int distance = adjMat[currentVert][j];
                // Overslaan als er geen edge is.
                if (distance == INFINITY) {
                    continue;
                }

                // In PQ plaatsen.
                putInPQ(j, distance);
            }

            // Geen vertices in PQ
            if (thePQ.size() == 0) {
                System.out.println("GRAPH NIET VERBONDEN");
                return;
            }

            // Edge met minimum afstand verwijderen van PQ.
            Edge theEdge = thePQ.removeMin();
            int sourceVert = theEdge.srcVert;
            currentVert = theEdge.destVert;

            // Edge bezichtigen vanaf begin(source) tot huidige(current).
            System.out.print("("+vertexList[sourceVert].naam+"-"+vertexList[currentVert].naam+")");
            System.out.print(" ");

        }

        System.out.println("\r");

        // mst is compleet
        // vertices demarkeren
        for(int j=0; j<nVerts; j++) {
            vertexList[j].isInTree = false;
        }

    }


    public void putInPQ(int newVert, int newDist) {
        // Nagaan als er een andere edge is met dezelfde bestemming vertex.
        int queueIndex = thePQ.find(newVert);

        if(queueIndex != -1) {
            // Edge ophalen.
            Edge tempEdge = thePQ.peekN(queueIndex);
            int oldDist = tempEdge.distance;

            // als nieuwe edge korter is,
            if(oldDist > newDist) {
                // Oude edge verwijderen.
                thePQ.removeN(queueIndex);
                Edge theEdge = new Edge(currentVert, newVert, newDist);
                // nieuwe edge toevoegen.
                thePQ.insert(theEdge);
            }

        } else {      // Geen edge met zelfde bestemming vertex.
            // Nieuwe toevoegen.
            Edge theEdge = new Edge(currentVert, newVert, newDist);
            thePQ.insert(theEdge);
        }
    }


    // Een method om alle verbonden wijken in Paramaribo te laten zien.
    public void displayAllConnVertices(){
        for(int start = 0; start < nVerts; start++) {
            for (int i = 0; i < nVerts; i++) {
                if (adjMat[start][i] != INFINITY) {
                    System.out.print("- " + vertexList[start].naam + " is verbonden met " + vertexList[i].naam + ".");
                    System.out.println("\r");
                }
            }
        }
    }


    // Een method om alle vertices(wijken) in de Vertexlist te bezichtigen.
    public void displayAllVertices() {
        if(nVerts != 0){
            System.out.println("Onderstaand een overzicht van alle wijken/ressorten in Paramaribo.");
        }

        for(int i = 0; i < nVerts; i++) {
            int number = i+1;
            System.out.print(number+". "+vertexList[i].naam);
            System.out.println("\r");
        }
    }


    // Een method om de unvisited adjacent vertex op te halen.
    public int getAdjUnvisitedVertex(int v) {
        for(int j=0; j<nVerts; j++) {
            if (adjMat[v][j] != INFINITY && !vertexList[j].wasVisited) {
                return j;
            }
        }
        return -1;
    }


    // Onderstaand een depth-first search method.
    public void dfs() {
        // Begin bij de vertex 0
        vertexList[0].wasVisited = true;   // markeer het.
        System.out.println("Zoekresultaten (DFS):");
        displayVertex(0);              // display het.
        System.out.println("\r");         // Een nieuwe regel.
        theStack.push(0);              // push het.

        while( !theStack.isEmpty() ) {
            // Haal een unvisited vertex adjacent op.
            int v = getAdjUnvisitedVertex( theStack.peek() );

            if(v == -1) {
                theStack.pop();           //    Verwijder een nieuwe
            } else {
                vertexList[v].wasVisited = true;  // markeer het.
                displayVertex(v);                 // display het.
                System.out.println("\r");         // Een nieuwe regel.
                theStack.push(v);                 // push het.
            }
        }

        // De stack is leeg dus we zijn klaar.
        for(int j=0; j<nVerts; j++) {
            // Vlaggen resetten.
            vertexList[j].wasVisited = false;
        }

    }


    // Onderstaand een breadth-first search method.
    public void bfs() {
        // Begin bij de vertex 0.
        vertexList[0].wasVisited = true;     // markeer het.
        System.out.println("Zoekresultaten (BFS):");
        displayVertex(0);                // display het.
        System.out.println("\r");         // Een nieuwe regel.
        theQueue.insert(0);              // Voeg toe aan de start.
        int v2;

        // Tot de queue leeg is.
        while (!theQueue.isEmpty()) {
            int v1 = theQueue.remove();   // Verwijder vertex bij de head.
            // Tot het geen unvisited buren heeft.
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                // een ophalen.
                vertexList[v2].wasVisited = true;  // markeer het.
                displayVertex(v2);                 // display het.
                System.out.println("\r");         // Een nieuwe regel.
                theQueue.insert(v2);               // Voeg het toe.
            }
        }

        // Queue is leeg dus we zijn klaar.
        // Vlaggen resetten.
        for (int j = 0; j < nVerts; j++) {
            vertexList[j].wasVisited = false;
        }

    }


    // Een method om het aantal vertices(wijken) in de Vertexlist te bezichtigen.
    public void numberOfVertices() {
        if(nVerts != 0){
            System.out.println("Onderstaand het aantal wijken/ressorten in Paramaribo voor het leveren van brood en bollen alvorens het overgaan tot het district Commewijne.");
        }

        System.out.println(nVerts+" wijken in Paramaribo.");
    }


    // Een method om het aantal edges(wegen) tussen de wijken te bezichtigen.
    public void numberOfEdges() {
        int edges = 0;

        if(nVerts >= 2){
            System.out.println("Onderstaand het aantal wegen tussen wijken/ressorten in Paramaribo voor het leveren van brood en bollen.");
        }

        for(int start = 0; start < nVerts; start++) {
            for (int i = 0; i < nVerts; i++) {
                if (adjMat[start][i] != INFINITY) {
                    if(adjMat[start][i] == adjMat[i][start]){
                        if(start > i){
                            continue;
                        }else{
                            edges++;
                        }
                    }
                }
            }
        }

        System.out.println(edges+" wegen.");
    }

    // Een method om de totale afstand in kilometers tussen de wijken te bezichtigen.
    public void numberOfKm() {
        int kilometers = 0;

        for(int start = 0; start < nVerts; start++) {
            for (int i = 0; i < nVerts; i++) {
                if (adjMat[start][i] != INFINITY) {
                    if(adjMat[start][i] == adjMat[i][start]){
                        if(start > i){
                            continue;
                        }else{
                            kilometers += adjMat[start][i];
                        }
                    }
                }
            }
        }

        System.out.println("Onderstaand de afstand in kilometers tussen de wijken/ressorten in Paramaribo voor het leveren van brood en bollen.");
        System.out.println(kilometers+" km.");
    }

}
