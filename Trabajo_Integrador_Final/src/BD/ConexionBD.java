package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:postgresql://localhost:5432/taller_motorinas";
    private static final String USUARIO = "postgres";
    private static final String CLAVE = "1234";

    public static Connection getConexion() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (SQLException e) {
            System.out.println(" Error al conectar: " + e.getMessage());
        }
        return conexion;
    }
}
