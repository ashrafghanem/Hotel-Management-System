package ProjectClasses;

import DatabaseRelations.Assigns;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

public class ChangePassword extends JPanel implements FocusListener {

    private JLabel mainLabel, confirmNewPassIcon, newPassIcon;
    private JTextField username;
    private JPasswordField oldPass;
    private JPasswordField newPass;
    private JPasswordField confirmNewPass;
    private JLabel checkPassIcon;
    private Query query;
    private EntityManager entityManager;
    private List<Assigns> adminAccounts;

    public ChangePassword() {
        entityManager = Main.entityManager;

        new Main.PanelAppear(this).start();
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setSize(360, 455);
        setLayout(null);
        setFocusable(true);

        JLabel usernameIcon = new JLabel(new ImageIcon(ChangePassword.class.getResource("usernameIcon.png")));
        usernameIcon.setBounds(10, 77, 47, 30);
        add(usernameIcon);

        username = new JTextField("Admin: " + MainScreen.currUsername);
        username.setEditable(false);
        username.setColumns(10);
        username.setForeground(Color.BLACK);
        username.setFont(new Font("Tahoma", Font.PLAIN, 14));
        username.setBounds(63, 77, 229, 30);
        add(username);
        username.addFocusListener(this);

        JLabel oldPassIcon = new JLabel(new ImageIcon(ChangePassword.class.getResource("passwordIcon.png")));
        oldPassIcon.setBounds(10, 136, 47, 30);
        add(oldPassIcon);

        oldPass = new JPasswordField();
        oldPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                query = entityManager.createNamedQuery("Assigns.findAll");
                adminAccounts = query.getResultList();
                boolean usernameFound = false;

                for (Assigns username : adminAccounts) {
                    if (MainScreen.currUsername.equals(username.getUsername())) {
                        usernameFound = true;
                        if (String.valueOf(oldPass.getPassword()).equals(username.getPassword())) {
                            newPass.setEditable(true);
                            confirmNewPass.setEditable(true);
                            newPass.requestFocus();
                            new ConfirmPassThread();
                            checkPassIcon.setIcon(new ImageIcon(Main.class.getResource("answerCorrect.png")));
                        } else {
                            checkPassIcon.setIcon(new ImageIcon(Main.class.getResource("answerWrong.png")));
                            newPass.setEditable(false);
                            confirmNewPass.setEditable(false);
                            return;
                        }
                        if (usernameFound) {
                            break;
                        }
                    }
                }
            }
        });
        oldPass.setText("Old Password");
        oldPass.setForeground(Color.GRAY);
        oldPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
        oldPass.setBounds(63, 136, 229, 30);
        add(oldPass);
        oldPass.addFocusListener(this);

        newPassIcon = new JLabel(new ImageIcon(ChangePassword.class.getResource("passwordIcon.png")));
        newPassIcon.setBounds(10, 174, 47, 30);
        add(newPassIcon);

        newPass = new JPasswordField();
        newPass.setEditable(false);
        newPass.setText("New Password");
        newPass.setForeground(Color.GRAY);
        newPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
        newPass.setBounds(63, 174, 229, 30);
        add(newPass);
        newPass.addFocusListener(this);

        confirmNewPass = new JPasswordField();
        confirmNewPass.setEditable(false);
        confirmNewPass.setText("Confirm New Password");
        confirmNewPass.setForeground(Color.GRAY);
        confirmNewPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
        confirmNewPass.setBounds(63, 212, 229, 30);
        add(confirmNewPass);
        confirmNewPass.addFocusListener(this);

        confirmNewPassIcon = new JLabel(new ImageIcon(ChangePassword.class.getResource("passNotMatchIcon.png")));
        confirmNewPassIcon.setBounds(10, 212, 47, 30);
        add(confirmNewPassIcon);

        JButton cancelBtn = new JButton(new ImageIcon(ChangePassword.class.getResource("cancelIcon.png")));
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new Main.PanelDisappear(ChangePassword.this).start();
                MainScreen.changePassCreated = false;
            }
        });
        cancelBtn.setBounds(63, 330, 50, 50);
        add(cancelBtn);

        JButton submitBtn = new JButton(new ImageIcon(ChangePassword.class.getResource("submitIcon.png")));
        submitBtn.setToolTipText("Submit");
        submitBtn.setBounds(153, 330, 50, 50);
        add(submitBtn);
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                query = entityManager.createNamedQuery("Assigns.findAll");
                adminAccounts = query.getResultList();
                boolean usernameFound = false;

                if (String.valueOf(oldPass.getPassword()).equals("Old Password") || String.valueOf(newPass.getPassword()).equals("New Password")
                        || String.valueOf(confirmNewPass.getPassword()).equals("Confirm New Password")) {
                    JOptionPane.showMessageDialog(ChangePassword.this, "You have to fill in all fields!", "Empty Fields", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (Assigns username : adminAccounts) {
                        if (MainScreen.currUsername.equals(username.getUsername())) {
                            usernameFound = true;
                            if (!String.valueOf(newPass.getPassword()).equals(String.valueOf(confirmNewPass.getPassword()))) {
                                JOptionPane.showMessageDialog(ChangePassword.this, "Passwords don't match!", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            username.setPassword(String.valueOf(newPass.getPassword()));
                            entityManager.getTransaction().commit();

                            JOptionPane.showMessageDialog(ChangePassword.this, "Password Updated Successfully!", "Change Password", JOptionPane.INFORMATION_MESSAGE);
                            entityManager.getTransaction().begin();
                            showUnfocusedText();
                        }
                        if (usernameFound) {
                            break;
                        }
                    }
                }
            }
        });

        JButton clearBtn = new JButton(new ImageIcon(ChangePassword.class.getResource("clearIcon.png")));
        clearBtn.setToolTipText("Clear Fields");
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                showUnfocusedText();
            }
        });
        clearBtn.setBounds(242, 330, 50, 50);
        add(clearBtn);

        new ConfirmPassThread().start();

        checkPassIcon = new JLabel();
        checkPassIcon.setBounds(301, 136, 47, 30);
        add(checkPassIcon);

        mainLabel = new JLabel(new ImageIcon(ChangePassword.class.getResource("changePassPanel.png")));
        mainLabel.setBounds(0, 0, 360, 455);
        add(mainLabel);

    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        if (focusEvent.getSource() == oldPass) {
            if (String.valueOf(oldPass.getPassword()).equals("Old Password")) {
                oldPass.setForeground(Color.BLACK);
                oldPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
                oldPass.setEchoChar('\u25CF');
                oldPass.setText("");
            }
        } else if (focusEvent.getSource() == newPass) {
            if (String.valueOf(newPass.getPassword()).equals("New Password")) {
                newPass.setForeground(Color.BLACK);
                newPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
                newPass.setEchoChar('\u25CF');
                newPass.setText("");
            }
        } else if (focusEvent.getSource() == confirmNewPass) {
            if (String.valueOf(confirmNewPass.getPassword()).equals("Confirm New Password")) {
                confirmNewPass.setForeground(Color.BLACK);
                confirmNewPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
                confirmNewPass.setEchoChar('\u25CF');
                confirmNewPass.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        if (focusEvent.getSource() == oldPass) {
            if (String.valueOf(oldPass.getPassword()).isEmpty()) {
                oldPass.setForeground(Color.gray);
                oldPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
                oldPass.setEchoChar((char) 0);
                oldPass.setText("Old Password");
            }
        } else if (focusEvent.getSource() == newPass) {
            if (String.valueOf(newPass.getPassword()).isEmpty()) {
                newPass.setForeground(Color.gray);
                newPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
                newPass.setEchoChar((char) 0);
                newPass.setText("New Password");
            }
        } else if (focusEvent.getSource() == confirmNewPass) {
            if (String.valueOf(confirmNewPass.getPassword()).isEmpty()) {
                confirmNewPass.setForeground(Color.gray);
                confirmNewPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
                confirmNewPass.setEchoChar((char) 0);
                confirmNewPass.setText("Confirm New Password");
            }
        }
    }

    private class ConfirmPassThread extends Thread {

        public void run() {
            try {
                while (true) {
                    if (String.valueOf(confirmNewPass.getPassword()).equals(String.valueOf(newPass.getPassword()))) {
                        confirmNewPassIcon.setIcon(new ImageIcon(Main.class.getResource("passMatchIcon.png")));
                    } else {
                        confirmNewPassIcon.setIcon(new ImageIcon(Main.class.getResource("passNotMatchIcon.png")));
                    }
                }
            } catch (Exception exception) {
            }
        }
    }

    public void showUnfocusedText() {
        oldPass.setForeground(Color.gray);
        oldPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
        oldPass.setEchoChar((char) 0);
        oldPass.setText("Old Password");

        newPass.setForeground(Color.gray);
        newPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
        newPass.setEchoChar((char) 0);
        newPass.setText("New Password");
        newPass.setEditable(false);

        confirmNewPass.setForeground(Color.gray);
        confirmNewPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
        confirmNewPass.setEchoChar((char) 0);
        confirmNewPass.setText("Confirm New Password");
        confirmNewPass.setEditable(false);

        checkPassIcon.setIcon(null);
    }
}
