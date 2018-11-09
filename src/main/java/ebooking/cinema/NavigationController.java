package ebooking.cinema;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class NavigationController {

    @GetMapping("/index")
    public String goHome(/*@SessionAttribute("user") User user*/) {

        return "index";
    }

}
