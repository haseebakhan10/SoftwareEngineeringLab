package AddressBookPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@RestController
public class AddressBookController {
    @Autowired
    private AddressBook ab;
    @Autowired
    private AddressBookRepository abr;
    @Autowired
    private BuddyInfoRepository bir;

    @Autowired
    private AddressBookGUI g;

    @Autowired
    public AddressBookController(AddressBook ab, AddressBookGUI g) {
        g.getNab().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newAddressbook();
                getG().update(getAb().getMyBuddies());
                getG().getFrame().setTitle("Addressbook ID: " + getAb().getId());
            }
        });
        g.getNb().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField name = new JTextField(20);
                JTextField number = new JTextField(15);

                JPanel myPanel = new JPanel();
                myPanel.add(new JLabel("Name:"));
                myPanel.add(name);
                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                myPanel.add(new JLabel("Phone Number:"));
                myPanel.add(number);

                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Enter the name and phone number...", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    addBuddy(getAb().getId(), name.getText(), Integer.parseInt(number.getText()));
                }
            }
        });
        g.getRb().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pos = JOptionPane.showInputDialog(getG().getFrame(),
                        "What position would you like to remove?", null);
                removeBuddy(getAb().getId(), Integer.parseInt(pos));
            }
        });
        g.update(ab.getMyBuddies());
        g.getDb().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(getG().getFrame(),
                        bir.findAll().toString());
            }
        });
    }

    @PostMapping("/addBuddy")
    public BuddyInfo addBuddy(@RequestParam(value = "abID") int abID, @RequestParam(value = "buddyName", defaultValue = "Default Name") String name, @RequestParam(value = "buddyNumber") int number) {
        BuddyInfo temp = new BuddyInfo(name, number);
        AddressBook adB = abr.findById(abID);
        adB.addBuddy(temp);
        abr.save(adB);
        ab.addBuddy(temp);
//        g.update(ab.getMyBuddies());
        return bir.findByName(name);
    }

    @PostMapping("/removeBuddy")
    public int removeBuddy(@RequestParam(value = "abID") int abID, @RequestParam(value = "index") int index) {
        if (index == -1) {
            abr.save(removeDefaultBuddy());
        } else {
            abr.save(removeBuddyFunction(index, abr.findById(abID)));
        }
//        g.update(ab.getMyBuddies());
        return index;
    }

    public AddressBook removeDefaultBuddy() {
        AddressBook temp = abr.findById(1);
        temp.getMyBuddies().remove(0);
        return temp;
    }

    public AddressBook removeBuddyFunction(int buddyId, AddressBook book) {
        for (int a = 0; a < book.getMyBuddies().size(); a++) {
            if (book.getMyBuddies().get(a).getId() == buddyId) {
                book.getMyBuddies().remove(a);
            }
        }
        return book;
    }

    public AddressBook getAb() {
        return ab;
    }

    public AddressBookGUI getG() {
        return g;
    }

    @PostMapping("/newAddressbook")
    public AddressBook newAddressbook() {
        ab = new AddressBook();
        abr.save(ab);
        return ab;
    }
}
