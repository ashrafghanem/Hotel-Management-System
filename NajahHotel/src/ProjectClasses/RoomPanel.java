package ProjectClasses;

import DatabaseRelations.Room;
import DatabaseRelations.RoomType;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class RoomPanel extends JPanel implements FocusListener {

    private JLabel mainLabel;
    private JTextField roomNO;
    private JTextField phoneNO;
    private JComboBox<String> roomType;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private EntityManager entityManager;
    private List<Room> roomRecords;
    private List<RoomType> roomTypeRecords;

    public RoomPanel() {
        entityManager = Main.entityManager;

        new Main.PanelAppear(this).start();
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setSize(360, 455);
        setLayout(null);
        setFocusable(true);

        JLabel roomNOIcon = new JLabel(new ImageIcon(RoomPanel.class.getResource("empNO.png")));
        roomNOIcon.setBounds(10, 109, 47, 30);
        add(roomNOIcon);

        roomNO = new JTextField();
        roomNO.setText("Room Number");
        roomNO.setForeground(Color.GRAY);
        roomNO.setFont(new Font("Tahoma", Font.PLAIN, 12));
        roomNO.setColumns(10);
        roomNO.setBounds(63, 109, 229, 30);
        add(roomNO);
        roomNO.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9')
                        || roomNO.getText().length() > 2) {
                    event.consume();
                }
            }
        });
        roomNO.addFocusListener(this);

        JLabel roomAvailabilityIcon = new JLabel(new ImageIcon(RoomPanel.class.getResource("availability.png")));
        roomAvailabilityIcon.setBounds(10, 158, 47, 30);
        add(roomAvailabilityIcon);

        JLabel phoneNOIcon = new JLabel(new ImageIcon(RoomPanel.class.getResource("phoneNO.png")));
        phoneNOIcon.setBounds(10, 207, 47, 30);
        add(phoneNOIcon);

        phoneNO = new JTextField();
        phoneNO.setText("Phone Number");
        phoneNO.setForeground(Color.GRAY);
        phoneNO.setFont(new Font("Tahoma", Font.PLAIN, 12));
        phoneNO.setBounds(63, 207, 229, 30);
        add(phoneNO);
        phoneNO.addFocusListener(this);
        phoneNO.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9')) {
                    event.consume();
                }
            }
        });

        JLabel roomTypeIcon = new JLabel(new ImageIcon(RoomPanel.class.getResource("typeCodeIcon.png")));
        roomTypeIcon.setBounds(10, 256, 47, 30);
        add(roomTypeIcon);

        roomType = new JComboBox<String>();
        roomType.setForeground(Color.GRAY);
        roomType.setFont(new Font("Tahoma", Font.PLAIN, 13));
        roomType.setBounds(63, 256, 229, 30);
        add(roomType);
        updateRoomTypes(roomType);

        JRadioButton availableRB = new JRadioButton("Available");
        buttonGroup.add(availableRB);
        availableRB.setSelected(true);
        availableRB.setFont(new Font("Tahoma", Font.PLAIN, 14));
        availableRB.setBounds(63, 158, 97, 30);
        availableRB.setBackground(new Color(0, 0, 0, 0));
        add(availableRB);

        JRadioButton notAvailableRB = new JRadioButton("Not Available");
        buttonGroup.add(notAvailableRB);
        notAvailableRB.setFont(new Font("Tahoma", Font.PLAIN, 14));
        notAvailableRB.setBounds(176, 158, 116, 30);
        notAvailableRB.setBackground(new Color(0, 0, 0, 0));
        add(notAvailableRB);

        JButton addRoomBtn = new JButton(new ImageIcon(RoomPanel.class.getResource("addIcon.png")));
        addRoomBtn.setToolTipText("Add Rooom");
        addRoomBtn.setBounds(63, 375, 50, 50);
        add(addRoomBtn);
        addRoomBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (roomNO.getText().equals("Room Number") || phoneNO.getText().equals("Phone Number")
                        || roomType.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(RoomPanel.this, "You have to fill in all fields!", "Empty Fields", JOptionPane.ERROR_MESSAGE);
                } else {
                    roomRecords = entityManager.createNamedQuery("Room.findAll").getResultList();
                    for (Room room : roomRecords) {
                        if (room.getRoomNo() == Short.parseShort(roomNO.getText())) {
                            JOptionPane.showMessageDialog(RoomPanel.this, "A room with this number already exists!", "Room", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    Room room = new Room();
                    entityManager.persist(room);

                    room.setRoomNo(Short.parseShort(roomNO.getText()));
                    if (availableRB.isSelected()) {
                        room.setAvailability('Y');
                    } else {
                        room.setAvailability('N');
                    }
                    room.setPhoneNo(Long.parseLong(phoneNO.getText()));

                    List<RoomType> roomTypes = Persistence.createEntityManagerFactory("NajahHotelPU").createEntityManager().createNamedQuery("RoomType.findAll").getResultList();
                    for (int i = 0; i < roomTypes.size(); i++) {
                        if (((String) roomType.getSelectedItem()).equals(roomTypes.get(i).getTypeCode())) {
                            room.setRoomType(roomTypes.get(i));
                            break;
                        }
                    }
                    entityManager.getTransaction().commit();

                    JOptionPane.showMessageDialog(RoomPanel.this, "Room Added Successfully!", "Room", JOptionPane.INFORMATION_MESSAGE);
                    entityManager.getTransaction().begin();
                    showUnfocusedText();
                }
            }
        });

        JButton clearBtn = new JButton(new ImageIcon(RoomPanel.class.getResource("clearIcon.png")));
        clearBtn.setToolTipText("Clear Fields");
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                showUnfocusedText();
                availableRB.setSelected(true);
                notAvailableRB.setSelected(false);

                if (roomType.getSelectedIndex() != -1) {
                    roomType.setSelectedIndex(0);
                }
            }
        });
        JButton reportBtn = new JButton(new ImageIcon(GuestPanel.class.getResource("reportBtnIcon.png")));
        reportBtn.setToolTipText("Show report");
        reportBtn.setBounds(150, 375, 50, 50);
        add(reportBtn);
        reportBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    InputStream inputStream = EmployeePanel.class.getResourceAsStream("RoomsReport.jrxml");
                    JasperReport JR = JasperCompileManager.compileReport(inputStream);
                    JasperPrint JP = JasperFillManager.fillReport(JR, null, Main.connection);                    
                    if (JP.getPages().isEmpty()) {
                        JOptionPane.showMessageDialog(RoomPanel.this, "The Document has no pages!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JasperViewer.viewReport(JP, false);
                        
                    }
                } catch (Exception ex) {
                }
            }
        });
        clearBtn.setBounds(242, 375, 50, 50);
        add(clearBtn);

        mainLabel = new JLabel(new ImageIcon(RoomPanel.class.getResource("RoomsPanel.png")));
        mainLabel.setBounds(0, 0, 360, 455);
        add(mainLabel);

    }

    public void updateRoomTypes(JComboBox<String> roomType) {
        roomType.removeAll();
        roomTypeRecords = entityManager.createNamedQuery("RoomType.findAll").getResultList();

        String types[] = new String[roomTypeRecords.size() + 1];
        types[0] = "Select a room type:";
        for (int i = 0; i < roomTypeRecords.size(); i++) {
            types[i + 1] = roomTypeRecords.get(i).getTypeCode();
        }
        roomType.setModel(new DefaultComboBoxModel<String>(types));
    }

    public void showUnfocusedText() {
        roomNO.setForeground(Color.gray);
        roomNO.setFont(new Font("Tahoma", Font.PLAIN, 12));
        roomNO.setText("Room Number");

        phoneNO.setForeground(Color.gray);
        phoneNO.setFont(new Font("Tahoma", Font.PLAIN, 12));
        phoneNO.setText("Phone Number");

        roomType.setSelectedIndex(0);
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        if (focusEvent.getSource() == roomNO) {
            if (roomNO.getText().equals("Room Number")) {
                roomNO.setForeground(Color.BLACK);
                roomNO.setFont(new Font("Tahoma", Font.PLAIN, 14));
                roomNO.setText("");
            }
        } else if (focusEvent.getSource() == phoneNO) {
            if (phoneNO.getText().equals("Phone Number")) {
                phoneNO.setForeground(Color.BLACK);
                phoneNO.setFont(new Font("Tahoma", Font.PLAIN, 14));
                phoneNO.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        if (focusEvent.getSource() == roomNO) {
            if (roomNO.getText().isEmpty()) {
                roomNO.setForeground(Color.gray);
                roomNO.setFont(new Font("Tahoma", Font.PLAIN, 12));
                roomNO.setText("Room Number");
            }
        } else if (focusEvent.getSource() == phoneNO) {
            if (phoneNO.getText().isEmpty()) {
                phoneNO.setForeground(Color.gray);
                phoneNO.setFont(new Font("Tahoma", Font.PLAIN, 12));
                phoneNO.setText("Phone Number");
            }
        }
    }
}
