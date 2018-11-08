package ebooking.cinema;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String userSubmit(@ModelAttribute("user") User user) {
        System.out.println(user.email);
        System.out.println(user.password);
        if (user.login()) {
            System.out.println("User status: " + user.loggedIn);
            return "index";
        }

        else {
            return "login";
        }
    }
}