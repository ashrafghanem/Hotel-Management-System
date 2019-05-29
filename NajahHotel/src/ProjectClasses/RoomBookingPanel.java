package ProjectClasses;

import DatabaseRelations.Assigns;
import DatabaseRelations.Booking;
import DatabaseRelations.Guest;
import DatabaseRelations.Person;
import DatabaseRelations.Room;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

class RoomTypesRecords {

    private String typeCode;
    private int roomPrice, roomFloor;

    public RoomTypesRecords(String type, int price, int floor) {
        typeCode = type;
        roomFloor = floor;
        roomPrice = price;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public int getRoomFloor() {
        return roomFloor;
    }

    public int getRoomPrice() {
        return roomPrice;
    }
}

class AvailableRoomNOs {

    private int roomNO, phoneNO;

    public AvailableRoomNOs(int roomNO, int phoneNO) {
        this.roomNO = roomNO;
        this.phoneNO = phoneNO;
    }

    public int getRoomNO() {
        return this.roomNO;
    }

    public int getPhoneNO() {
        return this.phoneNO;
    }
}

public class RoomBookingPanel extends JPanel implements FocusListener {

    private JLabel mainLabel;
    private JComboBox<String> roomTypes;
    private JComboBox<String> roomsAvailable;
    private JTextField roomPrice;
    private JTextField roomFloor;
    private JTextField phoneNO;
    private JRadioButton holidayRB, conferenceRB, groupRB, businessRB;
    private JTextField visitPeriod;
    private JDateChooser checkInDate;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private EntityManager entityManager;

