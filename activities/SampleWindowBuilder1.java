package activities;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SampleWindowBuilder1 {
    private JFrame frame;
    private JTextField txtName;
    private JButton btnClose;
    private JButton btnReset;

    /*
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SampleWindowBuilder1 window = new SampleWindowBuilder1();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*
     * Create the application.
     */
    public SampleWindowBuilder1() {
        initialize();
    }

    /*
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Sample WindowBuilder #1");
        frame.setBounds(100, 100, 426, 263);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Enter name here:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(123, 38, 184, 30);
        frame.getContentPane().add(lblNewLabel);

        JButton btnSubmit = new JButton("SUBMIT");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Welcome to WindowBuilder " + txtName.getText());
            }
        });
        btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnSubmit.setBounds(51, 123, 111, 36);
        frame.getContentPane().add(btnSubmit);

        txtName = new JTextField();
        txtName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtName.setBounds(133, 79, 111, 33);
        frame.getContentPane().add(txtName);
        txtName.setColumns(10);
        btnClose = new JButton("CLOSE");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnClose.setBounds(172, 123, 89, 36);
        frame.getContentPane().add(btnClose);
        btnReset = new JButton("RESET");
        btnReset.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtName.setText(" ");
                txtName.requestFocus();
            }
        });
        btnReset.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnReset.setBounds(272, 123, 89, 36);
        frame.getContentPane().add(btnReset);
    }
}