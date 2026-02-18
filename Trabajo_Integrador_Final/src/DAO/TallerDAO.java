package DAO;

import BD.ConexionBD;
import java.sql.*;

public class TallerDAO {
   
    
    
    public int crearTaller() {
        String sql = "INSERT INTO taller DEFAULT VALUES RETURNING id_taller";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_taller");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
