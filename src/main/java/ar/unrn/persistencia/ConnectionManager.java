package ar.unrn.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {
    protected static String DB = "tp02_oo2";
    protected static String user = "root";
    protected static String pass = "";
    protected static Connection conn = null;
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL_DB = "jdbc:mysql://localhost:3306/";

    public static void connect() {
        try {
            conn = DriverManager.getConnection(URL_DB + DB, user, pass);
        } catch (SQLException sqlEx) {
            throw new RuntimeException("No se ha podido conectar a " + URL_DB + DB + ". " + sqlEx.getMessage());

        }
    }

    public static void disconnect() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                throw new RuntimeException("Error al cerrar la conexión con la base de datos" + e.getMessage());
            }
        }
    }

    public static void reconnect() {
        disconnect();
        connect();
    }

    public static Connection getConnection() throws RuntimeException {
        if (conn == null) {
            connect();
        }
        if (conn == null) {
            throw new RuntimeException("No se pudo conectar a la Base de Datos.");
        }
        return conn;
    }

}


