package AddressBookPackage;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
@SpringBootTest
@AutoConfigureMockMvc
public class WebApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessageWithNewAddressBookAndNoBuddies() throws Exception {
        this.mockMvc.perform(post("/newAddressbook"));
        this.mockMvc.perform(get("/viewAddressbook?abID=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Your Addressbook")));
    }

    @Test
    public void shouldReturnABuddyInAnAddressBook() throws Exception {
        this.mockMvc.perform(post("/newAddressbook"));
        this.mockMvc.perform(post("/addBuddy?abID=1&buddyName=TestName&buddyNumber=123"));
        this.mockMvc.perform(get("/viewAddressbook?abID=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("TestName")));
    }

    @Test
    public void shouldNoteReturnBuddyInAddressbook() throws Exception {
        this.mockMvc.perform(post("/newAddressbook"));
        this.mockMvc.perform(post("/addBuddy?abID=1&buddyName=TestName&buddyNumber=123"));
        this.mockMvc.perform(get("/viewAddressbook?abID=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string((containsString("TestName"))));
        this.mockMvc.perform(post("/removeBuddy?abID=1&index=-1"));
        this.mockMvc.perform(get("/viewAddressbook?abID=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(not(containsString("TestName"))));
    }
}