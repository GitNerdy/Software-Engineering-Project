package ebooking.cinema;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class LoginController {

    @GetMapping("/login")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String userSubmit(@ModelAttribute("user") User user) {

        if (user.login()) {
            //System.out.println("User status: " + user.loggedIn);
            return "index";
        }

        else {
            return "login";
        }
    }

    @GetMapping("/logout")
    public String userLogout(@ModelAttribute("user") User user) {
        user.logout();
        System.out.println("User status: " + user.loggedIn);
        return "index";
    }
}