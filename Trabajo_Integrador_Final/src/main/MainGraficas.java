package main;

import DAO.*;
import modelo.*;
import Interfaces.*;
import javax.swing.SwingUtilities;
       


public class MainGraficas {

    public static void main(String[] args) {
       
            HistorialCambiosDAO historialDAO = new HistorialCambiosDAO();
            TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            MotorinaDAO motorinaDAO = new MotorinaDAO();
            TallerDAO tallerDAO = new TallerDAO();
            
            Taller sistemaTaller = new Taller(1,historialDAO, trabajadorDAO, clienteDAO, motorinaDAO, tallerDAO);
             
            SwingUtilities.invokeLater(() -> {
            Inicio_de_sesion ventanaInicio = new Inicio_de_sesion(sistemaTaller);
            ventanaInicio.setVisible(true);
        });
    }
}