    public RoomBookingPanel() {
        entityManager = Main.entityManager;

        new RoomBookingPanelPanelAppear(this).start();
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setSize(360, 455);
        setLayout(null);
        setFocusable(true);

        JLabel roomTypeIcon = new JLabel(new ImageIcon(RoomBookingPanel.class.getResource("typeCodeIcon.png")));
        roomTypeIcon.setBounds(10, 69, 47, 30);
        add(roomTypeIcon);

        roomsAvailable = new JComboBox<String>();
        roomsAvailable.setForeground(Color.GRAY);
        roomsAvailable.setFont(new Font("Tahoma", Font.PLAIN, 12));
        roomsAvailable.setBounds(141, 110, 151, 30);
        add(roomsAvailable);

        ArrayList<RoomTypesRecords> roomTypeRecords = new ArrayList<RoomTypesRecords>();
        ArrayList<AvailableRoomNOs> roomNOsRecords = new ArrayList<AvailableRoomNOs>();

        try {
            Main.result = Main.statement.executeQuery("SELECT type_code,price,floor FROM ROOM_TYPE");
            while (Main.result.next()) {
                roomTypeRecords.add(new RoomTypesRecords(Main.result.getString(1), Main.result.getInt(2), Main.result.getInt(3)));
            }
        } catch (Exception ex) {
        }

        String strType[] = new String[roomTypeRecords.size() + 1];
        strType[0] = "Select a room type:";
        roomTypes = new JComboBox<String>();
        roomTypes.setForeground(Color.GRAY);
        roomTypes.setFont(new Font("Tahoma", Font.PLAIN, 12));
        roomTypes.setBounds(63, 69, 229, 30);
        add(roomTypes);
        for (int i = 0; i < roomTypeRecords.size(); i++) {
            strType[i + 1] = roomTypeRecords.get(i).getTypeCode();
        }
        roomTypes.setModel(new DefaultComboBoxModel<String>(strType));

        roomTypes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                for (int i = 0; i < roomTypeRecords.size(); i++) {
                    if (roomTypeRecords.get(i).getTypeCode().equals(roomTypes.getSelectedItem())) {
                        try {
                            roomPrice.setForeground(Color.BLACK);
                            roomFloor.setForeground(Color.BLACK);
                            roomPrice.setText(String.valueOf(roomTypeRecords.get(i).getRoomPrice()));
                            roomFloor.setText(String.valueOf(roomTypeRecords.get(i).getRoomFloor()));
                            Main.result = Main.statement.executeQuery("SELECT ROOM.room_no,ROOM.phone_no FROM ROOM WHERE ROOM.availability='Y' AND room_type='" + roomTypes.getSelectedItem() + "'");
                            roomNOsRecords.clear();
                            while (Main.result.next()) {
                                roomNOsRecords.add(new AvailableRoomNOs(Main.result.getInt(1), Main.result.getInt(2)));
                            }
                            String strNO[] = new String[roomNOsRecords.size() + 1];
                            strNO[0] = "Select a room NO:";
                            for (int j = 0; j < roomNOsRecords.size(); j++) {
                                strNO[j + 1] = "" + roomNOsRecords.get(j).getRoomNO();
                            }
                            roomsAvailable.setModel(new DefaultComboBoxModel<String>(strNO));
                        } catch (Exception ex) {
                        }
                        break;
                    }
                }
                if (roomTypes.getSelectedIndex() == 0) {
                    roomNOsRecords.clear();
                    roomsAvailable.removeAll();
                    roomNOsRecords.clear();
                    roomsAvailable.setModel(new DefaultComboBoxModel<String>(new String[]{""}));

                    roomPrice.setForeground(Color.GRAY);
                    roomPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
                    roomPrice.setText("Room Price");

                    roomFloor.setForeground(Color.GRAY);
                    roomFloor.setFont(new Font("Tahoma", Font.PLAIN, 12));
                    roomFloor.setText("Room Floor");

                    phoneNO.setForeground(Color.GRAY);
                    phoneNO.setFont(new Font("Tahoma", Font.PLAIN, 12));
                    phoneNO.setText("Phone Number");
                }
            }
        });
        roomsAvailable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                for (int i = 0; i < roomNOsRecords.size(); i++) {
                    if (roomsAvailable.getSelectedItem().equals(String.valueOf(roomNOsRecords.get(i).getRoomNO()))) {
                        phoneNO.setForeground(Color.BLACK);
                        phoneNO.setText(String.valueOf(roomNOsRecords.get(i).getPhoneNO()));
                        break;
                    }
                }
                if (roomsAvailable.getSelectedIndex() == 0) {
                    phoneNO.setForeground(Color.GRAY);
                    phoneNO.setFont(new Font("Tahoma", Font.PLAIN, 12));
                    phoneNO.setText("Phone Number");
                }
            }
        });

        JLabel checkInDateIcon = new JLabel(new ImageIcon(RoomBookingPanel.class.getResource("checkInIcon.png")));
        checkInDateIcon.setBounds(10, 245, 47, 30);
        add(checkInDateIcon);

        checkInDate = new JDateChooser();
        checkInDate.setForeground(Color.GRAY);
        checkInDate.setDateFormatString("dd-MM-yyyy");
        checkInDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
        checkInDate.setBounds(63, 245, 229, 30);
        add(checkInDate);

        JLabel roomsAvailableIcon = new JLabel(new ImageIcon(RoomBookingPanel.class.getResource("roomsAvailableIcon.png")));
        roomsAvailableIcon.setBounds(87, 110, 47, 30);
        add(roomsAvailableIcon);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(10, 151, 340, 2);
        add(separator_1);

        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(10, 235, 340, 2);
        add(separator_2);

        JLabel roomPriceIcon = new JLabel(new ImageIcon(RoomBookingPanel.class.getResource("priceIcon.png")));
        roomPriceIcon.setBounds(10, 160, 30, 30);
        add(roomPriceIcon);

        roomPrice = new JTextField();
        roomPrice.setEditable(false);
        roomPrice.setText("Room Price");
        roomPrice.setForeground(Color.GRAY);
        roomPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
        roomPrice.setBounds(46, 160, 116, 30);
        add(roomPrice);

        JLabel roomFloorIcon = new JLabel(new ImageIcon(RoomBookingPanel.class.getResource("floorIcon.png")));
        roomFloorIcon.setBounds(181, 160, 30, 30);
        add(roomFloorIcon);

        roomFloor = new JTextField();
        roomFloor.setEditable(false);
        roomFloor.setText("Room Floor");
        roomFloor.setForeground(Color.GRAY);
        roomFloor.setFont(new Font("Tahoma", Font.PLAIN, 12));
        roomFloor.setBounds(217, 160, 116, 30);
        add(roomFloor);

        JLabel phoneNOIcon = new JLabel(new ImageIcon(RoomBookingPanel.class.getResource("phoneNO.png")));
        phoneNOIcon.setBounds(10, 200, 30, 30);
        add(phoneNOIcon);

        phoneNO = new JTextField();
        phoneNO.setEditable(false);
        phoneNO.setText("Phone Number");
        phoneNO.setForeground(Color.GRAY);
        phoneNO.setFont(new Font("Tahoma", Font.PLAIN, 12));
        phoneNO.setBounds(46, 200, 287, 30);
        add(phoneNO);

        visitPeriod = new JTextField();
        visitPeriod.setText("Visit Period");
        visitPeriod.setForeground(Color.GRAY);
        visitPeriod.setFont(new Font("Tahoma", Font.PLAIN, 12));
        visitPeriod.setBounds(63, 280, 229, 30);
        add(visitPeriod);
        visitPeriod.addFocusListener(this);
        visitPeriod.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9') || visitPeriod.getText().length() > 3) {
                    event.consume();
                }
            }
        });

        JLabel visitPeriodIcon = new JLabel(new ImageIcon(RoomBookingPanel.class.getResource("visitPeriodIcon.png")));
        visitPeriodIcon.setBounds(10, 280, 47, 30);
        add(visitPeriodIcon);

        JLabel purposeOfVisitIcon = new JLabel(new ImageIcon(RoomBookingPanel.class.getResource("visitPurposeIcon.png")));
        purposeOfVisitIcon.setBounds(10, 335, 47, 30);
        add(purposeOfVisitIcon);

        holidayRB = new JRadioButton("Holiday");
        buttonGroup.add(holidayRB);
        holidayRB.setFont(new Font("Tahoma", Font.BOLD, 14));
        holidayRB.setBounds(63, 320, 109, 30);
        holidayRB.setBackground(new Color(0, 0, 0, 0));
        add(holidayRB);

        conferenceRB = new JRadioButton("Conference");
        buttonGroup.add(conferenceRB);
        conferenceRB.setFont(new Font("Tahoma", Font.BOLD, 14));
        conferenceRB.setBounds(183, 320, 109, 30);
        conferenceRB.setBackground(new Color(0, 0, 0, 0));
        add(conferenceRB);

        groupRB = new JRadioButton("Group");
        buttonGroup.add(groupRB);
        groupRB.setFont(new Font("Tahoma", Font.BOLD, 14));
        groupRB.setBounds(63, 353, 109, 30);
        groupRB.setBackground(new Color(0, 0, 0, 0));
        add(groupRB);

        businessRB = new JRadioButton("Business");
        buttonGroup.add(businessRB);
        businessRB.setFont(new Font("Tahoma", Font.BOLD, 14));
        businessRB.setBounds(183, 353, 109, 30);
        businessRB.setBackground(new Color(0, 0, 0, 0));
        add(businessRB);

        JButton submitBtn = new JButton(new ImageIcon(RoomBookingPanel.class.getResource("submitIcon.png")));
        submitBtn.setToolTipText("Book Room");
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (roomTypes.getSelectedIndex() == 0 || roomsAvailable.getSelectedIndex() == 0 || checkInDate.getDate() == null
                        || visitPeriod.getText().equals("Visit Period") || (!holidayRB.isSelected() && !conferenceRB.isSelected()
                        && !groupRB.isSelected() && !businessRB.isSelected())) {
                    JOptionPane.showMessageDialog(RoomBookingPanel.this, "You have to fill in all fields!", "Empty Fields", JOptionPane.ERROR_MESSAGE);
                } else if (visitPeriod.getText().equals("0")) {
                    JOptionPane.showMessageDialog(RoomBookingPanel.this, "Period Cannot Be Zero!", "Wrong Input", JOptionPane.ERROR_MESSAGE);

                } else {
                    try {
                        Date currDate = new SimpleDateFormat("dd-MM-yyyy").parse(BookingPanel.bookingDate.getText());
                        if (checkInDate.getDate().before(currDate)) {
                            JOptionPane.showMessageDialog(RoomBookingPanel.this, "Choose a correct check in date!", "Room Booking", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        Booking booking = new Booking();
                        entityManager.persist(booking);

                        booking.setBookingNo(BookingPanel.bookingNumber);
                        booking.setBookingDate(currDate);
                        booking.setBookingType("Room");

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
                                        double period = Double.parseDouble(visitPeriod.getText());
                                        double price = Double.parseDouble(roomPrice.getText());
                                        guests.get(i).setTotalPayment(guests.get(i).getTotalPayment() + (period * price));

                                        selectedGuest = true;
                                        break;
                                    }
                                }
                            }
                            if (selectedGuest) {
                                break;
                            }
                        }
                        EntityManager roomEM = Persistence.createEntityManagerFactory("NajahHotelPU").createEntityManager();
                        List<Room> rooms = roomEM.createNamedQuery("Room.findAll").getResultList();

                        for (Room room : rooms) {
                            if (room.getRoomNo() == Short.parseShort((String) roomsAvailable.getSelectedItem())) {
                                roomEM.getTransaction().begin();
                                roomEM.persist(room);

                                room.setAvailability('N');
                                room.setCheckInDate(checkInDate.getDate());
                                if (holidayRB.isSelected()) {
                                    room.setPurposeOfVisit("Holiday");
                                } else if (groupRB.isSelected()) {
                                    room.setPurposeOfVisit("Group");
                                } else if (conferenceRB.isSelected()) {
                                    room.setPurposeOfVisit("Conference");
                                } else if (businessRB.isSelected()) {
                                    room.setPurposeOfVisit("Business");
                                }

                                room.setVisitPeriod(Short.parseShort(visitPeriod.getText()));
                                room.setBookingNo(booking);
                                break;
                            }
                        }

                        guestEM.getTransaction().commit();
                        entityManager.getTransaction().commit();
                        roomEM.getTransaction().commit();

                        JOptionPane.showMessageDialog(RoomBookingPanel.this, "Room Booking Done Successfully!", "Room Booking", JOptionPane.INFORMATION_MESSAGE);
                        entityManager.getTransaction().begin();
                        showUnfocusedText();
                    } catch (Exception e) {
                    }
                }
            }
        });
        submitBtn.setBounds(242, 394, 50, 50);
        add(submitBtn);

        JButton backBtn = new JButton(new ImageIcon(RoomBookingPanel.class.getResource("backIcon.png")));
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new Main.PanelDisappear(RoomBookingPanel.this).start();
                Settings.roomBookingCreated = false;

                Settings.booking = new BookingPanel();
                MainScreen.mainScreenFrame.getContentPane().add(Settings.booking);

                Settings.booking.requestFocus();
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.panel);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.background);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.separator);
            }
        });
        backBtn.setBounds(63, 394, 50, 50);
        add(backBtn);

        JButton clearBtn = new JButton(new ImageIcon(RoomBookingPanel.class.getResource("clearIcon.png")));
        clearBtn.setToolTipText("Clear Fields");
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                showUnfocusedText();
            }
        });
        clearBtn.setBounds(153, 394, 50, 50);
        add(clearBtn);

        mainLabel = new JLabel(new ImageIcon(RoomBookingPanel.class.getResource("roomBookingPanel.png")));
        mainLabel.setBounds(0, 0, 360, 455);
        add(mainLabel);

    }

    public void showUnfocusedText() {
        if (roomTypes.getSelectedIndex() != -1) {
            roomTypes.setSelectedIndex(0);
        }
        if (roomsAvailable.getSelectedIndex() != -1) {
            roomsAvailable.setSelectedIndex(0);
        }

        visitPeriod.setForeground(Color.gray);
        visitPeriod.setFont(new Font("Tahoma", Font.PLAIN, 12));
        visitPeriod.setText("Visit Period");

        roomPrice.setForeground(Color.GRAY);
        roomPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
        roomPrice.setText("Room Price");

        roomFloor.setForeground(Color.GRAY);
        roomFloor.setFont(new Font("Tahoma", Font.PLAIN, 12));
        roomFloor.setText("Room Floor");

        phoneNO.setForeground(Color.GRAY);
        phoneNO.setFont(new Font("Tahoma", Font.PLAIN, 12));
        phoneNO.setText("Phone Number");

        buttonGroup.clearSelection();

        checkInDate.getDateEditor().setDate(null);

        try {
            Main.result = Main.statement.executeQuery("SELECT BOOKING_SEQ.NEXTVAL FROM DUAL");
            Main.result.next();
            BookingPanel.bookingNumber = Main.result.getInt(1);
        } catch (Exception ex) {
        }
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        if (focusEvent.getSource() == visitPeriod) {
            if (visitPeriod.getText().equals("Visit Period")) {
                visitPeriod.setForeground(Color.BLACK);
                visitPeriod.setFont(new Font("Tahoma", Font.PLAIN, 14));
                visitPeriod.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        if (focusEvent.getSource() == visitPeriod) {
            if (visitPeriod.getText().isEmpty()) {
                visitPeriod.setForeground(Color.gray);
                visitPeriod.setFont(new Font("Tahoma", Font.PLAIN, 12));
                visitPeriod.setText("Visit Period");
            }
        }
    }

    public static class RoomBookingPanelPanelAppear extends Thread {

        int x = 300;
        int y = 150;
        JPanel panel;

        int oldWidth = 0, oldHeight = 0;

        public RoomBookingPanelPanelAppear(JPanel panel) {
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

    class CheckDate {

    }
}
