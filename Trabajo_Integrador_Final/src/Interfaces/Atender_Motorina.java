package Interfaces;

import modelo.*;
import javax.swing.*;
import java.awt.*;
import Estructuras.*;

public class Atender_Motorina extends JFrame {

    private final Taller sistema;

    public Atender_Motorina(Taller sistema) {
        super("Atender Motorina");
        this.sistema = sistema;
        initComponents();
    }
    
    private void cargarPendientes() {
    StringBuilder sb = new StringBuilder();
    Cola<Motorina> aux = new Cola<>();
    while (!sistema.getPendientes().isEmpty()) {
       Motorina p = sistema.getPendientes().poll();
        sb.append("ID: ").append(p.getId())
          .append(" - Modelo: ").append(p.getModelo())
          .append(" - Cliente: ").append(p.getCarnetIdentidad()).append("\n");
        aux.add(p);
    }
    while (!aux.isEmpty()) {
        sistema.getPendientes().add(aux.poll());
    }
    area.setText(sb.toString());

}


    private JTextArea area;

private void initComponents() {
    setSize(400, 300);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    area = new JTextArea();
    area.setEditable(false);
    add(new JScrollPane(area), BorderLayout.CENTER);

    JPanel botones = new JPanel(new FlowLayout());
    JButton btnAtender = new JButton("Atender");
    btnAtender.addActionListener(e -> {
       int id = sistema.getTrabajadorActivo().getId_trabajador(); 
       if (sistema.atenderMotorina(id)) { 
           JOptionPane.showMessageDialog(this, "Motorina atendida"); 
            cargarPendientes();
       } else { 
           JOptionPane.showMessageDialog(this, "La lista de pendientes está vacía"); }
    });
    botones.add(btnAtender);

    JButton btnSalir = new JButton("Salir");
    btnSalir.addActionListener(e -> dispose());
    botones.add(btnSalir);

    add(botones, BorderLayout.SOUTH);

    cargarPendientes(); // carga inicial
}

}
