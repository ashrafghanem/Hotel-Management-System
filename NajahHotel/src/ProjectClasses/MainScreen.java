package ProjectClasses;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class MainScreen {

    public static JFrame mainScreenFrame;
    public static JLabel userStripe, changePassLabel, usernameLabel, signOutLabel, title;
    public static JLabel background, stripeBG;
    public static JPanel panel, stripePanel;
    public static JSeparator separator;
    public static boolean changePassCreated = false;
    public static String currUsername;

    public MainScreen() {
        mainScreenFrame = new JFrame();
        mainScreenFrame.setTitle("An-Najah Hotel Management System");
        mainScreenFrame.setIconImage(new ImageIcon(Main.class.getResource("frameImage.png")).getImage());
        mainScreenFrame.setSize(805, 700);
        mainScreenFrame.setLocationRelativeTo(null);
        mainScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreenFrame.getContentPane().setLayout(null);
        mainScreenFrame.setResizable(false);

        new Settings();
        background = new JLabel();

        title = new JLabel();
        title.setIcon(new ImageIcon(MainScreen.class.getResource("title.png")));
        title.setBounds(392, 11, 397, 65);
        mainScreenFrame.getContentPane().add(title);

        panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setOpaque(false);
        panel.setBounds(0, 0, 800, 521);
        mainScreenFrame.getContentPane().add(panel);
        panel.setLayout(null);

        stripePanel = new JPanel();
        stripePanel.setBounds(-229, 11, 249, 81);
        panel.add(stripePanel);
        stripePanel.setLayout(null);
        stripePanel.setBackground(new Color(0, 0, 0, 0));
        stripePanel.setOpaque(false);
        stripePanel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent event) {
                new stripeAppear().start();
            }

            public void mouseExited(MouseEvent event) {
                new stripeAppear().start();
            }
        });
        currUsername = LogIn.currUsername;

        if (currUsername.equals("admin")) {
            changePassLabel = new JLabel("");
        } else {
            changePassLabel = new JLabel("Change Password");
        }
        changePassLabel.setHorizontalAlignment(SwingConstants.LEFT);
        changePassLabel.setForeground(Color.BLACK);
        changePassLabel.setFont(new Font("Bell MT", Font.BOLD, 13));
        changePassLabel.setBounds(50, 42, 120, 28);
        stripePanel.add(changePassLabel);
        changePassLabel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent event) {
                changePassLabel.setForeground(Color.RED);
            }

            public void mouseExited(MouseEvent event) {
                changePassLabel.setForeground(Color.BLACK);
            }

            public void mouseClicked(MouseEvent mouseEvent) {
                if (!changePassCreated) {
                    if (Settings.guestCreated) {
                        new Main.PanelDisappear(Settings.guest).start();
                        Settings.guestCreated = false;
                        Settings.guestLabel.setIcon(new ImageIcon(Settings.class.getResource("guests1.png")));
                    } else if (Settings.employeeCreated) {
                        Settings.employee.callEmpDisappear();
                        Settings.employeeCreated = false;
                        Settings.empLabel.setIcon(new ImageIcon(Settings.class.getResource("employees1.png")));
                    } else if (Settings.adminCreated) {
                        new Main.PanelDisappear(Settings.admin).start();
                        Settings.adminCreated = false;
                        Settings.adminLabel.setIcon(new ImageIcon(Settings.class.getResource("administrators1.png")));
                    } else if (Settings.roomTypesCreated) {
                        new Main.PanelDisappear(Settings.roomTypes).start();
                        Settings.roomTypesCreated = false;
                        Settings.roomTypesLabel.setIcon(new ImageIcon(Settings.class.getResource("roomTypes1.png")));
                    } else if (Settings.roomCreated) {
                        new Main.PanelDisappear(Settings.room).start();
                        Settings.roomCreated = false;
                        Settings.roomLabel.setIcon(new ImageIcon(Settings.class.getResource("rooms1.png")));
                    } else if (Settings.mealCreated) {
                        new Main.PanelDisappear(Settings.meal).start();
                        Settings.mealCreated = false;
                        Settings.mealLabel.setIcon(new ImageIcon(Settings.class.getResource("meals1.png")));
                    } else if (Settings.bookingCreated) {
                        new Main.PanelDisappear(Settings.booking).start();
                        Settings.bookingCreated = false;
                        Settings.bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                    }
                    if (Settings.roomBookingCreated) {
                        new Main.PanelDisappear(BookingPanel.roomBooking).start();
                        Settings.roomBookingCreated = false;
                        Settings.bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                    }
                    if (Settings.mealOrdersCreated) {
                        new Main.PanelDisappear(BookingPanel.mealOrders).start();
                        Settings.mealOrdersCreated = false;
                        Settings.bookingLabel.setIcon(new ImageIcon(Settings.class.getResource("bookings1.png")));
                    }
                    ChangePassword changePass = new ChangePassword();

                    if (Settings.slidingLabelOpened) {
                        new Settings.PanelClosing().start();
                        Settings.settingsLabel
                                .setIcon(new ImageIcon(Settings.class.getResource("settings3DLightIcon.png")));
                    }

                    changePassCreated = true;
                    MainScreen.mainScreenFrame.getContentPane().add(changePass);
                    changePass.showUnfocusedText();
                    MainScreen.mainScreenFrame.getContentPane().add(MainScreen.panel);
                    MainScreen.mainScreenFrame.getContentPane().add(MainScreen.background);
                    MainScreen.mainScreenFrame.getContentPane().add(MainScreen.separator);
                }
            }
        });

        if (currUsername.equals("admin")) {
            usernameLabel = new JLabel("Username: ");
        } else {
            usernameLabel = new JLabel("Username: " + currUsername);
        }
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Bell MT", Font.BOLD, 14));
        usernameLabel.setBounds(30, 11, 211, 28);
        stripePanel.add(usernameLabel);

        signOutLabel = new JLabel("Sign Out");
        signOutLabel.setHorizontalAlignment(SwingConstants.LEFT);
        signOutLabel.setForeground(Color.BLACK);
        signOutLabel.setFont(new Font("Bell MT", Font.BOLD, 13));
        signOutLabel.setBounds(172, 42, 72, 28);
        stripePanel.add(signOutLabel);
        signOutLabel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent event) {
                signOutLabel.setForeground(Color.RED);
            }

            public void mouseExited(MouseEvent event) {
                signOutLabel.setForeground(Color.BLACK);
            }

            public void mouseClicked(MouseEvent event) {
                MainScreen.mainScreenFrame.setVisible(false);
                MainScreen.mainScreenFrame = null;

                Settings.employeeCreated = false;
                Settings.guestCreated = false;
                Settings.adminCreated = false;
                Settings.roomCreated = false;
                Settings.roomTypesCreated = false;
                Settings.mealCreated = false;
                Settings.bookingCreated = false;
                changePassCreated = false;
                Settings.slidingLabelOpened = false;

                Main.frame.setVisible(true);

                Main.logIn.userTF.setForeground(Color.gray);
                Main.logIn.userTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
                Main.logIn.userTF.setText("Username");

                Main.logIn.passPF.setForeground(Color.gray);
                Main.logIn.passPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
                Main.logIn.passPF.setEchoChar((char) 0);
                Main.logIn.passPF.setText("Password");

                Main.logIn.panel.requestFocus();
            }
        });

        stripeBG = new JLabel(new ImageIcon(MainScreen.class.getResource("stripe.png")));
        stripeBG.setBounds(0, 0, 249, 81);
        stripePanel.add(stripeBG);

        int X = panel.getWidth();
        int Y = panel.getHeight();

        JLabel bubble1 = new JLabel(new ImageIcon(Settings.class.getResource("bubble1.png")));
        bubble1.setBounds(388, 301, 122, 122);
        panel.add(bubble1);
        new Bubbles(bubble1, -190, 0, 0, 11).start();

        JLabel bubble2 = new JLabel(new ImageIcon(Settings.class.getResource("bubble2.png")));
        bubble2.setBounds(285, 102, 114, 113);
        panel.add(bubble2);
        new Bubbles(bubble2, X, 0, 2, 8).start();

        JLabel bubble3 = new JLabel(new ImageIcon(Settings.class.getResource("bubble3.png")));
        bubble3.setBounds(238, 224, 128, 127);
        panel.add(bubble3);
        new Bubbles(bubble3, 0, Y, 1, 9).start();

        JLabel bubble4 = new JLabel(new ImageIcon(Settings.class.getResource("bubble4.png")));
        bubble4.setBounds(500, 125, 128, 127);
        panel.add(bubble4);
        new Bubbles(bubble4, X, Y, 3, 10).start();

        JLabel bubble5 = new JLabel(new ImageIcon(Settings.class.getResource("bubble5.png")));
        bubble5.setBounds(101, 301, 127, 128);
        panel.add(bubble5);
        new Bubbles(bubble5, X / 3, Y / 3, 0, 12).start();

        background.setIcon(new ImageIcon(MainScreen.class.getResource("mainScreenBG.png")));
        background.setBounds(0, 0, 800, 672);
        mainScreenFrame.getContentPane().add(background);

        separator = new JSeparator();
        separator.setBounds(40, 521, 720, 2);
        mainScreenFrame.getContentPane().add(separator);

        background.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent event) {
                new stripeDisappear().start();
            }
        });
        mainScreenFrame.setVisible(true);
    }

    private static class stripeAppear extends Thread {

        public void run() {
            if (stripePanel.getX() == -229) { // For Thread Synchronization
                while (stripePanel.getX() < -23) {
                    stripePanel.setBounds(stripePanel.getX() + 2, stripePanel.getY(), stripePanel.getWidth(),
                            stripePanel.getHeight());
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    private static class stripeDisappear extends Thread {

        public void run() {
            if (stripePanel.getX() == -23) {
                while (stripePanel.getX() > -229) { // For Thread
                    // Synchronization
                    stripePanel.setBounds(stripePanel.getX() - 2, stripePanel.getY(), stripePanel.getWidth(),
                            stripePanel.getHeight());

                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    private class Bubbles extends Thread {

        public JLabel label;
        public int x, y, state, sleepingTime;

        public Bubbles(JLabel bubbleLabel, int x, int y, int state, int time) {
            label = new JLabel();
            label = bubbleLabel;
            this.x = x;
            this.y = y;
            this.state = state;
            this.sleepingTime = time;
        }

        public void run() {
            try {
                while (true) {
                    Thread.sleep(sleepingTime);
                    if (state == 0) {
                        if (y + label.getHeight() > panel.getHeight()) {
                            state = 1;
                            continue;
                        }
                        if (x + label.getWidth() > panel.getWidth()) {
                            state = 2;
                            continue;
                        }
                        label.setLocation(x++, y++);
                    }
                    if (state == 1) {
                        if (y < 0) {
                            state = 0;
                            continue;
                        }
                        if (x + label.getWidth() > panel.getWidth()) {
                            state = 3;
                            continue;
                        }
                        label.setLocation(x++, y--);
                    }
                    if (state == 2) {
                        if (x < 0) {
                            state = 0;
                            continue;
                        }
                        if (y + label.getHeight() > panel.getHeight()) {
                            state = 3;
                            continue;
                        }
                        label.setLocation(x--, y++);
                    }
                    if (state == 3) {
                        if (x < 0) {
                            state = 1;
                            continue;
                        }
                        if (y < 0) {
                            state = 2;
                            continue;
                        }
                        label.setLocation(x--, y--);
                    }
                }
            } catch (Exception e) {

            }
        }
    }
}
