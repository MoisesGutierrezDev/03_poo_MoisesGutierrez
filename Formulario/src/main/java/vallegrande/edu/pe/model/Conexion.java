package vallegrande.edu.pe.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    // Reemplaza 'tu_base_de_datos' por el nombre exacto del schema en phpMyAdmin / MySQL Workbench
    private static final String URL = "jdbc:mysql://localhost:3306/usuario";
    private static final String USER = "root";
    private static final String PASS = "123";     // Coloca tu contraseña si usas una

    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.err.println("--- ERROR AL CONECTAR A MYSQL ---");
            e.printStackTrace();
        }
        return con;
    }
}
