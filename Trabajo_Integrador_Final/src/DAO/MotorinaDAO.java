package DAO;

import BD.ConexionBD;
import Estructuras.Cola;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.HistorialCambios;
import modelo.Motorina;

public class MotorinaDAO {
   

   public int insertar(Motorina m) {

    String sql = """
        INSERT INTO motorina (modelo, categoria, motor, bateria, carnet_identidad, id_taller,pendiente)
        VALUES (?, ?, ?, ?, ?, ?,?)
        RETURNING id_motorina
    """;

    try (Connection conn = ConexionBD.getConexion();
          PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, m.getModelo());
        ps.setString(2, m.getCategoria());
        ps.setString(3, m.getMotor());
        ps.setString(4, m.getBateria());
        ps.setString(5, m.getCarnetIdentidad());
        ps.setInt(6, m.getIdTaller());
        ps.setBoolean(7, m.isPendiente());

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt(1);
            m.setId(id); // 🔥 CLAVE
            return id;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1;
}


    public Motorina buscarPorId(int id) {
        String sql = "SELECT * FROM motorina WHERE id_motorina = ?";
        try (Connection conn = ConexionBD.getConexion();
              PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Motorina(
                        rs.getInt("id_motorina"),
                        rs.getString("modelo"),
                        rs.getString("categoria"),
                        rs.getString("motor"),
                        rs.getString("bateria"),
                        rs.getString("carnet_identidad"),
                        rs.getInt("id_taller"),
                        rs.getBoolean("pendiente")
                    );
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Motorina> listarPorCliente(String carnet) {
        List<Motorina> lista = new ArrayList<>();
        String sql = "SELECT * FROM motorina WHERE carnet_identidad = ?";
        try ( Connection conn = ConexionBD.getConexion();
              PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, carnet);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Motorina(
                        rs.getInt("id_motorina"),
                        rs.getString("modelo"),
                        rs.getString("categoria"),
                        rs.getString("motor"),
                        rs.getString("bateria"),
                        rs.getString("carnet_identidad"),
                        rs.getInt("id_taller"),
                        rs.getBoolean("pendiente")
                    ));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Cola<Motorina> listarTodas()  {
        Cola<Motorina> lista = new Cola();
        String sql = "SELECT * FROM motorina";
        try ( Connection conn = ConexionBD.getConexion();
              Statement ps = conn.createStatement();
              ResultSet rs = ps.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Motorina(
                        rs.getInt("id_motorina"),
                        rs.getString("modelo"),
                        rs.getString("categoria"),
                        rs.getString("motor"),
                        rs.getString("bateria"),
                        rs.getString("carnet_identidad"),
                        rs.getInt("id_taller"),
                        rs.getBoolean("pendiente")
                ));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

   public void eliminarPorCliente(String carnet_identidad)  {
    String sql = "DELETE FROM motorina WHERE carnet_identidad = ?";
    try (Connection conn = ConexionBD.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, carnet_identidad);
        ps.executeUpdate();
    }catch (SQLException e) {
            e.printStackTrace();
        }
}

    

    public Cola<Motorina> motorinasPendientes(){
     Cola<Motorina> listaP = new Cola<>();
     String sql = "SELECT * FROM motorina WHERE pendiente = true";
     try(Connection conn = ConexionBD.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
          ResultSet rs = ps.executeQuery() ){
          while (rs.next()) {
         listaP.add(  new Motorina(
                        rs.getInt("id_motorina"),
                        rs.getString("modelo"),
                        rs.getString("categoria"),
                        rs.getString("motor"),
                        rs.getString("bateria"),
                        rs.getString("carnet_identidad"),
                        rs.getInt("id_taller"),
                        rs.getBoolean("pendiente")));
          }
     
     }catch(SQLException e){ e.printStackTrace();}
     
     return listaP;
    }
    
    public Cola<Motorina> motorinasAtendidas(){
     Cola<Motorina> listaA = new Cola<>();
     String sql = "SELECT * FROM motorina WHERE pendiente = false";
     try(Connection conn = ConexionBD.getConexion();
          PreparedStatement ps = conn.prepareStatement(sql);
          ResultSet rs = ps.executeQuery() ){
          while (rs.next()) {
         listaA.add(new Motorina(
                        rs.getInt("id_motorina"),
                        rs.getString("modelo"),
                        rs.getString("categoria"),
                        rs.getString("motor"),
                        rs.getString("bateria"),
                        rs.getString("carnet_identidad"),
                        rs.getInt("id_taller"),
                        rs.getBoolean("pendiente")));
          }
     
     }catch(SQLException e){ e.printStackTrace();}
     
     return listaA;
    }
    
    public void cambiar_a_Atendido( int idMotorina){
        String sql = "UPDATE motorina SET pendiente = false WHERE id_motorina = ?";
        try(Connection conn = ConexionBD.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, idMotorina);
            ps.executeUpdate();
           
        }catch(SQLException e){ 
       
            e.printStackTrace();}
    }

    public void actualizar(Motorina m){
       String sql = "UPDATE motorina SET modelo = ?, categoria = ?, motor = ?, bateria = ?, carnet_identidad = ?, id_taller = ?, pendiente = ? WHERE id_motorina = ?";
       try(Connection conn = ConexionBD.getConexion();
           PreparedStatement ps = conn.prepareStatement(sql)){

           ps.setString(1, m.getModelo());
           ps.setString(2, m.getCategoria());
           ps.setString(3, m.getMotor());
           ps.setString(4, m.getBateria());
           ps.setString(5, m.getCarnetIdentidad());
           ps.setInt(6, m.getIdTaller());
           ps.setBoolean(7, m.isPendiente());
           ps.setInt(8, m.getId());

           ps.executeUpdate();

       }catch(SQLException e){
           e.printStackTrace();
       }
    }

}
