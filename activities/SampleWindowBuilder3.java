package activities;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.text.DecimalFormat;

public class SampleWindowBuilder3 implements ActionListener {
    DecimalFormat df = new DecimalFormat("0.00");
    private JFrame frame;
    private JTextField txtNum1, txtNum2;
    private JButton btnAdd, btnSubtract, btnMultiply, btnDivide, btnModulo, btnAve;
    private JLabel lblOutput;
    private JTextField txtResult;

    /*
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SampleWindowBuilder3 window = new SampleWindowBuilder3();
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
    public SampleWindowBuilder3() {
        initialize();
    }

    /*
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Sample WindowBuilder3 with Calculations");
        frame.setBounds(100, 100, 476, 277);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("MATH CALCULATIONS");
        lblNewLabel.setBounds(165, 27, 175, 14);
        frame.getContentPane().add(lblNewLabel);
        txtNum1 = new JTextField();
        txtNum1.setBounds(241, 61, 86, 20);
        frame.getContentPane().add(txtNum1);
        txtNum1.setColumns(10);

        txtNum2 = new JTextField();
        txtNum2.setBounds(241, 92, 86, 20);
        frame.getContentPane().add(txtNum2);
        txtNum2.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Enter 1st number");
        lblNewLabel_1.setBounds(112, 64, 119, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Enter 2nd number");
        lblNewLabel_2.setBounds(112, 95, 119, 14);
        frame.getContentPane().add(lblNewLabel_2);

        lblOutput = new JLabel("RESULT:");
        lblOutput.setBounds(165, 190, 61, 14);
        frame.getContentPane().add(lblOutput);

        btnAdd = new JButton("+");
        btnAdd.setBounds(46, 133, 51, 30);
        frame.getContentPane().add(btnAdd);

        btnSubtract = new JButton("-");
        btnSubtract.setBounds(107, 133, 51, 30);
        frame.getContentPane().add(btnSubtract);

        btnMultiply = new JButton("*");
        btnMultiply.setBounds(168, 133, 51, 30);
        frame.getContentPane().add(btnMultiply);

        btnDivide = new JButton("/");
        btnDivide.setBounds(229, 133, 51, 30);
        frame.getContentPane().add(btnDivide);

        btnModulo = new JButton("%");
        btnModulo.setBounds(289, 133, 51, 30);
        frame.getContentPane().add(btnModulo);

        btnAve = new JButton("AVE");
        btnAve.setBounds(350, 133, 74, 30);
        frame.getContentPane().add(btnAve);

        txtResult = new JTextField();
        txtResult.setEnabled(false);
        txtResult.setBounds(229, 182, 51, 30);
        frame.getContentPane().add(txtResult);
        txtResult.setColumns(10);

        btnAdd.addActionListener(this);
        btnSubtract.addActionListener(this);
        btnMultiply.addActionListener(this);
        btnDivide.addActionListener(this);
        btnModulo.addActionListener(this);
        btnAve.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        float n1 = Float.parseFloat(txtNum1.getText());
        float n2 = Float.parseFloat(txtNum2.getText());
        float result = 0; // txtResult.enable(false);
        if (e.getSource() == btnAdd) {
            result = n1 + n2;
        } else if (e.getSource() == btnSubtract) {
            result = n1 - n2;
        } else if (e.getSource() == btnMultiply) {
            result = n1 * n2;
        } else if (e.getSource() == btnDivide) {
            result = n1 / n2;
        } else if (e.getSource() == btnModulo) {
            result = n1 % n2;
        } else {
            result = (n1 + n2) / 2;
        }
        txtResult.setText("" + df.format(result));
    }
}