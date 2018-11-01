package ebooking.cinema;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("regUser", new RegUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute RegUser regUser) {
        System.out.println(regUser.password);
        System.out.println(regUser.email);
        System.out.println(regUser.fname);
        System.out.println(regUser.lname);
        return "register";
    }
}
