package com.jtorres.examenModulo01.Controladores;

import com.jtorres.examenModulo01.Modulos.Conexion;
import com.jtorres.examenModulo01.Modulos.Persona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JScrollPane;

public class Personas implements Gestionar {
    //Declaro que para la clase persona se espera crear un arraylsit aun no definido.
    private final ArrayList<Persona> personas;

    //Genero el constructor de la variable de tipo Arraylist previamente creada
    public Personas() {
        personas = new ArrayList<>();
    }

    @Override
    public boolean registrar() throws Exception {
        Persona persona = new Persona();
        persona.setNombre(JOptionPane.showInputDialog("Ingrese el nombre de la persona"));
        String scan = JOptionPane.showInputDialog("Ingrese el genero de la persona M o F");

        while (!scan.equals("M") && !scan.equals("F")) {
            System.out.println("Genero Ingresado incorrectamente");
            JOptionPane.showMessageDialog(null, "Por favor ingrese un genero correcto M o F");
            scan = JOptionPane.showInputDialog("Ingrese el genero de la persona M o F");
        }
        System.out.println("Genero Ingresado correctamente");
        persona.setGenero(scan.charAt(0));

        //Validando el campo de la edad!!!
        int edad = -1;
        while (edad < 0 || edad > 100) {  // Considerando la esperanza de vida
            try {
                edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese edad de persona"));
                persona.setEdad(edad);
                if (edad < 0 || edad > 100) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese una edad valida");
                }
            } catch (NumberFormatException ex) {  // Por evitar errores de formato
                JOptionPane.showMessageDialog(null, "Por favor ingrese una edad valida");
            }
        }
        persona.setEdad(edad);
        JOptionPane.showMessageDialog(null, "El elemento " + persona.getNombre() + " ha sido ingresado correctamente ");
        java.sql.Connection con = Conexion.getInstance().getConnection();
        Statement statement = con.createStatement();
        statement.executeUpdate("INSERT INTO REGISTROSDB.PERSONA (NOMBRE, GENERO, EDAD) VALUES ("
                + "'" + persona.getNombre() + "','" + persona.getGenero() + "'," + persona.getEdad() + ")");
        statement.close();
        con.close();
        return true;
    }


    @Override
    public void mostrar() throws Exception {
        java.sql.Connection con = Conexion.getInstance().getConnection();                   //Instancia la conexion a la base
        String sql = "SELECT ID_PERSONA, NOMBRE, EDAD, GENERO FROM REGISTROSDB.PERSONA";    //Hace la consulta de todos los miembros
        Statement statement = con.createStatement();                                        //Crea la sentencia
        ResultSet resultSet = statement.executeQuery(sql);                                   //Ejecuto la sentencia y espero un query
        DefaultTableModel modelo = new DefaultTableModel();                                     //Genero una tabla para almacenar los datos
        JTable tabla = new JTable(modelo);                                                      //Formateo la tabla para mostrar
        modelo.addColumn("ID_PERSONA");                                            //Formateo los encabezados de la tabla
        modelo.addColumn("NOMBRE");
        modelo.addColumn("GENERO");
        modelo.addColumn("EDAD");

        while (resultSet.next()) {                                                              //Barro todo el resultado de la tabla
            Persona persona = new Persona();                                                //Genero un arraylist persona momentaneo
            persona.setIdPersona(resultSet.getInt("ID_PERSONA"));               //Voy extrayendo los datos de la consulta
            persona.setNombre(resultSet.getString("NOMBRE"));
            persona.setEdad(resultSet.getInt("EDAD"));
            persona.setGenero(resultSet.getString("GENERO").charAt(0));
            personas.add(persona);
            Object[] fila = new Object[4]; // Hay tres columnas en la tabla                //defino como se va ir llenando la tabla
            fila[0] = (resultSet.getInt(1));                                //En la primer posición traigo el ID
            fila[1] = (resultSet.getString(2));                             //En la segunda posición traigo el nombre
            fila[2] = (resultSet.getInt(3));                                //En la tercera posicion traigo la edad
            fila[3] = (resultSet.getString(4).charAt(0));                   //En la cuarta posicion traigo el genero

            for (int i = 0; i < resultSet.getFetchSize(); i++)
                fila[i] = resultSet.getObject(i + 1);                             //Se hace el barrido del resultset
            modelo.addRow(fila);                                                            //Se añade la fila al final de la tabla
        }

        if (resultSet != null) {
            resultSet.close();
        }
        statement.close();
        con.close();
        JFrame frame = new JFrame("Registro de personas");                                  //Se crea una ventana en window
        JPanel panel = new JPanel();                                                             //Se define el panel para visualizar
        panel.setLayout(new BorderLayout());                                                     //Se customiza el panel
        JScrollPane tableContainer;                                                              //Se instancia el panel
        tableContainer = new JScrollPane(tabla);                                                 //Se incrusta la tabla creada en el panel
        panel.add(tableContainer, BorderLayout.CENTER);                                          //Se añaden los datos de la tabla
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);                                                                  //Se muestra la tabla en pantalla
        JOptionPane.showMessageDialog(null, "Personas ingresados");    //Se muestra el dialogo
        frame.setVisible(false);                                                                 //Se oculta la tabla en pantalla
    }




    @Override
    public boolean modificar(String identificador) throws Exception {
        java.sql.Connection con = Conexion.getInstance().getConnection();                                                                   //Instancia la conexion a la base
        Statement statement = con.createStatement();
        String Nombre = (JOptionPane.showInputDialog("Ingrese el nombre de la persona"));
        String scan = JOptionPane.showInputDialog("Ingrese el genero de la persona M o F");

        while (!scan.equals("M") && !scan.equals("F")) {
            System.out.println("Genero Ingresado incorrectamente");
            JOptionPane.showMessageDialog(null, "Por favor ingrese un genero correcto M o F");
            scan = JOptionPane.showInputDialog("Ingrese el genero de la persona M o F");
        }

        //Validando el campo de la edad!!!
        int edad = -1;
        while (edad < 0 || edad > 100) {  // Considerando la esperanza de vida
            try {
                edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese edad de persona"));
                if (edad < 0 || edad > 100) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese una edad valida");
                }
            } catch (NumberFormatException ex) {  // Por evitar errores de formato
                JOptionPane.showMessageDialog(null, "Por favor ingrese una edad valida");
            }
        }
        statement.executeUpdate("UPDATE REGISTROSDB.PERSONA SET NOMBRE = '" + Nombre + "' , GENERO = '"
                + scan + "', EDAD = " + edad + " WHERE ID_PERSONA = " + identificador);
        JOptionPane.showMessageDialog(null, "Registro actualizado con exito");                                          //Se muestra el dialogo
        return true;
    }

    @Override
    public boolean eliminar(String identificador) throws Exception {
        java.sql.Connection con = Conexion.getInstance().getConnection();                                                                   //Instancia la conexion a la baseo
        Statement statement = con.createStatement();
        statement.executeUpdate("DELETE FROM REGISTROSDB.PERSONA WHERE ID_PERSONA = " + identificador);
        JOptionPane.showMessageDialog(null, "Registro borrado con exito");                                    //Se muestra el dialogo
        statement.close();
        con.close();
        return true;
    }

    @Override
    public void buscar(String identificador) throws Exception {
        java.sql.Connection con = Conexion.getInstance().getConnection();                                                               //Instancia la conexion a la base
        String sql = "SELECT ID_PERSONA, NOMBRE, EDAD, GENERO FROM REGISTROSDB.PERSONA WHERE ID_PERSONA = " + "'"+identificador+"'";    //Hace la consulta de todos los miembros y concatena con el ID seleccionado
        Statement statement = con.createStatement();                                                                                    //Crea la sentencia
        DefaultTableModel modelo = new DefaultTableModel();                                                                             //Genero una tabla para almacenar los datos
        JTable tabla = new JTable(modelo);                                                                                              //Formateo la tabla para mostrar
        modelo.addColumn("ID_PERSONA");                                                                                    //Formateo los encabezados de la tabla
        modelo.addColumn("NOMBRE");
        modelo.addColumn("GENERO");
        modelo.addColumn("EDAD");
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {                                                              //Barro todo el resultado de la tabla
            Object[] fila = new Object[4]; // Hay tres columnas en la tabla                     //defino como se va ir llenando la tabla
            fila[0] = (resultSet.getInt(1));                                        //En la primer posición traigo el ID
            fila[1] = (resultSet.getString(2));                                     //En la segunda posición traigo el nombre
            fila[2] = (resultSet.getInt(3));                                        //En la tercera posicion traigo la edad
            fila[3] = (resultSet.getString(4).charAt(0));                           //En la cuarta posicion traigo el genero

            for (int i = 0; i < resultSet.getFetchSize(); i++)
                fila[i] = resultSet.getObject(i + 1);                               //Se hace el barrido del resultset
            modelo.addRow(fila);                                                                //Se añade la fila al final de la tabla
        }

        if (resultSet != null) {
            resultSet.close();
        }
        statement.close();
        con.close();
        JFrame frame = new JFrame("Consulta de persona según ID");                          //Se crea una ventana en window
        JPanel panel = new JPanel();                                                             //Se define el panel para visualizar
        panel.setLayout(new BorderLayout());                                                     //Se customiza el panel
        JScrollPane tableContainer;                                                              //Se instancia el panel
        tableContainer = new JScrollPane(tabla);                                                 //Se incrusta la tabla creada en el panel
        panel.add(tableContainer, BorderLayout.CENTER);                                          //Se añaden los datos de la tabla
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);                                                                  //Se muestra la tabla en pantalla
        JOptionPane.showMessageDialog(null, "Personas encontradas");    //Se muestra el dialogo
        frame.setVisible(false);                                                                 //Se oculta la tabla en pantalla
    }


    public boolean containts(Persona o) {
        return personas.contains(o);
    }

}