package com.jtorres.examenModulo01.Vistas;

import com.jtorres.examenModulo01.Controladores.Gestionar;

import javax.swing.*;

public class SubMenu {

    private Gestionar gestionador; // El que gestiona la entidad de interes
    private String entidad;

    public SubMenu() {
    }

    public SubMenu(Gestionar gestionador, String entidad) {
        this.gestionador = gestionador;
        this.entidad = entidad;
    }

    public int selectedOptions() {
        String options = "Seleccione una opción (número).\n" // Opciones del menú principal
                + "1. Registrar " + entidad + "\n"
                + "2. Mostrar " + entidad + "\n"
                + "3. Modificar " + entidad + "\n"
                + "4. Eliminar " + entidad + "\n"
                + "5. Buscar " + entidad + "\n"
                + "6. Atras";

        try { // Intentamos obtener un número
            return Integer.parseInt(JOptionPane.showInputDialog(options));
        } catch (NumberFormatException e) {
            return 0; // indica que no se ingreso opcion valida (número)
        }
    }

    public void show() throws Exception {
        int option; // Se alamacenara la opcion ingresada
        String identificador;
        do {
            option = selectedOptions(); // Mostrar y solicitar

            switch (option) {
                case 1: // Registrar
                    if (gestionador.registrar()) {
                        JOptionPane.showMessageDialog(null, entidad + " ha sido ingresado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, entidad + " No se pudo ingresar, el valor de identificación ya existe");
                    }
                    break;
                case 2: // Mostrar
                    gestionador.mostrar();
                    break;
                case 3: // Modificar
                    identificador = JOptionPane.showInputDialog("Ingrese el identificador de " + entidad + " que desea modificar");
                    if (gestionador.modificar(identificador)) {
                        JOptionPane.showMessageDialog(null, entidad + " fue modificado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "El " + entidad + " que desea modificar no existe");
                    }
                    break;
                case 4: // Eliminar
                    identificador = JOptionPane.showInputDialog("Ingrese el identificador de " + entidad + " que desa eliminar");
                    if (gestionador.eliminar(identificador)) {
                        JOptionPane.showMessageDialog(null, entidad + " fue eliminado del registro");
                    } else {
                        JOptionPane.showMessageDialog(null, "El " + entidad + " que desea eliminar no existe");
                    }
                    break;
                case 5: // Buscar
                    identificador = JOptionPane.showInputDialog("Ingrese el identificador de " + entidad + " que desa buscar");
                    gestionador.buscar(identificador);
                    break;
                case 6: // Salir del menu
                    return;
                default: // Datos invalidos
                    JOptionPane.showMessageDialog(null, "Por favor ingrese una opción valida");
            }
        } while (option != 6);

    }

    public Gestionar getGestionador() {
        return gestionador;
    }

    public void setGestionador(Gestionar gestionador) {
        this.gestionador = gestionador;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

}
