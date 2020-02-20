package com.jtorres.examenModulo01;

import java.awt.datatransfer.StringSelection;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) {

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

        System.out.println(caracteres);
        System.out.println("La definición 'JDBC' tiene un longitud de " + caracteres.length() + " caracteres");
        String palabras = caracteres;
        StringTokenizer st = new StringTokenizer(palabras);
        System.out.println ("Con una cantidad de " + st.countTokens() + " palabras");

    }
}