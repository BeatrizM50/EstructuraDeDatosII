package Interfaces;

import modelo.ITaller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Agregar_Motorina extends JFrame {

    private final ITaller sistema;
    private JRadioButton rbExistente, rbNuevo;
    private JTextField txtIdCliente, txtNombre, txtApellido;
    private JTextField txtModelo, txtCategoria, txtMotor, txtBateria;
    private JButton btnAgregar, btnSalir;

    public Agregar_Motorina(ITaller sistema) {
        super("Agregar motorina");
        this.sistema = sistema;
        initComponents();
        setVisible(true); // 🔑 MUY IMPORTANTE
    }

    private void initComponents() {
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Fondo con imagen
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

// Fondo con imagen (imagen #1)
        ImageIcon fondoIcono = new ImageIcon("src/Imagenes/fondo.png");
        JLabel fondo = new JLabel(fondoIcono);
        fondo.setLayout(null); // permite añadir componentes encima
        setContentPane(fondo);
        
// Panel oscuro encima del fondo (como en imagen #2)
        JPanel panelOscuro = new JPanel(null);
        panelOscuro.setBackground(new Color(30, 30, 30, 200)); // oscuro con transparencia
        panelOscuro.setBounds(100, 50, 400, 500); // centrado y más estrecho
        fondo.add(panelOscuro);

        // Panel oscuro encima del fondo
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(30, 30, 30, 200)); // oscuro con transparencia
        panel.setPreferredSize(new Dimension(400, 500));
        panel.setBounds(100, 50, 400, 500);
        fondo.add(panel);

        // Icono superior (imagen #4)
        JLabel iconoSuperior = new JLabel(new ImageIcon("/Imagenes/icon_agregar.jpg"));
        iconoSuperior.setBounds(150, 10, 100, 100);
        panel.add(iconoSuperior);

        // Campos motorina
        txtModelo = crearCampo(panel, "Modelo:", 120, 220);
        txtCategoria = crearCampo(panel, "Categoría:", 160, 220);
        txtMotor = crearCampo(panel, "Motor:", 200, 220);
        txtBateria = crearCampo(panel, "Batería:", 240, 220);

        // Cliente existente / nuevo
        ButtonGroup grupoCliente = new ButtonGroup();
        rbExistente = new JRadioButton("Cliente existente");
        rbExistente.setBounds(40, 280, 150, 25);
        rbExistente.setForeground(Color.WHITE);
        rbExistente.setOpaque(false);
        grupoCliente.add(rbExistente);
        panel.add(rbExistente);

        rbNuevo = new JRadioButton("Cliente nuevo");
        rbNuevo.setBounds(210, 280, 150, 25);
        rbNuevo.setForeground(Color.WHITE);
        rbNuevo.setOpaque(false);
        grupoCliente.add(rbNuevo);
        panel.add(rbNuevo);
        rbExistente.setSelected(true);

        txtIdCliente = crearCampo(panel, "ID:", 320, 220);
        txtNombre = crearCampo(panel, "Nombre:", 360, 220);
        txtApellido = crearCampo(panel, "Apellido:", 400, 220);

        // Botón Agregar con icono (imagen #2)
        btnAgregar = new JButton("Agregar", new ImageIcon("/Imagenes/icon_add.png"));
        btnAgregar.setBounds(60, 450, 140, 40);
        btnAgregar.setBackground(new Color(100, 200, 100));
        btnAgregar.addActionListener(this::onAgregar);
        panel.add(btnAgregar);

        // Botón Salir con icono (imagen #3)
        btnSalir = new JButton("Salir", new ImageIcon("/Imagenes/icon_exit.png"));
        btnSalir.setBounds(200, 450, 140, 40);
        btnSalir.setBackground(new Color(200, 100, 100));
        btnSalir.addActionListener(e -> dispose());
        panel.add(btnSalir);

        // Comportamiento dinámico
        rbExistente.addActionListener(e -> {
            txtNombre.setEnabled(false);
            txtApellido.setEnabled(false);
        });

        rbNuevo.addActionListener(e -> {
            txtNombre.setEnabled(true);
            txtApellido.setEnabled(true);
        });
    }

    private JTextField crearCampo(JPanel panel, String texto, int y, int ancho) {
        JLabel label = new JLabel(texto);
        label.setBounds(30, y, 100, 25);
        label.setForeground(Color.WHITE);
        panel.add(label);

        JTextField campo = new JTextField();
        campo.setBounds(130, y, ancho, 25);
        panel.add(campo);
        return campo;
    }

    private void onAgregar(ActionEvent e) {
        try {
            boolean clienteExistente = rbExistente.isSelected();
            String idCliente = txtIdCliente.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();

            String modelo = txtModelo.getText().trim();
            String categoria = txtCategoria.getText().trim();
            String motor = txtMotor.getText().trim();
            String bateria = txtBateria.getText().trim();

            if (modelo.isEmpty() || categoria.isEmpty() || motor.isEmpty() || bateria.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completa los datos de la motorina");
                return;
            }
            if (!clienteExistente && (nombre.isEmpty() || apellido.isEmpty())) {
                JOptionPane.showMessageDialog(this, "Completa nombre y apellido del cliente nuevo");
                return;
            }
            if (!clienteExistente && sistema.existeIdCliente(idCliente)) {
                JOptionPane.showMessageDialog(this, "Id ya existe");
                return;
            }

            if (clienteExistente && !sistema.existeIdCliente(idCliente)){
                JOptionPane.showMessageDialog(this, "Id no existe en el registro");
                return;
            }

            sistema.agregarMotorina(clienteExistente, idCliente, nombre, apellido, modelo, categoria, motor, bateria, true);

            JOptionPane.showMessageDialog(this, "Motorina agregada y en pendientes");
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID Cliente debe ser número (carnet)");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
