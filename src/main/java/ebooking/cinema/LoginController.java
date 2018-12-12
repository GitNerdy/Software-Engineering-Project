package ebooking.cinema;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class LoginController {

    /* @PostMapping("/login")
    public String userSubmit(@SessionAttribute("user") User user, Model model) {

        if (user.login()) {
            //System.out.println("User status: " + user.loggedIn);
            user.setLoggedIn(true);
            model.addAttribute("user", user);

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
    } */
}