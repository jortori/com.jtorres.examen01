package com.jtorres.examenModulo01.Controladores;

import com.jtorres.examenModulo01.Modulos.Conexion;
import com.jtorres.examenModulo01.Modulos.Evento;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Eventos implements Gestionar {

    @Override
    public boolean registrar() throws Exception {
        Evento evento = new Evento();
        String nombre  = JOptionPane.showInputDialog("Ingrese el nombre del evento no mas de 100 caracteres");
        while(nombre.length()>100) {
            System.out.println("Nombre demasiado largo");
            JOptionPane.showMessageDialog(null, "Nombre demasiado largo");
            nombre = JOptionPane.showInputDialog("Ingrese el nombre del evento no mas de 100 caracteres");
        }
        System.out.println("Nombre del evento ingresado correctamente");
        evento.setNombre(nombre);

        String ubicacion  = JOptionPane.showInputDialog("Ingrese la ubicacion del evento no mas de 100 caracteres");
        while(ubicacion.length()>100) {
            System.out.println("Nombre demasiado largo");
            JOptionPane.showMessageDialog(null, "Nombre demasiado largo");
            ubicacion = JOptionPane.showInputDialog("Ingrese la ubicacion del evento no mas de 100 caracteres");
        }
        System.out.println("Ubicacion  del evento ingresado correctamente");
        evento.setUbicacion(ubicacion);
        //Validando el campo capacidad del evento!!!
        int capacidad  = -1;
        while (capacidad < 0 || capacidad > 100000) {  // Considerando que en un evento no van a existir números negativos ni cantidades mayores a 100,000
            try {
                capacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese capacidad del evento en número"));
                evento.setCapacidad(capacidad);
                if (capacidad < 0 || capacidad > 100000) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese una capacidad para el evento valida");
                }
            } catch (NumberFormatException ex) {  // Por evitar errores de formato
                JOptionPane.showMessageDialog(null, "Por favor ingrese una capacidad para el evento valida");
            }
        }
        evento.setCapacidad(capacidad);
        JOptionPane.showMessageDialog(null, "El elemento " + evento.getNombre() + " ha sido ingresado correctamente ");

        java.sql.Connection con = Conexion.getInstance().getConnection();
        Statement statement = con.createStatement();
        statement.executeUpdate("INSERT INTO REGISTROSDB.EVENTOS (NOMBRE, UBICACION, CAPACIDAD) VALUES ("
                + "'" + evento.getNombre() + "','" + evento.getUbicacion() + "'," + evento.getCapacidad() + ")");
        statement.close();
        con.close();
        return true;
    }
    @Override
    public void mostrar() throws Exception{
        java.sql.Connection con = Conexion.getInstance().getConnection();                       //Instancia la conexion a la base
        String sql = "SELECT ID_EVENTOS, NOMBRE, UBICACION, CAPACIDAD FROM REGISTROSDB.EVENTOS";//Hace la consulta de todos los miembros
        Statement statement = con.createStatement();                                            //Crea la sentencia
        ResultSet resultSet = statement.executeQuery(sql);                                      //Ejecuto la sentencia y espero un query
        //**********************************************************************************************************************************
        //Creo la tabla
        DefaultTableModel modelo = new DefaultTableModel();                                     //Genero una tabla para almacenar los datos
        JTable tabla = new JTable(modelo);                                                      //Formateo la tabla para mostrar
        modelo.addColumn("ID_EVENTOS");                                            //Formateo los encabezados de la tabla
        modelo.addColumn("NOMBRE");
        modelo.addColumn("UBICACION");
        modelo.addColumn("CAPACIDAD");
        //***********************************************************************************************************************************
        //Inserto contenido en la tabla
        while (resultSet.next()) {                                                              //Barro todo el resultado de la tabla
            Object[] fila = new Object[4]; // Hay tres columnas en la tabla                     //defino como se va ir llenando la tabla
            fila[0] = (resultSet.getInt(1));                                        //En la primer posición traigo el ID
            fila[1] = (resultSet.getString(2));                                     //En la segunda posición traigo el nombre
            fila[2] = (resultSet.getString(3));                                     //En la tercera posicion traigo la ubicacion
            fila[3] = (resultSet.getInt(4));                                        //En la cuarta posicion traigo la capacidad

            for (int i = 0; i < resultSet.getFetchSize(); i++)
                fila[i] = resultSet.getObject(i + 1);                               //Se hace el barrido del resultset
            modelo.addRow(fila);                                                                //Se añade la fila al final de la tabla
        }
        //Finalizo construcción y llenado de tabla
        //************************************************************************************************************************************
        if (resultSet != null) {
            resultSet.close();
        }
        statement.close();                                                                       //Cierro instrucción
        con.close();                                                                             //Cierro conexion
        JFrame frame = new JFrame("Registro de eventos");                                   //Se crea una ventana en window
        JPanel panel = new JPanel();                                                             //Se define el panel para visualizar
        panel.setLayout(new BorderLayout());                                                     //Se customiza el panel
        JScrollPane tableContainer;                                                              //Se instancia el panel
        tableContainer = new JScrollPane(tabla);                                                 //Se incrusta la tabla creada en el panel
        panel.add(tableContainer, BorderLayout.CENTER);                                          //Se añaden los datos de la tabla
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);                                                                  //Se muestra la tabla en pantalla
        JOptionPane.showMessageDialog(null, "Eventos registrados");    //Se muestra el dialogo
        frame.setVisible(false);                                                                //Se oculta la tabla en pantalla
    }
    @Override
    public boolean modificar(String identificador) throws Exception{
        java.sql.Connection con = Conexion.getInstance().getConnection();                                                                   //Instancia la conexion a la base
        Statement statement = con.createStatement();
        String nombre  = JOptionPane.showInputDialog("Ingrese el nombre del evento no mas de 100 caracteres");
        while(nombre.length()>100) {
            System.out.println("Nombre demasiado largo");
            JOptionPane.showMessageDialog(null, "Nombre demasiado largo");
            nombre = JOptionPane.showInputDialog("Ingrese el nombre del evento no mas de 100 caracteres");
        }
        System.out.println("Nombre del evento ingresado correctamente");

        String ubicacion  = JOptionPane.showInputDialog("Ingrese la ubicacion del evento no mas de 100 caracteres");
        while(ubicacion.length()>100) {
            System.out.println("Nombre demasiado largo");
            JOptionPane.showMessageDialog(null, "Nombre demasiado largo");
            ubicacion = JOptionPane.showInputDialog("Ingrese la ubicacion del evento no mas de 100 caracteres");
        }
        System.out.println("Ubicacion  del evento ingresado correctamente");

        //Validando el campo capacidad del evento!!!
        int capacidad  = -1;
        while (capacidad < 0 || capacidad > 100000) {  // Considerando que en un evento no van a existir números negativos ni cantidades mayores a 100,000
            try {
                capacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese capacidad del evento en número"));
                if (capacidad < 0 || capacidad > 100000) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese una capacidad para el evento valida");
                }
            } catch (NumberFormatException ex) {  // Por evitar errores de formato
                JOptionPane.showMessageDialog(null, "Por favor ingrese una capacidad para el evento valida");
            }
        }
        statement.executeUpdate("UPDATE REGISTROSDB.EVENTOS SET NOMBRE = '" + nombre + "' , UBICACION = '"
                + ubicacion + "', CAPACIDAD = " + capacidad + " WHERE ID_EVENTOS = " + identificador);
        JOptionPane.showMessageDialog(null, "Registro actualizado con exito");
        return true;
    }

    @Override
    public boolean eliminar(String identificador) throws Exception{
        java.sql.Connection con = Conexion.getInstance().getConnection();                                                                   //Instancia la conexion a la baseo
        Statement statement = con.createStatement();
        statement.executeUpdate("DELETE FROM REGISTROSDB.EVENTOS WHERE ID_EVENTOS = " + identificador);
        JOptionPane.showMessageDialog(null, "Registro borrado con exito");                                    //Se muestra el dialogo
        statement.close();
        con.close();
        return true;
    }

    @Override
    public void buscar(String identificador) throws Exception {
        java.sql.Connection con = Conexion.getInstance().getConnection();                       //Instancia la conexion a la base
        String sql = "SELECT ID_EVENTOS, NOMBRE, UBICACION, CAPACIDAD FROM REGISTROSDB.EVENTOS WHERE ID_EVENTOS = "
                + "'" + identificador+"'";                                                      //Hace la consulta de todos los miembros
        Statement statement = con.createStatement();                                            //Crea la sentencia
        ResultSet resultSet = statement.executeQuery(sql);                                      //Ejecuto la sentencia y espero un query
        DefaultTableModel modelo = new DefaultTableModel();                                     //Genero una tabla para almacenar los datos
        JTable tabla = new JTable(modelo);                                                      //Formateo la tabla para mostrar
        modelo.addColumn("ID_EVENTOS");                                            //Formateo los encabezados de la tabla
        modelo.addColumn("NOMBRE");
        modelo.addColumn("UBICACION");
        modelo.addColumn("CAPACIDAD");

        while (resultSet.next()) {                                                              //Barro todo el resultado de la tabla
            Object[] fila = new Object[4]; // Hay tres columnas en la tabla                     //defino como se va ir llenando la tabla
            fila[0] = (resultSet.getInt(1));                                        //En la primer posición traigo el ID
            fila[1] = (resultSet.getString(2));                                     //En la segunda posición traigo el nombre
            fila[2] = (resultSet.getString(3));                                     //En la tercera posicion traigo la ubicacion
            fila[3] = (resultSet.getInt(4));                                        //En la cuarta posicion traigo la capacidad

            for (int i = 0; i < resultSet.getFetchSize(); i++)
                fila[i] = resultSet.getObject(i + 1);                               //Se hace el barrido del resultset
            modelo.addRow(fila);                                                                //Se añade la fila al final de la tabla
        }

        if (resultSet != null) {
            resultSet.close();
        }
        statement.close();
        con.close();
        JFrame frame = new JFrame("Registro de eventos");                                  //Se crea una ventana en window
        JPanel panel = new JPanel();                                                             //Se define el panel para visualizar
        panel.setLayout(new BorderLayout());                                                     //Se customiza el panel
        JScrollPane tableContainer;                                                              //Se instancia el panel
        tableContainer = new JScrollPane(tabla);                                                 //Se incrusta la tabla creada en el panel
        panel.add(tableContainer, BorderLayout.CENTER);                                          //Se añaden los datos de la tabla
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);                                                                  //Se muestra la tabla en pantalla
        JOptionPane.showMessageDialog(null, "Eventos registrados");    //Se muestra el dialogo
        frame.setVisible(false);                                                                //Se oculta la tabla en pantalla

    }
}
