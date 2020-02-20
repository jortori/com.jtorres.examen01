package com.jtorres.examenModulo01.Vistas;

import com.jtorres.examenModulo01.Controladores.Asistencias;
import com.jtorres.examenModulo01.Controladores.Eventos;
import com.jtorres.examenModulo01.Controladores.Personas;

import javax.swing.*;

public class Menu {


    private static Menu instance;

    private Menu() {
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public int selectedOptions() {
        String options = "Seleccione una opción (número).\n" // Opciones del menú principal
                + "1. Administrar personas.\n"
                + "2. Administrar eventos.\n"
                + "3. Administrar registros.\n"
                + "4. Salir";

        try { // Intentamos obtener un número
            return Integer.parseInt(JOptionPane.showInputDialog(options));
        } catch (NumberFormatException e) {
            return 0; // indica que no se ingreso opcion valida (número)
        }
    }

    public void show() throws Exception {
        // "BASE" de entidades
        Personas personas = new Personas();
        Eventos eventos = new Eventos();
        Asistencias asistencias = new Asistencias(personas,eventos);

        // Sub menú, par mostras las opciones de gestion
        SubMenu subMenu = new SubMenu();

        int option; // Se alamacenara la opcion ingresada

        do {
            option = selectedOptions(); // Mostrar y solicitar

            switch (option) {
                case 1: // Administrar personas
                    subMenu.setEntidad("personas"); // Para mostrar
                    subMenu.setGestionador(personas); // Para administrar
                    subMenu.show(); //Mostrar
                    break;
                case 2: // Administrar Eventos
                    subMenu.setEntidad("eventos");
                    subMenu.setGestionador(eventos);
                    subMenu.show();
                    break;
                case 3: // Administrar asistencias
                    subMenu.setEntidad("asistencias");
                    subMenu.setGestionador(asistencias);
                    subMenu.show();
                    break;
                case 4: // No es necesario, deberia solo salir, pero ...
                    JOptionPane.showMessageDialog(null, "¡Adios!");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Por favor ingrese una opción valida");
            }
        } while (option != 4);


    }

}
