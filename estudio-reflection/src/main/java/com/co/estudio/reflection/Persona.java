package com.co.estudio.reflection;

public class Persona {
    private String nombre;
    private int edad;

    public void saludar() {
        System.out.println("¡Hola, soy " + nombre + "!");
    }

    @Override
    public String toString() {
        return nombre + " - " + edad;
    }
}
