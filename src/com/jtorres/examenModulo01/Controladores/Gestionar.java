package com.jtorres.examenModulo01.Controladores;

    public interface Gestionar {

        boolean registrar() throws Exception;

        void mostrar() throws Exception;

        boolean modificar(String id) throws Exception;

        void buscar(String id) throws Exception;

        boolean eliminar(String id) throws Exception;

}
