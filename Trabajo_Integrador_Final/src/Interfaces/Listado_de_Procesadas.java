package Interfaces;

import modelo.*;
import Estructuras.*;
import javax.swing.*;
import java.awt.*;

public class Listado_de_Procesadas extends JFrame {
    private final Taller sistema;
    private JTextArea area;

    public Listado_de_Procesadas(Taller sistema) {
        super("Procesadas");
        this.sistema = sistema;
        initComponents();
        cargar();
    }

    private void initComponents() {
        setSize(420, 320);
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
        Pila<Motorina> aux = new Pila<>();
        while (!sistema.getAtendidos().isEmpty()) {
            Motorina a = sistema.getAtendidos().Pop();
            sb.append("ID: ").append(a.getId()).append(" - Modelo: ").append(a.getModelo())
              .append(" - Cliente: ").append(a.getCarnetIdentidad()).append("\n");
            aux.Push(a);
        }
        // Restaurar
        while (!aux.isEmpty()) {
            sistema.getAtendidos().Push(aux.Pop());
        }
        area.setText(sb.toString());
    }
}
