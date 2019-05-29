package ProjectClasses;

import DatabaseRelations.Meal;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class MealPanel extends JPanel implements FocusListener {

    private JLabel mainLabel;
    private JTextField mealNameTF1;
    private JTextField mealPrice1;
    private JTextField mealNameTF2;
    private JTextField mealPrice2;
    private JTextField mealNameTF3;
    private JTextField mealPrice3;
    private EntityManager entityManager;
    private List<Meal> mealRecords;

    public MealPanel() {
        entityManager = Main.entityManager;

        new Main.PanelAppear(this).start();
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setSize(360, 455);
        setLayout(null);
        setFocusable(true);

        JLabel mealNameIcon1 = new JLabel(new ImageIcon(MealPanel.class.getResource("mealNameIcon.png")));
        mealNameIcon1.setBounds(10, 113, 30, 30);
        add(mealNameIcon1);

        mealNameTF1 = new JTextField();
        mealNameTF1.setText("Meal -1- Name");
        mealNameTF1.setForeground(Color.GRAY);
        mealNameTF1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mealNameTF1.setBounds(46, 113, 144, 30);
        add(mealNameTF1);
        mealNameTF1.addFocusListener(this);

        JLabel mealPriceIcon1 = new JLabel(new ImageIcon(MealPanel.class.getResource("priceIcon.png")));
        mealPriceIcon1.setBounds(210, 113, 30, 30);
        add(mealPriceIcon1);

        mealPrice1 = new JTextField();
        mealPrice1.setText("Price");
        mealPrice1.setForeground(Color.GRAY);
        mealPrice1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mealPrice1.setBounds(247, 113, 76, 30);
        add(mealPrice1);
        mealPrice1.addFocusListener(this);
        mealPrice1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9')) {
                    event.consume();
                }
            }
        });

        JLabel mealNameIcon2 = new JLabel(new ImageIcon(MealPanel.class.getResource("mealNameIcon.png")));
        mealNameIcon2.setBounds(10, 160, 30, 30);
        add(mealNameIcon2);

        mealNameTF2 = new JTextField();
        mealNameTF2.setText("Meal -2- Name");
        mealNameTF2.setForeground(Color.GRAY);
        mealNameTF2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mealNameTF2.setBounds(46, 160, 144, 30);
        add(mealNameTF2);
        mealNameTF2.addFocusListener(this);

        JLabel mealPriceIcon2 = new JLabel(new ImageIcon(MealPanel.class.getResource("priceIcon.png")));
        mealPriceIcon2.setBounds(210, 160, 30, 30);
        add(mealPriceIcon2);

        mealPrice2 = new JTextField();
        mealPrice2.setText("Price");
        mealPrice2.setForeground(Color.GRAY);
        mealPrice2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mealPrice2.setBounds(247, 160, 76, 30);
        add(mealPrice2);
        mealPrice2.addFocusListener(this);
        mealPrice2.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9')) {
                    event.consume();
                }
            }
        });

        JLabel mealNameIcon3 = new JLabel(new ImageIcon(MealPanel.class.getResource("mealNameIcon.png")));
        mealNameIcon3.setBounds(10, 207, 30, 30);
        add(mealNameIcon3);

        mealNameTF3 = new JTextField();
        mealNameTF3.setText("Meal -3- Name");
        mealNameTF3.setForeground(Color.GRAY);
        mealNameTF3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mealNameTF3.setBounds(46, 207, 144, 30);
        add(mealNameTF3);
        mealNameTF3.addFocusListener(this);

        JLabel mealPriceIcon3 = new JLabel(new ImageIcon(MealPanel.class.getResource("priceIcon.png")));
        mealPriceIcon3.setBounds(210, 207, 30, 30);
        add(mealPriceIcon3);

        mealPrice3 = new JTextField();
        mealPrice3.setText("Price");
        mealPrice3.setForeground(Color.GRAY);
        mealPrice3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mealPrice3.setBounds(247, 207, 76, 30);
        add(mealPrice3);
        mealPrice3.addFocusListener(this);
        mealPrice3.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                if (event.getKeyChar() == ' ' || (event.getKeyChar() < '0') || (event.getKeyChar() > '9')) {
                    event.consume();
                }
            }
        });

        JButton addMealBtn = new JButton(new ImageIcon(MealPanel.class.getResource("addIcon.png")));
        addMealBtn.setToolTipText("Add Meal");
        addMealBtn.setBounds(63, 375, 50, 50);
        add(addMealBtn);
        addMealBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (mealNameTF1.getText().equals("Meal -1- Name") && mealNameTF2.getText().equals("Meal -2- Name")
                        && mealNameTF3.getText().equals("Meal -3- Name") && mealPrice1.getText().equals("Price")
                        && mealPrice2.getText().equals("Price") && mealPrice3.getText().equals("Price")) {
                    JOptionPane.showMessageDialog(MealPanel.this, "Insert at least one meal name with its price!", "Empty Fields", JOptionPane.ERROR_MESSAGE);
                } else {
                    mealRecords = entityManager.createNamedQuery("Meal.findAll").getResultList();
                    for (Meal meal : mealRecords) {
                        if (mealNameTF1.getText().equals(meal.getName())) {
                            JOptionPane.showMessageDialog(MealPanel.this, "Meal -1- Name already exists!", "Meal", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (mealNameTF2.getText().equals(meal.getName())) {
                            JOptionPane.showMessageDialog(MealPanel.this, "Meal -2- Name already exists!", "Meal", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (mealNameTF3.getText().equals(meal.getName())) {
                            JOptionPane.showMessageDialog(MealPanel.this, "Meal -3- Name already exists!", "Meal", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (mealNameTF1.getText().equals(mealNameTF2.getText()) || mealNameTF1.getText().equals(mealNameTF3.getText())) {
                            JOptionPane.showMessageDialog(MealPanel.this, "Meal Name Repetition Has Occured!", "Meal", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    Meal meal1 = new Meal();
                    Meal meal2 = new Meal();
                    Meal meal3 = new Meal();

                    boolean meal1Valid = false, meal2Valid = false, meal3Valid = false;

                    if (!mealNameTF1.getText().equals("Meal -1- Name") && !mealPrice1.getText().equals("Price")) {
                        entityManager.persist(meal1);
                        meal1Valid = true;
                    }
                    if (!mealNameTF2.getText().equals("Meal -2- Name") && !mealPrice2.getText().equals("Price")) {
                        entityManager.persist(meal2);
                        meal2Valid = true;
                    }
                    if (!mealNameTF3.getText().equals("Meal -3- Name") && !mealPrice3.getText().equals("Price")) {
                        entityManager.persist(meal3);
                        meal3Valid = true;
                    }

                    if (meal1Valid) {
                        meal1.setName(mealNameTF1.getText());
                        meal1.setPrice(Integer.parseInt(mealPrice1.getText()));
                    }
                    if (meal2Valid) {
                        meal2.setName(mealNameTF2.getText());
                        meal2.setPrice(Integer.parseInt(mealPrice2.getText()));
                    }
                    if (meal3Valid) {
                        meal3.setName(mealNameTF3.getText());
                        meal3.setPrice(Integer.parseInt(mealPrice3.getText()));
                    }
                    if (meal1Valid || meal2Valid || meal3Valid) {
                        entityManager.getTransaction().commit();

                        JOptionPane.showMessageDialog(MealPanel.this, "Meals Added Successfully!", "Meal", JOptionPane.INFORMATION_MESSAGE);
                        entityManager.getTransaction().begin();
                        showUnfocusedText();
                    }
                }
            }
        });

        JButton clearBtn = new JButton(new ImageIcon(MealPanel.class.getResource("clearIcon.png")));
        clearBtn.setToolTipText("Clear Fields");
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                showUnfocusedText();
            }
        });
        clearBtn.setBounds(242, 375, 50, 50);
        add(clearBtn);

        JButton reportBtn = new JButton(new ImageIcon(GuestPanel.class.getResource("reportBtnIcon.png")));
        reportBtn.setToolTipText("Show report");
        reportBtn.setBounds(150, 375, 50, 50);
        add(reportBtn);
        reportBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    InputStream inputStream = EmployeePanel.class.getResourceAsStream("MealsReport.jrxml");
                    JasperReport JR = JasperCompileManager.compileReport(inputStream);
                    JasperPrint JP = JasperFillManager.fillReport(JR, null, Main.connection);
                    if (JP.getPages().isEmpty()) {
                        JOptionPane.showMessageDialog(MealPanel.this, "The Document has no pages!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JasperViewer.viewReport(JP, false);
                    }
                } catch (Exception ex) {
                }
            }
        });

        mainLabel = new JLabel(new ImageIcon(MealPanel.class.getResource("mealsPanel.png")));
        mainLabel.setBounds(0, 0, 360, 455);
        add(mainLabel);

    }

    public void showUnfocusedText() {
        mealNameTF1.setForeground(Color.gray);
        mealNameTF1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mealNameTF1.setText("Meal -1- Name");

        mealNameTF2.setForeground(Color.gray);
        mealNameTF2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mealNameTF2.setText("Meal -2- Name");

        mealNameTF3.setForeground(Color.gray);
        mealNameTF3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mealNameTF3.setText("Meal -3- Name");

        mealPrice1.setForeground(Color.gray);
        mealPrice1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mealPrice1.setText("Price");

        mealPrice2.setForeground(Color.gray);
        mealPrice2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mealPrice2.setText("Price");

        mealPrice3.setForeground(Color.gray);
        mealPrice3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mealPrice3.setText("Price");
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        if (focusEvent.getSource() == mealNameTF1) {
            if (mealNameTF1.getText().equals("Meal -1- Name")) {
                mealNameTF1.setForeground(Color.BLACK);
                mealNameTF1.setFont(new Font("Tahoma", Font.PLAIN, 13));
                mealNameTF1.setText("");
            }
        } else if (focusEvent.getSource() == mealNameTF2) {
            if (mealNameTF2.getText().equals("Meal -2- Name")) {
                mealNameTF2.setForeground(Color.BLACK);
                mealNameTF2.setFont(new Font("Tahoma", Font.PLAIN, 13));
                mealNameTF2.setText("");
            }
        } else if (focusEvent.getSource() == mealNameTF3) {
            if (mealNameTF3.getText().equals("Meal -3- Name")) {
                mealNameTF3.setForeground(Color.BLACK);
                mealNameTF3.setFont(new Font("Tahoma", Font.PLAIN, 13));
                mealNameTF3.setText("");
            }
        } else if (focusEvent.getSource() == mealPrice1) {
            if (mealPrice1.getText().equals("Price")) {
                mealPrice1.setForeground(Color.BLACK);
                mealPrice1.setFont(new Font("Tahoma", Font.PLAIN, 13));
                mealPrice1.setText("");
            }
        } else if (focusEvent.getSource() == mealPrice2) {
            if (mealPrice2.getText().equals("Price")) {
                mealPrice2.setForeground(Color.BLACK);
                mealPrice2.setFont(new Font("Tahoma", Font.PLAIN, 13));
                mealPrice2.setText("");
            }
        } else if (focusEvent.getSource() == mealPrice3) {
            if (mealPrice3.getText().equals("Price")) {
                mealPrice3.setForeground(Color.BLACK);
                mealPrice3.setFont(new Font("Tahoma", Font.PLAIN, 13));
                mealPrice3.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        if (focusEvent.getSource() == mealNameTF1) {
            if (mealNameTF1.getText().isEmpty()) {
                mealNameTF1.setForeground(Color.gray);
                mealNameTF1.setFont(new Font("Tahoma", Font.PLAIN, 12));
                mealNameTF1.setText("Meal -1- Name");
            }
        } else if (focusEvent.getSource() == mealNameTF2) {
            if (mealNameTF2.getText().isEmpty()) {
                mealNameTF2.setForeground(Color.gray);
                mealNameTF2.setFont(new Font("Tahoma", Font.PLAIN, 12));
                mealNameTF2.setText("Meal -2- Name");
            }
        } else if (focusEvent.getSource() == mealNameTF3) {
            if (mealNameTF3.getText().isEmpty()) {
                mealNameTF3.setForeground(Color.gray);
                mealNameTF3.setFont(new Font("Tahoma", Font.PLAIN, 12));
                mealNameTF3.setText("Meal -3- Name");
            }
        } else if (focusEvent.getSource() == mealPrice1) {
            if (mealPrice1.getText().isEmpty()) {
                mealPrice1.setForeground(Color.gray);
                mealPrice1.setFont(new Font("Tahoma", Font.PLAIN, 12));
                mealPrice1.setText("Price");
            }
        } else if (focusEvent.getSource() == mealPrice2) {
            if (mealPrice2.getText().isEmpty()) {
                mealPrice2.setForeground(Color.gray);
                mealPrice2.setFont(new Font("Tahoma", Font.PLAIN, 12));
                mealPrice2.setText("Price");
            }
        } else if (focusEvent.getSource() == mealPrice3) {
            if (mealPrice3.getText().isEmpty()) {
                mealPrice3.setForeground(Color.gray);
                mealPrice3.setFont(new Font("Tahoma", Font.PLAIN, 12));
                mealPrice3.setText("Price");
            }
        }
    }
}
