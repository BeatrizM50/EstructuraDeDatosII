package Interfaces;

import modelo.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu_Principal extends JFrame {

    private final Taller sistema;

    public Menu_Principal(Taller sistema) {
        super("Menú Principal");
        this.sistema = sistema;
        initComponents();
    }

    private void initComponents() {
        setSize(500, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Fondo con imagen (capa semitransparente para contraste)
        ImagenFondoPanel fondo = new ImagenFondoPanel("/Imagenes/fondo.jpg");
        fondo.setLayout(new GridBagLayout()); // para centrar el panel oscuro
        add(fondo);

        // Panel oscuro con sombra y bordes redondeados (tarjeta centrada)
        JPanel panelOscuro = new JPanel(new BorderLayout(18, 18)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();
                int arc = 22;

        

                // Tarjeta principal
                g2.setColor(new Color(41, 46, 75));
                g2.fillRoundRect(0, 0, w , h, arc, arc);

                g2.dispose();
            }
        };
        panelOscuro.setOpaque(false);
        panelOscuro.setPreferredSize(new Dimension(230, 370));

        // Contenedor interior para respetar padding
        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setOpaque(false);
        contenido.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        panelOscuro.add(contenido, BorderLayout.CENTER);

        // Título
        JLabel titulo = new JLabel("MENÚ PRINCIPAL", SwingConstants.CENTER);
        titulo.setFont(new Font("Clarendon", Font.BOLD, 18));
        titulo.setForeground(new Color(227,164,113));
        titulo.setBorder(BorderFactory.createEmptyBorder(12, 0, 18, 0));
        contenido.add(titulo, BorderLayout.NORTH);

        // Panel de botones (3 filas x 2 columnas)
        // Panel de botones (3 filas x 2 columnas) con gaps muy pequeños
        JPanel panelBotones = new JPanel(new GridLayout(3, 2, 4, 4)); // gaps reducidos a 4px
        panelBotones.setOpaque(false);
       

// Crear botones pequeños con acciones concretas (ya los tienes)
        FlowLayout centeredNoGap = new FlowLayout(FlowLayout.CENTER, 0, 0);
        JButton b1 = crearBotonPequeno("/Imagenes/icon_agregar.jpg", () -> new Agregar_Motorina(sistema).setVisible(true));
        JButton b2 = crearBotonPequeno("/Imagenes/icon_atender.jpg", () -> new Atender_Motorina(sistema).setVisible(true));
        JButton b3 = crearBotonPequeno("/Imagenes/icon_pendientes.jpg", () -> new Listado_de_Pendientes(sistema).setVisible(true));
        JButton b4 = crearBotonPequeno("/Imagenes/icon_procesadas.jpg", () -> new Listado_de_Procesadas(sistema).setVisible(true));
        JButton b5 = crearBotonPequeno("/Imagenes/icon_historial.jpg", () -> new Historial_de_Cambios(sistema).setVisible(true));
        JButton b6 = crearBotonPequeno("/Imagenes/icon_clientes.jpg", () -> new Listado_de_Clientes(sistema).setVisible(true));

// Asegurar tamaño fijo en cada botón pequeño
        Dimension small = new Dimension(48, 48);
        for (JButton b : new JButton[]{b1, b2, b3, b4, b5, b6}) {
            b.setPreferredSize(small);
            b.setMinimumSize(small);
            b.setMaximumSize(small);
        }

// Envolver cada botón en un panel con FlowLayout centrado y sin gaps
        FlowLayout centered = new FlowLayout(FlowLayout.CENTER, 0, 0); // 0,0 elimina espacio extra
        JPanel c1 = new JPanel(centered);
        c1.setOpaque(false);
        c1.add(b1);
        JPanel c2 = new JPanel(centered);
        c2.setOpaque(false);
        c2.add(b2);
        JPanel c3 = new JPanel(centered);
        c3.setOpaque(false);
        c3.add(b3);
        JPanel c4 = new JPanel(centered);
        c4.setOpaque(false);
        c4.add(b4);
        JPanel c5 = new JPanel(centered);
        c5.setOpaque(false);
        c5.add(b5);
        JPanel c6 = new JPanel(centered);
        c6.setOpaque(false);
        c6.add(b6);

        panelBotones.add(c1);
        panelBotones.add(c2);
        panelBotones.add(c3);
        panelBotones.add(c4);
        panelBotones.add(c5);
        panelBotones.add(c6);

// Panel inferior con botón salir (separado)
        JPanel panelSalir = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelSalir.setOpaque(false);
// Añadir un borde superior para separar el bloque de botones del botón salir
        panelSalir.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0)); // 12px de separación arriba

        JButton salir = crearBotonPequeno("/Imagenes/icon_salir.jpg", () -> {
            sistema.cerrarSesion();
            dispose();
            System.exit(0);
        });
        Dimension salirSize = new Dimension(48, 48);
        salir.setPreferredSize(salirSize);
        salir.setMinimumSize(salirSize);
        salir.setMaximumSize(salirSize);
        panelSalir.add(salir);

