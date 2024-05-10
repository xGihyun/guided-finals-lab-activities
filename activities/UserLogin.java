package activities;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class UserLogin extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static String query;
    static String id, name, age, sex;

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserLogin window = new UserLogin();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void dbConnect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initializeDB() {
        try {
            stmt.execute("create database if not exists dbsample");
            stmt.execute("use dbsample");
            stmt.execute(
                    "create table if not exists tbluser(uname varchar(20) not null, pword varchar(20) not null, ulevel varchar(20) not null)");

            rs = stmt.executeQuery("select * from tbluser");
            rs.last();

            if (rs.getRow() == 0)
                stmt.execute(
                        "insert into tbluser values('user1','pw1','user'),('user2','pw2','user'),('user3','pw3','admin')");
            // conn.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public UserLogin() {
        initialize();
        dbConnect();
        initializeDB();
        try {
            query = "select * from tbluser";
            rs = stmt.executeQuery(query);
            rs.first();

            // conn.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    private void initialize() {
        setBounds(100, 100, 389, 290);
        setTitle("SAMPLE DATABASE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblNewLabel.setBounds(78, 79, 100, 17);
        getContentPane().add(lblNewLabel);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Dialog", Font.PLAIN, 14));
        txtUsername.setColumns(10);
        txtUsername.setBounds(167, 67, 126, 29);
        getContentPane().add(txtUsername);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Dialog", Font.PLAIN, 14));
        txtPassword.setBounds(167, 98, 126, 29);
        getContentPane().add(txtPassword);

        JLabel lblNewLabel_1 = new JLabel("Password: ");
        lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(78, 113, 100, 14);
        getContentPane().add(lblNewLabel_1);

        JCheckBox chkShowPassword = new JCheckBox("show password");
        chkShowPassword.setFont(new Font("Dialog", Font.PLAIN, 14));
        chkShowPassword.setBounds(167, 134, 126, 23);
        getContentPane().add(chkShowPassword);
        chkShowPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chkShowPassword.isSelected()) {
                    txtPassword.setEchoChar((char) 0);
                } else {
                    txtPassword.setEchoChar('*');
                }
            }
        });

        JButton btnClear = new JButton("CLEAR");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtUsername.setText("");
                txtPassword.setText("");
                chkShowPassword.setSelected(false);
                txtPassword.setEchoChar('*');
            }
        });
        btnClear.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnClear.setBounds(188, 169, 105, 41);
        getContentPane().add(btnClear);

        JButton btnSubmit = new JButton("SUBMIT");
        btnSubmit.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {
                try {
                    query = "select * from tbluser where pword='" + txtPassword.getText() + "'" + "and uname='" +
                            txtUsername.getText() + "'";
                    rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "password accepted...");
                        DisplayRecord dp = new DisplayRecord();
                        // dp.setTitle("TRANSACTION - User:+txtUsername.getText());
                        dp.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "username and/or password does not match...");
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        btnSubmit.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnSubmit.setBounds(73, 169, 105, 41);
        getContentPane().add(btnSubmit);

        JLabel lblNewLabel_2 = new JLabel("LOGIN");
        lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 18));
        lblNewLabel_2.setBounds(141, 25, 68, 26);
        getContentPane().add(lblNewLabel_2);
    }
}

class DisplayRecord extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtAge;
    private JTextField txtSex;
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static String query;
    static String id, name, age, sex;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DisplayRecord window = new DisplayRecord();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void dbConnect() {
        // to connect to server and database public static void dbConnect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbsample", "root", "");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initializeDB() {
        try {
            stmt.execute(
                    "create table if not exists tblsample(id varchar(3) not null primary key, name varchar(30) not null, age varchar(3) not null, sex varchar(1) not null)");

            rs = stmt.executeQuery("select * from tblsample");
            rs.last();

            if (rs.getRow() == 0)
                stmt.execute(
                        "insert into tblsample values(101,'Richard','25','M'),(102,'Dorie','20','F'),(103,'Darrel','19','M'),(104,'Ayien','17','F'),(105,'Raine','14','M')");
            // conn.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public DisplayRecord() {
        initialize();
        dbConnect();
        initializeDB();

        try {
            query = "select * from tblsample";
            rs = stmt.executeQuery(query);
            rs.first();
            displayValues();
            // conn.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void displayValues() throws SQLException {
        txtId.setText(rs.getString("id"));
        txtName.setText(rs.getString("name"));
        txtAge.setText(rs.getString("age"));
        txtSex.setText(rs.getString("sex"));
    }

    private void initialize() {
        new JFrame();
        setTitle("SAMPLE DATABASE");
        setBounds(100, 100, 407, 194);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("ID #: ");
        lblNewLabel.setBounds(22, 21, 46, 14);

        getContentPane().add(lblNewLabel);
        txtId = new JTextField();
        txtId.setBounds(78, 18, 86, 20);
        getContentPane().add(txtId);
        txtId.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("NAME:");
        lblNewLabel_1.setBounds(22, 52, 46, 14);
        getContentPane().add(lblNewLabel_1);
        txtName = new JTextField();
        txtName.setBounds(78, 49, 86, 20);
        getContentPane().add(txtName);
        txtName.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("AGE: ");
        lblNewLabel_2.setBounds(22, 83, 46, 14);
        getContentPane().add(lblNewLabel_2);
        txtAge = new JTextField();
        txtAge.setBounds(78, 80, 86, 20);
        getContentPane().add(txtAge);
        txtAge.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("SEX:");
        lblNewLabel_3.setBounds(22, 117, 46, 14);
        getContentPane().add(lblNewLabel_3);
        txtSex = new JTextField();
        txtSex.setBounds(78, 111, 86, 20);
        getContentPane().add(txtSex);
        txtSex.setColumns(10);

        JButton btnFirst = new JButton("FIRST");
        btnFirst.setBounds(193, 17, 132, 23);
        getContentPane().add(btnFirst);
        btnFirst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    query = "select * from tblsample";
                    rs = stmt.executeQuery(query);
                    rs.first();
                    displayValues();
                    // conn.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        JButton btnNext = new JButton("Next");
        btnNext.setBounds(193, 48, 64, 23);
        getContentPane().add(btnNext);
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!rs.isLast()) {
                        rs.next();
                        displayValues();
                    } else {
                        JOptionPane.showMessageDialog(null, "last record encountered...");
                        return;
                    }
                    // conn.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        JButton btnPrev = new JButton("Prev");
        btnPrev.setBounds(261, 48, 64, 23);
        getContentPane().add(btnPrev);
        btnPrev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!rs.isFirst()) {
                        rs.previous();
                        displayValues();
                    } else {
                        JOptionPane.showMessageDialog(null, "first record encountered...");
                        return;
                    }
                    // conn.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        JButton btnLast = new JButton("LAST");
        btnLast.setBounds(193, 79, 132, 23);
        getContentPane().add(btnLast);
        btnLast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    rs.last();
                    displayValues();
                    // conn.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        JButton btnSearch = new JButton("SEARCH");
        btnSearch.setBounds(214, 113, 89, 23);
        getContentPane().add(btnSearch);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String idnum = JOptionPane.showInputDialog("Type id number: ");
                    query = "select * from tblsample where id=" + idnum;
                    rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        displayValues();
                    } else {
                        JOptionPane.showMessageDialog(null, "id number not found...");
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
    }
}