
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import activities.Login;
import activities.LoginTransaction;
import activities.ManipulateRecord;
import activities.RadioCheckDemo;
import activities.SampleWindowBuilder1;
import activities.SampleWindowBuilder2;
import activities.SampleWindowBuilder3;
import activities.TableSample;
import activities.TableShowRecords;
import activities.UserLogin;

public class Main implements ActionListener {
    JFrame frame;
    JComboBox<String> dropdown;
    Map<String, Runnable> activities = new LinkedHashMap<>() {
        {
            put("Choose an activity to view", () -> {
            });
            put("Practice Exercise 1 - Login", () -> Login.main(null));
            put("Practice Exercise 2 - SampleWindowBuilder1", () -> SampleWindowBuilder1.main(null));
            put("Practice Exercise 3 - SampleWindowBuilder2", () -> SampleWindowBuilder2.main(null));
            put("Practice Exercise 4 - SampleWindowBuilder3", () -> SampleWindowBuilder3.main(null));
            put("Practice Exercise 5 - RadioCheckDemo", () -> RadioCheckDemo.main(null));
            put("Practice Exercise 6 - LoginTransaction", () -> LoginTransaction.main(null));
            put("Practice Exercise 7 - UserLogin", () -> UserLogin.main(null));
            put("Practice Exercise 8 - ManipulateRecord", () -> ManipulateRecord.main(null));
            put("Practice Exercise 8 - TableShowRecords", () -> TableShowRecords.main(null));
            put("Practice Exercise 9 - TableSample", () -> TableSample.main(null));
        }
    };

    public static void main(String[] args) {
        Main main = new Main();
        SwingUtilities.invokeLater(main::run);
    }

    public void run() {
        frame = new JFrame();
        frame.setTitle("Run an Activity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        init();
        frame.add(dropdown);
        frame.setVisible(true);
    }

    public void init() {
        dropdown = new JComboBox(activities.keySet().toArray());
        dropdown.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        dropdown.setBounds(100, 20, 400, 40);
        dropdown.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        JComboBox dropdown = (JComboBox) e.getSource();
        activities.get((String) dropdown.getSelectedItem()).run();
    }
}
