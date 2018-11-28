package ebooking.cinema;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.*;

@Controller
public class InsertController {

    @PostMapping("/insertShowTime")
    public String addShowTime(@ModelAttribute ShowTime showTime) {
        ShowTime STDO = new ShowTime();
        STDO.insertShowTime(showTime.startTime);

        return "redirect:/editShowTimes";
    }

    @PostMapping("/insertPromo")
    public String addPromotion(@ModelAttribute Promotion promo) {
        Promotion PDO = new Promotion();
        PDO.insertPromo(promo);

        return "redirect:/editPromotions";
    }

    @PostMapping("/insertShowing/{id}")
    public String addShowing(@ModelAttribute MovieShowing showing, @PathVariable String id) {
        MovieShowing MSDO = new MovieShowing();
        MSDO.insertShowing(showing, id);

        return "redirect:/editHalls";
    }
}
