package com.example.route.management.entities;

public class PriorityQ {

    private final Edge[] queArray;
    private int size;

    // Constructor van de Priority class.
    public PriorityQ() {
        // Datamembers van de Priority class. De array is gesorteerd waarbij max de waarde 0 heeft en min de waarde -1.
        int SIZE = 20;
        queArray = new Edge[SIZE];
        size = 0;
    }

    // Item toevoegen in gesorteerde volgorde.
    public void insert(Edge item) {
        int j;

        // Plaats vinden om toe te voegen.
        for(j=0; j<size; j++) {
            if (item.distance >= queArray[j].distance) {
                break;
            }
        }

        // Items naar boven schuiven.
        if (size - j >= 0) System.arraycopy(queArray, j, queArray, j + 1, size - j);

        // Item toevoegen.
        queArray[j] = item;
        size++;
    }

    // Onderstaand een method voor het verwijderen van de minimum item.
    public Edge removeMin() {
        return queArray[--size];
    }

    // Onderstaand een method voor het verwijderen van de item bij (n).
    public void removeN(int n) {
        // Items opschuiven naar beneden.
        if (size - 1 - n >= 0) System.arraycopy(queArray, n + 1, queArray, n, size - 1 - n);

        size--;
    }

    // Kijkje nemen naar het minimum item.
    //public Edge peekMin() { return queArray[size-1]; }

    // Aantal items laten terugkeren.
    public int size() {
        return size;
    }

    // Onderstaand een method welke de waarde true teruggeeft als de queue leeg is.
    //public boolean isEmpty() { return (size==0); }

    // Kijkje nemen naar item (n).
    public Edge peekN(int n) {
        return queArray[n];
    }

    // Gespecificeerde item vinden.
    public int find(int findDex) {
        // De destination vertexts waarde.
        for(int j=0; j<size; j++) {
            if (queArray[j].destVert == findDex) {
                return j;
            }
        }

        return -1;
    }

}
