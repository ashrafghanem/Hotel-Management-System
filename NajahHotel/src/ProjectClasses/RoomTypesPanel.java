package ProjectClasses;

import DatabaseRelations.RoomType;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class RoomTypesPanel extends JPanel implements FocusListener {

    private JTextField typeCodeTF;
    private JTextField priceTF;
    private JTextField floorTF;
    private JTextArea descriptionTA;
    private JLabel mainLabel;
    private EntityManager entityManager;
    private Query query;
    private List<RoomType> roomTypesRecords;

    public RoomTypesPanel() {
        entityManager = Main.entityManager;

        new Main.PanelAppear(this).start();
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setSize(360, 455);
        setLayout(null);
        setFocusable(true);

        JLabel typeCodeIcon = new JLabel(new ImageIcon(RoomTypesPanel.class.getResource("typeCodeIcon.png")));
        typeCodeIcon.setBounds(6, 92, 47, 30);
        add(typeCodeIcon);

        typeCodeTF = new JTextField();
        typeCodeTF.setText("Type Code");
        typeCodeTF.setForeground(Color.GRAY);
        typeCodeTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
        typeCodeTF.setColumns(10);
        typeCodeTF.setBounds(59, 92, 229, 30);
        typeCodeTF.addFocusListener(this);
        add(typeCodeTF);
        typeCodeTF.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (typeCodeTF.getText().length() > 14) {
                    event.consume();
                }
            }
        });

        JLabel priceIcon = new JLabel(new ImageIcon(RoomTypesPanel.class.getResource("priceIcon.png")));
        priceIcon.setBounds(6, 130, 47, 30);
        add(priceIcon);

        priceTF = new JTextField();
        priceTF.setText("Price");
        priceTF.setForeground(Color.GRAY);
        priceTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
        priceTF.setBounds(59, 130, 229, 30);
        priceTF.addFocusListener(this);
        add(priceTF);
        priceTF.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9') || priceTF.getText().length() > 5) {
                    if (event.getKeyChar() != '.') {
                        event.consume();
                    }
                }
            }
        });

        JLabel floorIcon = new JLabel(new ImageIcon(RoomTypesPanel.class.getResource("floorIcon.png")));
        floorIcon.setBounds(6, 169, 47, 30);
        add(floorIcon);

        floorTF = new JTextField();
        floorTF.setText("Floor");
        floorTF.setForeground(Color.GRAY);
        floorTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
        floorTF.setBounds(59, 169, 229, 30);
        floorTF.addFocusListener(this);
        add(floorTF);
        floorTF.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9')
                        || floorTF.getText().length() > 2) {
                    event.consume();
                }
            }
        });

        JLabel descriptionIcon = new JLabel(new ImageIcon(RoomTypesPanel.class.getResource("descriptionIcon.png")));
        descriptionIcon.setBounds(6, 207, 47, 30);
        add(descriptionIcon);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(59, 207, 229, 133);
        add(scrollPane);

        descriptionTA = new JTextArea();
        scrollPane.setViewportView(descriptionTA);
        descriptionTA.setWrapStyleWord(true);
        descriptionTA.setForeground(Color.GRAY);
        descriptionTA.setText("Description");
        descriptionTA.setLineWrap(true);
        descriptionTA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        descriptionTA.addFocusListener(this);
        descriptionTA.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (descriptionTA.getText().length() > 250) {
                    event.consume();
                }
            }
        });

        JButton addTypeBtn = new JButton(new ImageIcon(RoomTypesPanel.class.getResource("addIcon.png")));
        addTypeBtn.setToolTipText("Add Room Type");
        addTypeBtn.setBounds(59, 375, 50, 50);
        add(addTypeBtn);
        addTypeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (typeCodeTF.getText().equals("Type Code") || priceTF.getText().equals("Price") || floorTF.getText().equals("Floor")) {
                    JOptionPane.showMessageDialog(RoomTypesPanel.this, "You have to fill in the first three fields!", "Empty Fields", JOptionPane.ERROR_MESSAGE);
                } else {
                    query = entityManager.createNamedQuery("RoomType.findAll");
                    roomTypesRecords = query.getResultList();
                    for (RoomType type : roomTypesRecords) {
                        if (type.getTypeCode().equals(typeCodeTF.getText())) {
                            JOptionPane.showMessageDialog(RoomTypesPanel.this, "This type already exists!", "Room Types", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    RoomType roomType = new RoomType();
                    entityManager.persist(roomType);

                    roomType.setTypeCode(typeCodeTF.getText());
                    roomType.setPrice(Integer.parseInt(priceTF.getText()));
                    roomType.setFloor(Short.parseShort(floorTF.getText()));
                    if (descriptionTA.getText().equals("Description")) {
                        roomType.setDescription("");
                    } else {
                        roomType.setDescription(descriptionTA.getText());
                    }

                    entityManager.getTransaction().commit();

                    JOptionPane.showMessageDialog(RoomTypesPanel.this, "Room Type Added Successfully!", "Room Types", JOptionPane.INFORMATION_MESSAGE);
                    entityManager.getTransaction().begin();
                    showUnfocusedText();
                }
            }
        });

        JButton clearBtn = new JButton(new ImageIcon(RoomTypesPanel.class.getResource("clearIcon.png")));
        clearBtn.setToolTipText("Clear Fields");
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                showUnfocusedText();
            }
        });
        clearBtn.setBounds(238, 375, 50, 50);
        add(clearBtn);

        mainLabel = new JLabel(new ImageIcon(RoomTypesPanel.class.getResource("RoomTypesPanel.png")));
        mainLabel.setBounds(0, 0, 360, 455);
        add(mainLabel);
    }

    public void showUnfocusedText() {
        typeCodeTF.setForeground(Color.gray);
        typeCodeTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
        typeCodeTF.setText("Type Code");

        priceTF.setForeground(Color.gray);
        priceTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
        priceTF.setText("Price");

        floorTF.setForeground(Color.gray);
        floorTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
        floorTF.setText("Floor");

        descriptionTA.setForeground(Color.gray);
        descriptionTA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        descriptionTA.setText("Description");
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        if (focusEvent.getSource() == typeCodeTF) {
            if (typeCodeTF.getText().equals("Type Code")) {
                typeCodeTF.setForeground(Color.BLACK);
                typeCodeTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
                typeCodeTF.setText("");
            }
        } else if (focusEvent.getSource() == priceTF) {
            if (priceTF.getText().equals("Price")) {
                priceTF.setForeground(Color.BLACK);
                priceTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
                priceTF.setText("");
            }
        } else if (focusEvent.getSource() == floorTF) {
            if (floorTF.getText().equals("Floor")) {
                floorTF.setForeground(Color.BLACK);
                floorTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
                floorTF.setText("");
            }
        } else if (focusEvent.getSource() == descriptionTA) {
            if (descriptionTA.getText().equals("Description")) {
                descriptionTA.setForeground(Color.BLACK);
                descriptionTA.setFont(new Font("Tahoma", Font.PLAIN, 14));
                descriptionTA.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        if (focusEvent.getSource() == typeCodeTF) {
            if (typeCodeTF.getText().isEmpty()) {
                typeCodeTF.setForeground(Color.gray);
                typeCodeTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
                typeCodeTF.setText("Type Code");
            }
        } else if (focusEvent.getSource() == priceTF) {
            if (priceTF.getText().isEmpty()) {
                priceTF.setForeground(Color.gray);
                priceTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
                priceTF.setText("Price");
            }
        } else if (focusEvent.getSource() == floorTF) {
            if (floorTF.getText().isEmpty()) {
                floorTF.setForeground(Color.gray);
                floorTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
                floorTF.setText("Floor");
            }
        } else if (focusEvent.getSource() == descriptionTA) {
            if (descriptionTA.getText().isEmpty()) {
                descriptionTA.setForeground(Color.gray);
                descriptionTA.setFont(new Font("Tahoma", Font.PLAIN, 14));
                descriptionTA.setText("Description");
            }
        }
    }
}
