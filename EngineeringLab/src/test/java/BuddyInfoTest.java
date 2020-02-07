import AddressBookPackage.BuddyInfo;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BuddyInfoTest {

    @Test
    public void testPersistence() {
        // Create objects
        BuddyInfo b1 = new BuddyInfo();
        b1.setId(1);
        b1.setName("Buddy 1");
        BuddyInfo b2 = new BuddyInfo();
        b2.setId(2);
        b2.setName("Buddy 2");

        // Connecting to the database through EntityManagerFactory
        // connection details loaded from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-ab");

        EntityManager em = emf.createEntityManager();

        // Creating a new transaction
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // Persisting the product entity objects
        em.persist(b1);
        em.persist(b2);

        tx.commit();

        // Querying the contents of the database using JPQL query
        Query q = em.createQuery("SELECT b FROM BuddyInfo b");

        @SuppressWarnings("unchecked")
        List<BuddyInfo> results = q.getResultList();

        System.out.println("List of buddies\n----------------");

        for (BuddyInfo b : results) {

            System.out.println(b.getName() + " (id=" + b.getId() + ")");
        }

        // Closing connection
        em.close();

        emf.close();
    }

    @Test
    public void testGetName() {
        String expected = "aName";
        BuddyInfo b1 = new BuddyInfo(expected, 123);
        String actual = b1.getName();
        assertEquals(expected, actual);
    }
    @Test
    public void testGetNumber() {
        int expected = 123;
        BuddyInfo b1 = new BuddyInfo("aName", expected);
        int actual = b1.getPhoneNumber();
        assertEquals(expected, actual);
    }
    @Test
    public void testSetName() {
        String expected = "aNewName";
        BuddyInfo b1 = new BuddyInfo("aName", 123);
        b1.setName(expected);
        String actual = b1.getName();
        assertEquals(expected, actual);
    }
    @Test
    public void testSetNumber() {
        int expected = 321;
        BuddyInfo b1 = new BuddyInfo("aName", 123);
        b1.setNumber(expected);
        int actual = b1.getPhoneNumber();
        assertEquals(expected, actual);
    }
//    @Test
//    public void testToString() {
//        String name = "aName";
//        int number = 123;
//        String expected = "Name: " + name + " Phone Number: " + number;
//        BuddyInfo b1 = new BuddyInfo(name, number);
//        String actual = b1.toString();
//        assertEquals(expected, actual);
//    }
}