package AddressBookPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Component
public class BuddyInfo {

    @Id
    @GeneratedValue
    private Integer id = null;

    String name;

    int phoneNumber;

    @Autowired
    public BuddyInfo() {

    }

    public BuddyInfo(String personName, int number) {
        name = personName;
        phoneNumber = number;
    }

    public void setName(String n) {
        name = n;
    }

    public void setNumber(int n) {
        phoneNumber = n;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String toString() {
        return "ID: " + id + " Name: " + name + " Phone Number: " + phoneNumber;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
