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
@Scope("session")
public class NavigationController {

    User user = new User();

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

    @GetMapping("/editHalls")
    public ModelAndView showHallList(Model model) {

        model.addAttribute("newShowing", new MovieShowing());

        Hall tempHall = new Hall();
        MovieShowing tempShowing = new MovieShowing();
        SearchResult tempMovie = new SearchResult();
        ShowTime tempShowTime = new ShowTime();

        List<Hall> hallResults = tempHall.getHalls();
        List<MovieShowing> showingResults = tempShowing.getShowings();
        List<SearchResult> movieResults = tempMovie.getMovieList();
        List<ShowTime>  showTimeResults = tempShowTime.getShowTimes();

        ModelAndView mav = new ModelAndView("editHallList");
        mav.addObject("hallList", hallResults);
        mav.addObject("showingList", showingResults);
        mav.addObject("movieList", movieResults);
        mav.addObject("showTimeList", showTimeResults);

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

    @GetMapping("/getEditMovie/{id}")
    public ModelAndView editMovie(@PathVariable String id, Model model) {

        Movie newMovie = new Movie();

        Movie result = new Movie();
        result = result.getMovie(id);

        newMovie.title = result.title;
        newMovie.synopsis = result.synopsis;
        newMovie.director = result.director;
        newMovie.producer = result.producer;
        newMovie.cast = result.cast;
        newMovie.image = result.image;
        newMovie.genre = result.genre;

        model.addAttribute("newMovie", newMovie);


        ModelAndView mav = new ModelAndView("editMovie");
        mav.addObject("movie", result);

        return mav;
    }
}
