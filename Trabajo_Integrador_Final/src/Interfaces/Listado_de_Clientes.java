package Interfaces;

import BD.ConexionBD;
import modelo.ITaller;
import modelo.Cliente;
import DAO.ClienteDAO;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Listado_de_Clientes extends JFrame {
    private final ITaller sistema;
    private JTextArea area;

    public Listado_de_Clientes(ITaller sistema) {
        super("Clientes");
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

        JPanel bottom = new JPanel(new FlowLayout());
        JTextField txtId = new JTextField(10);
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> {
            try {
                String id = txtId.getText().trim();
                sistema.eliminarCliente(id);
                cargar();
                JOptionPane.showMessageDialog(this, "Cliente eliminado");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Carnet debe ser número");
            }
        });

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> {
            String id = txtId.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el carnet del cliente a modificar.");
                return;
            }

            Cliente encontrado = null;
            try {
                ClienteDAO dao = new ClienteDAO();
                List<Cliente> clientes = dao.listarTodos();
                for (Cliente c : clientes) {
                    if (id.equals(String.valueOf(c.getCarnet_identidad()))) {
                        encontrado = c;
                        break;
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al buscar clientes: " + ex.getMessage());
                return;
            }

            if (encontrado == null) {
                JOptionPane.showMessageDialog(this, "Cliente con carnet " + id + " no encontrado.");
                return;
            }

            DialogEditarCliente dialog = new DialogEditarCliente(this, encontrado);
            dialog.setVisible(true);
            if (dialog.isGuardado()) {
                try {
                    ClienteDAO dao = new ClienteDAO();
                    dao.actualizarCliente(encontrado);
                    cargar();
                    JOptionPane.showMessageDialog(this, "Cliente modificado.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al actualizar cliente: " + ex.getMessage());
                }
            }
        });

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> dispose());

        bottom.add(new JLabel("Carnet a eliminar/editar:"));
        bottom.add(txtId);
        bottom.add(btnEliminar);
        bottom.add(btnModificar);
        bottom.add(btnSalir);
        add(bottom, BorderLayout.SOUTH);
    }

    private void cargar() {
        try(Connection con = ConexionBD.getConexion()){
            ClienteDAO dao = new ClienteDAO();
            List<Cliente> clientes = dao.listarTodos();
            StringBuilder sb = new StringBuilder();
            for (Cliente c : clientes) {
                sb.append("Carnet: ").append(c.getCarnet_identidad())
                        .append(" - ").append(c.getNombre()).append(" ").append(c.getApellido()).append("\n");
            }
            area.setText(sb.toString());
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Diálogo interno para editar cliente
    private static class DialogEditarCliente extends JDialog {
        private final JTextField tfNombre;
        private final JTextField tfApellido;
        private final JTextField tfIdTaller;
        private boolean guardado = false;
        private final Cliente cliente;

        public DialogEditarCliente(Window owner, Cliente cliente) {
            super(owner, "Editar Cliente", ModalityType.APPLICATION_MODAL);
            this.cliente = cliente;

            setLayout(new BorderLayout());
            JPanel form = new JPanel(new GridLayout(0,2,6,6));
            form.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));

            form.add(new JLabel("Nombre:")); tfNombre = new JTextField(cliente.getNombre()); form.add(tfNombre);
            form.add(new JLabel("Apellido:")); tfApellido = new JTextField(cliente.getApellido()); form.add(tfApellido);
            form.add(new JLabel("ID Taller:")); tfIdTaller = new JTextField(String.valueOf(cliente.getId_taller())); form.add(tfIdTaller);

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
            if (tfNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre es obligatorio.");
                return;
            }
            cliente.setNombre(tfNombre.getText().trim());
            cliente.setApellido(tfApellido.getText().trim());
            try {
                int idT = Integer.parseInt(tfIdTaller.getText().trim());
                cliente.setId_taller(idT);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID Taller debe ser número.");
                return;
            }
            guardado = true;
            dispose();
        }

        public boolean isGuardado() { return guardado; }
        public Cliente getCliente() { return cliente; }
    }
}
