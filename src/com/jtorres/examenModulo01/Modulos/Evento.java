package com.jtorres.examenModulo01.Modulos;

public class Evento {

    String nombre;
    String Ubicacion;
    int capacidad;

    public Evento() {
    }

    public Evento(String nombre, String ubicacion, int capacidad) {
        this.nombre = nombre;
        this.Ubicacion = ubicacion;
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento)) return false;

        Evento evento = (Evento) o;

        if (capacidad != evento.capacidad) return false;
        if (!nombre.equals(evento.nombre)) return false;
        return Ubicacion.equals(evento.Ubicacion);
    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + Ubicacion.hashCode();
        result = 31 * result + capacidad;
        return result;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nombre='" + nombre + '\'' +
                ", Ubicacion='" + Ubicacion + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }
}
