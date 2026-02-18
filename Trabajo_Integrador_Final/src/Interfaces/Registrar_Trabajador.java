package Interfaces;

import modelo.ITaller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Registrar_Trabajador extends JFrame {

    private final ITaller sistema;
    private JTextField txtUsuario, txtNombre, txtApellido;
    private JPasswordField txtContra;

    public Registrar_Trabajador(ITaller sistema) {
        super("Registrar Trabajador");
        this.sistema = sistema;
        initComponents();
    }

    private void initComponents() {
        // Tamaño deseado de la ventana
        final int WIDTH = 420;
        final int HEIGHT = 500;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // --- Fondo escalado ---
        ImageIcon bgIcon = loadIconScaled("/Imagenes/fondo.jpg", WIDTH, HEIGHT);
        JLabel background = new JLabel();
        if (bgIcon != null) {
            background.setIcon(bgIcon);
            background.setLayout(new GridBagLayout()); // para centrar panel oscuro
            background.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setContentPane(background);
        } else {
            getContentPane().setBackground(Color.DARK_GRAY);
            getContentPane().setLayout(new GridBagLayout());
            getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        }

        // --- Panel oscuro central con esquinas redondeadas ---
        Color darkColor = new Color(41, 46, 75); // semitransparente
        RoundedPanel darkPanel = new RoundedPanel(24, darkColor);
        darkPanel.setPreferredSize(new Dimension(340, 390));
        darkPanel.setLayout(new BorderLayout(10, 10));
        darkPanel.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));

        // --- Icono superior (más pequeño o más grande según prefieras) ---
        JPanel topIconPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topIconPanel.setOpaque(false);
        ImageIcon addUserIcon = loadIconScaled("/Imagenes/icon_adduser.jpg", 72, 72);
        JLabel lblIcon;
        if (addUserIcon != null) {
            lblIcon = new JLabel(addUserIcon);
        } else {
            lblIcon = new JLabel();
        }
        lblIcon.setPreferredSize(new Dimension(72, 72));
        topIconPanel.add(lblIcon);
        topIconPanel.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));
        darkPanel.add(topIconPanel, BorderLayout.NORTH);

        // --- Panel central con los campos (alineados verticalmente) ---
        // Panel central con los campos (alineados verticalmente y centrados)
        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.fill = GridBagConstraints.NONE;          // no estirar horizontalmente
        c.weightx = 0.0;                           // no ocupar todo el ancho
        c.anchor = GridBagConstraints.CENTER;      // centrar cada fila
        c.insets = new Insets(6, 0, 6, 0);

// Crear campos
        txtUsuario = new JTextField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtContra = new JPasswordField();

// Añadir filas centradas y más cortas
        c.gridy = 0;
        center.add(createStyledFieldSmall("/Imagenes/icon_user.jpg", txtUsuario), c);
        c.gridy = 1;
        center.add(createStyledFieldSmall("/Imagenes/icon_name.jpg", txtNombre), c);
        c.gridy = 2;
        center.add(createStyledFieldSmall("/Imagenes/icon_id.jpg", txtApellido), c);
        c.gridy = 3;
        center.add(createStyledFieldSmall("/Imagenes/icon_lock.jpg", txtContra), c);

        darkPanel.add(center, BorderLayout.CENTER);

        // --- Panel inferior con botones ---
        // Panel inferior con botones (solo iconos)
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        buttonsPanel.setOpaque(false);

// Cargar iconos escalados (ajusta tamaños si quieres)
        ImageIcon addIconOnly = loadIconScaled("/Imagenes/icon_add.png", 28, 28);
        ImageIcon exitIconOnly = loadIconScaled("/Imagenes/icon_exit.png", 64, 64);

// Botón Agregar (solo icono)
        JButton btnRegistrar = new JButton();
        if (addIconOnly != null) {
            btnRegistrar.setIcon(addIconOnly);
        }
        btnRegistrar.setToolTipText("Agregar");
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setContentAreaFilled(false);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setOpaque(false);
        btnRegistrar.setPreferredSize(new Dimension(40, 40)); // espacio alrededor del icono
        btnRegistrar.addActionListener(this::onRegistrar);

// Botón Salir (solo icono)
        JButton btnSalir = new JButton();
        if (exitIconOnly != null) {
            btnSalir.setIcon(exitIconOnly);
        }
        btnSalir.setToolTipText("Salir");
        btnSalir.setFocusPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setBorderPainted(false);
        btnSalir.setOpaque(false);
        btnSalir.setPreferredSize(new Dimension(40, 40));
        btnSalir.addActionListener(e -> dispose());

// Añadir al panel
        buttonsPanel.add(btnRegistrar);
        buttonsPanel.add(btnSalir);

