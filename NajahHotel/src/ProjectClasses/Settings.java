package ProjectClasses;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Settings extends MouseAdapter {

    public static JLabel slidingLabel, settingsLabel;
    private static JLabel closeLabel;
    private static JPanel slidingPanel;
    public static JLabel empLabel;
    public static JLabel adminLabel;
    public static JLabel roomLabel;
    public static JLabel roomTypesLabel;
    public static JLabel mealLabel;
    public static JLabel bookingLabel;
    public static JLabel modifyLabel, guestLabel;
    public static GuestPanel guest;
    public static EmployeePanel employee;
    public static AdministratorPanel admin;
    public static RoomTypesPanel roomTypes;
    public static RoomPanel room;
    public static MealPanel meal;
    public static BookingPanel booking;
    public static boolean slidingLabelOpened = false, guestCreated = false, employeeCreated = false,
            adminCreated = false, roomTypesCreated = false, roomCreated = false, mealCreated = false,
            bookingCreated = false, mealOrdersCreated = false, roomBookingCreated = false;

    public Settings() {

        settingsLabel = new JLabel(new ImageIcon(Settings.class.getResource("settings3DLightIcon.png")));
        settingsLabel.addMouseListener(this);
        settingsLabel.setBounds(350, 552, 101, 95);
        MainScreen.mainScreenFrame.getContentPane().add(settingsLabel);

        closeLabel = new JLabel(new ImageIcon(Settings.class.getResource("closeSettingsIcon.png")));
        closeLabel.setBounds(45, 527, 30, 27);
        MainScreen.mainScreenFrame.getContentPane().add(closeLabel);
        closeLabel.addMouseListener(this);
        closeLabel.setVisible(false);

        slidingPanel = new JPanel();
        slidingPanel.setBounds(366, 527, 44, 95);
        MainScreen.mainScreenFrame.getContentPane().add(slidingPanel);
        slidingPanel.setLayout(null);
        slidingPanel.setBackground(new Color(0, 0, 0, 0));

        adminLabel = new JLabel(new ImageIcon(Settings.class.getResource("administrators1.png")));
        adminLabel.setBounds(181, 11, 82, 74);
        slidingPanel.add(adminLabel);
        adminLabel.addMouseListener(this);

        empLabel = new JLabel(new ImageIcon(Settings.class.getResource("employees1.png")));
        empLabel.setBounds(104, 11, 82, 74);
        slidingPanel.add(empLabel);
        empLabel.addMouseListener(this);

        roomLabel = new JLabel(new ImageIcon(Settings.class.getResource("rooms1.png")));
        roomLabel.setBounds(334, 11, 78, 74);
        slidingPanel.add(roomLabel);
        roomLabel.addMouseListener(this);

        roomTypesLabel = new JLabel(new ImageIcon(Settings.class.getResource("roomTypes1.png")));
        roomTypesLabel.setBounds(256, 14, 78, 74);
        slidingPanel.add(roomTypesLabel);
        roomTypesLabel.addMouseListener(this);

        mealLabel = new JLabel(new ImageIcon(Settings.class.getResource("meals1.png")));
        mealLabel.setBounds(410, 11, 78, 74);
        slidingPanel.add(mealLabel);
        mealLabel.addMouseListener(this);

        bookingLabel = new JLabel(new ImageIcon(Settings.class.getResource("bookings1.png")));
        bookingLabel.setBounds(487, 11, 82, 74);
        slidingPanel.add(bookingLabel);
        bookingLabel.addMouseListener(this);

        modifyLabel = new JLabel(new ImageIcon(Settings.class.getResource("modifications1.png")));
        modifyLabel.setBounds(565, 11, 82, 74);
        slidingPanel.add(modifyLabel);
        modifyLabel.addMouseListener(this);

        guestLabel = new JLabel(new ImageIcon(Settings.class.getResource("guests1.png")));
        guestLabel.setBounds(28, 11, 78, 74);
        slidingPanel.add(guestLabel);
        guestLabel.addMouseListener(this);

        slidingLabel = new JLabel(new ImageIcon(Settings.class.getResource("slidingLabelIcon.png")));
        slidingLabel.setBounds(0, 0, 681, 95);
        slidingPanel.add(slidingLabel);
        slidingPanel.setVisible(false);

    }

    public void mouseEntered(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == settingsLabel) {
            if (settingsLabel.getX() == 350) {
                settingsLabel.setIcon(new ImageIcon(Settings.class.getResource("settings3DDarkIcon.png")));
            }

        } else if (mouseEvent.getSource() == guestLabel && !guestCreated) {
            guestLabel.setIcon(new ImageIcon(Settings.class.getResource("guests2.png")));

        } else if (mouseEvent.getSource() == empLabel && !employeeCreated) {
            empLabel.setIcon(new ImageIcon(Settings.class.getResource("employees2.png")));

        } else if (mouseEvent.getSource() == adminLabel && !adminCreated) {
            adminLabel.setIcon(new ImageIcon(Settings.class.getResource("administrators2.png")));

        } else if (mouseEvent.getSource() == roomLabel && !roomCreated) {
            roomLabel.setIcon(new ImageIcon(Settings.class.getResource("rooms2.png")));

        } else if (mouseEvent.getSource() == roomTypesLabel && !roomTypesCreated) {
            roomTypesLabel.setIcon(new ImageIcon(Settings.class.getResource("roomTypes2.png")));

        } else if (mouseEvent.getSource() == mealLabel && !mealCreated) {
            mealLabel.setIcon(new ImageIcon(Settings.class.getResource("meals2.png")));

        } else if (mouseEvent.getSource() == bookingLabel && !bookingCreated) {
            bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings2.png")));

        } else if (mouseEvent.getSource() == modifyLabel) {
            modifyLabel.setIcon(new ImageIcon(Settings.class.getResource("modifications2.png")));

        }
    }

    public void mouseExited(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == settingsLabel) {
            if (settingsLabel.getX() == 350) {
                settingsLabel.setIcon(new ImageIcon(Settings.class.getResource("settings3DLightIcon.png")));
            }

        } else if (mouseEvent.getSource() == guestLabel && !guestCreated) {
            guestLabel.setIcon(new ImageIcon(Settings.class.getResource("guests1.png")));

        } else if (mouseEvent.getSource() == empLabel && !employeeCreated) {
            empLabel.setIcon(new ImageIcon(Settings.class.getResource("employees1.png")));

        } else if (mouseEvent.getSource() == adminLabel && !adminCreated) {
            adminLabel.setIcon(new ImageIcon(Settings.class.getResource("administrators1.png")));

        } else if (mouseEvent.getSource() == roomLabel && !roomCreated) {
            roomLabel.setIcon(new ImageIcon(Settings.class.getResource("rooms1.png")));

        } else if (mouseEvent.getSource() == roomTypesLabel && !roomTypesCreated) {
            roomTypesLabel.setIcon(new ImageIcon(Settings.class.getResource("roomTypes1.png")));

        } else if (mouseEvent.getSource() == mealLabel && !mealCreated) {
            mealLabel.setIcon(new ImageIcon(Settings.class.getResource("meals1.png")));

        } else if (mouseEvent.getSource() == bookingLabel && !bookingCreated) {
            bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));

        } else if (mouseEvent.getSource() == modifyLabel) {
            modifyLabel.setIcon(new ImageIcon(Settings.class.getResource("modifications1.png")));

        }
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == settingsLabel) {
            if (!MainScreen.changePassCreated) {
                new PanelOpenning().start();
                slidingLabelOpened = true;
                settingsLabel.setIcon(new ImageIcon(Settings.class.getResource("settingsFlatIcon.png")));
            }
        } else if (mouseEvent.getSource() == closeLabel) {
            new PanelClosing().start();
            slidingLabelOpened = false;
            settingsLabel.setIcon(new ImageIcon(Settings.class.getResource("settings3DLightIcon.png")));
        } else if (mouseEvent.getSource() == guestLabel) {
            if (!guestCreated) {
                if (employeeCreated) {
                    employee.callEmpDisappear();
                    employee = null;
                    employeeCreated = false;
                    empLabel.setIcon(new ImageIcon(Settings.class.getResource("employees1.png")));
                } else if (adminCreated) {
                    new Main.PanelDisappear(admin).start();
                    admin = null;
                    adminCreated = false;
                    adminLabel.setIcon(new ImageIcon(Settings.class.getResource("administrators1.png")));
                } else if (roomTypesCreated) {
                    new Main.PanelDisappear(roomTypes).start();
                    roomTypes = null;
                    roomTypesCreated = false;
                    roomTypesLabel.setIcon(new ImageIcon(Settings.class.getResource("roomTypes1.png")));
                } else if (roomCreated) {
                    new Main.PanelDisappear(room).start();
                    room = null;
                    roomCreated = false;
                    roomLabel.setIcon(new ImageIcon(Settings.class.getResource("rooms1.png")));
                } else if (mealCreated) {
                    new Main.PanelDisappear(meal).start();
                    meal = null;
                    mealCreated = false;
                    mealLabel.setIcon(new ImageIcon(Settings.class.getResource("meals1.png")));
                } else if (bookingCreated) {
                    new Main.PanelDisappear(booking).start();
                    booking = null;
                    bookingCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (roomBookingCreated) {
                    new Main.PanelDisappear(BookingPanel.roomBooking).start();
                    BookingPanel.roomBooking = null;
                    roomBookingCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (mealOrdersCreated) {
                    new Main.PanelDisappear(BookingPanel.mealOrders).start();
                    BookingPanel.mealOrders = null;
                    mealOrdersCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                guestLabel.setIcon(new ImageIcon(Settings.class.getResource("guests3.png")));

                guest = new GuestPanel();
                guestCreated = true;
                MainScreen.mainScreenFrame.getContentPane().add(guest);

                guest.showUnfocusedText();
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.panel);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.background);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.separator);
            }
        } else if (mouseEvent.getSource() == empLabel) {
            if (!employeeCreated) {
                if (guestCreated) {
                    new Main.PanelDisappear(guest).start();
                    guest = null;
                    guestCreated = false;
                    guestLabel.setIcon(new ImageIcon(Settings.class.getResource("guests1.png")));
                } else if (adminCreated) {
                    new Main.PanelDisappear(admin).start();
                    admin = null;
                    adminCreated = false;
                    adminLabel.setIcon(new ImageIcon(Settings.class.getResource("administrators1.png")));
                } else if (roomTypesCreated) {
                    new Main.PanelDisappear(roomTypes).start();
                    roomTypes = null;
                    roomTypesCreated = false;
                    roomTypesLabel.setIcon(new ImageIcon(Settings.class.getResource("roomTypes1.png")));
                } else if (roomCreated) {
                    new Main.PanelDisappear(room).start();
                    room = null;
                    roomCreated = false;
                    roomLabel.setIcon(new ImageIcon(Settings.class.getResource("rooms1.png")));
                } else if (mealCreated) {
                    new Main.PanelDisappear(meal).start();
                    meal = null;
                    mealCreated = false;
                    mealLabel.setIcon(new ImageIcon(Settings.class.getResource("meals1.png")));
                } else if (bookingCreated) {
                    new Main.PanelDisappear(booking).start();
                    booking = null;
                    bookingCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (roomBookingCreated) {
                    new Main.PanelDisappear(BookingPanel.roomBooking).start();
                    BookingPanel.roomBooking = null;
                    roomBookingCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (mealOrdersCreated) {
                    new Main.PanelDisappear(BookingPanel.mealOrders).start();
                    BookingPanel.mealOrders = null;
                    mealOrdersCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (!MainScreen.currUsername.equals("ashrafghanem")) {
                    JOptionPane.showMessageDialog(MainScreen.background, "You're not authorized to add a new employee!", "Employee", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                empLabel.setIcon(new ImageIcon(Settings.class.getResource("employees3.png")));
                employeeCreated = true;
                employee = new EmployeePanel();

                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.stripePanel);
                MainScreen.mainScreenFrame.getContentPane().add(employee);

                employee.showUnfocusedText();
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.panel);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.background);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.separator);
            }
        } else if (mouseEvent.getSource() == adminLabel) {
            if (!adminCreated) {
                if (guestCreated) {
                    new Main.PanelDisappear(guest).start();
                    guest = null;
                    guestCreated = false;
                    guestLabel.setIcon(new ImageIcon(Settings.class.getResource("guests1.png")));
                } else if (employeeCreated) {
                    employee.callEmpDisappear();
                    employee = null;
                    employeeCreated = false;
                    empLabel.setIcon(new ImageIcon(Settings.class.getResource("employees1.png")));
                } else if (roomTypesCreated) {
                    new Main.PanelDisappear(roomTypes).start();
                    roomTypes = null;
                    roomTypesCreated = false;
                    roomTypesLabel.setIcon(new ImageIcon(Settings.class.getResource("roomTypes1.png")));
                } else if (roomCreated) {
                    new Main.PanelDisappear(room).start();
                    room = null;
                    roomCreated = false;
                    roomLabel.setIcon(new ImageIcon(Settings.class.getResource("rooms1.png")));
                } else if (mealCreated) {
                    new Main.PanelDisappear(meal).start();
                    meal = null;
                    mealCreated = false;
                    mealLabel.setIcon(new ImageIcon(Settings.class.getResource("meals1.png")));
                } else if (bookingCreated) {
                    new Main.PanelDisappear(booking).start();
                    booking = null;
                    bookingCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (roomBookingCreated) {
                    new Main.PanelDisappear(BookingPanel.roomBooking).start();
                    BookingPanel.roomBooking = null;
                    roomBookingCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (mealOrdersCreated) {
                    new Main.PanelDisappear(BookingPanel.mealOrders).start();
                    BookingPanel.mealOrders = null;
                    mealOrdersCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (!MainScreen.currUsername.equals("ashrafghanem")) {
                    JOptionPane.showMessageDialog(MainScreen.background, "You're not authorized to add a new administrator!", "Administrators", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                adminLabel.setIcon(new ImageIcon(Settings.class.getResource("administrators3.png")));
                admin = new AdministratorPanel();
                adminCreated = true;
                MainScreen.mainScreenFrame.getContentPane().add(admin);

                admin.showUnfocusedText();
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.panel);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.background);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.separator);
            }
        } else if (mouseEvent.getSource() == roomLabel) {
            if (!roomCreated) {
                if (guestCreated) {
                    new Main.PanelDisappear(guest).start();
                    guest = null;
                    guestCreated = false;
                    guestLabel.setIcon(new ImageIcon(Settings.class.getResource("guests1.png")));
                } else if (employeeCreated) {
                    employee.callEmpDisappear();
                    employee = null;
                    employeeCreated = false;
                    empLabel.setIcon(new ImageIcon(Settings.class.getResource("employees1.png")));
                } else if (adminCreated) {
                    new Main.PanelDisappear(admin).start();
                    admin = null;
                    adminCreated = false;
                    adminLabel.setIcon(new ImageIcon(Settings.class.getResource("administrators1.png")));
                } else if (roomTypesCreated) {
                    new Main.PanelDisappear(roomTypes).start();
                    roomTypes = null;
                    roomTypesCreated = false;
                    roomTypesLabel.setIcon(new ImageIcon(Settings.class.getResource("roomTypes1.png")));
                } else if (mealCreated) {
                    new Main.PanelDisappear(meal).start();
                    meal = null;
                    mealCreated = false;
                    mealLabel.setIcon(new ImageIcon(Settings.class.getResource("meals1.png")));
                } else if (bookingCreated) {
                    new Main.PanelDisappear(booking).start();
                    booking = null;
                    bookingCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (roomBookingCreated) {
                    new Main.PanelDisappear(BookingPanel.roomBooking).start();
                    BookingPanel.roomBooking = null;
                    roomBookingCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (mealOrdersCreated) {
                    new Main.PanelDisappear(BookingPanel.mealOrders).start();
                    BookingPanel.mealOrders = null;
                    mealOrdersCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (!MainScreen.currUsername.equals("ashrafghanem")) {
                    JOptionPane.showMessageDialog(MainScreen.background, "You're not authorized to add a new room!", "Rooms", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                roomLabel.setIcon(new ImageIcon(Settings.class.getResource("rooms3.png")));
                room = new RoomPanel();
                roomCreated = true;
                MainScreen.mainScreenFrame.getContentPane().add(room);

                room.showUnfocusedText();
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.panel);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.background);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.separator);
            }
        } else if (mouseEvent.getSource() == roomTypesLabel) {
            if (!roomTypesCreated) {
                if (guestCreated) {
                    new Main.PanelDisappear(guest).start();
                    guest = null;
                    guestCreated = false;
                    guestLabel.setIcon(new ImageIcon(Settings.class.getResource("guests1.png")));
                } else if (employeeCreated) {
                    employee.callEmpDisappear();
                    employee = null;
                    employeeCreated = false;
                    empLabel.setIcon(new ImageIcon(Settings.class.getResource("employees1.png")));
                } else if (adminCreated) {
                    new Main.PanelDisappear(admin).start();
                    admin = null;
                    adminCreated = false;
                    adminLabel.setIcon(new ImageIcon(Settings.class.getResource("administrators1.png")));
                } else if (roomCreated) {
                    new Main.PanelDisappear(room).start();
                    room = null;
                    roomCreated = false;
                    roomLabel.setIcon(new ImageIcon(Settings.class.getResource("rooms1.png")));
                } else if (mealCreated) {
                    new Main.PanelDisappear(meal).start();
                    meal = null;
                    mealCreated = false;
                    mealLabel.setIcon(new ImageIcon(Settings.class.getResource("meals1.png")));
                } else if (bookingCreated) {
                    new Main.PanelDisappear(booking).start();
                    booking = null;
                    bookingCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (roomBookingCreated) {
                    new Main.PanelDisappear(BookingPanel.roomBooking).start();
                    BookingPanel.roomBooking = null;
                    roomBookingCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (mealOrdersCreated) {
                    new Main.PanelDisappear(BookingPanel.mealOrders).start();
                    BookingPanel.mealOrders = null;
                    mealOrdersCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (!MainScreen.currUsername.equals("ashrafghanem")) {
                    JOptionPane.showMessageDialog(MainScreen.background, "You're not authorized to add a new room type!", "Room Types", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                roomTypesLabel.setIcon(new ImageIcon(Settings.class.getResource("roomTypes3.png")));
                roomTypes = new RoomTypesPanel();
                roomTypesCreated = true;
                MainScreen.mainScreenFrame.getContentPane().add(roomTypes);

                roomTypes.showUnfocusedText();
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.panel);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.background);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.separator);
            }
        } else if (mouseEvent.getSource() == mealLabel) {
            if (!mealCreated) {
                if (guestCreated) {
                    new Main.PanelDisappear(guest).start();
                    guest = null;
                    guestCreated = false;
                    guestLabel.setIcon(new ImageIcon(Settings.class.getResource("guests1.png")));
                } else if (employeeCreated) {
                    employee.callEmpDisappear();
                    employee = null;
                    employeeCreated = false;
                    empLabel.setIcon(new ImageIcon(Settings.class.getResource("employees1.png")));
                } else if (adminCreated) {
                    new Main.PanelDisappear(admin).start();
                    admin = null;
                    adminCreated = false;
                    adminLabel.setIcon(new ImageIcon(Settings.class.getResource("administrators1.png")));
                } else if (roomCreated) {
                    new Main.PanelDisappear(room).start();
                    room = null;
                    roomCreated = false;
                    roomLabel.setIcon(new ImageIcon(Settings.class.getResource("rooms1.png")));
                } else if (roomTypesCreated) {
                    new Main.PanelDisappear(roomTypes).start();
                    roomTypes = null;
                    roomTypesCreated = false;
                    roomTypesLabel.setIcon(new ImageIcon(Settings.class.getResource("roomTypes1.png")));
                } else if (bookingCreated) {
                    new Main.PanelDisappear(booking).start();
                    booking = null;
                    bookingCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (roomBookingCreated) {
                    new Main.PanelDisappear(BookingPanel.roomBooking).start();
                    BookingPanel.roomBooking = null;
                    roomBookingCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (mealOrdersCreated) {
                    new Main.PanelDisappear(BookingPanel.mealOrders).start();
                    BookingPanel.mealOrders = null;
                    mealOrdersCreated = false;
                    bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                }
                if (!MainScreen.currUsername.equals("ashrafghanem")) {
                    JOptionPane.showMessageDialog(MainScreen.background, "You're not authorized to add a new meal!", "Meals", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                mealLabel.setIcon(new ImageIcon(Settings.class.getResource("meals3.png")));
                meal = new MealPanel();
                mealCreated = true;
                MainScreen.mainScreenFrame.getContentPane().add(meal);

                meal.showUnfocusedText();
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.panel);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.background);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.separator);
            }
        } else if (mouseEvent.getSource() == bookingLabel) {
            if (!bookingCreated) {
                if (guestCreated) {
                    new Main.PanelDisappear(guest).start();
                    guest = null;
                    guestCreated = false;
                    guestLabel.setIcon(new ImageIcon(Settings.class.getResource("guests1.png")));
                } else if (employeeCreated) {
                    employee.callEmpDisappear();
                    employee = null;
                    employeeCreated = false;
                    empLabel.setIcon(new ImageIcon(Settings.class.getResource("employees1.png")));
                } else if (adminCreated) {
                    new Main.PanelDisappear(admin).start();
                    admin = null;
                    adminCreated = false;
                    adminLabel.setIcon(new ImageIcon(Settings.class.getResource("administrators1.png")));
                } else if (roomCreated) {
                    new Main.PanelDisappear(room).start();
                    room = null;
                    roomCreated = false;
                    roomLabel.setIcon(new ImageIcon(Settings.class.getResource("rooms1.png")));
                } else if (roomTypesCreated) {
                    new Main.PanelDisappear(roomTypes).start();
                    roomTypes = null;
                    roomTypesCreated = false;
                    roomTypesLabel.setIcon(new ImageIcon(Settings.class.getResource("roomTypes1.png")));
                } else if (mealCreated) {
                    new Main.PanelDisappear(meal).start();
                    meal = null;
                    mealCreated = false;
                    mealLabel.setIcon(new ImageIcon(Settings.class.getResource("meals1.png")));
                }
                bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings3.png")));
                booking = new BookingPanel();
                bookingCreated = true;
                MainScreen.mainScreenFrame.getContentPane().add(booking);

                booking.showUnfocusedText();
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.panel);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.background);
                MainScreen.mainScreenFrame.getContentPane().add(MainScreen.separator);
            }
        } else if (mouseEvent.getSource() == modifyLabel) {

        }
    }

    private class PanelOpenning extends Thread {

        public void run() {
            slidingPanel.setVisible(true);
            try {
                while (slidingPanel.getWidth() < 681) {
                    Thread.sleep(5);
                    slidingPanel.setBounds(slidingPanel.getX() - 6, slidingPanel.getY(), slidingPanel.getWidth() + 12,
                            slidingPanel.getHeight());
                    settingsLabel.setBounds(settingsLabel.getX() + 6, settingsLabel.getY(), settingsLabel.getWidth(),
                            settingsLabel.getHeight());
                }
                closeLabel.setVisible(true);
            } catch (InterruptedException e) {
            }
        }
    }

    public static class PanelClosing extends Thread {

        public void run() {
            closeLabel.setVisible(false);
            try {
                while (slidingPanel.getWidth() > 44) {
                    Thread.sleep(5);
                    slidingPanel.setBounds(slidingPanel.getX() + 6, slidingPanel.getY(), slidingPanel.getWidth() - 12,
                            slidingPanel.getHeight());
                    settingsLabel.setBounds(settingsLabel.getX() - 6, settingsLabel.getY(), settingsLabel.getWidth(),
                            settingsLabel.getHeight());
                }
                slidingPanel.setVisible(false);
            } catch (InterruptedException e) {
            }
        }
    }
}
