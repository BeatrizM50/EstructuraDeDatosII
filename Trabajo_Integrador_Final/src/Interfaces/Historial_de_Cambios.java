package Interfaces;

import Estructuras.Pila;
import modelo.*;
import javax.swing.*;
import java.awt.*;

public class Historial_de_Cambios extends JFrame {
    private final Taller sistema;
    private JTextArea area;

    public Historial_de_Cambios(Taller sistema) {
        super("Historial de cambios");
        this.sistema = sistema;
        initComponents();
        cargar();
    }

    private void initComponents() {
        setSize(480, 360);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        area = new JTextArea();
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);


        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> dispose());
        add(btnSalir, BorderLayout.SOUTH);
    }

    private void cargar() {
        StringBuilder sb = new StringBuilder();
        Pila<HistorialCambios> aux = new Pila<>();

        while(!sistema.getHistorialCambios().isEmpty()){
            HistorialCambios h = sistema.getHistorialCambios().Pop();
            if(h!=null){
                sb.append(h.getDescripcion()).append("\n");
                aux.Push(h);
            }

        }
        while(!aux.isEmpty()){
            sistema.getHistorialCambios().Push(aux.Pop());
        }
        area.setText(sb.toString());
    }

}
