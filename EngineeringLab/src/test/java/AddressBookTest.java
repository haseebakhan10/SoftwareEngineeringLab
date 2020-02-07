import AddressBookPackage.AddressBook;
import AddressBookPackage.BuddyInfo;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

import static org.junit.Assert.*;
public class AddressBookTest {

    @Test
    public void testPersistence() {
        // Create objects
        BuddyInfo b1 = new BuddyInfo();
        b1.setId(1);
        b1.setName("Buddy 1");
        BuddyInfo b2 = new BuddyInfo();
        b2.setId(2);
        b2.setName("Buddy 2");

        AddressBook ab = new AddressBook(1);
        ab.addBuddy(b1);
        ab.addBuddy(b2);

        // Connecting to the database through EntityManagerFactory
        // connection details loaded from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-ab");

        EntityManager em = emf.createEntityManager();

        // Creating a new transaction
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // Persisting the product entity objects
        em.persist(ab);

        tx.commit();

        // Querying the contents of the database using JPQL query
        Query q = em.createQuery("SELECT ab FROM AddressBook ab");

        @SuppressWarnings("unchecked")
        List<AddressBook> results = q.getResultList();

        System.out.println("List of buddies\n----------------");

        for (AddressBook book : results) {

           System.out.println("Addressbook Id:" + book.getId() + " Buddies: \n" + book.toString());
        }

        // Closing connection
        em.close();

        emf.close();
    }

    @Test
    public void testAddBuddy() {
        AddressBook ab = new AddressBook();
        BuddyInfo expected = new BuddyInfo("aName", 123);
        ab.addBuddy(expected);
        assertEquals(1, ab.getMyBuddies().size());
        BuddyInfo actual = ab.getMyBuddies().get(0);
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveBuddy() {
        AddressBook ab = new AddressBook();
        BuddyInfo b1 = new BuddyInfo("aName", 123);
        ab.addBuddy(b1);
        assertEquals(1, ab.getMyBuddies().size());
        ab.removeBuddy(0);
        assertEquals(0, ab.getMyBuddies().size());
    }

    @Test
    public void testToString() {
        String name = "aName";
        int phoneNumber = 123;
        String expected = "Name: " + name + " Phone Number: " + phoneNumber + "\n";
        AddressBook ab = new AddressBook();
        BuddyInfo b1 = new BuddyInfo(name, phoneNumber);
        ab.addBuddy(b1);
        assertEquals(expected, ab.toString());
    }
}