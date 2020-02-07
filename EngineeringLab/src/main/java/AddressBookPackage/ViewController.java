package AddressBookPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ViewController   {

    @Autowired
    private AddressBookRepository repo;

    @Autowired
    public ViewController(){
    }

    @GetMapping("viewAddressbook")
    public String viewAddressbook(@RequestParam(value = "abID") int ab, Model model) {
//        System.out.println(repo.findById(ab).toString());
        model.addAttribute("ab", repo.findById(ab));
        return "AddressBook.html";
    }

}
