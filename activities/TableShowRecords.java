package activities;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableShowRecords extends JFrame {
    private static final long serialVersionUID = 1L;
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static String query;
    JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TableShowRecords window = new TableShowRecords();
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
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TableShowRecords() {
        dbConnect();
        initialize();
    }

    private void initialize() {
        new JFrame();
        setBounds(200, 200, 469, 361);
        showRecords();
        this.setTitle("SAMPLE DATABASE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        setLocationRelativeTo(null);
    }

    public void showRecords() {
        String id = "";
        String name = "";
        String age = "";
        String sex = "";
        try {
            dbConnect();
            stmt.execute("use dbsample");
            query = "select * from tblsample";
            rs = stmt.executeQuery(query);

            // headers for the table
            String[] columns = { "ID", "NAME", "AGE", "SEX" };
            String row[][] = new String[10][4];
            // actual data for the table in a 2d array
            int i = 0;
            while (rs.next()) {
                // actual data for the table in a 2d array
                id = rs.getString("id");
                name = rs.getString("name");
                age = rs.getString("age");
                sex = rs.getString("sex");

                row[i][0] = id;
                row[i][1] = name;
                row[i][2] = age;
                row[i][3] = sex;
                i++;
            }
            table = new JTable(row, columns);
            // create table with data

            // add the table to the frame
            getContentPane().add(new JScrollPane(table));

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
