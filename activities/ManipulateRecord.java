package activities;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ManipulateRecord extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  private JTextField txtId, txtName, txtAge, txtSex;
  private JButton btnSearch, btnNew, btnSave, btnDelete, btnUpdate, btnShow;
  static Connection conn;
  static Statement stmt;
  static ResultSet rs;
  static String query;
  static String id, name, age, sex;

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          ManipulateRecord window = new ManipulateRecord();
          window.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public static void dbConnect() {
    try {
      conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "root", "");
      // stmt = conn.createStatement();
      stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void initializeDB() {
    try {
      stmt.execute("create database if not exists dbsample");
      stmt.execute("use dbsample");
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

  public ManipulateRecord() {
    setTitle("SAMPLE DATABASE");
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
    setBounds(100, 100, 394, 208);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    getContentPane().setLayout(null);

    JLabel lblNewLabel = new JLabel("ID #: ");
    lblNewLabel.setBounds(25, 26, 46, 14);
    getContentPane().add(lblNewLabel);

    JLabel lblNewLabel_1 = new JLabel("NAME:");
    lblNewLabel_1.setBounds(25, 57, 46, 14);
    getContentPane().add(lblNewLabel_1);

    txtId = new JTextField();
    txtId.setEditable(false);
    txtId.setText((String) null);
    txtId.setColumns(10);
    txtId.setBounds(81, 23, 86, 20);
    getContentPane().add(txtId);

    txtName = new JTextField();
    txtName.setText((String) null);
    txtName.setColumns(10);
    txtName.setBounds(81, 54, 86, 20);
    getContentPane().add(txtName);

    JLabel lblNewLabel_2 = new JLabel("AGE:");
    lblNewLabel_2.setBounds(25, 88, 46, 14);
    getContentPane().add(lblNewLabel_2);

    txtAge = new JTextField();
    txtAge.setText((String) null);
    txtAge.setColumns(10);
    txtAge.setBounds(81, 85, 86, 20);
    getContentPane().add(txtAge);

    JLabel lblNewLabel_3 = new JLabel("SEX:");
    lblNewLabel_3.setBounds(25, 122, 46, 14);
    getContentPane().add(lblNewLabel_3);

    txtSex = new JTextField();
    txtSex.setText((String) null);
    txtSex.setColumns(10);
    txtSex.setBounds(81, 116, 86, 20);
    getContentPane().add(txtSex);

    btnNew = new JButton("NEW");
    btnNew.setBounds(177, 20, 172, 23);
    getContentPane().add(btnNew);

    btnUpdate = new JButton("UPDATE");
    btnUpdate.setBounds(257, 51, 92, 23);
    getContentPane().add(btnUpdate);

    btnSave = new JButton("SAVE");
    btnSave.setBounds(177, 51, 71, 23);
    getContentPane().add(btnSave);

    btnDelete = new JButton("DELETE");
    btnDelete.setBounds(177, 82, 172, 23);
    getContentPane().add(btnDelete);

    btnSearch = new JButton("SEARCH");
    btnSearch.setBounds(217, 113, 89, 23);
    getContentPane().add(btnSearch);

    btnSearch.addActionListener(this);
    btnNew.addActionListener(this);
    btnSave.addActionListener(this);
    btnDelete.addActionListener(this);
    btnUpdate.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) { // TODO Auto-generated method stub
    if (e.getSource() == btnSearch) {
      try {
        String idnum = JOptionPane.showInputDialog("Type id number: ");
        query = "select * from tblsample where id=" + idnum;
        rs = stmt.executeQuery(query);
        if (rs.next()) {
          displayValues();
        } else {
          JOptionPane.showMessageDialog(null, "id number not found...");
        }
      } catch (

      SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
    if (e.getSource() == btnNew) {
      txtName.setText("");
      txtAge.setText("");
      txtSex.setText("");
      txtId.requestFocus();
      try {
        query = "select * from tblsample";
        rs = stmt.executeQuery(query);
        rs.last();
        txtId.setText(String.valueOf(Integer.parseInt(rs.getString("id")) + 1));
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
    if (e.getSource() == btnSave) {
      try {
        rs.moveToInsertRow();
        rs.updateString("id", txtId.getText());
        rs.updateString("name", txtName.getText());
        rs.updateString("age", txtAge.getText());
        rs.updateString("sex", txtSex.getText());
        rs.insertRow();

        JOptionPane.showMessageDialog(null, "Record saved...");
      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
    if (e.getSource() == btnUpdate) {
      try {
        // query = "UPDATE tblsample set name="Gwapo, age='49', sex= 'M' where
        // id='103'";
        query = "UPDATE tblsample set name='" + txtName.getText() + "', age='" + txtAge.getText() +
            "', sex='" + txtSex.getText() + "'where id=" + txtId.getText();

        int r = stmt.executeUpdate(query);
        if (r > 0)
          JOptionPane.showMessageDialog(null, "Record updated...");
        else
          JOptionPane.showMessageDialog(null, "Error occured...");

      } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
    if (e.getSource() == btnDelete) {
      int res = JOptionPane.showConfirmDialog(null, "Delete Record?", "Delete", JOptionPane.YES_NO_OPTION);
      if (res == JOptionPane.YES_OPTION) {
        try {
          query = "DELETE FROM tblsample WHERE id='" + txtId.getText() + "'";
          int r;
          r = stmt.executeUpdate(query);

          if (r > 0) {
            JOptionPane.showMessageDialog(null, "A user was deleted successfully!");
            query = "select * from tblsample";
            rs = stmt.executeQuery(query);
            rs.first();
            displayValues();
          }
        } catch (SQLException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
    }
  }
}
