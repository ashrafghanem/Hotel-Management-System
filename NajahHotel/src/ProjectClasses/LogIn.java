package ProjectClasses;

import DatabaseRelations.Assigns;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogIn implements FocusListener {

    public static JTextField userTF;
    public JPasswordField passPF;
    public JLabel username, password, forgotPass, logInBorder;
    public JButton logInBtn;
    public ForgotPassword forgotPassword;
    public JPanel panel;
    private List<Assigns> adminAccounts;
    private EntityManager entityManager;
    private Query query;
    public static String currUsername;

    public LogIn() {
        entityManager = Main.entityManager;

        userTF = new JTextField();
        userTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        userTF.setBounds(500, 200, 220, 30);
        Main.frame.getContentPane().add(userTF);
        userTF.setColumns(10);
        userTF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                passPF.requestFocus();
            }
        });
        userTF.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ') {
                    event.consume();
                }
            }
        });
        userTF.addFocusListener(this);

        passPF = new JPasswordField();
        passPF.setEchoChar('\u25CF');
        passPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        passPF.setBounds(500, 241, 220, 30);
        Main.frame.getContentPane().add(passPF);
        passPF.setColumns(10);
        passPF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                boolean usernameFound = false;
                query = entityManager.createNamedQuery("Assigns.findAll");
                adminAccounts = query.getResultList();

                for (Assigns username : adminAccounts) {
                    if (username.getUsername().equals(userTF.getText())) {
                        usernameFound = true;
                        if (username.getPassword().equals(String.valueOf(passPF.getPassword()))) {
                            Main.frame.setVisible(false);
                            currUsername = userTF.getText();
                            new MainScreen();
                        } else {
                            JOptionPane.showMessageDialog(Main.frame, "Password not correct!", "Wrong Password", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (usernameFound) {
                        break;
                    }
                }
                if (!usernameFound) {
                    JOptionPane.showMessageDialog(Main.frame, "Username not found!", "Wrong username", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        passPF.addFocusListener(this);

        username = new JLabel(new ImageIcon(Main.class.getResource("usernameIcon.png")));
        username.setFont(new Font("Tahoma", Font.PLAIN, 14));
        username.setBounds(460, 200, 30, 30);
        username.setForeground(Color.BLACK);
        Main.frame.getContentPane().add(username);

        password = new JLabel(new ImageIcon(Main.class.getResource("passwordIcon.png")));
        password.setFont(new Font("Tahoma", Font.PLAIN, 14));
        password.setBounds(460, 241, 30, 30);
        password.setForeground(Color.BLACK);
        Main.frame.getContentPane().add(password);

        logInBtn = new JButton(new ImageIcon(LogIn.class.getResource("logInIcon.png")));
        logInBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        logInBtn.setBounds(660, 300, 60, 60);
        logInBtn.setMnemonic('L');
        Main.frame.getContentPane().add(logInBtn);
        Color color = logInBtn.getBackground();
        logInBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent mouseEvent) {
                logInBtn.setBackground(new Color(77, 15, 17));
            }

            public void mouseExited(MouseEvent mouseEvent) {
                logInBtn.setBackground(color);
            }
        });
        logInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                boolean usernameFound = false;
                query = entityManager.createNamedQuery("Assigns.findAll");
                adminAccounts = query.getResultList();

                for (Assigns username : adminAccounts) {
                    if (username.getUsername().equals(userTF.getText())) {
                        usernameFound = true;
                        if (username.getPassword().equals(String.valueOf(passPF.getPassword()))) {
                            Main.frame.setVisible(false);
                            currUsername = userTF.getText();
                            new MainScreen();
                        } else {
                            JOptionPane.showMessageDialog(Main.frame, "Password not correct!", "Wrong Password", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (usernameFound) {
                        break;
                    }
                }
                if (!usernameFound) {
                    JOptionPane.showMessageDialog(Main.frame, "Username not found!", "Wrong username", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        forgotPass = new JLabel("Forgot Password? ");
        forgotPass.setForeground(new Color(92, 6, 8));
        forgotPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
        forgotPass.setBounds(500, 265, 150, 30);
        Main.frame.getContentPane().add(forgotPass);
        forgotPass.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent event) {
                forgotPass.setFont(new Font("Tahoma", Font.BOLD, 12));
                forgotPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent event) {
                forgotPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
            }

            public void mouseClicked(MouseEvent event) {
                Main.executorService.execute(new Main.LogInToForgotPass());
                forgotPass.setVisible(false);
            }
        });

        logInBorder = new JLabel(new ImageIcon(Main.class.getResource("logInBorder.png")));
        logInBorder.setBounds(405, 110, 367, 335);
        Main.frame.getContentPane().add(logInBorder);

        panel = new JPanel();
        panel.setBackground(new Color(153, 131, 86, 150));
        panel.setBounds(428, 185, 322, 185);
        Main.frame.add(panel);
    }

    public void logInSlide() {
        userTF.setBounds(userTF.getX() - 5, 200, 220, 30);
        passPF.setBounds(passPF.getX() - 5, 241, 220, 30);
        username.setBounds(username.getX() - 5, 200, 30, 30);
        password.setBounds(password.getX() - 5, 241, 30, 30);
        logInBtn.setBounds(logInBtn.getX() - 5, 300, 60, 60);
        forgotPass.setBounds(forgotPass.getX() - 5, 268, 150, 30);
        logInBorder.setBounds(logInBorder.getX() - 5, 110, 367, 335);
        panel.setBounds(panel.getX() - 5, 185, 322, 185);
    }

    public void logInRePosition() {
        userTF.setBounds(900, 200, 220, 30);
        passPF.setBounds(900, 241, 220, 30);
        username.setBounds(860, 200, 30, 30);
        password.setBounds(860, 241, 30, 30);
        logInBtn.setBounds(1060, 300, 60, 60);
        forgotPass.setBounds(900, 268, 150, 30);
        logInBorder.setBounds(805, 110, 367, 335);
        panel.setBounds(828, 185, 316, 185);
    }

    @Override
    public void focusGained(FocusEvent event) {
        if (event.getSource() == userTF) {
            if (userTF.getText().equals("Username")) {
                userTF.setForeground(Color.BLACK);
                userTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
                userTF.setText("");
            }
        } else if (event.getSource() == passPF) {
            if (String.valueOf(passPF.getPassword()).equals("Password")) {
                passPF.setForeground(Color.BLACK);
                passPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
                passPF.setEchoChar('\u25CF');
                passPF.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent event) {
        if (event.getSource() == userTF) {
            if (userTF.getText().isEmpty()) {
                userTF.setForeground(Color.gray);
                userTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
                userTF.setText("Username");
            }
        } else if (event.getSource() == passPF) {
            if (String.valueOf(passPF.getPassword()).isEmpty()) {
                passPF.setForeground(Color.gray);
                passPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
                passPF.setEchoChar((char) 0);
                passPF.setText("Password");
            }
        }
    }
}
