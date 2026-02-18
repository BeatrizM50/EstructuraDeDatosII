package Interfaces;

import modelo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.accessibility.AccessibleContext;

public class Inicio_de_sesion extends JFrame {

    private final Taller sistema;
    private JTextField txtUsuario;
    private JPasswordField txtContra;

    public Inicio_de_sesion(Taller sistema) {
        super("Inicio de sesión");
        this.sistema = sistema;
        initComponents();
    }

    public void setAccessibleContext(AccessibleContext accessibleContext) {
        this.accessibleContext = accessibleContext;
    }

    class RoundedPanel extends JPanel {

        private int cornerRadius;

        public RoundedPanel(int radius) {
            super();
            this.cornerRadius = radius;
            setOpaque(false);

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            g2.dispose();
        }
    }

    class RoundedBackgroundPanel extends JPanel {

        private int cornerRadius;

        public RoundedBackgroundPanel(int radius) {
            super();
            this.cornerRadius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
    }

    class RoundedButton extends JButton {

        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setBackground(new Color(226, 164, 115));
            setPreferredSize(new Dimension(100, 30));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // esquinas redondeadas

            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            // Sin borde
        }
    }

    private JPanel crearCampoConIcono(JComponent campo, String iconPath) {
        JPanel panelCampo = new JPanel(new BorderLayout());
        panelCampo.setOpaque(false);
        panelCampo.setPreferredSize(new Dimension(280, 35));
        campo.setBackground(new Color(245, 245, 245));

        panelCampo.add(campo, BorderLayout.CENTER);

        ImageIcon icono = new ImageIcon(getClass().getResource(iconPath));
        Image imgEscalada = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel lblIcono = new JLabel(new ImageIcon(imgEscalada));
        lblIcono.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        panelCampo.add(lblIcono, BorderLayout.WEST);

        return panelCampo;
    }

    class RoundedFieldPanel extends JPanel {

        private int cornerRadius;

        public RoundedFieldPanel(int radius) {
            super(new BorderLayout());
            this.cornerRadius = radius;
            setOpaque(false);
            setBackground(new Color(245, 245, 245)); // gris muy claro
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

            g2.dispose();
        }
    }

    class ImagenFondoPanel extends JPanel {

        private Image imagenn;

        public ImagenFondoPanel(String rutaImagen) {
            imagenn = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(imagenn, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setBackground(new Color(1, 26, 59));
        setLayout(new BorderLayout());

        // Fondo azul claro
        ImagenFondoPanel fondo = new ImagenFondoPanel("/Imagenes/fondo.jpg"); // usa tu imagen
        fondo.setLayout(new GridBagLayout());
        add(fondo, BorderLayout.CENTER);

        // Panel blanco centrado
        RoundedPanel panel = new RoundedPanel(30);
        panel.setBackground(new Color(41, 46, 75));
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(300, 260));
        fondo.add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        ImageIcon icono = new ImageIcon(getClass().getResource("/Imagenes/user_icon.jpg"));
        Image imagenEscalada = icono.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        icono = new ImageIcon(imagenEscalada);
        JLabel lblIcono = new JLabel(icono);
        panel.add(lblIcono, gbc);

        // Campo Usuario con ícono
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        txtUsuario = new JTextField();
        txtUsuario = new JTextField(18);
        txtUsuario.setBorder(BorderFactory.createEmptyBorder());
        RoundedFieldPanel campoUsuario = new RoundedFieldPanel(20);
        ImageIcon iconUser = new ImageIcon(getClass().getResource("/Imagenes/icon_user.png"));
        Image imgUser = iconUser.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel lblUserIcon = new JLabel(new ImageIcon(imgUser));
        campoUsuario.add(lblUserIcon, BorderLayout.WEST);
        campoUsuario.add(txtUsuario, BorderLayout.CENTER);
        panel.add(campoUsuario, gbc);

        // Campo Contraseña con ícono
        gbc.gridy = 2;
        txtContra = new JPasswordField();
        txtContra.setBorder(BorderFactory.createEmptyBorder());
        RoundedFieldPanel campoContra = new RoundedFieldPanel(20);
        ImageIcon iconLock = new ImageIcon(getClass().getResource("/imagenes/icon_lock.png"));
        Image imgLock = iconLock.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel lblLockIcon = new JLabel(new ImageIcon(imgLock));
        campoContra.add(lblLockIcon, BorderLayout.WEST);
        campoContra.add(txtContra, BorderLayout.CENTER);
        panel.add(campoContra, gbc);

        // Botón OK centrado con esquinas redondeadas
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        RoundedButton btnOk = new RoundedButton("OK");
        btnOk.addActionListener(this::onLogin);
        panel.add(btnOk, gbc);
        getRootPane().setDefaultButton(btnOk);

        // Enlace Registrar Trabajador
        gbc.gridy = 4;
        JLabel lblRegistrar = new JLabel("< Registrar Trabajador >", SwingConstants.CENTER);
        lblRegistrar.setForeground(new Color(226, 164, 115));
        lblRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Registrar_Trabajador(sistema).setVisible(true);
            }
        }
        );

        panel.add(lblRegistrar, gbc);

    }

    private void onLogin(ActionEvent event) {
        String usuario = txtUsuario.getText().trim();
        String contra = new String(txtContra.getPassword());

        if (sistema.login(usuario, contra)) {
            sistema.cargarDatos();
            new Menu_Principal(sistema).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
        }
    }
}
