import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LoginWindow extends JFrame {
    private SistemaLogin loginSystem;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginWindow(SistemaLogin loginSystem) {
        this.loginSystem = loginSystem;

        setTitle("Login");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLUE);
        leftPanel.setLayout(new BorderLayout());

        ImageIcon imageIconLeft = new ImageIcon(new ImageIcon("C:\\Users\\andre\\Downloads\\Imagenes-proyecto\\login_izq.png").getImage().getScaledInstance(375, 375, Image.SCALE_AREA_AVERAGING));
        JLabel imagen_izq = new JLabel(imageIconLeft);

        leftPanel.add(imagen_izq, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("CONTIX");
        titleLabel.setFont(new Font("Nunito", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.BLUE);
        titlePanel.add(titleLabel);

        leftPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        // Cargando la imagen para el panel derecho
        ImageIcon imageIconRight = new ImageIcon(new ImageIcon("C:\\Users\\andre\\Downloads\\Imagenes-proyecto\\login_dere.png").getImage().getScaledInstance(250, 250, Image.SCALE_AREA_AVERAGING));
        JLabel imagen_derecha = new JLabel(imageIconRight, SwingConstants.CENTER);
        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topRightPanel.add(imagen_derecha);

        JPanel centerRightPanel = new JPanel(new GridBagLayout());
        placeComponents(centerRightPanel);

        rightPanel.add(topRightPanel, BorderLayout.NORTH);
        rightPanel.add(centerRightPanel, BorderLayout.CENTER);

        panel.add(leftPanel);
        panel.add(rightPanel);
        add(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel userLabel = new JLabel("Usuario");
        userLabel.setForeground(Color.BLUE);  // Set label text color
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));  // Set label font
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(userLabel, gbc);

        usernameField = new JTextField(20);
        usernameField.setBorder(new RoundBorder(10));  // Set round border
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));  // Set text field font
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Contraseña");
        passwordLabel.setForeground(Color.BLUE);  // Set label text color
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));  // Set label font
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setBorder(new RoundBorder(10));  // Set round border
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));  // Set password field font
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Iniciar sesión");
        loginButton.setBackground(Color.BLUE);  // Set button background color
        loginButton.setForeground(Color.WHITE);  // Set button text color
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));  // Set button font
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (loginSystem.login(username, password)) {
                app app = new app();
                app.setTitle("Rol de pagos");
                app.setSize(500, 500);
                app.setVisible(true);
                app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña inválidos.");
            }
        });
    }

    // Create a new RoundBorder class
    public class RoundBorder implements Border {
        private int radius;

        RoundBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

}
