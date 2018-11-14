package ebooking.cinema;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Scope("session")
public class NavigationController {

    @GetMapping("/index")
    public String goHome() {
        //model.addAttribute("user", new User());
        //user.loggedIn = false;

        return "index";
    }

}
