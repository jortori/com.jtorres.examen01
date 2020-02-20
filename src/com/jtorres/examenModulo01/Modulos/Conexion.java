package com.jtorres.examenModulo01.Modulos;

import java.sql.DriverManager;
import java.sql.SQLException;


    public class Conexion {
        private static final String BD2_PREFIX = "jdbc:as400:";
        private static final String MARIADB_PREFIX = "jdbc:mariadb:";
        public static final String BD2_DRIVER = "com.ibm.as400.access.AS400JDBCDriver";
        public static final String MARIADB_DRIVER = "org.mariadb.jdbc.Driver";


        private static Conexion instance;

        private String url;
        private String user;
        private String password;
        private static String driver;
        private java.sql.Connection connection;


        private Conexion() throws ClassNotFoundException {
            Class.forName(driver);
        }

        public synchronized static Conexion getInstance() throws ClassNotFoundException {
            if (instance == null) {

                instance = new Conexion();

            }
            return instance;
        }

        public java.sql.Connection getConnection() throws SQLException {
            return DriverManager.getConnection(url, user, password);
        }

        public static void setDriver(String driver) {
            Conexion.driver = driver;
        }

        public void setUrl(String url) {

            String urlPrefix;

            switch (driver) {
                case BD2_DRIVER:
                    urlPrefix = BD2_PREFIX;
                    break;
                case MARIADB_DRIVER:
                    urlPrefix = MARIADB_PREFIX;
                    break;
                default:
                    urlPrefix = "";
            }
            this.url = urlPrefix + url;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public void setPassword(String password) {
            this.password = password;
        }

}
