package activities;

import java.awt.EventQueue;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class RadioCheckDemo extends JFrame {
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RadioCheckDemo window = new RadioCheckDemo();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RadioCheckDemo() {
        initialize();
    }

    private void initialize() {
        new JFrame();
        setTitle("RadioButton & CheckBox Sample");
        setBounds(100, 100, 452, 277);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JCheckBox chkCandy = new JCheckBox("CANDY (P20)");
        chkCandy.setBounds(197, 21, 97, 23);
        getContentPane().add(chkCandy);

        JCheckBox chkLollipop = new JCheckBox("LOLLIPOP (P30)");
        chkLollipop.setBounds(197, 51, 133, 23);
        getContentPane().add(chkLollipop);

        JCheckBox chkChocolate = new JCheckBox("CHOCOLATE (P50)");
        chkChocolate.setBounds(197, 77, 145, 23);
        getContentPane().add(chkChocolate);

        JLabel lblOutput = new JLabel("OUTPUT");
        lblOutput.setBounds(145, 147, 281, 38);
        getContentPane().add(lblOutput);

        JLabel lblTotal = new JLabel("TOTAL");
        lblTotal.setBounds(143, 201, 220, 14);
        getContentPane().add(lblTotal);

        ButtonGroup rdoGroupGender = new ButtonGroup();
        JRadioButton rdoMale = new JRadioButton("Male");
        rdoMale.setBounds(40, 21, 109, 23);
        getContentPane().add(rdoMale);
        rdoGroupGender.add(rdoMale);
        rdoMale.setSelected(true); // default gender

        JRadioButton rdoFemale = new JRadioButton("Female");
        rdoFemale.setBounds(40, 51, 109, 23);
        getContentPane().add(rdoFemale);
        rdoGroupGender.add(rdoFemale);

        JCheckBox chkStatus = new JCheckBox("Single");
        chkStatus.setBounds(40, 92, 97, 23);
        getContentPane().add(chkStatus);
        chkStatus.setSelected(true); // default value

        JButton btnTest1 = new JButton("TEST1");
        btnTest1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String stat = "";
                if (chkStatus.isSelected()) {
                    stat = "Single";
                } else {
                    stat = "Not Available";
                }

                if (rdoMale.isSelected()) {
                    JOptionPane.showMessageDialog(null, "You are Male and " + stat);
                    // sex = rdoMale.getText(); // or sex = "Male";
                } else if (rdoFemale.isSelected()) {
                    JOptionPane.showMessageDialog(null, "You are Female and " + stat);
                    // sex = rdoFemale.getText(); // or sex = "Female"
                } else {
                    JOptionPane.showMessageDialog(null, "You are " + stat);
                }
            }
        });

        btnTest1.setBounds(35, 147, 89, 23);
        getContentPane().add(btnTest1);

        JButton btnClear = new JButton("CLEAR");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chkStatus.setSelected(false);
                rdoGroupGender.clearSelection();
                chkCandy.setSelected(false);
                chkLollipop.setSelected(false);
                chkChocolate.setSelected(false);
                lblOutput.setText("OUTPUT");
                lblTotal.setText("TOTAL");
            }
        });
        btnClear.setBounds(35, 181, 89, 23);
        getContentPane().add(btnClear);

        JButton btnTest2 = new JButton("TEST 2");
        btnTest2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String item = "";
                int price = 0, total = 0;
                if (chkCandy.isSelected()) {
                    item = " Candy ";
                    price = 20;
                    total = total + price;
                }
                if (chkLollipop.isSelected()) {
                    item = item + " Lollipop ";
                    price = 30;
                    total = total + price;
                }
                if (chkChocolate.isSelected()) {
                    item = item + " Chocolate ";
                    price = 50;
                    total = total + price;
                }
                lblOutput.setText("You selected:" + item);
                lblTotal.setText("Total Price: " + total);
            }
        });
        btnTest2.setBounds(205, 117, 89, 23);
        getContentPane().add(btnTest2);
    }
}
