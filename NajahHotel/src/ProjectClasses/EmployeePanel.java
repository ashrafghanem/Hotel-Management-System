package ProjectClasses;

import DatabaseRelations.Dependent;
import DatabaseRelations.DependentPK;
import DatabaseRelations.Employee;
import DatabaseRelations.Person;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

class DependentPanel implements FocusListener {

    public JTextField depName;
    public JComboBox<String> relationship;
    public JRadioButton maleRB, femaleRB;
    public JDateChooser dateChooser;
    public ButtonGroup buttonGroup;

    public DependentPanel(int yPosition) {
        depName = new JTextField();
        depName.setBounds(10, yPosition, 86, 30);
        EmployeePanel.inPanel.add(depName);
        depName.setColumns(10);
        depName.addFocusListener(this);

        relationship = new JComboBox<String>();
        relationship.setBounds(202, yPosition, 86, 30);
        EmployeePanel.inPanel.add(relationship);
        relationship.setModel(new DefaultComboBoxModel<String>(new String[]{"...", "Uncle", "Son", "Daughter",
            "Father", "Brother", "Nephew", "Cousin", "Sister", "Other"}));

        buttonGroup = new ButtonGroup();

        maleRB = new JRadioButton("Male");
        maleRB.setBounds(108, yPosition - 7, 60, 23);
        buttonGroup.add(maleRB);
        EmployeePanel.inPanel.add(maleRB);
        maleRB.setFont(new Font("Tahoma", Font.BOLD, 13));

        femaleRB = new JRadioButton("Female");
        femaleRB.setBounds(108, yPosition + 18, 75, 23);
        EmployeePanel.inPanel.add(femaleRB);
        buttonGroup.add(femaleRB);
        femaleRB.setFont(new Font("Tahoma", Font.BOLD, 13));

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-MM-yyyy");
        dateChooser.setBounds(295, yPosition, 152, 30);
        EmployeePanel.inPanel.add(dateChooser);

        requestFocus();
        EmployeePanel.inPanel.repaint();
    }

