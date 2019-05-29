package ProjectClasses;

import DatabaseRelations.Assigns;
import DatabaseRelations.Employee;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class AdministratorPanel extends JPanel implements FocusListener {

    private JTextField empNameTF;
    private JTextField username;
    private JTextField answer;
    private JPasswordField password;
    private JPasswordField confirmPass;
    private JCheckBox yesCheckBox;
    private JButton createAdminBtn;
    private JComboBox<String> securityQuestion;
    private List<Assigns> adminRecord;
    private List<Employee> employeeRecord;
    private JLabel passwordIcon, confirmPassIcon;
    private JLabel mainLabel;
    private EntityManager entityManager;
    private Query query;
    private ArrayList<String> allEmployeesNames;
    private ArrayList<Integer> allEmployeesNOs;

    public AdministratorPanel() {
        entityManager = Main.entityManager;

        new Main.PanelAppear(this).start();
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setSize(360, 455);
        setLayout(null);
        setFocusable(true);

        allEmployeesNames = new ArrayList<String>();
        allEmployeesNOs = new ArrayList<Integer>();

        try {
            Main.result = Main.statement.executeQuery("SELECT Fname, Mname, Lname,emp_no FROM PERSON,EMPLOYEE WHERE PERSON.ID IN(SELECT EMP_ID FROM EMPLOYEE WHERE EMP_NO NOT IN (SELECT EMP_NO FROM ASSIGNS)) AND PERSON.ID = EMPLOYEE.EMP_ID");
            while (Main.result.next()) {
                String firstName = Main.result.getString(1);
                String midName = Main.result.getString(2);
                String lastName = Main.result.getString(3);
                int emp_no = Main.result.getInt(4);

                allEmployeesNames.add(firstName + " " + midName + " " + lastName);
                allEmployeesNOs.add(emp_no);
            }
        } catch (Exception ex) {
        }

        JLabel empNameIcon = new JLabel(new ImageIcon(AdministratorPanel.class.getResource("usernameIcon.png")));
        empNameIcon.setBounds(10, 83, 47, 30);
        add(empNameIcon);

        empNameTF = new JTextField();
        empNameTF.setBounds(63, 83, 229, 30);
        empNameTF.setForeground(Color.gray);
        empNameTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
        empNameTF.setText("Employee Name");
        add(empNameTF);
        empNameTF.setColumns(10);
        empNameTF.addFocusListener(this);
        empNameTF.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_BACK_SPACE:
                        break;
                    case KeyEvent.VK_ENTER:
                        empNameTF.setText(empNameTF.getText());
                        empNameTF.setEditable(false);
                    default:
                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                String txt = empNameTF.getText();
                                if (!empNameTF.getText().isEmpty()) {
                                    autoComplete(txt);
                                }
                            }
                        });
                }
            }
        });

        JLabel AdministratorPanelUsernameIcon = new JLabel(
                new ImageIcon(AdministratorPanel.class.getResource("usernameIcon.png")));
        AdministratorPanelUsernameIcon.setBounds(10, 186, 47, 30);
        add(AdministratorPanelUsernameIcon);

        username = new JTextField();
        username.setEnabled(false);
        username.setBounds(63, 186, 229, 30);
        username.setForeground(Color.gray);
        username.setFont(new Font("Tahoma", Font.PLAIN, 12));
        username.setText("Username");
        username.setColumns(10);
        add(username);
        username.addFocusListener(this);
        username.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ') {
                    event.consume();
                }
            }
        });

        passwordIcon = new JLabel(new ImageIcon(AdministratorPanel.class.getResource("passwordIcon.png")));
        passwordIcon.setBounds(10, 224, 47, 30);
        add(passwordIcon);

        JLabel areUSureLabel = new JLabel("Are you sure you want to make this employee");
        areUSureLabel.setForeground(Color.WHITE);
        areUSureLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        areUSureLabel.setBounds(10, 124, 327, 30);
        add(areUSureLabel);

        JLabel anAdministratorLabel = new JLabel("an Administrator?");
        anAdministratorLabel.setForeground(Color.WHITE);
        anAdministratorLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        anAdministratorLabel.setBounds(10, 144, 131, 30);
        add(anAdministratorLabel);

        confirmPassIcon = new JLabel(new ImageIcon(AdministratorPanel.class.getResource("passNotMatchIcon.png")));
        confirmPassIcon.setBounds(10, 263, 47, 30);
        add(confirmPassIcon);

        yesCheckBox = new JCheckBox("Yes");
        yesCheckBox.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                if (yesCheckBox.isSelected()) {
                    for (String empNames : allEmployeesNames) {
                        if (empNameTF.getText().equals(empNames)) {
                            empNameTF.setEditable(false);
                            username.setEnabled(true);
                            password.setEnabled(true);
                            confirmPass.setEnabled(true);
                            securityQuestion.setEnabled(true);
                            answer.setEnabled(true);
                            break;
                        }
                    }
                } else {
                    username.setEnabled(false);
                    password.setEnabled(false);
                    confirmPass.setEnabled(false);
                    securityQuestion.setEnabled(false);
                    answer.setEnabled(false);
                }
            }
        });
        yesCheckBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        yesCheckBox.setForeground(new Color(243, 153, 67));
        yesCheckBox.setBounds(153, 149, 69, 23);
        yesCheckBox.setBackground(new Color(0, 0, 0, 0));
        yesCheckBox.setOpaque(false);
        yesCheckBox.setFocusable(false);
        add(yesCheckBox);

        securityQuestion = new JComboBox<String>();
        securityQuestion.setMaximumRowCount(3);
        securityQuestion.setEnabled(false);
        securityQuestion.setFont(new Font("Tahoma", Font.PLAIN, 12));
        securityQuestion.setBounds(63, 301, 229, 30);
        add(securityQuestion);
        securityQuestion.setModel(new DefaultComboBoxModel<String>(
                new String[]{"- Select One -", "Where were you born?", "Who was your first teacher?",
                    "Where was your first job?", "Who is your best childhood friend?"}));

        JLabel answerIcon = new JLabel(new ImageIcon(AdministratorPanel.class.getResource("answerIcon.png")));
        answerIcon.setBounds(10, 339, 47, 30);
        add(answerIcon);

        answer = new JTextField();
        answer.setEnabled(false);
        answer.setBounds(63, 339, 229, 30);
        answer.setForeground(Color.gray);
        answer.setFont(new Font("Tahoma", Font.PLAIN, 12));
        answer.setText("Security Question Answer");
        answer.setColumns(10);
        answer.addFocusListener(this);
        add(answer);

        JLabel securityQuestionIcon = new JLabel(
                new ImageIcon(AdministratorPanel.class.getResource("securityQuestionIcon.png")));
        securityQuestionIcon.setBounds(10, 301, 47, 30);
        add(securityQuestionIcon);

        createAdminBtn = new JButton(
                new ImageIcon(AdministratorPanel.class.getResource("createAdminAccountIcon.png")));
        createAdminBtn.setToolTipText("Create Admin");
        createAdminBtn.setBounds(63, 375, 50, 50);
        add(createAdminBtn);
        createAdminBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (username.getText().equals("Username") || String.valueOf(password.getPassword()).equals("Password") || String.valueOf(confirmPass.getPassword()).equals("Confirm Password")
                        || securityQuestion.getSelectedIndex() == 0 || answer.getText().equals("Security Question Answer")) {
                    JOptionPane.showMessageDialog(AdministratorPanel.this, "You have to fill in all fields!", "Empty Fields", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean usernameAlreadyExists = false;
                    query = entityManager.createNamedQuery("Assigns.findAll");
                    adminRecord = query.getResultList();
                    for (Assigns admin : adminRecord) {
                        if (username.getText().equals(admin.getUsername())) {
                            usernameAlreadyExists = true;
                            JOptionPane.showMessageDialog(AdministratorPanel.this, "An administrator with this username already exists!", "Person", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    if (!String.valueOf(password.getPassword()).equals(String.valueOf(confirmPass.getPassword()))) {
                        JOptionPane.showMessageDialog(AdministratorPanel.this, "Passwords don't match!", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (!usernameAlreadyExists) {
                            Assigns administrator = new Assigns();
                            entityManager.persist(administrator);

                            EntityManager em = Persistence.createEntityManagerFactory("NajahHotelPU").createEntityManager();
                            employeeRecord = em.createNamedQuery("Employee.findAll").getResultList();

                            int index = -1;
                            for (int i = 0; i < allEmployeesNames.size(); i++) {
                                if (allEmployeesNames.get(i).equals(empNameTF.getText())) {
                                    index = i;
                                    break;
                                }
                            }
                            for (Employee EMP : employeeRecord) {
                                if (index != -1 && String.valueOf(EMP.getEmpNo()).equals(String.valueOf(allEmployeesNOs.get(index)))) {
                                    administrator.setEmpNo(EMP);
                                    break;
                                }
                            }

                            administrator.setUsername(username.getText());
                            administrator.setPassword(String.valueOf(password.getPassword()));
                            administrator.setSecurityQuestion((String) securityQuestion.getSelectedItem());
                            administrator.setAnswer(answer.getText());

                            entityManager.getTransaction().commit();
                            JOptionPane.showMessageDialog(AdministratorPanel.this, "Administrator Created Successfully!", "Administrators", JOptionPane.INFORMATION_MESSAGE);
                            for (int i = 0; i < allEmployeesNames.size(); i++) {
                                if (allEmployeesNames.get(i).equals(empNameTF.getText())) {
                                    allEmployeesNames.remove(i);
                                    allEmployeesNOs.remove(i);
                                    break;
                                }
                            }
                            entityManager.getTransaction().begin();
                            showUnfocusedText();
                        }
                    }
                }
            }
        });

        JButton clearBtn = new JButton(new ImageIcon(AdministratorPanel.class.getResource("clearIcon.png")));
        clearBtn.setToolTipText("Clear Fields");
        clearBtn.setBounds(242, 375, 50, 50);
        add(clearBtn);
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                showUnfocusedText();
            }
        });

        password = new JPasswordField();
        password.setEnabled(false);
        password.setForeground(Color.gray);
        password.setFont(new Font("Tahoma", Font.PLAIN, 12));
        password.setEchoChar((char) 0);
        password.setText("Password");
        password.setBounds(63, 224, 229, 30);
        password.addFocusListener(this);
        add(password);

        confirmPass = new JPasswordField();
        confirmPass.setEnabled(false);
        confirmPass.setForeground(Color.gray);
        confirmPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
        confirmPass.setEchoChar((char) 0);
        confirmPass.setText("Confirm Password");
        confirmPass.setBounds(63, 263, 229, 30);
        confirmPass.addFocusListener(this);
        add(confirmPass);

        new ConfirmPassThread().start();

        mainLabel = new JLabel(new ImageIcon(AdministratorPanel.class.getResource("adminPanel.png")));
        mainLabel.setBounds(0, 0, 360, 455);
        add(mainLabel);

    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        if (focusEvent.getSource() == empNameTF) {
            if (empNameTF.getText().equals("Employee Name")) {
                empNameTF.setForeground(Color.BLACK);
                empNameTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
                empNameTF.setText("");
            }
        } else if (focusEvent.getSource() == username) {
            if (username.getText().equals("Username")) {
                username.setForeground(Color.BLACK);
                username.setFont(new Font("Tahoma", Font.PLAIN, 14));
                username.setText("");
            }
        } else if (focusEvent.getSource() == password) {
            if (String.valueOf(password.getPassword()).equals("Password")) {
                password.setForeground(Color.BLACK);
                password.setFont(new Font("Tahoma", Font.PLAIN, 14));
                password.setEchoChar('\u25CF');
                password.setText("");
            }
        } else if (focusEvent.getSource() == answer) {
            if (answer.getText().equals("Security Question Answer")) {
                answer.setForeground(Color.BLACK);
                answer.setFont(new Font("Tahoma", Font.PLAIN, 14));
                answer.setText("");
            }
        } else if (focusEvent.getSource() == confirmPass) {
            if (String.valueOf(confirmPass.getPassword()).equals("Confirm Password")) {
                confirmPass.setForeground(Color.BLACK);
                confirmPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
                confirmPass.setEchoChar('\u25CF');
                confirmPass.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        if (focusEvent.getSource() == empNameTF) {
            if (empNameTF.getText().isEmpty()) {
                empNameTF.setForeground(Color.gray);
                empNameTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
                empNameTF.setText("Employee Name");
            }
        } else if (focusEvent.getSource() == username) {
            if (username.getText().isEmpty()) {
                username.setForeground(Color.gray);
                username.setFont(new Font("Tahoma", Font.PLAIN, 12));
                username.setText("Username");
            }
        } else if (focusEvent.getSource() == password) {
            if (String.valueOf(password.getPassword()).isEmpty()) {
                password.setForeground(Color.gray);
                password.setFont(new Font("Tahoma", Font.PLAIN, 12));
                password.setEchoChar((char) 0);
                password.setText("Password");
            }
        } else if (focusEvent.getSource() == answer) {
            if (answer.getText().isEmpty()) {
                answer.setForeground(Color.gray);
                answer.setFont(new Font("Tahoma", Font.PLAIN, 12));
                answer.setText("Security Question Answer");
            }
        } else if (focusEvent.getSource() == confirmPass) {
            if (String.valueOf(confirmPass.getPassword()).isEmpty()) {
                confirmPass.setForeground(Color.gray);
                confirmPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
                confirmPass.setEchoChar((char) 0);
                confirmPass.setText("Confirm Password");
            }
        }
    }

    private class ConfirmPassThread extends Thread {

        public void run() {
            try {
                while (true) {
                    if (String.valueOf(confirmPass.getPassword()).equals(String.valueOf(password.getPassword()))) {
                        confirmPassIcon.setIcon(new ImageIcon(Main.class.getResource("passMatchIcon.png")));
                    } else {
                        confirmPassIcon.setIcon(new ImageIcon(Main.class.getResource("passNotMatchIcon.png")));
                    }
                }
            } catch (Exception exception) {
            }
        }
    }

    public void showUnfocusedText() {

        empNameTF.setForeground(Color.gray);
        empNameTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
        empNameTF.setText("Employee Name");
        empNameTF.setEditable(true);

        username.setForeground(Color.gray);
        username.setFont(new Font("Tahoma", Font.PLAIN, 12));
        username.setText("Username");

        password.setForeground(Color.gray);
        password.setFont(new Font("Tahoma", Font.PLAIN, 12));
        password.setEchoChar((char) 0);
        password.setText("Password");

        answer.setForeground(Color.gray);
        answer.setFont(new Font("Tahoma", Font.PLAIN, 12));
        answer.setText("Security Question Answer");

        confirmPass.setForeground(Color.gray);
        confirmPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
        confirmPass.setEchoChar((char) 0);
        confirmPass.setText("Confirm Password");

        securityQuestion.setSelectedIndex(0);
        yesCheckBox.setSelected(false);
    }

    private void autoComplete(String txt) {
        String complete = "";
        int start = txt.length();
        int last = txt.length();

        for (int i = 0; i < allEmployeesNames.size(); i++) {
            if (allEmployeesNames.get(i).startsWith(txt)) {
                complete = allEmployeesNames.get(i);
                last = complete.length();
                break;
            }
        }
        if (last > start) {
            empNameTF.setText(complete);
            empNameTF.setCaretPosition(last);
            empNameTF.moveCaretPosition(start);
        }
    }
}
