package ProjectClasses;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class BookingPanel extends JPanel implements FocusListener {

    private JLabel mainLabel;
    private JTextField bookingNO;
    public static JTextField bookingDate;
    public static JTextField guestName;
    private JTextField username;
    private JLabel bookingTypeIcon;
    public static RoomBookingPanel roomBooking;
    private JRadioButton mealRB, roomRB;
    public static MealOrdersPanel mealOrders;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private ArrayList<String> allGuestsNames;
    private ArrayList<Integer> allGuestsIDs;
    private EntityManager entityManager;
    public static int bookingNumber;

    public BookingPanel() {
        entityManager = Main.entityManager;

        new Main.PanelAppear(this).start();
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setSize(360, 455);
        setLayout(null);
        setFocusable(true);

        try {
            Main.result = Main.statement.executeQuery("SELECT BOOKING_SEQ.NEXTVAL FROM DUAL");
            Main.result.next();
            bookingNumber = Main.result.getInt(1);
        } catch (Exception ex) {
        }

        allGuestsNames = new ArrayList<String>();
        allGuestsIDs = new ArrayList<Integer>();

        try {
            Main.result = Main.statement.executeQuery("SELECT fname, mname, lname,GUEST.guest_id FROM PERSON,GUEST WHERE PERSON.ID = GUEST.guest_id");
            while (Main.result.next()) {
                String firstName = Main.result.getString(1);
                String midName = Main.result.getString(2);
                String lastName = Main.result.getString(3);
                int guest_id = Main.result.getInt(4);

                allGuestsNames.add(firstName + " " + midName + " " + lastName);
                allGuestsIDs.add(guest_id);
            }
        } catch (Exception ex) {
        }

        JLabel bookingNOIcon = new JLabel(new ImageIcon(BookingPanel.class.getResource("empNO.png")));
        bookingNOIcon.setBounds(10, 75, 47, 30);
        add(bookingNOIcon);

        bookingNO = new JTextField("Booking Number: " + bookingNumber);
        bookingNO.setEditable(false);
        bookingNO.setFont(new Font("Tahoma", Font.PLAIN, 13));
        bookingNO.setColumns(10);
        bookingNO.setBounds(63, 75, 229, 30);
        add(bookingNO);
        bookingNO.addFocusListener(this);

        JLabel bookingDateIcon = new JLabel(new ImageIcon(BookingPanel.class.getResource("BDIcon.png")));
        bookingDateIcon.setBounds(10, 116, 47, 30);
        add(bookingDateIcon);

        bookingDate = new JTextField();
        bookingDate.setEditable(false);
        bookingDate.setDisabledTextColor(Color.black);
        bookingDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
        bookingDate.setBounds(63, 116, 229, 30);
        add(bookingDate);

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        bookingDate.setText(dateFormat.format(date));

        JLabel guestNameIcon = new JLabel(new ImageIcon(BookingPanel.class.getResource("usernameIcon.png")));
        guestNameIcon.setBounds(10, 157, 47, 30);
        add(guestNameIcon);

        guestName = new JTextField();
        guestName.setText("Guest Name");
        guestName.setForeground(Color.GRAY);
        guestName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        guestName.setBounds(63, 157, 229, 30);
        add(guestName);
        guestName.addFocusListener(this);
        guestName.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_BACK_SPACE:
                        break;
                    case KeyEvent.VK_ENTER:
                        guestName.setText(guestName.getText());
                        guestName.setEditable(false);
                    default:
                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                String txt = guestName.getText();
                                if (!guestName.getText().isEmpty()) {
                                    autoComplete(txt);
                                }
                            }
                        });
                }
            }
        });

        JLabel usernameIcon = new JLabel(new ImageIcon(BookingPanel.class.getResource("adminIcon.png")));
        usernameIcon.setBounds(10, 198, 47, 30);
        add(usernameIcon);

        username = new JTextField("Admin: " + MainScreen.currUsername);
        username.setEditable(false);
        username.setFont(new Font("Tahoma", Font.PLAIN, 13));
        username.setBounds(63, 198, 229, 30);
        add(username);
        username.addFocusListener(this);

        bookingTypeIcon = new JLabel(new ImageIcon(BookingPanel.class.getResource("bookingTypeIcon.png")));
        bookingTypeIcon.setBounds(10, 239, 47, 30);
        add(bookingTypeIcon);

        roomRB = new JRadioButton("Room");
        buttonGroup.add(roomRB);
        roomRB.setFont(new Font("Tahoma", Font.BOLD, 14));
        roomRB.setBackground(new Color(0, 0, 0, 0));
        roomRB.setBounds(63, 239, 109, 30);
        add(roomRB);

        mealRB = new JRadioButton("Meal");
        buttonGroup.add(mealRB);
        mealRB.setFont(new Font("Tahoma", Font.BOLD, 14));
        mealRB.setBounds(183, 239, 109, 30);
        mealRB.setBackground(new Color(0, 0, 0, 0));
        add(mealRB);

        JButton goToBookBtn = new JButton(new ImageIcon(BookingPanel.class.getResource("bookingIcon.png")));
        goToBookBtn.setToolTipText("Book");
        goToBookBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (guestName.getText().equals("Guest Name") || (!roomRB.isSelected() && !mealRB.isSelected())) {
                    JOptionPane.showMessageDialog(BookingPanel.this, "You should insert the guest name and the booking type!", "Wrong Input", JOptionPane.ERROR_MESSAGE);
                } else if (roomRB.isSelected()) {
                    new Main.PanelDisappear(BookingPanel.this).start();
                    Settings.roomBookingCreated = true;
                    roomBooking = new RoomBookingPanel();
                    MainScreen.mainScreenFrame.getContentPane().add(roomBooking);
                    roomBooking.showUnfocusedText();
                    MainScreen.mainScreenFrame.getContentPane().add(MainScreen.panel);
                    MainScreen.mainScreenFrame.getContentPane().add(MainScreen.background);
                    MainScreen.mainScreenFrame.getContentPane().add(MainScreen.separator);

                } else if (mealRB.isSelected()) {
                    new Main.PanelDisappear(BookingPanel.this).start();
                    Settings.mealOrdersCreated = true;
                    mealOrders = new MealOrdersPanel();
                    MainScreen.mainScreenFrame.getContentPane().add(mealOrders);
                    mealOrders.showUnfocusedText();
                    MainScreen.mainScreenFrame.getContentPane().add(MainScreen.panel);
                    MainScreen.mainScreenFrame.getContentPane().add(MainScreen.background);
                    MainScreen.mainScreenFrame.getContentPane().add(MainScreen.separator);
                }
            }
        });
        goToBookBtn.setBounds(63, 375, 50, 50);
        add(goToBookBtn);

        JButton clearBtn = new JButton(new ImageIcon(BookingPanel.class.getResource("clearIcon.png")));
        clearBtn.setToolTipText("Clear Fields");
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                showUnfocusedText();
            }
        });
        clearBtn.setBounds(242, 375, 50, 50);
        add(clearBtn);

        mainLabel = new JLabel(new ImageIcon(BookingPanel.class.getResource("bookingsPanel.png")));
        mainLabel.setBounds(0, 0, 360, 455);
        add(mainLabel);
    }

    public void showUnfocusedText() {
        guestName.setForeground(Color.gray);
        guestName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        guestName.setText("Guest Name");
        guestName.setEditable(true);

        buttonGroup.clearSelection();
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        if (focusEvent.getSource() == bookingNO) {
            if (bookingNO.getText().equals("Booking Number")) {
                bookingNO.setForeground(Color.BLACK);
                bookingNO.setFont(new Font("Tahoma", Font.PLAIN, 13));
                bookingNO.setText("");
            }
        } else if (focusEvent.getSource() == guestName) {
            if (guestName.getText().equals("Guest Name")) {
                guestName.setForeground(Color.BLACK);
                guestName.setFont(new Font("Tahoma", Font.PLAIN, 13));
                guestName.setText("");
            }

        }
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        if (focusEvent.getSource() == bookingNO) {
            if (bookingNO.getText().isEmpty()) {
                bookingNO.setForeground(Color.gray);
                bookingNO.setFont(new Font("Tahoma", Font.PLAIN, 12));
                bookingNO.setText("Booking Number");
            }
        } else if (focusEvent.getSource() == guestName) {
            if (guestName.getText().isEmpty()) {
                guestName.setForeground(Color.gray);
                guestName.setFont(new Font("Tahoma", Font.PLAIN, 12));
                guestName.setText("Guest Name");
            }
        }
    }

    private void autoComplete(String txt) {
        String complete = "";
        int start = txt.length();
        int last = txt.length();

        for (int i = 0; i < allGuestsNames.size(); i++) {
            if (allGuestsNames.get(i).startsWith(txt)) {
                complete = allGuestsNames.get(i);
                last = complete.length();
                break;
            }
        }
        if (last > start) {
            guestName.setText(complete);
            guestName.setCaretPosition(last);
            guestName.moveCaretPosition(start);
        }
    }
}
