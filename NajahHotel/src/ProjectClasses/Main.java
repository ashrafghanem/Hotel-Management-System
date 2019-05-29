package ProjectClasses;

import DatabaseRelations.Room;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import oracle.jdbc.pool.OracleDataSource;

public class Main {

    public static JFrame frame;
    public static JLabel background, splash;
    public static LogIn logIn;
    private static ForgotPassword forgotPassword;
    public static ExecutorService executorService;
    public static boolean spaceFlag = false, mouseFlag = false;
    public static EntityManager entityManager;
    public static OracleDataSource ods;
    public static Connection connection;
    public static Statement statement;
    public static ResultSet result;

    public static void main(String[] args) {
        // Changing the look and feel for the application.
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }
        executorService = Executors.newCachedThreadPool();

        frame = new JFrame("An-Najah Hotel Management System");
        frame.setIconImage(new ImageIcon(Main.class.getResource("frameImage.png")).getImage());
        frame.setSize(805, 528);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        entityManager = Persistence.createEntityManagerFactory("NajahHotelPU").createEntityManager();
        entityManager.getTransaction().begin();

        try {
            ods = new OracleDataSource();
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            ods.setURL(url);
            ods.setUser("HotelDB");
            ods.setPassword("hoteldb");
            connection = ods.getConnection();
            statement = connection.createStatement();
        } catch (Exception ex) {
        }
        // to make rooms with finished period available to be booked.
        freeBookedRooms();

        splash = new JLabel();
        splash.setBounds(0, 0, 800, 500);
        splash.setIcon(new ImageIcon(Main.class.getResource("Splash.gif")));
        frame.add(splash);
        splash.setFocusable(true);

        splash.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                if (!mouseFlag) {
                    spaceFlag = true; // To prevent space from working after a
                    // mouse click.
                    executorService.execute(new SplashToLogIn());
                    mouseFlag = !mouseFlag; // To execute only once!
                }
            }
        });

        splash.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (!spaceFlag && event.getKeyChar() == ' ') {
                    mouseFlag = true; // To prevent mouse from working after a
                    // space press.
                    executorService.execute(new SplashToLogIn());
                    spaceFlag = !spaceFlag; // To execute only once!
                }
            }
        });

        background = new JLabel();
        frame.setVisible(true);
    }

    public static void freeBookedRooms() {
        List<Room> rooms = entityManager.createNamedQuery("Room.findAll").getResultList();

        for (Room room : rooms) {
            if (room.getAvailability() == 'N') {
                Date date = room.getCheckInDate();
                int period = room.getVisitPeriod();
                if (checkBookingFinished(date, period)) {
                    entityManager.persist(room);
                    room.setAvailability('Y');
                    room.setBookingNo(null);
                    room.setCheckInDate(null);
                    room.setPurposeOfVisit(null);
                    room.setVisitPeriod(null);
                }
            }
        }
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
    }

    public static boolean checkBookingFinished(Date date, int period) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Calendar calendar = Calendar.getInstance();

        Date checkInDate = date;
        calendar.setTime(checkInDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // as month here starts from 0
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Date currDate = new Date();

        for (int i = 1; i <= period; i++) {
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                day++;
                if (day > 31) {
                    day = 1;
                    month++;
                    if (month > 12) {
                        month = 1;
                        year++;
                    }
                }
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                day++;
                if (day > 30) {
                    day = 1;
                    month++;
                    if (month > 12) {
                        month = 1;
                        year++;
                    }
                }
            } else if (month == 2 && year % 4 != 0) {
                day++;
                if (day > 28) {
                    day = 1;
                    month++;
                    if (month > 12) {
                        month = 1;
                        year++;
                    }
                }
            } else if (month == 2 && year % 4 == 0) {
                day++;
                if (day > 29) {
                    day = 1;
                    month++;
                    if (month > 12) {
                        month = 1;
                        year++;
                    }
                }
            }
        }
        try {
            String str = day + "-" + month + "-" + year;
            Date newDate = dateFormat.parse(str);

            if (newDate.after(currDate)|| newDate.equals(currDate)) {
                return false;
            } else {
                return true;
            }

        } catch (Exception ex) {
        }
        return true;
    }

    public static class SplashToLogIn extends Thread {

        public void run() {
            try {
                background.setBounds(800, 0, 800, 500);
                background.setIcon(new ImageIcon(Main.class.getResource("Transition.gif")));
                frame.add(background);

                while ((splash.getX() + splash.getWidth() > 0)) {
                    splash.setBounds(splash.getX() - 5, splash.getY(), splash.getWidth(), splash.getHeight());
                    background.setBounds(background.getX() - 5, background.getY(), background.getWidth(),
                            background.getHeight());
                    Thread.sleep(2);
                }
                logIn = new LogIn();
                logIn.userTF.requestFocus();
                logIn.passPF.requestFocus();
                logIn.panel.requestFocus();

                frame.add(background);
                frame.repaint();
            } catch (InterruptedException e) {
            }
        }
    }

    public static class LogInToForgotPass extends Thread {

        public void run() {
            try {
                forgotPassword = new ForgotPassword();
                logIn.logInRePosition();

                while (forgotPassword.panel.getX() > 428) {
                    forgotPassword.forgotPassSlide();

                    frame.add(background);
                    Thread.sleep(2);
                }

                forgotPassword.showUnfocusedText();
                 forgotPassword.userTF.setForeground(Color.gray);
                          forgotPassword.  userTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
                          forgotPassword.  userTF.setText("Username");
                frame.repaint();
            } catch (InterruptedException e) {
            }
        }
    }

    public static class ForgotPassToLogIn extends Thread {

        public void run() {
            try {
                logIn = new LogIn();
                logIn.logInRePosition();
                forgotPassword.forgotPassRePosition();

                while (logIn.panel.getX() > 428) {
                    logIn.logInSlide();
                    frame.add(background);
                    Thread.sleep(2);
                }

                logIn.userTF.requestFocus();
                logIn.passPF.requestFocus();
                logIn.panel.requestFocus();

                frame.repaint();
            } catch (InterruptedException e) {
            }
        }
    }

    public static class PanelAppear extends Thread {

        int x = 300;
        int y = 150;
        JPanel panel;

        int oldWidth = 0, oldHeight = 0;

        public PanelAppear(JPanel panel) {
            this.panel = panel;
        }

        public void run() {
            try {
                while (oldHeight < 455) {
                    Thread.sleep(5);
                    oldHeight += 10;
                    panel.setBounds(x--, y--, oldWidth, oldHeight);
                }
                while (oldWidth < 360) {
                    Thread.sleep(5);
                    oldWidth += 10;
                    panel.setBounds(x--, y--, oldWidth, oldHeight);
                }
            } catch (Exception e) {
            }
        }
    }

    public static class PanelDisappear extends Thread {

        JPanel panel;

        public PanelDisappear(JPanel panel) {
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
