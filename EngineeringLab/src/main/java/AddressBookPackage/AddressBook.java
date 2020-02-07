package AddressBookPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Component
public class AddressBook {
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<BuddyInfo> myBuddies;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Autowired
    public AddressBook() {
        myBuddies = new ArrayList<BuddyInfo>();
    }

    public AddressBook(int id) {
        myBuddies = new ArrayList<BuddyInfo>();
        this.setId((id));
    }

    public void addBuddy(BuddyInfo buddy) {
        myBuddies.add(buddy);
    }

    public BuddyInfo getBuddy(int pos) {
        return myBuddies.get(pos);
    }

    public List<BuddyInfo> getMyBuddies() {
        return myBuddies;
    }

    public void removeBuddy(int index) {
        myBuddies.remove(index);
    }

    public String toString() {
        String temp = "";
        for (BuddyInfo buddy : myBuddies) {
            temp += buddy.toString() + "\n";
        }
        return temp;
    }

    public void clear() {
        myBuddies.clear();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
