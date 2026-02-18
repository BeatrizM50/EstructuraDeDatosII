package DAO;

import BD.ConexionBD;
import modelo.Trabajador;
import java.sql.*;
import java.util.*;

public class TrabajadorDAO {

    public void insertar(Trabajador t) {
        String sql = """
            INSERT INTO trabajador
            (usuario, nombre, apellido, contrasena, id_taller)
            VALUES (?, ?, ?, ?, ?)
            """;
        try ( Connection conn = ConexionBD.getConexion();
              PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getUsuario());
            ps.setString(2, t.getNombre());
            ps.setString(3, t.getApellido());
            ps.setString(4, t.getContraseña());
            ps.setInt(5, t.getId_taller());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Trabajador> listarPorTaller(int idTaller) {
        List<Trabajador> lista = new ArrayList<>();
        String sql = "SELECT * FROM trabajador WHERE id_taller = ?";
        try ( Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTaller);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Trabajador t = new Trabajador(
                        rs.getInt("id_trabajador"),
                        rs.getString("usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("contrasena"),
                        rs.getInt("id_taller")
                );

                lista.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Trabajador buscarPorUsuario(String Usuario) {
        String sql = "SELECT * FROM trabajador WHERE usuario = ?";
        try ( Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, Usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Trabajador(
                        rs.getInt("id_trabajador"),
                        rs.getString("usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("contrasena"),
                        rs.getInt("id_taller")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void eliminarPorId(int idTrabajador) {
        String sql = "DELETE FROM trabajador WHERE id_trabajador = ?";
        try ( Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTrabajador);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
