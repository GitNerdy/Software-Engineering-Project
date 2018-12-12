package ebooking.cinema;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/updateMovie/{id}")
    public String updateMovie(@PathVariable String id, @ModelAttribute Movie movie) {
        movie.updateMovie();

        String page = "redirect:/getEditMovie/{id}";

        return page;
    }
}
