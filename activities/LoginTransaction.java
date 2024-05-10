package activities;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;

public class LoginTransaction extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginTransaction window = new LoginTransaction();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginTransaction() {
        initialize();
    }

    private void initialize() {
        setTitle("LOGIN");
        setBounds(100, 100, 405, 279);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        JButton btnSubmit = new JButton("SUBMIT");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtPassword.getText().equalsIgnoreCase("gwapo")) {
                    Transaction tr = new Transaction();
                    tr.setVisible(true);
                    tr.setTitle(tr.getTitle() + " - User: " + txtUsername.getText());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid password");
                }
            }
        });
        btnSubmit.setBounds(90, 165, 89, 23);
        getContentPane().add(btnSubmit);
        txtUsername = new JTextField();
        txtUsername.setBounds(168, 72, 126, 20);
        getContentPane().add(txtUsername);
        txtUsername.setColumns(10);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(168, 103, 126, 20);
        getContentPane().add(txtPassword);

        JButton btnClear = new JButton("CLEAR");
        btnClear.setBounds(189, 165, 89, 23);
        btnClear.addActionListener((e) -> {
            txtUsername.setText("");
            txtPassword.setText("");
        });
        getContentPane().add(btnClear);

        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setBounds(96, 74, 83, 17);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Password:");
        lblNewLabel_1.setBounds(96, 106, 83, 14);
        getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("LOGIN");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(148, 26, 68, 26);
        getContentPane().add(lblNewLabel_2);

        JCheckBox chkShowPassword = new JCheckBox("show password");
        chkShowPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chkShowPassword.isSelected()) {
                    txtPassword.setEchoChar((char) 0);
                } else {
                    txtPassword.setEchoChar('*');
                }
            }
        });
        chkShowPassword.setBounds(178, 130, 126, 23);
        getContentPane().add(chkShowPassword);
    }
}

class Transaction extends JFrame {
    private static final long serialVersionUID = 1L;
    JLabel lblUsername;

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Transaction window = new Transaction();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Transaction() {
        setTitle("TRANSACTION");
        initialize();
    }

    private void initialize() {
        new JFrame();
        setBounds(100, 100, 468, 245);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // headers for the table
        String[] columns = new String[] { "ID", "NAME", "AGE", "SEX" };
        // actual data for the table in a 2d array
        Object[][] data = new Object[][] {
                { 101, "Darrel", 21, 'M' },
                { 102, "Ayien", 15, 'F' },
                { 103, "Raine", 14, 'F' },
        };

        // create table with data
        JTable table = new JTable(data, columns);

        // add the table to the frame
        getContentPane().add(new JScrollPane(table));
        pack();
        setVisible(true);
    }
}