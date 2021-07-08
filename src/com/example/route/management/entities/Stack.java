package com.example.route.management.entities;

public class Stack {

    private final int[] st;
    private int top;

    // Constructor van de Stack class.
    public Stack() {
        // Datamembers van de Stack class.
        int SIZE = 50;
        st = new int[SIZE];   // Array maken.
        top = -1;
    }

    // Items toevoegen aan de stack.
    public void push(int j) {
        st[++top] = j;
    }

    // Item verwijderen van de stack.
    public int pop() {
        return st[top--];
    }

    // Kijkje nemen naar de top van de stack.
    public int peek() {
        return st[top];
    }

    // Geeft waarde true terug als stack leeg is.
    public boolean isEmpty() {
        return (top == -1);
    }

}
