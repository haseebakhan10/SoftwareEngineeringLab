package AddressBookPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
public class AddressBookGUI {
    private JButton nab = null;
    private JButton nb = null;
    private JButton rb = null;
    private JButton db = null;
    private JFrame f = null;
    private JTextArea area = null;

    @Autowired
    public AddressBookGUI() {
//        init();
    }

    public void init() {
        f = new JFrame();

        nab = new JButton("New Address Book");
        nb = new JButton("New Buddy");
        rb = new JButton("Remove Buddy");
        db = new JButton("Check DB");
        nb.setBounds(210, 10, 100, 40);
        nab.setBounds(10, 10, 200, 40);
        rb.setBounds(310, 10, 200, 40);
        db.setBounds(510, 10, 200, 40);
        f.add(nab);
        f.add(nb);
        f.add(rb);
        f.add(db);

        area = new JTextArea();
        area.setBounds(10, 60, 700, 300);
        area.setEditable(false);
        f.add(area);


        f.setSize(750, 500);
        f.setLayout(null);
        f.setVisible(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JButton getNab() {
        return nab;
    }

    public JButton getNb() {
        return nb;
    }

    public JButton getRb() {
        return rb;
    }

    public JButton getDb() {
        return db;
    }

    public JFrame getFrame() {
        return f;
    }

    public JTextArea getArea() {
        return area;
    }

    public void update(List<BuddyInfo> buddies) {
        String s = "";
        for (BuddyInfo b : buddies) {
            s += b.toString() + "\n";
        }
        area.setText(s);
    }
}  