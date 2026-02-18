package DAO;


import BD.ConexionBD;
import modelo.Cliente;
import java.sql.*;
import java.util.*;

public class ClienteDAO {


    public void insertar(Cliente c) {
        String sql = """
        INSERT INTO cliente
        (carnet_identidad, nombre, apellido, id_taller)
        VALUES (?, ?, ?, ?)
        """;
        try (Connection conn = ConexionBD.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getCarnet_identidad());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellido());
            ps.setInt(4, c.getId_taller());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try ( Connection conn = ConexionBD.getConexion();
              PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
              while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getString("carnet_identidad"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("id_taller")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Cliente> listarPorTaller(int id_taller){
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE id_taller = ?";
       try (Connection conn = ConexionBD.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_taller);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cliente c = new Cliente(
                            rs.getString("carnet_identidad"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getInt("id_taller"));
                    lista.add(c);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Cliente buscarPorId(String idCliente) {
        String sql = "SELECT * FROM cliente WHERE carnet_identidad = ?";
        try (Connection conn = ConexionBD.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, idCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getString("carnet_identidad"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("id_taller")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void eliminarPorId(String carnetId) {
        String sql = "DELETE FROM cliente WHERE carnet_identidad = ? ";

  try (Connection conn = ConexionBD.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, carnetId);
        ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public void actualizarCliente(Cliente c) {
        if (c == null || c.getCarnet_identidad() == null || c.getCarnet_identidad().trim().isEmpty()) {
            return;
        }

        String sql = "UPDATE cliente SET nombre = ?, apellido = ?, id_taller = ? WHERE carnet_identidad = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setInt(3, c.getId_taller());
            ps.setString(4, c.getCarnet_identidad());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                // No se encontró cliente con ese carnet; opcional manejarlo (log, excepción, etc.)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
