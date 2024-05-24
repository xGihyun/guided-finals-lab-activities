package activities;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class TableSample extends JFrame {
  private static final long serialVersionUID = 1L;
  private JTextField txtId;
  private JTextField txtName;
  private JTextField txtAge;
  private JTextField txtSex;
  static Connection conn;
  static Statement stmt;
  static ResultSet rs;
  static String query;
  private JTextField txtQuery;

  public static void main(String[] args) {
    EventQueue.invokeLater(
        new Runnable() {
          public void run() {
            try {
              TableSample window = new TableSample();
              window.setVisible(true);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        });
  }

  public TableSample() {
    initialize();
  }

  private void initialize() {
    JTable table = new JTable();
    Object[] columns = { "ID", "NAME", "AGE", "SEX" };
    DefaultTableModel model = new DefaultTableModel();

    setTitle("SAMPLE DATABASE");
    setBounds(100, 100, 425, 392);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    getContentPane().setLayout(null);
    setVisible(true);

    model.setColumnIdentifiers(columns);
    table.setModel(model); // table properties
    table.setBackground(Color.white);
    table.setForeground(Color.black);

    table.setSelectionBackground(Color.red);
    table.setGridColor(Color.red);
    table.setSelectionBackground(Color.white);
    table.setFont(new Font("Tahoma", Font.PLAIN, 17));
    table.setRowHeight(30);
    table.setAutoCreateRowSorter(true);
    table.setVisible(true);

    JScrollPane pane = new JScrollPane(table);
    pane.setForeground(Color.RED);
    pane.setBackground(Color.WHITE);
    pane.setBounds(10, 11, 388, 184);
    getContentPane().add(pane);

    Object[] row = new Object[4];
    JButton btnAdd = new JButton("ADD");
    btnAdd.setBounds(181, 276, 88, 23);
    getContentPane().add(btnAdd);
    btnAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        row[0] = txtId.getText();
        row[1] = txtName.getText();
        row[2] = txtAge.getText();
        row[3] = txtSex.getText();

        model.addRow(row);
      }
    });

    JButton btnDelete = new JButton("DELETE");
    btnDelete.setBounds(279, 276, 89, 23);
    getContentPane().add(btnDelete);
    btnDelete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int i = table.getSelectedRow();
        if (i >= 0) {
          model.removeRow(i);
        } else {
          JOptionPane.showMessageDialog(null, "Delete Error");
        }
      }
    });

    txtId = new JTextField();
    txtId.setBounds(52, 237, 86, 20);
    getContentPane().add(txtId);
    txtId.setColumns(10);

    txtName = new JTextField();
    txtName.setBounds(52, 265, 86, 20);
    getContentPane().add(txtName);
    txtName.setColumns(10);

    txtAge = new JTextField();
    txtAge.setBounds(52, 294, 86, 20);
    getContentPane().add(txtAge);
    txtAge.setColumns(10);

    txtSex = new JTextField();
    txtSex.setBounds(52, 322, 86, 20);
    getContentPane().add(txtSex);
    txtSex.setColumns(10);

    JLabel lblNewLabel = new JLabel("ID");
    lblNewLabel.setBounds(10, 240, 46, 14);
    getContentPane().add(lblNewLabel);

    JLabel lblNewLabel_1 = new JLabel("NAME");
    lblNewLabel_1.setBounds(10, 268, 46, 14);
    getContentPane().add(lblNewLabel_1);

    JLabel lblNewLabel_2 = new JLabel("AGE");
    lblNewLabel_2.setBounds(10, 297, 46, 14);
    getContentPane().add(lblNewLabel_2);

    JLabel lblNewLabel_3 = new JLabel("SEX");
    lblNewLabel_3.setBounds(10, 325, 46, 14);
    getContentPane().add(lblNewLabel_3);

    JButton btnShow = new JButton("SHOW RECORDS");
    btnShow.setBounds(148, 236, 250, 23);
    getContentPane().add(btnShow);
    btnShow.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "root", "");
          stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

          stmt.execute("create database if not exists dbsample");
          stmt.execute("use dbsample");
          stmt.execute(
              "create table if not exists tblsample(id varchar(3) not null primary key, name varchar(30) not null, age varchar(3) not null, sex varchar(1) not null)");

          if (txtQuery.getText().equals(""))
            query = "select * from tblsample";
          else
            query = txtQuery.getText();
          rs = stmt.executeQuery(query);
          while (model.getRowCount() > 0) {
            model.removeRow(0);
          }
          while (rs.next()) {
            row[0] = rs.getString("id");
            row[1] = rs.getString("name");
            row[2] = rs.getString("age");
            row[3] = rs.getString("sex");
            model.addRow(row);
          }
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });

    txtQuery = new JTextField();
    txtQuery.setBounds(10, 206, 388, 20);
    getContentPane().add(txtQuery);
    txtQuery.setColumns(10);

    JButton btnClose = new JButton("CLOSE");
    btnClose.setBounds(146, 321, 252, 23);
    getContentPane().add(btnClose);
    btnClose.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    pane.setVisible(true);
  }
}
