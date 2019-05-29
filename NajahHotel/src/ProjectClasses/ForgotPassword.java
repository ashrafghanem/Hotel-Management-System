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
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ForgotPassword implements FocusListener {

    private JLabel username, securityQuestion, answer, password, confirmPass, forgotPassBorder, checkAnswer;
    public JTextField userTF, securityQuestionTF, answerTF;
    public JPasswordField passPF, confirmPassPF;
    private JButton searchBtn, updateBtn, backBtn;
    public JPanel panel;
    private JSeparator separator;
    private List<Assigns> adminAccounts;
    private EntityManager entityManager;
    private Query query;

    public ForgotPassword() {
        entityManager = Main.entityManager;

        username = new JLabel(new ImageIcon(Main.class.getResource("usernameIcon.png")));
        username.setBounds(842, 169, 30, 30);
        Main.frame.getContentPane().add(username);
        username.setFont(new Font("Tahoma", Font.PLAIN, 14));

        userTF = new JTextField();
        userTF.setBounds(891, 169, 191, 30);
        Main.frame.getContentPane().add(userTF);
        userTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        userTF.setColumns(10);
        userTF.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ') {
                    event.consume();
                }
            }
        });
        userTF.addFocusListener(this);

        securityQuestion = new JLabel(new ImageIcon(Main.class.getResource("securityQuestionIcon.png")));
        securityQuestion.setBounds(842, 207, 30, 30);
        Main.frame.getContentPane().add(securityQuestion);
        securityQuestion.setFont(new Font("Tahoma", Font.PLAIN, 14));

        securityQuestionTF = new JTextField();
        securityQuestionTF.setBounds(891, 207, 191, 30);
        Main.frame.getContentPane().add(securityQuestionTF);
        securityQuestionTF.setEditable(false);
        securityQuestionTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
        securityQuestionTF.addFocusListener(this);

        answer = new JLabel(new ImageIcon(Main.class.getResource("answerIcon.png")));
        answer.setBounds(842, 245, 30, 30);
        Main.frame.getContentPane().add(answer);
        answer.setFont(new Font("Tahoma", Font.PLAIN, 14));

        answerTF = new JTextField();
        answerTF.setBounds(891, 245, 191, 30);
        Main.frame.getContentPane().add(answerTF);
        answerTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        answerTF.setColumns(10);
        answerTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                query = entityManager.createNamedQuery("Assigns.findAll");
                adminAccounts = query.getResultList();

                for (Assigns answers : adminAccounts) {
                    if (answers.getUsername().equals(userTF.getText())) {
                        if (answerTF.getText().equals(answers.getAnswer())) {
                            answerTF.setEditable(false);
                            Main.executorService.execute(new ConfirmPassThread());
                            checkAnswer.setIcon(new ImageIcon(Main.class.getResource("answerCorrect.png")));
                            passPF.setEditable(true);
                            passPF.requestFocus();
                            confirmPassPF.setEditable(true);
                        } else {
                            checkAnswer.setIcon(new ImageIcon(Main.class.getResource("answerWrong.png")));
                            passPF.setEditable(false);
                            confirmPassPF.setEditable(false);
                        }
                    }
                }
            }
        });
        answerTF.addFocusListener(this);

        checkAnswer = new JLabel();
        checkAnswer.setBounds(1091, 245, 47, 30);
        Main.frame.getContentPane().add(checkAnswer);

        separator = new JSeparator();
        separator.setBounds(882, 284, 209, 2);
        Main.frame.getContentPane().add(separator);

        password = new JLabel(new ImageIcon(Main.class.getResource("passwordIcon.png")));
        password.setFont(new Font("Tahoma", Font.PLAIN, 14));
        password.setBounds(842, 294, 30, 30);
        Main.frame.getContentPane().add(password);

        confirmPass = new JLabel(new ImageIcon(Main.class.getResource("passNotMatchIcon.png")));
        confirmPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
        confirmPass.setBounds(842, 332, 30, 30);
        Main.frame.getContentPane().add(confirmPass);

        passPF = new JPasswordField();
        passPF.setEchoChar('\u25CF');
        passPF.setEditable(false);
        passPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        passPF.setColumns(10);
        passPF.setBounds(891, 294, 191, 30);
        Main.frame.getContentPane().add(passPF);
        passPF.addFocusListener(this);

        confirmPassPF = new JPasswordField();
        confirmPassPF.setEchoChar('\u25CF');
        confirmPassPF.setEditable(false);
        confirmPassPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        confirmPassPF.setColumns(10);
        confirmPassPF.setBounds(891, 332, 191, 30);
        Main.frame.getContentPane().add(confirmPassPF);
        confirmPassPF.addFocusListener(this);

        searchBtn = new JButton(new ImageIcon(Main.class.getResource("searchIcon.png")));
        searchBtn.setToolTipText("Search");
        searchBtn.setBorderPainted(false);
        searchBtn.setMnemonic('S');
        searchBtn.setBounds(1091, 169, 47, 30);
        Main.frame.getContentPane().add(searchBtn);
        searchBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
        searchBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent event) {
                searchBtn.setIcon(new ImageIcon(Main.class.getResource("searchHighlightedIcon.png")));
                searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent event) {
                searchBtn.setIcon(new ImageIcon(Main.class.getResource("searchIcon.png")));
            }
        });
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                query = entityManager.createNamedQuery("Assigns.findAll");
                adminAccounts = query.getResultList();

                boolean usernameFound = false;

                for (Assigns username : adminAccounts) {
                    if (userTF.getText().equals("ashrafghanem")) {
                        showUnfocusedText();
                        JTextArea TA = new JTextArea("It's not that easy to change \nthe major admin password from here :)");
                        TA.setBackground(new Color(0, 0, 0, 0));
                        TA.setEnabled(false);
                        TA.setDisabledTextColor(Color.black);
                        TA.setBorder(null);

                        usernameFound = true;
                        JOptionPane.showMessageDialog(Main.frame, TA, "Forgot Password", JOptionPane.WARNING_MESSAGE);
                        break;
                    } else if (userTF.getText().equals(username.getUsername())) {
                        usernameFound = true;
                        securityQuestionTF.setForeground(Color.black);
                        securityQuestionTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
                        securityQuestionTF.setText(username.getSecurityQuestion());
                        answerTF.setEditable(true);
                        answerTF.setText("");
                        checkAnswer.setIcon(null);
                        answerTF.requestFocus();
                        panel.requestFocus();
                    }
                    if (usernameFound) {
                        break;
                    }
                }
                if (!usernameFound) {
                    showUnfocusedText();
                    JOptionPane.showMessageDialog(Main.frame, "Username not found!", "Wrong Username", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateBtn = new JButton("Update");
        updateBtn.setBounds(891, 375, 83, 30);
        updateBtn.setMnemonic('U');
        Main.frame.getContentPane().add(updateBtn);
        updateBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(passPF.getPassword()).equals(String.valueOf(confirmPassPF.getPassword()))) {
                    query = entityManager.createNamedQuery("Assigns.findAll");
                    adminAccounts = query.getResultList();

                    for (Assigns password : adminAccounts) {
                        if (password.getUsername().equals(userTF.getText())) {
                            password.setPassword(String.valueOf(passPF.getPassword()));
                            entityManager.getTransaction().commit();
                            JOptionPane.showMessageDialog(Main.frame, "Password Updated!", "Update Success", JOptionPane.INFORMATION_MESSAGE);

                            showUnfocusedText();

                            userTF.setForeground(Color.gray);
                            userTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
                            userTF.setText("Username");
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(Main.frame, "Passwords don't match!", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backBtn = new JButton("Back");
        backBtn.setBounds(999, 375, 83, 30);
        Main.frame.getContentPane().add(backBtn);
        backBtn.setMnemonic('B');
        backBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Main.executorService.execute(new Main.ForgotPassToLogIn());
                backBtn.setEnabled(false);
            }
        });

        forgotPassBorder = new JLabel(new ImageIcon(Main.class.getResource("forgotPassBorder.png")));
        forgotPassBorder.setBounds(805, 110, 367, 335);
        Main.frame.getContentPane().add(forgotPassBorder);

        panel = new JPanel();
        panel.setBackground(new Color(153, 131, 86, 150));
        panel.setBounds(828, 119, 316, 317);
        Main.frame.getContentPane().add(panel);
    }

    public void showUnfocusedText() {
        answerTF.setForeground(Color.gray);
        answerTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
        answerTF.setText("Security Question Answer");
        answerTF.setEditable(false);

        checkAnswer.setIcon(null);

        passPF.setForeground(Color.gray);
        passPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
        passPF.setEchoChar((char) 0);
        passPF.setText("New Password");
        passPF.setEditable(false);

        confirmPassPF.setForeground(Color.gray);
        confirmPassPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
        confirmPassPF.setEchoChar((char) 0);
        confirmPassPF.setText("Confirm New Password");
        confirmPassPF.setEditable(false);

        securityQuestionTF.setForeground(Color.gray);
        securityQuestionTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
        securityQuestionTF.setText("Security Question");
    }

    public void forgotPassSlide() {
        username.setBounds(username.getX() - 5, 169, 30, 30);
        password.setBounds(password.getX() - 5, 294, 30, 30);
        confirmPass.setBounds(confirmPass.getX() - 5, 332, 30, 30);
        separator.setBounds(separator.getX() - 5, 284, 209, 2);
        securityQuestion.setBounds(securityQuestion.getX() - 5, 207, 30, 30);
        answer.setBounds(answer.getX() - 5, 245, 30, 30);
        userTF.setBounds(userTF.getX() - 5, 169, 191, 30);
        checkAnswer.setBounds(checkAnswer.getX() - 5, 245, 47, 30);
        securityQuestionTF.setBounds(securityQuestionTF.getX() - 5, 207, 191, 30);
        passPF.setBounds(passPF.getX() - 5, 294, 191, 30);
        confirmPassPF.setBounds(confirmPassPF.getX() - 5, 332, 191, 30);
        answerTF.setBounds(answerTF.getX() - 5, 245, 191, 30);
        updateBtn.setBounds(updateBtn.getX() - 5, 375, 83, 30);
        searchBtn.setBounds(searchBtn.getX() - 5, 169, 47, 30);
        backBtn.setBounds(backBtn.getX() - 5, 375, 83, 30);
        forgotPassBorder.setBounds(forgotPassBorder.getX() - 5, 110, 367, 335);
        panel.setBounds(panel.getX() - 5, 119, 316, 317);
    }

    public void forgotPassRePosition() {
        forgotPassBorder.setBounds(805, 110, 367, 335);
        panel.setBounds(828, 119, 316, 317);
        username.setBounds(842, 169, 30, 30);
        password.setBounds(842, 294, 30, 30);
        confirmPass.setBounds(842, 332, 30, 30);
        separator.setBounds(882, 284, 209, 2);
        securityQuestion.setBounds(842, 207, 30, 30);
        answer.setBounds(842, 245, 30, 30);
        userTF.setBounds(891, 169, 191, 30);
        checkAnswer.setBounds(1091, 245, 47, 30);
        securityQuestionTF.setBounds(891, 207, 191, 30);
        passPF.setBounds(891, 294, 191, 30);
        confirmPassPF.setBounds(891, 332, 191, 30);
        answerTF.setBounds(891, 245, 191, 30);
        updateBtn.setBounds(891, 375, 83, 30);
        searchBtn.setBounds(1091, 169, 47, 30);
        backBtn.setBounds(999, 375, 83, 30);
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
            if (String.valueOf(passPF.getPassword()).equals("New Password")) {
                passPF.setForeground(Color.BLACK);
                passPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
                passPF.setEchoChar('\u25CF');
                passPF.setText("");
            }
        } else if (event.getSource() == answerTF) {
            if (answerTF.getText().equals("Security Question Answer")) {
                answerTF.setForeground(Color.BLACK);
                answerTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
                answerTF.setText("");
            }
        } else if (event.getSource() == confirmPassPF) {
            if (String.valueOf(confirmPassPF.getPassword()).equals("Confirm New Password")) {
                confirmPassPF.setForeground(Color.BLACK);
                confirmPassPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
                confirmPassPF.setEchoChar('\u25CF');
                confirmPassPF.setText("");
            }
        } else if (event.getSource() == securityQuestionTF) {
            if (securityQuestionTF.getText().equals("Security Question")) {
                securityQuestionTF.setForeground(Color.BLACK);
                securityQuestionTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
                securityQuestionTF.setText("");
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
                passPF.setText("New Password");
            }
        } else if (event.getSource() == answerTF) {
            if (answerTF.getText().isEmpty()) {
                answerTF.setForeground(Color.gray);
                answerTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
                answerTF.setText("Security Question Answer");
            }
        } else if (event.getSource() == confirmPassPF) {
            if (String.valueOf(confirmPassPF.getPassword()).isEmpty()) {
                confirmPassPF.setForeground(Color.gray);
                confirmPassPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
                confirmPassPF.setEchoChar((char) 0);
                confirmPassPF.setText("Confirm New Password");
            }
        } else if (event.getSource() == securityQuestionTF) {
            if (securityQuestionTF.getText().isEmpty()) {
                securityQuestionTF.setForeground(Color.gray);
                securityQuestionTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
                securityQuestionTF.setText("Security Question");
            }
        }
    }

    class ConfirmPassThread extends Thread {

        public void run() {
            try {
                while (true) {
                    if (String.valueOf(confirmPassPF.getPassword()).equals(String.valueOf(passPF.getPassword()))) {
                        confirmPass.setIcon(new ImageIcon(Main.class.getResource("passMatchIcon.png")));
                    } else {
                        confirmPass.setIcon(new ImageIcon(Main.class.getResource("passNotMatchIcon.png")));
                    }
                }
            } catch (Exception exception) {
            }
        }
    }
}
