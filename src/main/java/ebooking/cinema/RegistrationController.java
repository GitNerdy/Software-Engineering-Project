package ebooking.cinema;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Scope("session")
public class RegistrationController {

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("address", new Address());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute User user) {
        user.register();
        //Email.sendMail(user.email);
        /* System.out.println(user.password);
        System.out.println(user.email);
        System.out.println(user.fname);
        System.out.println(user.lname); */
        user.loggedIn = true;
        return "index";
    }
}