// Añadir paneles al contenido
        contenido.add(panelBotones, BorderLayout.CENTER);
        contenido.add(panelSalir, BorderLayout.SOUTH);

        // Añadir panelOscuro centrado en el fondo
        fondo.add(panelOscuro);

        // Forzar revalidación y repintado para asegurar que las imágenes se muestren
        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });

        // Mostrar ventana
        setVisible(true);
    }

    // Método para crear botones pequeños
    private JButton crearBotonPequeno(String rutaIcono, Runnable accion) {
        return new RoundedIconButtonSmall(rutaIcono, accion);
    }

    // Botón redondeado pequeño con carga síncrona de icono y efecto hover/pressed
    // Reemplaza tu clase RoundedIconButtonSmall por esta
class RoundedIconButtonSmall extends JButton {
    private BufferedImage originalIcon; // icono original (alta resolución)
    private boolean hover = false;
    private boolean pressed = false;
    private final Color baseColor = new Color(145, 112, 97);
    private final Color hoverColor = new Color(227, 164, 113);
    private final int arc = 14;
    private final int size = 48;     // tamaño del botón (no cambiar)
    private float scale = 1.0f;      // escala para hover/pressed

    public RoundedIconButtonSmall(String rutaIcono, Runnable accion) {
        setPreferredSize(new Dimension(size, size));
        setMinimumSize(new Dimension(size, size));
        setMaximumSize(new Dimension(size, size));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setOpaque(false);

        // Cargar icono en la mayor resolución disponible (no lo escalamos aquí)
        try {
            java.net.URL res = getClass().getResource(rutaIcono);
            if (res != null) {
                originalIcon = ImageIO.read(res);
            } else {
                originalIcon = null;
                System.err.println("Recurso no encontrado: " + rutaIcono);
            }
        } catch (IOException | IllegalArgumentException ex) {
            originalIcon = null;
            System.err.println("Error cargando imagen: " + rutaIcono + " -> " + ex.getMessage());
        }

        addActionListener(e -> {
            try { accion.run(); } catch (Exception ex) { /* evitar que la UI se rompa */ }
        });

        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { hover = true; scale = 1.12f; repaint(); }
            @Override public void mouseExited(MouseEvent e)  { hover = false; pressed = false; scale = 1.0f; repaint(); }
            @Override public void mousePressed(MouseEvent e) { pressed = true; scale = 0.96f; repaint(); }
            @Override public void mouseReleased(MouseEvent e){ pressed = false; scale = hover ? 1.12f : 1.0f; repaint(); }
        });

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        Color fill = hover ? hoverColor : baseColor;
        if (pressed) fill = fill.darker();

        // Fondo redondeado
        g2.setColor(fill);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        // Borde sutil
        g2.setColor(new Color(0, 0, 0, 30));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

        // Dibujar icono centrado y más grande sin cambiar el botón
        if (originalIcon != null) {
            // Queremos que el icono ocupe un porcentaje del botón (ej. 70%)
            float targetRatio = 0.70f * scale; // escala adicional por hover/pressed
            int maxW = (int) (getWidth() * targetRatio);
            int maxH = (int) (getHeight() * targetRatio);

            // Mantener proporción del icono original
            int ow = originalIcon.getWidth();
            int oh = originalIcon.getHeight();
            float ratio = Math.min((float) maxW / ow, (float) maxH / oh);

            int iw = Math.max(1, Math.round(ow * ratio));
            int ih = Math.max(1, Math.round(oh * ratio));

            int x = (getWidth() - iw) / 2;
            int y = (getHeight() - ih) / 2;

            // Dibujar con calidad
            g2.drawImage(originalIcon, x, y, iw, ih, null);
        }

        g2.dispose();
    }
}


    // Panel con fondo de imagen; simulamos desenfoque con una capa semitransparente encima
    class ImagenFondoPanel extends JPanel {

        private Image imagen;

        public ImagenFondoPanel(String rutaImagen) {
            try {
                imagen = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
            } catch (Exception ex) {
                imagen = null;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            if (imagen != null) {
                g2.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            } else {
                g2.setColor(new Color(20, 30, 60));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }

            // Capa para suavizar / simular desenfoque
            g2.setColor(new Color(255, 255, 255, 30));
            g2.fillRect(0, 0, getWidth(), getHeight());

            // Capa oscura para dar contraste al panel
            g2.setColor(new Color(10, 18, 35, 80));
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.dispose();
        }
    }
}
