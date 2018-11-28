package ebooking.cinema;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DeleteController {

    @GetMapping("/deleteShowTime/{id}")
    public String deleteShowTime(@PathVariable String id) {
        ShowTime STDO = new ShowTime();
        STDO.deleteShowTime(id);


        return "redirect:/editShowTimes";
    }

    @GetMapping("/deletePromo/{id}")
    public String deletePromo(@PathVariable String id) {
        Promotion PDO = new Promotion();
        PDO.deletePromo(id);

        return "redirect:/editPromotions";
    }

    @GetMapping("/deleteShowing/{id}")
    public String deleteShowing(@PathVariable String id) {
        MovieShowing MSDO = new MovieShowing();
        MSDO.deleteShowing(id);

        return "redirect:/editHalls";
    }
}