    public void requestFocus() {
        depName.requestFocus();
        EmployeePanel.inPanel.requestFocus();
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        if (focusEvent.getSource() == depName) {
            if (depName.getText().equals("Dep. Name")) {
                depName.setForeground(Color.BLACK);
                depName.setFont(new Font("Tahoma", Font.PLAIN, 14));
                depName.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        if (focusEvent.getSource() == depName) {
            if (depName.getText().isEmpty()) {
                depName.setForeground(Color.gray);
                depName.setFont(new Font("Tahoma", Font.PLAIN, 12));
                depName.setText("Dep. Name");
            }
        }
    }
}

public class EmployeePanel extends JPanel implements FocusListener {

    private JTextField firstName, midName, lastName, ID, mobile, email, nationality, street, city, country, phone,
            familyCount;
    private JTextField empNO, salary;
    private JLabel emailLabel, birthLabel, empNOLabel, phoneLabel, familyCountLabel, socialStateLabel, salaryLabel;

    private JScrollPane inPanelScroll;
    private JScrollPane outPanelScroll;

    private JRadioButton male;
    private JRadioButton female;

    private JComboBox<String> socialState;

    public static JPanel outPanel, inPanel;

    private final ButtonGroup buttonGroup = new ButtonGroup();

    private JButton clearBtn;
    private JButton addEmpBtn;
    private JButton btnAdd;
    private int depYDist = 27;
    private ArrayList<DependentPanel> dependents = new ArrayList<DependentPanel>();
    private JLabel background;
    private JDateChooser dateChooser;
    private List<Person> employeeRecord;
    private List<Dependent> dependentRecord;
    private EntityManager entityManager;
    private Query query;
    private int employeeNO;
    private Employee emp;
    private JCheckBox hasDependents;

    public EmployeePanel() {
        entityManager = Main.entityManager;
        emp = new Employee();

        try {
            Main.result = Main.statement.executeQuery("SELECT EMP_SEQ.NEXTVAL FROM DUAL");
            Main.result.next();
            employeeNO = Main.result.getInt(1);
        } catch (Exception ex) {
        }

        new EmployeePanelPanelAppear(this).start();
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setSize(682, 455);
        setLayout(null);
        setFocusable(true);

        outPanel = new JPanel();
        outPanel.setPreferredSize(new Dimension(664, 580));
        outPanel.setLayout(null);
        outPanel.setBackground(new Color(0, 0, 0, 0));
        outPanel.setOpaque(false);

        outPanelScroll = new JScrollPane(outPanel);
        outPanelScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        outPanelScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outPanelScroll.setBounds(0, 54, 681, 406);
        outPanelScroll.setBackground(new Color(0, 0, 0, 0));
        add(outPanelScroll);

        addEmpBtn = new JButton(new ImageIcon(EmployeePanel.class.getResource("addIcon.png")));
        addEmpBtn.setToolTipText("Add Employee");
        addEmpBtn.setBounds(460, 290, 50, 50);
        outPanel.add(addEmpBtn);
        addEmpBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        addEmpBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                if ((firstName.getText().equals("Fname") || midName.getText().equals("M") || lastName.getText().equals("Lname")
                        || ID.getText().equals("Employee ID") || mobile.getText().equals("Mobile Number") || email.getText().equals("Email Address")
                        || dateChooser.getDate() == null || (!male.isSelected() && !female.isSelected())
                        || street.getText().equals("Street") || city.getText().equals("City") || country.getText().equals("Country")
                        || nationality.getText().equals("Nationality")) || salary.getText().equals("Salary")
                        || familyCount.getText().equals("Family Count") || socialState.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(EmployeePanel.this, "You have to fill in all fields!", "Empty Fields", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean personAlreadyExists = false;
                    query = entityManager.createNamedQuery("Person.findAll");
                    employeeRecord = query.getResultList();
                    for (Person person : employeeRecord) {
                        if (Integer.parseInt(ID.getText()) == person.getId()) {
                            personAlreadyExists = true;
                            JOptionPane.showMessageDialog(EmployeePanel.this, "A person with this ID already exists!", "Person", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }
                    if (!personAlreadyExists) {
                        Date date = new Date();
                        if (dateChooser.getDate().after(date)) {
                            JOptionPane.showMessageDialog(EmployeePanel.this, "Choose a correct birth date!", "BirthDate", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        Person person_emp = new Person();
                        entityManager.persist(person_emp);

                        emp = new Employee();
                        entityManager.persist(emp);

                        person_emp.setId(Integer.parseInt(ID.getText()));
                        person_emp.setBirthDate(dateChooser.getDate());
                        person_emp.setCity(city.getText());
                        person_emp.setCountry(country.getText());
                        person_emp.setStreet(street.getText());
                        person_emp.setEmail(email.getText());
                        person_emp.setFname(firstName.getText());
                        person_emp.setMname(midName.getText().toCharArray()[0]);
                        person_emp.setLname(lastName.getText());
                        person_emp.setMobileNo(Integer.parseInt(mobile.getText()));
                        person_emp.setNationality(nationality.getText());
                        if (male.isSelected()) {
                            person_emp.setGender('M');
                        } else {
                            person_emp.setGender('F');
                        }

                        emp.setEmpNo(employeeNO);
                        emp.setEmpId(person_emp);
                        emp.setFamilyCount(Short.parseShort(familyCount.getText()));
                        emp.setPhoneNo(Long.parseLong(phone.getText()));
                        emp.setSalary(Integer.parseInt(salary.getText()));
                        emp.setSocialState((String) socialState.getSelectedItem());

                        if (hasDependents.isSelected()) {
                            for (int i = 0; i < dependents.size(); i++) {
                                boolean dependentAlreadyExists = false;
                                query = entityManager.createNamedQuery("Dependent.findAll");
                                dependentRecord = query.getResultList();
                                for (Dependent dependent : dependentRecord) {
                                    if (dependent.getDependentPK().getEmpNo() == emp.getEmpNo()) {
                                        if (dependent.getDependentPK().getDepName().equals(dependents.get(i).depName.getText())) {
                                            dependentAlreadyExists = true;
                                            JOptionPane.showMessageDialog(EmployeePanel.this, "A dependent with this name already exists!", "Dependent", JOptionPane.ERROR_MESSAGE);
                                            break;
                                        }
                                    }
                                }
                                if (dependentAlreadyExists || dependents.get(i).depName.getText().equals("Dep. Name") || dependents.get(i).dateChooser == null
                                        || dependents.get(i).relationship.getSelectedIndex() == 0 || (!dependents.get(i).femaleRB.isSelected()
                                        && !dependents.get(i).maleRB.isSelected())) {
                                    continue;
                                }
                                Dependent dependent = new Dependent();
                                DependentPK dependentPK = new DependentPK();
                                entityManager.persist(dependent);

                                dependentPK.setEmpNo(employeeNO);
                                dependentPK.setDepName(dependents.get(i).depName.getText());
                                dependent.setDependentPK(dependentPK);

                                if (dependents.get(i).femaleRB.isSelected()) {
                                    dependent.setDepGender('F');
                                } else {
                                    dependent.setDepGender('M');
                                }
                                dependent.setDepRelationship((String) dependents.get(i).relationship.getSelectedItem());
                                dependent.setDepBd(dependents.get(i).dateChooser.getDate());
                                dependent.setEmployee(emp);
                            }
                        }
                        entityManager.getTransaction().commit();

                        JOptionPane.showMessageDialog(EmployeePanel.this, "Employee Added Successfully!", "Employees", JOptionPane.INFORMATION_MESSAGE);
                        entityManager.getTransaction().begin();

                        try {
                            Main.result = Main.statement.executeQuery("SELECT EMP_SEQ.NEXTVAL FROM DUAL");
                            Main.result.next();
                            employeeNO = Main.result.getInt(1);
                            empNO.setText("Employee Number: " + employeeNO);
                        } catch (Exception ex) {
                        }
                        showUnfocusedText();
                    }
                }
            }
        });

        inPanel = new JPanel();
        inPanel.setBorder(
                new TitledBorder(null, "Dependents Info.", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        inPanel.setPreferredSize(new Dimension(376, 170));
        inPanel.setLayout(null);

        inPanelScroll = new JScrollPane(inPanel);
        inPanelScroll.setBounds(10, 343, 476, 170);
        outPanel.add(inPanelScroll);
        inPanelScroll.setVisible(false);

        familyCountLabel = new JLabel(new ImageIcon(EmployeePanel.class.getResource("familyCount.png")));
        familyCountLabel.setBounds(360, 11, 47, 30);
        outPanel.add(familyCountLabel);

        familyCount = new JTextField();
        familyCount.setBounds(405, 11, 228, 30);
        outPanel.add(familyCount);
        familyCount.setFont(new Font("Tahoma", Font.PLAIN, 13));
        familyCount.setColumns(10);
        familyCount.addFocusListener(this);
        familyCount.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9')) {
                    event.consume();
                }
            }
        });

        socialState = new JComboBox<String>();
        socialState.setModel(new DefaultComboBoxModel<String>(new String[]{"...", "Single", "Married", "Engaged"}));
        socialState.setBounds(405, 49, 228, 30);
        outPanel.add(socialState);
        socialState.setFont(new Font("Tahoma", Font.PLAIN, 13));

        socialStateLabel = new JLabel(new ImageIcon(EmployeePanel.class.getResource("socialState.png")));
        socialStateLabel.setBounds(360, 49, 47, 30);
        outPanel.add(socialStateLabel);

        salaryLabel = new JLabel(new ImageIcon(EmployeePanel.class.getResource("salary.png")));
        salaryLabel.setBounds(360, 87, 47, 30);
        outPanel.add(salaryLabel);

        salary = new JTextField();
        salary.setBounds(405, 87, 228, 30);
        outPanel.add(salary);
        salary.setFont(new Font("Tahoma", Font.PLAIN, 13));
        salary.setColumns(10);
        salary.addFocusListener(this);
        salary.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9')) {
                    event.consume();
                }
            }
        });

        JLabel nameLabel = new JLabel(new ImageIcon(EmployeePanel.class.getResource("usernameIcon.png")));
        nameLabel.setBounds(10, 11, 47, 30);
        outPanel.add(nameLabel);

        firstName = new JTextField();
        firstName.setBounds(57, 11, 91, 30);
        outPanel.add(firstName);
        firstName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        firstName.setColumns(10);
        firstName.addFocusListener(this);
        firstName.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ') {
                    event.consume();
                }
            }
        });

        midName = new JTextField();
        midName.setBounds(153, 11, 35, 30);
        outPanel.add(midName);
        midName.setHorizontalAlignment(SwingConstants.CENTER);
        midName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        midName.setColumns(10);
        midName.addFocusListener(this);
        midName.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (midName.getText().length() == 1) {
                    event.consume();
                }
            }
        });

        lastName = new JTextField();
        lastName.setBounds(193, 11, 91, 30);
        outPanel.add(lastName);
        lastName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lastName.setColumns(10);
        lastName.addFocusListener(this);
        lastName.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ') {
                    event.consume();
                }
            }
        });

        ID = new JTextField();
        ID.setBounds(57, 49, 227, 30);
        outPanel.add(ID);
        ID.setFont(new Font("Tahoma", Font.PLAIN, 13));
        ID.setColumns(10);
        ID.addFocusListener(this);
        ID.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9')
                        || ID.getText().length() > 8) {
                    event.consume();
                }
            }
        });

        JLabel idLabel = new JLabel(new ImageIcon(EmployeePanel.class.getResource("idIcon.png")));
        idLabel.setBounds(10, 49, 47, 30);
        outPanel.add(idLabel);

        empNOLabel = new JLabel(new ImageIcon(EmployeePanel.class.getResource("empNO.png")));
        empNOLabel.setBounds(10, 87, 47, 30);
        outPanel.add(empNOLabel);

        empNO = new JTextField("Employee Number: " + employeeNO);
        empNO.setEditable(false);
        empNO.setBounds(57, 87, 227, 30);
        outPanel.add(empNO);
        empNO.setFont(new Font("Tahoma", Font.PLAIN, 13));
        empNO.setColumns(10);
        empNO.addFocusListener(this);
        empNO.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9')) {
                    event.consume();
                }
            }
        });

        mobile = new JTextField();
        mobile.setBounds(57, 125, 227, 30);
        outPanel.add(mobile);
        mobile.setFont(new Font("Tahoma", Font.PLAIN, 13));
        mobile.setColumns(10);
        mobile.addFocusListener(this);
        mobile.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9')) {
                    event.consume();
                }
            }
        });

        JLabel mobileLabel = new JLabel(new ImageIcon(EmployeePanel.class.getResource("mobileIcon.png")));
        mobileLabel.setBounds(10, 125, 47, 30);
        outPanel.add(mobileLabel);

        phoneLabel = new JLabel(new ImageIcon(EmployeePanel.class.getResource("phoneNO.png")));
        phoneLabel.setBounds(10, 163, 47, 30);
        outPanel.add(phoneLabel);

        phone = new JTextField();
        phone.setBounds(57, 163, 227, 30);
        outPanel.add(phone);
        phone.setFont(new Font("Tahoma", Font.PLAIN, 13));
        phone.setColumns(10);
        phone.addFocusListener(this);
        phone.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9')) {
                    event.consume();
                }
            }
        });

        JLabel genderLabel = new JLabel(new ImageIcon(EmployeePanel.class.getResource("genderIcon.png")));
        genderLabel.setBounds(10, 202, 47, 30);
        outPanel.add(genderLabel);

        male = new JRadioButton("Male");
        buttonGroup.add(male);
        male.setBounds(57, 205, 60, 23);
        male.setBackground(new Color(0, 0, 0, 0));
        outPanel.add(male);
        male.setFont(new Font("Tahoma", Font.BOLD, 13));

        female = new JRadioButton("Female");
        buttonGroup.add(female);
        female.setBounds(157, 205, 75, 23);
        outPanel.add(female);
        female.setBackground(new Color(0, 0, 0, 0));
        female.setFont(new Font("Tahoma", Font.BOLD, 13));

        nationality = new JTextField();
        nationality.setBounds(57, 240, 227, 30);
        outPanel.add(nationality);
        nationality.setFont(new Font("Tahoma", Font.PLAIN, 13));
        nationality.setColumns(10);
        nationality.addFocusListener(this);

        JLabel nationLabel = new JLabel(new ImageIcon(EmployeePanel.class.getResource("nationIcon.png")));
        nationLabel.setBounds(10, 240, 47, 30);
        outPanel.add(nationLabel);

        birthLabel = new JLabel(new ImageIcon(EmployeePanel.class.getResource("BDIcon.png")));
        birthLabel.setBounds(360, 202, 47, 30);
        outPanel.add(birthLabel);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(405, 202, 227, 30);
        outPanel.add(dateChooser);
        dateChooser.setDateFormatString("dd-MM-yyyy");

        emailLabel = new JLabel(new ImageIcon(EmployeePanel.class.getResource("emailIcon.png")));
        emailLabel.setBounds(360, 240, 47, 30);
        outPanel.add(emailLabel);

        email = new JTextField();
        email.setBounds(405, 240, 227, 30);
        outPanel.add(email);
        email.setFont(new Font("Tahoma", Font.PLAIN, 13));
        email.setColumns(10);
        email.addFocusListener(this);

        JLabel addressLabel = new JLabel(new ImageIcon(EmployeePanel.class.getResource("addressIcon.png")));
        addressLabel.setBounds(360, 125, 47, 30);
        outPanel.add(addressLabel);

        street = new JTextField();
        street.setBounds(405, 125, 110, 30);
        outPanel.add(street);
        street.setFont(new Font("Tahoma", Font.PLAIN, 13));
        street.setColumns(10);
        street.addFocusListener(this);

        city = new JTextField();
        city.setBounds(522, 125, 110, 30);
        outPanel.add(city);
        city.setFont(new Font("Tahoma", Font.PLAIN, 13));
        city.setColumns(10);
        city.addFocusListener(this);

        country = new JTextField();
        country.setBounds(405, 163, 227, 30);
        outPanel.add(country);
        country.setFont(new Font("Tahoma", Font.PLAIN, 13));
        country.setColumns(10);
        country.addFocusListener(this);

        hasDependents = new JCheckBox("Has dependents? ");
        hasDependents.setBounds(10, 297, 129, 23);
        hasDependents.setBackground(new Color(0, 0, 0, 0));
        outPanel.add(hasDependents);
        hasDependents.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                if (hasDependents.isSelected()) {
                    inPanelScroll.setVisible(true);
                    btnAdd.setEnabled(true);
                } else {
                    inPanelScroll.setVisible(false);
                    for (int i = 0; i < dependents.size(); i++) {
                        dependents.get(i).depName.setForeground(Color.gray);
                        dependents.get(i).depName.setFont(new Font("Tahoma", Font.PLAIN, 12));
                        dependents.get(i).depName.setText("Dep. Name");
                        dependents.get(i).dateChooser.getDateEditor().setDate(null);
                        dependents.get(i).relationship.setSelectedIndex(0);
                        dependents.get(i).buttonGroup.clearSelection();
                    }
                    dependents.clear();
                    btnAdd.setEnabled(false);
                }
            }
        });

        clearBtn = new JButton(new ImageIcon(EmployeePanel.class.getResource("clearIcon.png")));
        clearBtn.setToolTipText("Clear Fields");
        clearBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        clearBtn.setBounds(583, 290, 50, 50);
        outPanel.add(clearBtn);
        clearBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                showUnfocusedText();
            }
        });

        btnAdd = new JButton("Add a Dependent");
        btnAdd.setToolTipText("Add Dependent");
        btnAdd.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnAdd.setEnabled(false);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                DependentPanel dep = new DependentPanel(depYDist);
                depYDist += 72;
                dependents.add(dep);
                inPanel.setPreferredSize(new Dimension(376, inPanel.getHeight() + 100));
            }
        });
        btnAdd.setBounds(153, 289, 129, 43);
        outPanel.add(btnAdd);
        
        JButton reportBtn = new JButton(new ImageIcon(GuestPanel.class.getResource("reportBtnIcon.png")));
        reportBtn.setToolTipText("Show report");
        reportBtn.setBounds(522, 290, 50, 50);
        outPanel.add(reportBtn);
        reportBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    InputStream inputStream = EmployeePanel.class.getResourceAsStream("EmployeeReport.jrxml");

                    JasperReport JR = JasperCompileManager.compileReport(inputStream);
                    JasperPrint JP = JasperFillManager.fillReport(JR, null, Main.connection);
                    if (JP.getPages().isEmpty()) {
                        JOptionPane.showMessageDialog(EmployeePanel.this, "The Document has no pages!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JasperViewer.viewReport(JP, false);
                    }
                } catch (Exception ex) {
                }
            }
        });

        background = new JLabel(new ImageIcon(EmployeePanel.class.getResource("empPanel.png")));
        background.setBounds(0, 0, 681, 455);
        add(background);
    }

    public void showUnfocusedText() {
        firstName.setForeground(Color.gray);
        firstName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        firstName.setText("Fname");

        midName.setForeground(Color.gray);
        midName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        midName.setText("M");

        lastName.setForeground(Color.gray);
        lastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lastName.setText("Lname");

        ID.setForeground(Color.gray);
        ID.setFont(new Font("Tahoma", Font.PLAIN, 12));
        ID.setText("Employee ID");

        mobile.setForeground(Color.gray);
        mobile.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mobile.setText("Mobile Number");

        email.setForeground(Color.gray);
        email.setFont(new Font("Tahoma", Font.PLAIN, 12));
        email.setText("Email Address");

        street.setForeground(Color.gray);
        street.setFont(new Font("Tahoma", Font.PLAIN, 12));
        street.setText("Street");

        city.setForeground(Color.gray);
        city.setFont(new Font("Tahoma", Font.PLAIN, 12));
        city.setText("City");

        country.setForeground(Color.gray);
        country.setFont(new Font("Tahoma", Font.PLAIN, 12));
        country.setText("Country");

        nationality.setForeground(Color.gray);
        nationality.setFont(new Font("Tahoma", Font.PLAIN, 12));
        nationality.setText("Nationality");

        salary.setForeground(Color.gray);
        salary.setFont(new Font("Tahoma", Font.PLAIN, 12));
        salary.setText("Salary");

        familyCount.setForeground(Color.gray);
        familyCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
        familyCount.setText("Family Count");

        phone.setForeground(Color.gray);
        phone.setFont(new Font("Tahoma", Font.PLAIN, 12));
        phone.setText("Phone Number");

        socialState.setSelectedIndex(0);
        dateChooser.getDateEditor().setDate(null);
        buttonGroup.clearSelection();
        hasDependents.setSelected(false);
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        if (focusEvent.getSource() == firstName) {
            if (firstName.getText().equals("Fname")) {
                firstName.setForeground(Color.BLACK);
                firstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
                firstName.setText("");
            }
        } else if (focusEvent.getSource() == midName) {
            if (midName.getText().equals("M")) {
                midName.setForeground(Color.BLACK);
                midName.setFont(new Font("Tahoma", Font.PLAIN, 14));
                midName.setText("");
            }
        } else if (focusEvent.getSource() == lastName) {
            if (lastName.getText().equals("Lname")) {
                lastName.setForeground(Color.BLACK);
                lastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
                lastName.setText("");
            }
        } else if (focusEvent.getSource() == ID) {
            if (ID.getText().equals("Employee ID")) {
                ID.setForeground(Color.BLACK);
                ID.setFont(new Font("Tahoma", Font.PLAIN, 14));
                ID.setText("");
            }
        } else if (focusEvent.getSource() == mobile) {
            if (mobile.getText().equals("Mobile Number")) {
                mobile.setForeground(Color.BLACK);
                mobile.setFont(new Font("Tahoma", Font.PLAIN, 14));
                mobile.setText("");
            }
        } else if (focusEvent.getSource() == phone) {
            if (phone.getText().equals("Phone Number")) {
                phone.setForeground(Color.BLACK);
                phone.setFont(new Font("Tahoma", Font.PLAIN, 14));
                phone.setText("");
            }
        } else if (focusEvent.getSource() == email) {
            if (email.getText().equals("Email Address")) {
                email.setForeground(Color.BLACK);
                email.setFont(new Font("Tahoma", Font.PLAIN, 14));
                email.setText("");
            }
        } else if (focusEvent.getSource() == nationality) {
            if (nationality.getText().equals("Nationality")) {
                nationality.setForeground(Color.BLACK);
                nationality.setFont(new Font("Tahoma", Font.PLAIN, 14));
                nationality.setText("");
            }
        } else if (focusEvent.getSource() == street) {
            if (street.getText().equals("Street")) {
                street.setForeground(Color.BLACK);
                street.setFont(new Font("Tahoma", Font.PLAIN, 14));
                street.setText("");
            }
        } else if (focusEvent.getSource() == city) {
            if (city.getText().equals("City")) {
                city.setForeground(Color.BLACK);
                city.setFont(new Font("Tahoma", Font.PLAIN, 14));
                city.setText("");
            }
        } else if (focusEvent.getSource() == country) {
            if (country.getText().equals("Country")) {
                country.setForeground(Color.BLACK);
                country.setFont(new Font("Tahoma", Font.PLAIN, 14));
                country.setText("");
            }
        } else if (focusEvent.getSource() == salary) {
            if (salary.getText().equals("Salary")) {
                salary.setForeground(Color.BLACK);
                salary.setFont(new Font("Tahoma", Font.PLAIN, 14));
                salary.setText("");
            }
        } else if (focusEvent.getSource() == familyCount) {
            if (familyCount.getText().equals("Family Count")) {
                familyCount.setForeground(Color.BLACK);
                familyCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
                familyCount.setText("");
            }
        }

    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        if (focusEvent.getSource() == firstName) {
            if (firstName.getText().isEmpty()) {
                firstName.setForeground(Color.gray);
                firstName.setFont(new Font("Tahoma", Font.PLAIN, 12));
                firstName.setText("Fname");
            }
        } else if (focusEvent.getSource() == midName) {
            if (midName.getText().isEmpty()) {
                midName.setForeground(Color.gray);
                midName.setFont(new Font("Tahoma", Font.PLAIN, 12));
                midName.setText("M");
            }
        } else if (focusEvent.getSource() == lastName) {
            if (lastName.getText().isEmpty()) {
                lastName.setForeground(Color.gray);
                lastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
                lastName.setText("Lname");
            }
        } else if (focusEvent.getSource() == ID) {
            if (ID.getText().isEmpty()) {
                ID.setForeground(Color.gray);
                ID.setFont(new Font("Tahoma", Font.PLAIN, 12));
                ID.setText("Employee ID");
            }
        } else if (focusEvent.getSource() == mobile) {
            if (mobile.getText().isEmpty()) {
                mobile.setForeground(Color.gray);
                mobile.setFont(new Font("Tahoma", Font.PLAIN, 12));
                mobile.setText("Mobile Number");
            }
        } else if (focusEvent.getSource() == phone) {
            if (phone.getText().isEmpty()) {
                phone.setForeground(Color.gray);
                phone.setFont(new Font("Tahoma", Font.PLAIN, 12));
                phone.setText("Phone Number");
            }
        } else if (focusEvent.getSource() == email) {
            if (email.getText().isEmpty()) {
                email.setForeground(Color.gray);
                email.setFont(new Font("Tahoma", Font.PLAIN, 12));
                email.setText("Email Address");
            }
        } else if (focusEvent.getSource() == street) {
            if (street.getText().isEmpty()) {
                street.setForeground(Color.gray);
                street.setFont(new Font("Tahoma", Font.PLAIN, 12));
                street.setText("Street");
            }
        } else if (focusEvent.getSource() == city) {
            if (city.getText().isEmpty()) {
                city.setForeground(Color.gray);
                city.setFont(new Font("Tahoma", Font.PLAIN, 12));
                city.setText("City");
            }
        } else if (focusEvent.getSource() == country) {
            if (country.getText().isEmpty()) {
                country.setForeground(Color.gray);
                country.setFont(new Font("Tahoma", Font.PLAIN, 12));
                country.setText("Country");
            }
        } else if (focusEvent.getSource() == nationality) {
            if (nationality.getText().isEmpty()) {
                nationality.setForeground(Color.gray);
                nationality.setFont(new Font("Tahoma", Font.PLAIN, 12));
                nationality.setText("Nationality");
            }
        } else if (focusEvent.getSource() == salary) {
            if (salary.getText().isEmpty()) {
                salary.setForeground(Color.gray);
                salary.setFont(new Font("Tahoma", Font.PLAIN, 12));
                salary.setText("Salary");
            }
        } else if (focusEvent.getSource() == familyCount) {
            if (familyCount.getText().isEmpty()) {
                familyCount.setForeground(Color.gray);
                familyCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
                familyCount.setText("Family Count");
            }
        }
    }

    public void callEmpDisappear() {
        new EmployeePanelPanelDisAppear(this).start();
    }

    public static class EmployeePanelPanelAppear extends Thread {

        int x = 170;
        int y = 180;
        JPanel panel;

        int oldWidth = 0, oldHeight = 0;

        public EmployeePanelPanelAppear(JPanel panel) {
            this.panel = panel;
        }

        public void run() {
            try {
                while (oldHeight < 455) {
                    Thread.sleep(5);
                    oldHeight += 10;
                    panel.setBounds(x--, y--, oldWidth, oldHeight);
                }
                while (oldWidth < 700) {
                    Thread.sleep(5);
                    oldWidth += 10;
                    panel.setBounds(x--, y--, oldWidth, oldHeight);
                }
            } catch (Exception e) {
            }
        }
    }

    private class EmployeePanelPanelDisAppear extends Thread {

        JPanel panel;

        public EmployeePanelPanelDisAppear(JPanel panel) {
            this.panel = panel;
        }

        public void run() {
            try {
                while (panel.getX() < 800) {
                    Thread.sleep(5);
                    panel.setBounds(panel.getX() + 15, panel.getY(), panel.getWidth(), panel.getHeight());
                }
            } catch (InterruptedException e) {
            }
        }
    }
}
