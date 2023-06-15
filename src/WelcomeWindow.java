import javax.swing.*;

public class WelcomeWindow extends JFrame {
    public WelcomeWindow(String username) {
        setTitle("Welcome, " + username);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel welcomeLabel = new JLabel("Welcome, " + username);
        add(welcomeLabel);

        setVisible(true);
    }
}
