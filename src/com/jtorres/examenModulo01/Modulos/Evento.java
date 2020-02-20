package com.jtorres.examenModulo01.Modulos;

public class Evento {

    int idEvento;
    String nombre;
    String Ubicacion;
    int capacidad;

    public Evento() {
    }

    public Evento(int idEvento, String nombre, String ubicacion, int capacidad) {
        this.idEvento = idEvento;
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

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento)) return false;

        Evento evento = (Evento) o;

        if (idEvento != evento.idEvento) return false;
        if (capacidad != evento.capacidad) return false;
        if (!nombre.equals(evento.nombre)) return false;
        return Ubicacion.equals(evento.Ubicacion);
    }

    @Override
    public int hashCode() {
        int result = idEvento;
        result = 31 * result + nombre.hashCode();
        result = 31 * result + Ubicacion.hashCode();
        result = 31 * result + capacidad;
        return result;
    }
}
