package ebooking.cinema;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class NavigationController {

    @GetMapping("/index")
    public ModelAndView goHome(@ModelAttribute("user") User user, Model model) {
        if (user.loggedIn != true) {
            model.addAttribute("user", new User());
        }

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("user", user);


        return mav;
    }

    @GetMapping("/admin")
    public ModelAndView adminPage(@ModelAttribute("user") User user) {

        ModelAndView mav = new ModelAndView("adminMain");
        mav.addObject("user", user);

        return mav;
    }

    @GetMapping("/editMovieList")
    public ModelAndView movieList(Model model) {

        SearchResult temp = new SearchResult();
        List<SearchResult> results = temp.getMovieList();

        ModelAndView mav = new ModelAndView("editMovieList");
        mav.addObject("movieList", results);

        return mav;
    }

    @GetMapping("/editShowTimes")
    public ModelAndView showTimeList(Model model) {

        model.addAttribute("newTime", new ShowTime());

        ShowTime temp = new ShowTime();
        List<ShowTime> results = temp.getShowTimes();

        ModelAndView mav = new ModelAndView("editShowTimes");
        mav.addObject("showTimeList", results);

        return mav;
    }

    @GetMapping("/editPromotions")
    public ModelAndView showPromoList(Model model) {

        model.addAttribute("newPromo", new Promotion());

        Promotion temp = new Promotion();
        List<Promotion> results = temp.getPromos();

        ModelAndView mav = new ModelAndView("editPromotions");
        mav.addObject("promoList", results);

        return mav;
    }

    @GetMapping("/editUsers")
    public ModelAndView showUserList() {

        User temp = new User();
        List<User> results = temp.getUsers();

        ModelAndView mav = new ModelAndView("editUsers");
        mav.addObject("userList", results);

        return mav;
    }

    @GetMapping("/getMovie/{id}")
    public ModelAndView movieDetail(@PathVariable String id) {

        Movie result = new Movie();
        result = result.getMovie(id);

        ModelAndView mav = new ModelAndView("movieDetail");
        mav.addObject("movie", result);

        return mav;
    }

}
