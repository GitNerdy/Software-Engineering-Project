package ebooking.cinema;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @GetMapping("/index")
    public String goHome() {
        return "index";
    }

}
