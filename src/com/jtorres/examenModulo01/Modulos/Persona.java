package com.jtorres.examenModulo01.Modulos;

import java.util.Objects;

public class Persona {

    private String nombre;
    private char genero;
    private int edad;
    private int IdPersona;

    public Persona() {
    }

    public Persona(String nombre, char genero, int edad, int idPersona) {
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.IdPersona = idPersona;
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

    public int getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(int idPersona) {
        IdPersona = idPersona;
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