// Añade buttonsPanel al darkPanel como antes
        darkPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Añadir panel oscuro al content pane (centrado)
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        getContentPane().add(darkPanel, gbc);

        // Forzar tamaño final, centrar y dejar listo para mostrar
        setResizable(false);
        setSize(WIDTH, HEIGHT);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null); // centra la ventana en pantalla
    }

    private JComponent createStyledField(String iconPath, JComponent field) {
        // Panel contenedor con fondo blanco redondeado
        RoundedPanel wrapper = new RoundedPanel(12, Color.WHITE);
        wrapper.setLayout(new BorderLayout(10, 0));
        wrapper.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        // Icono (escalado a 28x28)
        ImageIcon icon = loadIconScaled(iconPath, 28, 28);
        JLabel lblIcon = new JLabel();
        if (icon != null) {
            lblIcon.setIcon(icon);
        }
        lblIcon.setPreferredSize(new Dimension(34, 34));
        lblIcon.setOpaque(false);

        // Campo: quitar opacidad para que el wrapper blanco sea visible
        if (field instanceof JTextField) {
            ((JTextField) field).setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
            ((JTextField) field).setOpaque(false);
        } else if (field instanceof JPasswordField) {
            ((JPasswordField) field).setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
            ((JPasswordField) field).setOpaque(false);
        }

        wrapper.add(lblIcon, BorderLayout.WEST);
        wrapper.add(field, BorderLayout.CENTER);

        // Forzar tamaño preferido para consistencia visual
        wrapper.setPreferredSize(new Dimension(360, 48));
        return wrapper;
    }

    // Carga un ImageIcon escalado (devuelve null si no existe)
    private ImageIcon loadIconScaled(String resourcePath, int width, int height) {
        try {
            java.net.URL imgURL = getClass().getResource(resourcePath);
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            } else {
                System.err.println("Recurso no encontrado: " + resourcePath);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void onRegistrar(ActionEvent e) {
        String u = txtUsuario.getText().trim();
        String n = txtNombre.getText().trim();
        String a = txtApellido.getText().trim();
        String c = new String(txtContra.getPassword());
        
        if (u.isEmpty() || n.isEmpty() || a.isEmpty() || c.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos");
            return;
        }
        if(sistema.existeUsuario(u)){
            JOptionPane.showMessageDialog(this, "Error: Ya existe usuario!!");
            return;
        }
        sistema.registrarTrabajador(u, n, a, c);
        JOptionPane.showMessageDialog(this, "Trabajador registrado");
        dispose();
    }

    // ----------------- Clases auxiliares -----------------
    static class RoundedPanel extends JPanel {

        private final int cornerRadius;
        private Color backgroundColor;

        public RoundedPanel(int cornerRadius, Color bgColor) {
            this.cornerRadius = cornerRadius;
            this.backgroundColor = bgColor;
            setOpaque(false); // dejamos que paintComponent dibuje el fondo
        }

        public void setBackgroundColor(Color c) {
            this.backgroundColor = c;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            int width = getWidth();
            int height = getHeight();
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Pintar fondo redondeado con el color actual
            g2.setColor(backgroundColor != null ? backgroundColor : getBackground());
            g2.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);

            // Borde sutil
            g2.setColor(new Color(0, 0, 0, 40));
            g2.setStroke(new BasicStroke(1f));
            g2.drawRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);

            g2.dispose();

            // Importante: no dejamos que super vuelva a pintar el fondo por defecto
            super.paintComponent(g);
        }
    }

    private JComponent createStyledFieldSmall(String iconPath, JComponent field) {
        // Wrapper blanco totalmente opaco visualmente (usamos alpha 255 para blanco sólido)
        RoundedPanel wrapper = new RoundedPanel(12, new Color(255, 255, 255, 255));
        wrapper.setLayout(new BorderLayout(8, 0));
        wrapper.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        // Icono escalado (más grande si quieres)
        ImageIcon icon = loadIconScaled(iconPath, 24, 24);
        JLabel lblIcon = new JLabel();
        if (icon != null) {
            lblIcon.setIcon(icon);
        }
        lblIcon.setPreferredSize(new Dimension(36, 36));
        lblIcon.setOpaque(false);

        // Campo: transparente para que se vea el wrapper blanco
        if (field instanceof JTextField) {
            ((JTextField) field).setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            ((JTextField) field).setOpaque(false); // **crucial**
            ((JTextField) field).setBackground(new Color(0, 0, 0, 0));
        } else if (field instanceof JPasswordField) {
            ((JPasswordField) field).setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            ((JPasswordField) field).setOpaque(false); // **crucial**
            ((JPasswordField) field).setBackground(new Color(0, 0, 0, 0));
        }

        wrapper.add(lblIcon, BorderLayout.WEST);
        wrapper.add(field, BorderLayout.CENTER);

        // Tamaño preferido compacto
        wrapper.setPreferredSize(new Dimension(280, 40));
        wrapper.setMaximumSize(new Dimension(280, 40));
        return wrapper;
    }

}
