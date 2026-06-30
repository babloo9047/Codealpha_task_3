import javax.swing.*;
import java.awt.event.*;

public class LoginPage extends JFrame implements ActionListener {

    JTextField userField;
    JPasswordField passField;
    JButton loginBtn;

    LoginPage() {
        setTitle("Stock Trading Login");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        userField = new JTextField();
        userField.setBounds(120, 30, 150, 25);
        add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 80, 25);
        add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(120, 70, 150, 25);
        add(passField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(120, 110, 100, 30);
        loginBtn.addActionListener(this);
        add(loginBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String user = userField.getText();
        String pass = new String(passField.getPassword());

        if (user.equals("admin") && pass.equals("1234")) {
            new StockDashboard();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Login");
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}