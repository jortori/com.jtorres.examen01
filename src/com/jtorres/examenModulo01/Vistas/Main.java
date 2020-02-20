package com.jtorres.examenModulo01.Vistas;

import com.jtorres.examenModulo01.Controladores.Personas;
import com.jtorres.examenModulo01.Modulos.Conexion;
import org.w3c.dom.ls.LSOutput;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {


        String caracteres;
        caracteres = "Java Database Connectivity (JDBC) es la especificación JavaSoft de una interfaz de \n" +
                "programación de aplicaciones (API) estándar que permite que los programas Java accedan a \n" +
                "sistemas de gestión de bases de datos. La API JDBC consiste en un conjunto de interfaces y \n" +
                "clases escritas en el lenguaje de programación Java.\n" +
                "Con estas interfaces y clases estándar, los programadores pueden escribir aplicaciones que \n" +
                "conecten con bases de datos, envíen consultas escritas en el lenguaje de consulta estructurada \n" +
                "(SQL) y procesen los resultados.\n" +
                "Puesto que JDBC es una especificación estándar, un programa Java que utilice la API JDBC \n" +
                "puede conectar con cualquier sistema de gestión de bases de datos (DBMS), siempre y cuando \n" +
                "haya un controlador para dicho DBMS en concreto.\n";


        String palabras = caracteres;
        StringTokenizer st = new StringTokenizer(palabras);

        JOptionPane.showMessageDialog(null,caracteres);
        JOptionPane.showMessageDialog(null,"La definición 'JDBC' tiene un longitud de "+
                caracteres.length() + " caracteres \n" + "Con una cantidad de " + st.countTokens() + " palabras");


        System.out.println("CONEXION A MARIADB");
        Conexion.setDriver(Conexion.MARIADB_DRIVER);
        String usuario = JOptionPane.showInputDialog("Ingrese su usuario");
        String password = JOptionPane.showInputDialog("Ingrese su contraseña");
        Personas personas = new Personas();
        try {
            Conexion.getInstance().setUrl("//localhost");
            Conexion.getInstance().setUser(usuario);
            Conexion.getInstance().setPassword(password);
            System.out.println("Conexion exitosa");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Fallo en conexion");
        }

        Menu menu = Menu.getInstance();
        menu.show();

    }

}