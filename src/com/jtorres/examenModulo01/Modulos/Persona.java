package com.jtorres.examenModulo01.Modulos;

import java.util.Objects;

public class Persona {

    private int IdPersona;
    private String nombre;
    private char genero;
    private int edad;


    public Persona() {
    }

    public Persona(int idPersona, String nombre, char genero, int edad) {
        this.IdPersona = idPersona;
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setIdPersona(int idPersona) {
        IdPersona = idPersona;
    }
    public int getIdPersona() {
        return IdPersona;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;

        Persona persona = (Persona) o;

        if (genero != persona.genero) return false;
        if (edad != persona.edad) return false;
        if (IdPersona != persona.IdPersona) return false;
        return nombre.equals(persona.nombre);
    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + (int) genero;
        result = 31 * result + edad;
        result = 31 * result + IdPersona;
        return result;
    }
}