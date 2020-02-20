package com.jtorres.examenModulo01.Modulos;

import java.util.Objects;

public class Asistencia {
    int ID_EVENTO;
    int ID_PERSONA;
    int ID_ASISTENCIA;

    public Asistencia() {
    }

    public Asistencia(int ID_EVENTO, int ID_PERSONA, int ID_ASISTENCIA) {
        this.ID_EVENTO = ID_EVENTO;
        this.ID_PERSONA = ID_PERSONA;
        this.ID_ASISTENCIA = ID_ASISTENCIA;
    }

    public int getID_EVENTO() {
        return ID_EVENTO;
    }

    public void setID_EVENTO(int ID_EVENTO) {
        this.ID_EVENTO = ID_EVENTO;
    }

    public int getID_PERSONA() {
        return ID_PERSONA;
    }

    public void setID_PERSONA(int ID_PERSONA) {
        this.ID_PERSONA = ID_PERSONA;
    }

    public int getID_ASISTENCIA() {
        return ID_ASISTENCIA;
    }

    public void setID_ASISTENCIA(int ID_ASISTENCIA) {
        this.ID_ASISTENCIA = ID_ASISTENCIA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asistencia that = (Asistencia) o;
        return ID_EVENTO == that.ID_EVENTO &&
                ID_PERSONA == that.ID_PERSONA &&
                ID_ASISTENCIA == that.ID_ASISTENCIA;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID_EVENTO, ID_PERSONA, ID_ASISTENCIA);
    }


}
