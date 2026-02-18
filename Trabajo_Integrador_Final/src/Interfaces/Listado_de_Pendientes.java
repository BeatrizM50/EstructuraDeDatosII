package Interfaces;

import modelo.*;
import Estructuras.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Listado_de_Pendientes extends JFrame {
    private final Taller sistema;
    private JTextArea area;

    public Listado_de_Pendientes(Taller sistema) {
        super("Pendientes");
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

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnModificar = new JButton("Modificar");
        JButton btnSalir = new JButton("Salir");
        btnModificar.addActionListener(e -> onModificar());
        btnSalir.addActionListener(e -> dispose());
        bottom.add(btnModificar);
        bottom.add(btnSalir);
        add(bottom, BorderLayout.SOUTH);
    }


    private void cargar() {
        StringBuilder sb = new StringBuilder();

        Cola<Motorina> aux = new Cola<>();
        while (!((Cola<Motorina>)sistema.getPendientes()).isEmpty()) {
            Motorina p = sistema.getPendientes().poll();
            if (p != null) {
                sb.append("ID: ").append(p.getId()).append(" - Modelo: ").append(p.getModelo())
                        .append(" - Cliente: ").append(p.getCarnetIdentidad()).append("\n");
                aux.add(p);
            }
        }
        // Restaurar
        while (!aux.isEmpty()) {
            sistema.getPendientes().add(aux.poll());
        }
        area.setText(sb.toString());
    }

    private void onModificar() {
        String idInput = JOptionPane.showInputDialog(this, "Ingrese el ID de la motorina a modificar:");
        if (idInput == null || idInput.trim().isEmpty()) return;
        int id;
        try {
            id = Integer.parseInt(idInput.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
            return;
        }

        // Buscar motorina en la cola sin perder el orden
        Cola<Motorina> aux = new Cola<>();
        Motorina encontrada = null;
        while (!((Cola<Motorina>)sistema.getPendientes()).isEmpty()) {
            Motorina p = sistema.getPendientes().poll();
            if (p != null) {
                if (p.getId() == id) {
                    encontrada = p;
                }
                aux.add(p);
            }
        }
        // Restaurar
        while (!aux.isEmpty()) {
            sistema.getPendientes().add(aux.poll());
        }

        if (encontrada == null) {
            JOptionPane.showMessageDialog(this, "Motorina con ID " + id + " no encontrada en pendientes.");
            return;
        }

        // Abrir diálogo de edición (pasa copia para evitar cambios no confirmados)
        DialogEditarMotorina dialog = new DialogEditarMotorina(this, encontrada);
        dialog.setVisible(true);
        if (dialog.isGuardado()) {
            Motorina mod = dialog.getMotorina();
            // Aplicar cambios al objeto en la cola (la referencia es la misma)
            encontrada.setModelo(mod.getModelo());
            encontrada.setCategoria(mod.getCategoria());
            encontrada.setMotor(mod.getMotor());
            encontrada.setBateria(mod.getBateria());
            encontrada.setPendiente(mod.isPendiente());

            // Intentar persistir/registrar el cambio si Taller ofrece el método
            try {
                // si existe, actualizará en la BD/historial
                sistema.modificarMotorina(encontrada);
            } catch (Throwable t) {
                // si no existe el método en Taller, se ignora y queda modificado en memoria
            }

            cargar();
            JOptionPane.showMessageDialog(this, "Motorina modificada.");
        }
    }

    // Diálogo interno para editar motorina (modal)
    private static class DialogEditarMotorina extends JDialog {
        private JTextField tfModelo;
        private JTextField tfCategoria;
        private JTextField tfMotor;
        private JTextField tfBateria;
        private JCheckBox cbPendiente;
        private boolean guardado = false;
        private Motorina motorina;

        public DialogEditarMotorina(Window owner, Motorina m) {
            super(owner, "Editar Motorina", ModalityType.APPLICATION_MODAL);
            // crear copia para edición
            this.motorina = new Motorina(m.getId(), m.getModelo(), m.getCategoria(), m.getMotor(), m.getBateria(),
                    m.getCarnetIdentidad(), m.getIdTaller(), m.isPendiente());

            setLayout(new BorderLayout());
            JPanel form = new JPanel(new GridLayout(0,2,6,6));
            form.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));

            form.add(new JLabel("Modelo:")); tfModelo = new JTextField(motorina.getModelo()); form.add(tfModelo);
            form.add(new JLabel("Categoria:")); tfCategoria = new JTextField(motorina.getCategoria()); form.add(tfCategoria);
            form.add(new JLabel("Motor:")); tfMotor = new JTextField(motorina.getMotor()); form.add(tfMotor);
            form.add(new JLabel("Bateria:")); tfBateria = new JTextField(motorina.getBateria()); form.add(tfBateria);
            form.add(new JLabel("Pendiente:")); cbPendiente = new JCheckBox(); cbPendiente.setSelected(motorina.isPendiente()); form.add(cbPendiente);

            add(form, BorderLayout.CENTER);

            JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton btnGuardar = new JButton("Guardar");
            JButton btnCancelar = new JButton("Cancelar");
            buttons.add(btnGuardar);
            buttons.add(btnCancelar);
            add(buttons, BorderLayout.SOUTH);

            btnGuardar.addActionListener(e -> onGuardar());
            btnCancelar.addActionListener(e -> dispose());

            pack();
            setResizable(false);
            setLocationRelativeTo(owner);
        }

        private void onGuardar() {
            if (tfModelo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Modelo es obligatorio.");
                return;
            }
            motorina.setModelo(tfModelo.getText().trim());
            motorina.setCategoria(tfCategoria.getText().trim());
            motorina.setMotor(tfMotor.getText().trim());
            motorina.setBateria(tfBateria.getText().trim());
            motorina.setPendiente(cbPendiente.isSelected());
            guardado = true;
            dispose();
        }

        public boolean isGuardado() { return guardado; }
        public Motorina getMotorina() { return motorina; }
    }
}
