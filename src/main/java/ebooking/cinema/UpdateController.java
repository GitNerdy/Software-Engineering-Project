package ebooking.cinema;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UpdateController {

    @GetMapping("/suspendAccount/{id}")
    public String suspendAccount(@PathVariable String id) {
        User UDO = new User();
        UDO.updateStatus(id,"suspended");

        return "redirect:/editUsers";
    }

    @GetMapping("/activateAccount/{id}")
    public String activateAccount(@PathVariable String id) {
        User UDO = new User();
        UDO.updateStatus(id,"active");

        return "redirect:/editUsers";
    }
}
