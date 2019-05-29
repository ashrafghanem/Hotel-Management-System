package ProjectClasses;

import DatabaseRelations.Guest;
import DatabaseRelations.Person;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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

public class GuestPanel extends JPanel implements FocusListener {

    private JTextField firstName;
    private JTextField midName;
    private JTextField lastName;
    private JTextField id;
    private JTextField mobile;
    private JTextField email;
    private ButtonGroup buttonGroup;
    private JTextField nationality;
    private JTextField street;
    private JTextField city;
    private JTextField country;
    private JTextField totalPayment;
    private JDateChooser dateChooser;
    private List<Person> guestRecord;
    private EntityManager entityManager;
    private Query query;

    public GuestPanel() {
        entityManager = Main.entityManager;

        new Main.PanelAppear(this).start();
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setSize(360, 455);
        setLayout(null);
        setFocusable(true);

        JLabel mainLabel = new JLabel();
        mainLabel.setLocation(0, 0);
        mainLabel.setSize(360, 455);
        mainLabel.setIcon(new ImageIcon(GuestPanel.class.getResource("guestsPanel.png")));

        JLabel nameLabel = new JLabel(new ImageIcon(GuestPanel.class.getResource("usernameIcon.png")));
        nameLabel.setBounds(10, 74, 47, 30);
        add(nameLabel);

        firstName = new JTextField();
        firstName.setForeground(Color.BLACK);
        firstName.setFont(new Font("Tahoma", Font.BOLD, 13));
        firstName.setColumns(10);
        firstName.setBounds(57, 74, 91, 30);
        add(firstName);
        firstName.addFocusListener(this);
        firstName.setColumns(10);
        firstName.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ') {
                    event.consume();
                }
            }
        });

        midName = new JTextField();
        midName.setHorizontalAlignment(SwingConstants.CENTER);
        midName.setForeground(Color.BLACK);
        midName.setFont(new Font("Tahoma", Font.BOLD, 13));
        midName.setColumns(10);
        midName.setBounds(153, 74, 35, 30);
        add(midName);
        midName.addFocusListener(this);
        midName.setColumns(10);
        midName.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (midName.getText().length() == 1) {
                    event.consume();
                }
            }
        });

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-MM-yyyy");
        dateChooser.setBounds(57, 226, 227, 30);
        add(dateChooser);

        lastName = new JTextField();
        lastName.setForeground(Color.BLACK);
        lastName.setFont(new Font("Tahoma", Font.BOLD, 13));
        lastName.setColumns(10);
        lastName.setBounds(193, 74, 91, 30);
        add(lastName);
        lastName.addFocusListener(this);
        lastName.setColumns(10);
        lastName.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ') {
                    event.consume();
                }
            }
        });

        JLabel idLabel = new JLabel(new ImageIcon(GuestPanel.class.getResource("idIcon.png")));
        idLabel.setBounds(10, 112, 47, 30);
        add(idLabel);

        id = new JTextField();
        id.setForeground(Color.BLACK);
        id.setFont(new Font("Tahoma", Font.BOLD, 13));
        id.setColumns(10);
        id.setBounds(57, 112, 227, 30);
        add(id);
        id.addFocusListener(this);
        id.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9')
                        || id.getText().length() > 8) {
                    event.consume();
                }
            }
        });

        JLabel mobileLabel = new JLabel(new ImageIcon(GuestPanel.class.getResource("mobileIcon.png")));
        mobileLabel.setBounds(10, 150, 47, 30);
        add(mobileLabel);

        mobile = new JTextField();
        mobile.setForeground(Color.BLACK);
        mobile.setFont(new Font("Tahoma", Font.BOLD, 13));
        mobile.setColumns(10);
        mobile.setBounds(57, 150, 227, 30);
        add(mobile);
        mobile.addFocusListener(this);
        mobile.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9')) {
                    event.consume();
                }
            }
        });

        JLabel emailLabel = new JLabel(new ImageIcon(GuestPanel.class.getResource("emailIcon.png")));
        emailLabel.setBounds(10, 188, 47, 30);
        add(emailLabel);

        email = new JTextField();
        email.setForeground(Color.BLACK);
        email.setFont(new Font("Tahoma", Font.BOLD, 13));
        email.setColumns(10);
        email.setBounds(57, 188, 227, 30);
        email.addFocusListener(this);
        add(email);

        JLabel birthLabel = new JLabel(new ImageIcon(GuestPanel.class.getResource("BDIcon.png")));
        birthLabel.setBounds(10, 226, 47, 30);
        add(birthLabel);

        JLabel genderLabel = new JLabel(new ImageIcon(GuestPanel.class.getResource("genderIcon.png")));
        genderLabel.setBounds(10, 264, 47, 30);
        add(genderLabel);

        buttonGroup = new ButtonGroup();

        JRadioButton male = new JRadioButton("Male");
        male.setFont(new Font("Tahoma", Font.BOLD, 13));
        male.setBackground(new Color(0, 0, 0, 0));
        male.setBounds(57, 264, 86, 30);
        add(male);
        buttonGroup.add(male);

        JRadioButton female = new JRadioButton("Female");
        female.setFont(new Font("Tahoma", Font.BOLD, 13));
        female.setBounds(157, 264, 86, 30);
        female.setBackground(new Color(0, 0, 0, 0));
        add(female);
        buttonGroup.add(female);

        JLabel nationLabel = new JLabel(new ImageIcon(GuestPanel.class.getResource("nationIcon.png")));
        nationLabel.setBounds(10, 341, 47, 30);
        add(nationLabel);

        nationality = new JTextField();
        nationality.setForeground(Color.BLACK);
        nationality.setFont(new Font("Tahoma", Font.BOLD, 13));
        nationality.setColumns(10);
        nationality.setBounds(57, 341, 91, 30);
        add(nationality);
        nationality.addFocusListener(this);

        JLabel addressLabel = new JLabel(new ImageIcon(GuestPanel.class.getResource("addressIcon.png")));
        addressLabel.setBounds(10, 302, 47, 30);
        add(addressLabel);

        street = new JTextField();
        street.setForeground(Color.BLACK);
        street.setFont(new Font("Tahoma", Font.BOLD, 13));
        street.setColumns(10);
        street.setBounds(57, 302, 91, 30);
        street.addFocusListener(this);
        add(street);

        city = new JTextField();
        city.setForeground(Color.BLACK);
        city.setFont(new Font("Tahoma", Font.BOLD, 13));
        city.setColumns(10);
        city.setBounds(153, 302, 86, 30);
        city.addFocusListener(this);
        add(city);

        country = new JTextField();
        country.setForeground(Color.BLACK);
        country.setFont(new Font("Tahoma", Font.BOLD, 13));
        country.setColumns(10);
        country.setBounds(244, 302, 91, 30);
        country.addFocusListener(this);
        add(country);

        JLabel totalPayLabel = new JLabel(new ImageIcon(GuestPanel.class.getResource("paymentIcon.png")));
        totalPayLabel.setBounds(197, 341, 47, 30);
        add(totalPayLabel);

        totalPayment = new JTextField();
        totalPayment.setText("0.00");
        totalPayment.setFont(new Font("Tahoma", Font.BOLD, 13));
        totalPayment.setEditable(false);
        totalPayment.setColumns(10);
        totalPayment.setBounds(244, 341, 91, 30);
        add(totalPayment);

        JButton clearBtn = new JButton(new ImageIcon(GuestPanel.class.getResource("clearIcon.png")));
        clearBtn.setToolTipText("Clear Fields");
        clearBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        clearBtn.setBounds(244, 388, 50, 50);
        clearBtn.setOpaque(false);
        add(clearBtn);
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                showUnfocusedText();
            }
        });

        JButton reportBtn = new JButton(new ImageIcon(GuestPanel.class.getResource("reportBtnIcon.png")));
        reportBtn.setToolTipText("Show report");
        reportBtn.setBounds(153, 388, 50, 50);
        add(reportBtn);
        reportBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    InputStream inputStream = EmployeePanel.class.getResourceAsStream("GuestsReport.jrxml");
                    JasperReport JR = JasperCompileManager.compileReport(inputStream);
                    JasperPrint JP = JasperFillManager.fillReport(JR, null, Main.connection);
                    if (JP.getPages().isEmpty()) {
                        JOptionPane.showMessageDialog(GuestPanel.this, "The Document has no pages!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JasperViewer.viewReport(JP, false);
                    }
                } catch (Exception ex) {
                }
            }
        });

        JButton addGuestBtn = new JButton(new ImageIcon(GuestPanel.class.getResource("addIcon.png")));
        addGuestBtn.setToolTipText("Add Guest");
        addGuestBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        addGuestBtn.setBounds(57, 388, 50, 50);
        add(addGuestBtn);
        addGuestBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if ((firstName.getText().equals("Fname") || midName.getText().equals("M") || lastName.getText().equals("Lname")
                        || id.getText().equals("Guest ID") || mobile.getText().equals("Mobile Number") || email.getText().equals("Email Address")
                        || dateChooser.getDate() == null || (!male.isSelected() && !female.isSelected())
                        || street.getText().equals("Street") || city.getText().equals("City") || country.getText().equals("Country")
                        || nationality.getText().equals("Nationality"))) {
                    JOptionPane.showMessageDialog(GuestPanel.this, "You have to fill in all fields!", "Empty Fields", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean personAlreadyExists = false;
                    query = entityManager.createNamedQuery("Person.findAll");
                    guestRecord = query.getResultList();
                    for (Person person : guestRecord) {
                        if (Integer.parseInt(id.getText()) == person.getId()) {
                            personAlreadyExists = true;
                            JOptionPane.showMessageDialog(GuestPanel.this, "A person with this ID already exists!", "Person", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }
                    if (!personAlreadyExists) {
                        Date date = new Date();
                        if (dateChooser.getDate().after(date)) {
                            JOptionPane.showMessageDialog(GuestPanel.this, "Choose a correct birth date!", "BirthDate", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        Person person_guest = new Person();
                        Guest guest = new Guest();
                        entityManager.persist(person_guest);
                        entityManager.persist(guest);

                        person_guest.setId(Integer.parseInt(id.getText()));
                        person_guest.setBirthDate(dateChooser.getDate());
                        person_guest.setCity(city.getText());
                        person_guest.setCountry(country.getText());
                        person_guest.setStreet(street.getText());
                        person_guest.setEmail(email.getText());
                        person_guest.setFname(firstName.getText());
                        person_guest.setMname(midName.getText().toCharArray()[0]);
                        person_guest.setLname(lastName.getText());
                        person_guest.setMobileNo(Integer.parseInt(mobile.getText()));
                        person_guest.setNationality(nationality.getText());
                        if (male.isSelected()) {
                            person_guest.setGender('M');
                        } else {
                            person_guest.setGender('F');
                        }

                        guest.setGuestId(Integer.parseInt(id.getText()));
                        guest.setTotalPayment(Double.parseDouble(totalPayment.getText()));

                        entityManager.getTransaction().commit();
                        JOptionPane.showMessageDialog(GuestPanel.this, "Guest Added Successfully!", "Guests", JOptionPane.INFORMATION_MESSAGE);

                        entityManager.getTransaction().begin();
                        showUnfocusedText();
                    }
                }
            }
        });
        add(mainLabel);
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

        id.setForeground(Color.gray);
        id.setFont(new Font("Tahoma", Font.PLAIN, 12));
        id.setText("Guest ID");

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

        dateChooser.getDateEditor().setDate(null);
        buttonGroup.clearSelection();
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
        } else if (focusEvent.getSource() == id) {
            if (id.getText().equals("Guest ID")) {
                id.setForeground(Color.BLACK);
                id.setFont(new Font("Tahoma", Font.PLAIN, 14));
                id.setText("");
            }
        } else if (focusEvent.getSource() == mobile) {
            if (mobile.getText().equals("Mobile Number")) {
                mobile.setForeground(Color.BLACK);
                mobile.setFont(new Font("Tahoma", Font.PLAIN, 14));
                mobile.setText("");
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
        } else if (focusEvent.getSource() == id) {
            if (id.getText().isEmpty()) {
                id.setForeground(Color.gray);
                id.setFont(new Font("Tahoma", Font.PLAIN, 12));
                id.setText("Guest ID");
            }
        } else if (focusEvent.getSource() == mobile) {
            if (mobile.getText().isEmpty()) {
                mobile.setForeground(Color.gray);
                mobile.setFont(new Font("Tahoma", Font.PLAIN, 12));
                mobile.setText("Mobile Number");
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
        }
    }
}
