package com.jtorres.examenModulo01.Controladores;

import com.jtorres.examenModulo01.Modulos.Asistencia;
import com.jtorres.examenModulo01.Modulos.Conexion;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Asistencias  implements Gestionar {
    private final ArrayList<Asistencia> asistencias;
    private final Personas personas;
    private final Eventos eventos;

    public Asistencias(Personas personas, Eventos eventos) {
        this.asistencias = new ArrayList<>();
        this.personas = personas;
        this.eventos = eventos;
    }

    @Override
    public boolean registrar() throws Exception{
        int ID_Evento = parseInt(JOptionPane.showInputDialog("Ingrese el ID del evento que desea agendar"));
        int ID_Persona = parseInt(JOptionPane.showInputDialog("Ingrese el ID de la persona que desea registrar"));
        java.sql.Connection con = Conexion.getInstance().getConnection();                                                   //Instancia la conexion a la base
        Statement statement = con.createStatement();
        statement.executeUpdate("INSERT INTO REGISTROSDB.ASISTENCIAS (ID_PERSONA, ID_EVENTOS) VALUES ("
                + "'" + ID_Persona + "','" + ID_Evento + "')");
        statement.close();
        con.close();
        return true;
    }

    @Override
    public void mostrar() throws Exception{
        java.sql.Connection con = Conexion.getInstance().getConnection();                       //Instancia la conexion a la base
        String sql = "SELECT A.ID_ASISTENCIA, A.ID_PERSONA, B.NOMBRE, A.ID_EVENTOS, C.NOMBRE, C.UBICACION FROM REGISTROSDB.ASISTENCIAS A " +
                "JOIN REGISTROSDB.PERSONA B ON A.ID_PERSONA = B.ID_PERSONA JOIN REGISTROSDB.EVENTOS C ON A.ID_EVENTOS = C.ID_EVENTOS";
        Statement statement = con.createStatement();                                            //Crea la sentencia
        ResultSet resultSet = statement.executeQuery(sql);                                      //Ejecuto la sentencia y espero un query
        DefaultTableModel modelo = new DefaultTableModel();                                     //Genero una tabla para almacenar los datos
        JTable tabla = new JTable(modelo);                                                      //Formateo la tabla para mostrar
        modelo.addColumn("ID_ASISTENCIA");                                            //Formateo los encabezados de la tabla
        modelo.addColumn("ID_PERSONA");
        modelo.addColumn("NOMBRE PERSONA");
        modelo.addColumn("ID_EVENTOS");
        modelo.addColumn("NOMBRE EVENTO");
        modelo.addColumn("UBICACION");

        while (resultSet.next()) {                                                              //Barro todo el resultado de la tabla
            Object[] fila = new Object[6]; // Hay tres columnas en la tabla                     //defino como se va ir llenando la tabla
            fila[0] = (resultSet.getInt(1));                                        //En la primer posición traigo el ID
            fila[1] = (resultSet.getInt(2));                                     //En la segunda posición traigo el nombre
            fila[2] = (resultSet.getString(3));                                     //En la segunda posición traigo el nombre
            fila[3] = (resultSet.getInt(4));                                     //En la tercera posicion traigo la ubicacion
            fila[4] = (resultSet.getString(5));                                     //En la segunda posición traigo el nombre
            fila[5] = (resultSet.getString(6));                                     //En la segunda posición traigo el nombre

            for (int i = 0; i < resultSet.getFetchSize(); i++)
                fila[i] = resultSet.getObject(i + 1);                               //Se hace el barrido del resultset
            modelo.addRow(fila);                                                                //Se añade la fila al final de la tabla
        }
        if (resultSet != null) {
            resultSet.close();
        }
        statement.close();
        con.close();
        JFrame frame = new JFrame("Registro de asistencias");                                  //Se crea una ventana en window
        JPanel panel = new JPanel();                                                             //Se define el panel para visualizar
        panel.setLayout(new BorderLayout());                                                     //Se customiza el panel
        JScrollPane tableContainer;                                                              //Se instancia el panel
        tableContainer = new JScrollPane(tabla);                                                 //Se incrusta la tabla creada en el panel
        panel.add(tableContainer, BorderLayout.CENTER);                                          //Se añaden los datos de la tabla
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);                                                                  //Se muestra la tabla en pantalla
        JOptionPane.showMessageDialog(null, "Asistencias registradas");    //Se muestra el dialogo
        frame.setVisible(false);                                                                //Se oculta la tabla en pantalla
    }

    @Override
    public boolean modificar(String identificador) throws Exception{
        int ID_Evento = parseInt(JOptionPane.showInputDialog("Ingrese el ID del evento que desea modificar"));
        int ID_Persona = parseInt(JOptionPane.showInputDialog("Ingrese el ID de la persona que desea modificar"));
        java.sql.Connection con = Conexion.getInstance().getConnection();                                                                   //Instancia la conexion a la base
        Statement statement = con.createStatement();
        statement.executeUpdate("UPDATE REGISTROSDB.ASISTENCIAS SET ID_EVENTOS = '" + ID_Evento + "' , ID_PERSONA = '"
                + ID_Persona + "' WHERE ID_ASISTENCIA = " + identificador);
        JOptionPane.showMessageDialog(null, "Registro actualizado con exito");                                          //Se muestra el dialogo
        return true;
    }

    @Override
    public boolean eliminar(String identificador) throws Exception{
        java.sql.Connection con = Conexion.getInstance().getConnection();                                                                   //Instancia la conexion a la baseo
        Statement statement = con.createStatement();
        statement.executeUpdate("DELETE FROM REGISTROSDB.ASISTENCIAS WHERE ID_ASISTENCIA = " + identificador);
        JOptionPane.showMessageDialog(null, "Registro borrado con exito");                                    //Se muestra el dialogo
        statement.close();
        con.close();
        return true;
    }

    @Override
    public void buscar(String identificador) throws Exception {
        java.sql.Connection con = Conexion.getInstance().getConnection();                       //Instancia la conexion a la base
        Statement statement = con.createStatement();
        String sql = "SELECT A.ID_ASISTENCIA, A.ID_PERSONA, B.NOMBRE, A.ID_EVENTOS, C.NOMBRE, C.UBICACION FROM REGISTROSDB.ASISTENCIAS A " +
                "JOIN REGISTROSDB.PERSONA B ON A.ID_PERSONA = B.ID_PERSONA JOIN REGISTROSDB.EVENTOS C ON A.ID_EVENTOS = C.ID_EVENTOS" +
                " WHERE ID_ASISTENCIA = " + "'" + identificador+"'";                                                      //Hace la consulta de todos los miembros
        ResultSet resultSet = statement.executeQuery(sql);                                      //Ejecuto la sentencia y espero un query
        DefaultTableModel modelo = new DefaultTableModel();                                     //Genero una tabla para almacenar los datos
        JTable tabla = new JTable(modelo);                                                      //Formateo la tabla para mostrar
        modelo.addColumn("ID_ASISTENCIA");                                            //Formateo los encabezados de la tabla
        modelo.addColumn("ID_PERSONA");
        modelo.addColumn("NOMBRE PERSONA");
        modelo.addColumn("ID_EVENTOS");
        modelo.addColumn("NOMBRE EVENTO");
        modelo.addColumn("UBICACION");

        while (resultSet.next()) {                                                              //Barro todo el resultado de la tabla
            Object[] fila = new Object[6]; // Hay tres columnas en la tabla                     //defino como se va ir llenando la tabla
            fila[0] = (resultSet.getInt(1));                                        //En la primer posición traigo el ID
            fila[1] = (resultSet.getInt(2));                                     //En la segunda posición traigo el nombre
            fila[2] = (resultSet.getString(3));                                     //En la segunda posición traigo el nombre
            fila[3] = (resultSet.getInt(4));                                     //En la tercera posicion traigo la ubicacion
            fila[4] = (resultSet.getString(5));                                     //En la segunda posición traigo el nombre
            fila[5] = (resultSet.getString(6));                                     //En la segunda posición traigo el nombre

            for (int i = 0; i < resultSet.getFetchSize(); i++)
                fila[i] = resultSet.getObject(i + 1);                               //Se hace el barrido del resultset
            modelo.addRow(fila);                                                                //Se añade la fila al final de la tabla
        }

        if (resultSet != null) {
            resultSet.close();
        }
        statement.close();
        con.close();
        JFrame frame = new JFrame("Registro de Asistencias");                                  //Se crea una ventana en window
        JPanel panel = new JPanel();                                                             //Se define el panel para visualizar
        panel.setLayout(new BorderLayout());                                                     //Se customiza el panel
        JScrollPane tableContainer;                                                              //Se instancia el panel
        tableContainer = new JScrollPane(tabla);                                                 //Se incrusta la tabla creada en el panel
        panel.add(tableContainer, BorderLayout.CENTER);                                          //Se añaden los datos de la tabla
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);                                                                  //Se muestra la tabla en pantalla
        JOptionPane.showMessageDialog(null, "Asistencias registradas");    //Se muestra el dialogo
        frame.setVisible(false);

    }

}
