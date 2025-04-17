//EX3_212002000_327690947

/*package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import control.Hospital;
import control.User;
import enums.UserRole;
import exceptions.*;

public class LoginScreen extends JFrame {

    private static final long serialVersionUID = 21L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Hospital hospital;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginScreen frame = new LoginScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginScreen() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginScreen.class.getResource("/view/resources/hanamal-favicon-color (1).png")));
        setTitle("Hanamal Management System - Login");
        setSize(531, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        hospital = Hospital.getInstance();

        contentPane = new JPanel();
        contentPane.setBackground(new Color(249, 246, 237));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/view/resources/hanamal-high-resolution-logo-transparent.png"));
        Image logoImage = logoIcon.getImage().getScaledInstance(200, 101, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(logoImage);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(166, 25, 200, 101);
        contentPane.add(logoLabel);

        // Username label and field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(new Color(17, 17, 17));
        usernameLabel.setBounds(101, 168, 100, 25);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBackground(new Color(249, 246, 237));
        usernameField.setBounds(211, 168, 180, 25);
        contentPane.add(usernameField);

        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(101, 218, 100, 25);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(249, 246, 237));
        passwordField.setBounds(211, 218, 180, 25);
        contentPane.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBorderPainted(false);
        loginButton.setBounds(199, 279, 100, 30);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(249, 246, 237));
        loginButton.setForeground(new Color(123, 174, 232));
        loginButton.addActionListener(new LoginAction());
        contentPane.add(loginButton);
        
   

        // Background image
        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/view/resources/background.png"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(531, 400, Image.SCALE_SMOOTH);
        backgroundIcon = new ImageIcon(backgroundImage);
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 531, 400);
        contentPane.add(backgroundLabel);

        setContentPane(contentPane);
    }

    class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (loginButton.getModel().isPressed())
                loginButton.setBackground(Color.decode("#1d93fa"));
            else
                loginButton.setBackground(Color.decode("#F9F6ED"));

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (username.equals("ADMIN")&&password.equals("ADMIN")) {
            	User admin = new User(username, password, UserRole.ADMIN);
            	try {
        			new MainFrame(admin);
        		} catch (IOException e1) {
        			// TODO Auto-generated catch block
        			ErrorSoundPlayer.playErrorSound();

        			e1.printStackTrace();
        		}
        		dispose(); // Close the login window            	
        }
           
            else {
            	User user = Hospital.getInstance().getUserByUsername(username);          
            	if (user != null && user.checkPassword(password)) {
            		try {
            			new MainFrame(user);
            		} catch (IOException e1) {
            			// TODO Auto-generated catch block
            			e1.printStackTrace();
            		}
            		dispose(); // Close the login window
            	} else {
        			ErrorSoundPlayer.playErrorSound();

            		JOptionPane.showMessageDialog(LoginScreen.this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            	}
            }
        }  
    }
}*/

/* this is a responsive window but it wont show the design very well*/
  package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import control.Hospital;
import control.User;
import enums.UserRole;

public class LoginScreen extends JFrame {

    private static final long serialVersionUID = 21L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Hospital hospital;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginScreen frame = new LoginScreen();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginScreen() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginScreen.class.getResource("/view/resources/hanamal-favicon-color (1).png")));
        setTitle("Hanamal Management System - Login");
        setSize(531, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        hospital = Hospital.getInstance();

        contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(new Color(249, 246, 237));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        
        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/view/resources/hanamal-high-resolution-logo-transparent.png"));
        Image logoImage = logoIcon.getImage().getScaledInstance(200, 101, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(logoImage);
        JLabel logoLabel = new JLabel(logoIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPane.add(logoLabel, gbc);

        // Username label and field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(new Color(17, 17, 17));
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        usernameField.setBackground(new Color(249, 246, 237));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(usernameField, gbc);

        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setBackground(new Color(249, 246, 237));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(passwordField, gbc);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBorderPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(249, 246, 237));
        loginButton.setForeground(new Color(123, 174, 232));
        loginButton.addActionListener(new LoginAction());
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(loginButton, gbc);

        // Background image
        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/view/resources/background.png"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(531, 400, Image.SCALE_SMOOTH);
        backgroundIcon = new ImageIcon(backgroundImage);
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(backgroundLabel, gbc);

        setContentPane(contentPane);
    }

    class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (loginButton.getModel().isPressed())
                loginButton.setBackground(Color.decode("#1d93fa"));
            else
                loginButton.setBackground(Color.decode("#F9F6ED"));

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (username.equals("ADMIN") && password.equals("ADMIN")) {
                User admin = new User(username, password, UserRole.ADMIN);
                try {
                    new MainFrame(admin);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                dispose(); // Close the login window
            } else {
                User user = Hospital.getInstance().getUserByUsername(username);
                if (user != null && user.checkPassword(password)) {
                    try {
                        new MainFrame(user);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    dispose(); // Close the login window
                } else {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

}

 