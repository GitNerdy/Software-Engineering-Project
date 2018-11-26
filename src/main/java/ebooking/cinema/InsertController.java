package ebooking.cinema;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
}
