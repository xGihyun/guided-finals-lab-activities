package activities;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SampleWindowBuilder2 implements ActionListener {
  private JFrame frame;
  private JTextField txtName;
  private JButton btnSubmit, btnClose, btnReset;

  /*
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          SampleWindowBuilder2 window = new SampleWindowBuilder2();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /*
   * Create the application
   */
  public SampleWindowBuilder2() {
    initialize();
  }

  /*
   * Initialize the contents of the frame.
   */

  private void initialize() {
    frame = new JFrame();
    frame.setTitle("Sample WindowBuilder2 using ActionLister");
    frame.setBounds(100, 100, 450, 300);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.getContentPane().setLayout(null);

    JLabel lblNewLabel = new JLabel("Enter name here:");
    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
    lblNewLabel.setBounds(134, 65, 184, 30);
    frame.getContentPane().add(lblNewLabel);

    txtName = new JTextField();
    txtName.setFont(new Font("Tahoma", Font.PLAIN, 18));
    txtName.setColumns(10);
    txtName.setBounds(153, 106, 111, 33);
    frame.getContentPane().add(txtName);

    btnSubmit = new JButton("SUBMIT");
    btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 18));
    btnSubmit.setBounds(71, 150, 111, 36);
    frame.getContentPane().add(btnSubmit);

    btnClose = new JButton("CLOSE");
    btnClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
    btnClose.setBounds(192, 150, 89, 36);
    frame.getContentPane().add(btnClose);

    btnReset = new JButton("RESET");
    btnReset.setFont(new Font("Tahoma", Font.PLAIN, 18));
    btnReset.setBounds(292, 150, 89, 36);
    frame.getContentPane().add(btnReset);

    btnSubmit.addActionListener(this);
    btnReset.addActionListener(this);
    btnClose.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    if (e.getSource() == btnSubmit) {
      JOptionPane.showMessageDialog(null, "Hello " + txtName.getText());
    } else if (e.getSource() == btnReset) {
      txtName.setText(null);
      txtName.requestFocus();
    } else {
      frame.dispose();
    }
  }
}
