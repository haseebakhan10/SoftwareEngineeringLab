package AddressBookPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ViewController   {

    @Autowired
    private AddressBookRepository repo;

    @Autowired
    public ViewController(){
    }

    @GetMapping("/viewAddressbook")
    public @ResponseBody AddressBook viewAddressbook(@RequestParam(value = "abID") int ab, Model model) {
//        System.out.println(repo.findById(ab).toString());
//        model.addAttribute("ab", repo.findById(ab));
//        return "AddressBook.html";
        return repo.findById(ab);
    }

    @GetMapping("/")
    public String viewAddressbook(Model model) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (AddressBook b: repo.findAll()) {
            ids.add(b.getId());
        }
        model.addAttribute("ab", null);
        model.addAttribute("ids", ids);
        return "AddressBook.html";
    }

}
