package ProjectClasses;

import DatabaseRelations.Assigns;
import DatabaseRelations.Booking;
import DatabaseRelations.Guest;
import DatabaseRelations.Meal;
import DatabaseRelations.Person;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Meals_Names_Prices {

    private String name;
    private double price;

    public Meals_Names_Prices(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }
}

class Person_GuestRecords {

    private String name;
    private int guestID;

    public Person_GuestRecords(String name, int guestID) {
        this.name = name;
        this.guestID = guestID;
    }

    public String getName() {
        return this.name;
    }

    public int getGuestID() {
        return guestID;
    }
}

public class MealOrdersPanel extends JPanel implements FocusListener {

    private JLabel mainLabel;
    private JTextField mealPrice;
    private JTextField mealQuantity;
    private JComboBox<String> mealsComboBox;
    private EntityManager entityManager;
    private ArrayList<Meals_Names_Prices> meals_prices;

    public MealOrdersPanel() {
        entityManager = Main.entityManager;

        new MealOrdersPanelPanelAppear(this).start();
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setSize(360, 455);
        setLayout(null);
        setFocusable(true);

        meals_prices = new ArrayList<Meals_Names_Prices>();

        mealsComboBox = new JComboBox<String>();
        mealsComboBox.setForeground(Color.GRAY);
        mealsComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mealsComboBox.setBounds(63, 95, 229, 30);
        add(mealsComboBox);

        try {
            Main.result = Main.statement.executeQuery("SELECT name,price FROM MEAL");
            while (Main.result.next()) {
                meals_prices.add(new Meals_Names_Prices(Main.result.getString(1), Main.result.getDouble(2)));
            }
        } catch (Exception ex) {
        }
        String str[] = new String[meals_prices.size() + 1];
        str[0] = "Select a meal name:";
        for (int i = 0; i < meals_prices.size(); i++) {
            str[i + 1] = meals_prices.get(i).getName();
        }
        mealsComboBox.setModel(new DefaultComboBoxModel<String>(str));
        mealsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < meals_prices.size(); i++) {
                    if (mealsComboBox.getSelectedItem().equals(meals_prices.get(i).getName())) {
                        mealPrice.setForeground(Color.BLACK);
                        mealPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
                        mealPrice.setText("" + meals_prices.get(i).getPrice());
                        break;
                    }
                }
                if (mealsComboBox.getSelectedIndex() == 0) {
                    mealQuantity.setForeground(Color.gray);
                    mealQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
                    mealQuantity.setText("Quantity");

                    mealPrice.setText("Meal Price");
                    mealPrice.setForeground(Color.GRAY);
                    mealPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
                }
            }
        });

        JLabel mealsIcon = new JLabel(new ImageIcon(MealOrdersPanel.class.getResource("mealNameIcon.png")));
        mealsIcon.setBounds(10, 95, 47, 30);
        add(mealsIcon);

        JButton backBtn = new JButton(new ImageIcon(MealOrdersPanel.class.getResource("backIcon.png")));
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new Main.PanelDisappear(MealOrdersPanel.this).start();
                Settings.mealOrdersCreated = false;

                Settings.booking = new BookingPanel();
                MainScreen.mainScreenFrame.getContentPane().add(Settings.booking);

                Settings.booking.requestFocus();
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.panel);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.background);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.separator);
            }
        });
        backBtn.setBounds(63, 305, 50, 50);
        add(backBtn);

        JButton clearBtn = new JButton(new ImageIcon(MealOrdersPanel.class.getResource("clearIcon.png")));
        clearBtn.setToolTipText("Clear Fields");
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                showUnfocusedText();
            }
        });
        clearBtn.setBounds(153, 305, 50, 50);
        add(clearBtn);

        JButton submitBtn = new JButton(new ImageIcon(MealOrdersPanel.class.getResource("submitIcon.png")));
        submitBtn.setToolTipText("Order Meal");
        submitBtn.setBounds(242, 305, 50, 50);
        add(submitBtn);
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (mealsComboBox.getSelectedIndex() == 0 || mealQuantity.getText().equals("Quantity")) {
                    JOptionPane.showMessageDialog(MealOrdersPanel.this, "You have to fill in all fields!", "Empty Fields", JOptionPane.ERROR_MESSAGE);
                } else if (mealQuantity.getText().equals("0")) {
                    JOptionPane.showMessageDialog(MealOrdersPanel.this, "Quantity Cannot Be Zero!", "Wrong Input", JOptionPane.ERROR_MESSAGE);

                } else {
                    Booking booking = new Booking();
                    entityManager.persist(booking);

                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = dateFormat.parse(BookingPanel.bookingDate.getText());
                        booking.setBookingDate(date);

                        booking.setBookingNo(BookingPanel.bookingNumber);
                        booking.setBookingType("Meal");

                        EntityManager adminEM = Persistence.createEntityManagerFactory("NajahHotelPU").createEntityManager();
                        List<Assigns> admins = adminEM.createNamedQuery("Assigns.findAll").getResultList();

                        for (Assigns admin : admins) {
                            if (admin.getUsername().equals(MainScreen.currUsername)) {
                                booking.setUsername(admin);
                                break;
                            }
                        }

                        EntityManager guestEM = Persistence.createEntityManagerFactory("NajahHotelPU").createEntityManager();
                        List<Guest> guests = guestEM.createNamedQuery("Guest.findAll").getResultList();

                        EntityManager personEM = Persistence.createEntityManagerFactory("NajahHotelPU").createEntityManager();
                        List<Person> persons = personEM.createNamedQuery("Person.findAll").getResultList();

                        boolean selectedGuest = false;
                        for (int i = 0; i < guests.size(); i++) {
                            for (int j = 0; j < persons.size(); j++) {
                                if ((persons.get(j).getFname() + " " + persons.get(j).getMname() + " " + persons.get(j).getLname()).equals(BookingPanel.guestName.getText())) {
                                    if (guests.get(i).getGuestId().equals(persons.get(j).getId())) {
                                        booking.setGuestId(guests.get(i));

                                        guestEM.getTransaction().begin();
                                        guestEM.persist(guests.get(i));
                                        double quantity = Double.parseDouble(mealQuantity.getText());
                                        double price = Double.parseDouble(mealPrice.getText());
                                        guests.get(i).setTotalPayment(guests.get(i).getTotalPayment() + (quantity * price));

                                        selectedGuest = true;
                                        break;
                                    }
                                }
                            }
                            if (selectedGuest) {
                                break;
                            }
                        }
                        EntityManager mealEM = Persistence.createEntityManagerFactory("NajahHotelPU").createEntityManager();
                        List<Meal> meals = mealEM.createNamedQuery("Meal.findAll").getResultList();

                        for (int i = 0; i < meals.size(); i++) {
                            if (mealsComboBox.getSelectedItem().equals(meals.get(i).getName())) {
                                mealEM.getTransaction().begin();
                                mealEM.persist(meals.get(i));

                                meals.get(i).setBookingNo(booking);
                            }
                        }

                        guestEM.getTransaction().commit();
                        entityManager.getTransaction().commit();
                        mealEM.getTransaction().commit();

                        JOptionPane.showMessageDialog(MealOrdersPanel.this, "Meal Ordering Done Successfully!", "Room Booking", JOptionPane.INFORMATION_MESSAGE);
                        entityManager.getTransaction().begin();
                        showUnfocusedText();
                    } catch (Exception exception) {
                        //JOptionPane.showMessageDialog(MealOrdersPanel.this, "Check your input Validation!", "Wrong Input", JOptionPane.ERROR_MESSAGE);
                        exception.printStackTrace();
                    }
                }
            }
        });

        JLabel mealPriceIcon = new JLabel(new ImageIcon(MealOrdersPanel.class.getResource("priceIcon.png")));
        mealPriceIcon.setBounds(10, 136, 47, 30);
        add(mealPriceIcon);

        mealPrice = new JTextField();
        mealPrice.setEditable(false);
        mealPrice.setText("Meal Price");
        mealPrice.setForeground(Color.GRAY);
        mealPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mealPrice.setBounds(63, 136, 116, 30);
        add(mealPrice);

        mealQuantity = new JTextField();
        mealQuantity.setText("Quantity");
        mealQuantity.setForeground(Color.GRAY);
        mealQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mealQuantity.setBounds(63, 177, 116, 30);
        add(mealQuantity);
        mealQuantity.addFocusListener(this);
        mealQuantity.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9')) {
                    event.consume();
                }
            }
        });

        JLabel mealQuantityIcon = new JLabel(new ImageIcon(MealOrdersPanel.class.getResource("quantityIcon.png")));
        mealQuantityIcon.setBounds(10, 177, 47, 30);
        add(mealQuantityIcon);

        mainLabel = new JLabel(new ImageIcon(MealOrdersPanel.class.getResource("mealOrdersPanel.png")));
        mainLabel.setBounds(0, 0, 360, 455);
        add(mainLabel);
    }

    public void showUnfocusedText() {
        if (mealsComboBox.getSelectedIndex() != -1) {
            mealsComboBox.setSelectedIndex(0);
        }

        mealQuantity.setForeground(Color.gray);
        mealQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mealQuantity.setText("Quantity");

        try {
            Main.result = Main.statement.executeQuery("SELECT BOOKING_SEQ.NEXTVAL FROM DUAL");
            Main.result.next();
            BookingPanel.bookingNumber = Main.result.getInt(1);
        } catch (Exception ex) {
        }
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        if (focusEvent.getSource() == mealQuantity) {
            if (mealQuantity.getText().equals("Quantity")) {
                mealQuantity.setForeground(Color.BLACK);
                mealQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
                mealQuantity.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        if (focusEvent.getSource() == mealQuantity) {
            if (mealQuantity.getText().isEmpty()) {
                mealQuantity.setForeground(Color.gray);
                mealQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
                mealQuantity.setText("Quantity");
            }
        }
    }

    public static class MealOrdersPanelPanelAppear extends Thread {

        int x = 300;
        int y = 150;
        JPanel panel;

        int oldWidth = 0, oldHeight = 0;

        public MealOrdersPanelPanelAppear(JPanel panel) {
            this.panel = panel;
        }

        public void run() {
            try {
                while (oldWidth < 360) {
                    Thread.sleep(5);
                    oldWidth += 10;
                    panel.setBounds(x--, y--, oldWidth, oldHeight);
                }
                while (oldHeight < 455) {
                    Thread.sleep(5);
                    oldHeight += 10;
                    panel.setBounds(x--, y--, oldWidth, oldHeight);
                }
            } catch (Exception e) {
            }
        }
    }
}
