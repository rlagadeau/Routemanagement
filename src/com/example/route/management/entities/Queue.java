package com.example.route.management.entities;

public class Queue {

    // Datamembers van de Queue class.
    private final int maxSize;
    private final int[] queArray;
    private int front;
    private int rear;
    private int nItems;

    // Constructor van de queue class.
    public Queue(int s) {
        maxSize = s;
        queArray = new int[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    // Item bij de achterkant(rear) van de queue toevoegen.
    public void insert(int j) {
        // Bezig zijn met wraparound.
        if(rear == maxSize-1) {
            rear = -1;
        }

        queArray[++rear] = j;    // Achterkant(rear) incrementen en daarna toevoegen.
        nItems++;               // Nog een item.
    }

    // Item verwijderen bij de voorkant van de queue.
    public int remove() {
        // Value ophalen en de voorkant(front) incrementen.
        int temp = queArray[front++];
        // Bezig zijn met wraparound.
        if(front == maxSize) {
            front = 0;
        }

        nItems--;    // Een item minder.
        return temp;
    }

    // Kijkje nemen naar de voorkant(front) van de queue.
    /*public int peekFront() {
        return queArray[front];
    }*/

    // Geeft waarde true terug als de queue leeg is.
    public boolean isEmpty() {
        return (nItems==0);
    }

    // Geeft waarde true terug als de queue vol is.
    /*public boolean isFull() {
        return (nItems==maxSize);
    }

    // Geeft aantal items terug in de queue.
    public int size() {
        return nItems;
    }*/

}
