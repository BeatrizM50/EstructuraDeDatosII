package DAO;

import BD.ConexionBD;
import Estructuras.Cola;
import java.sql.*;
import java.util.*;
import java.time.*;
import modelo.*;

public class HistorialCambiosDAO {

    // Insertar un historial
    public void insertar(HistorialCambios h) {
        String sql = "INSERT INTO historial(id_historial, id_taller, descripcion, fecha) VALUES (?, ?, ?, ?)";

        try (  Connection conn = ConexionBD.getConexion();
               PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, h.getId_historial());
            ps.setInt(2, h.getId_taller());
            ps.setString(3, h.getDescripcion());
            ps.setTimestamp(4, Timestamp.valueOf(h.getFechaHora()));
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todos los historiales
    public Cola<HistorialCambios> listarTodos()  {
        Cola<HistorialCambios> lista = new Cola();
        String sql = "SELECT * FROM historial";
        try ( Connection conn = ConexionBD.getConexion();
               Statement ps = conn.createStatement(); 
               ResultSet rs = ps.executeQuery(sql)) {

            while (rs.next()) {
   
                lista.add( new HistorialCambios( rs.getInt("id_historial"),
               rs.getInt("id_taller"),
                 rs.getString("descripcion"),
                 rs.getTimestamp("fecha").toLocalDateTime()));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Buscar historial por id
    public HistorialCambios buscarPorId(int id_historial) {
        String sql = "SELECT * FROM historial_cambios WHERE id_historial = ?";
        try ( Connection conn = ConexionBD.getConexion();
              PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_historial);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new HistorialCambios( rs.getInt("id_historial"),
               rs.getInt("id_taller"),
                 rs.getString("descripcion"),
                 rs.getTimestamp("fecha").toLocalDateTime());
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
